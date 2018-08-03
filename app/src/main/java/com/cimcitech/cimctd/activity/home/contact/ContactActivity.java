package com.cimcitech.cimctd.activity.home.contact;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.adapter.contact.ContactAdapter;
import com.cimcitech.cimctd.bean.contact.Contact;
import com.cimcitech.cimctd.bean.contact.ContactReq;
import com.cimcitech.cimctd.bean.contact.ContactVo;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.cimcitech.cimctd.utils.ContactPinyinComparator;
import com.cimcitech.cimctd.utils.PinyinUtils;
import com.cimcitech.cimctd.widget.MyBaseActivity;
import com.cimcitech.cimctd.widget.ClearEditText;
import com.cimcitech.cimctd.widget.SideBar;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class ContactActivity extends MyBaseActivity {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.sideBar)
    SideBar sideBar;
    @Bind(R.id.dialog)
    TextView dialog;
    @Bind(R.id.filter_edit)
    ClearEditText clearEditText;

    private Handler handler = new Handler();
    private ContactAdapter adapter;
    private ContactVo contactVo;
    private List<Contact> data = new ArrayList<Contact>();
    private int pageNum = 1;
    private boolean isLoading;
    private boolean myData = true;
    private String TAG = "contactlog";
    private LinearLayoutManager manager;
    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private ContactPinyinComparator contactPinyinComparator;
    private final int GET_DATE_SUCCESS = 1;
    private final int GET_DATE_FAIL = 2;
    private IntentFilter myIntentFilter;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET_DATE_SUCCESS:
                    mLoading.dismiss();
                    initViewData();
                    break;
                case GET_DATE_FAIL:
                    if(mLoading != null && mLoading.isShowing()) mLoading.dismiss();
                    Toast.makeText(ContactActivity.this,"数据加载失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void sendMsg(int flag){
        Message msg = new Message();
        msg.what = flag;
        mHandler.sendMessage(msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        registerBoradcastReceiver();//注册是否更新数据的广播
        mLoading.show();
        //初始化比较器
        contactPinyinComparator = new ContactPinyinComparator();
        adapter = new ContactAdapter(ContactActivity.this, data);
        //initViewsListener();
        getData();
        //initViewsListener();
        //initViewData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if (Config.isAddPerson) {
            Config.isAddPerson = false;
            updateData();
        }*/
    }

    public void initViewData() {
        sideBar.setTextView(dialog);

        //根据输入框输入值的改变来过滤搜索
        clearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // 根据a-z进行排序源数据
        Collections.sort(data, contactPinyinComparator);
        //RecyclerView社置manager
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        adapter = new ContactAdapter(ContactActivity.this, data);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        //给List添加点击事件
        adapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               Intent intent = new Intent(ContactActivity.this, ContactDetailActivity.class);
                Contact contact = (Contact) adapter.getAll().get(position);
                intent.putExtra("LettersContact",contact);
                intent.putExtra("isAdd",false);
                startActivity(intent);
                //finish();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                //do nothing
            }

            @Override
            public void onTelClick(View view, int position) {
                Contact contact = (Contact) adapter.getAll().get(position);
                String phoneNum = contact.getTel();
                if(null != phoneNum && phoneNum.length() != 0) {
                    Dial(phoneNum);
                }
            }
        });

        //设置右侧SideBar触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }

            }
        });
    }

    //注册本地广播，更快，高效，安全
    public void registerBoradcastReceiver(){
        myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(ContactDetailActivity.REFRESHCONTACTDATA);
        LocalBroadcastManager.getInstance(ContactActivity.this).registerReceiver(mBroadcastReceiver,
                myIntentFilter);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(ContactDetailActivity.REFRESHCONTACTDATA)){
                mLoading.show();
                getData();
            }
        }
    };

    //注销广播接收器
    public void unRegisterBoradcastReceiver(){
        if (null != myIntentFilter) {
            LocalBroadcastManager.getInstance(ContactActivity.this).unregisterReceiver(mBroadcastReceiver);
        }
    }

    @OnClick({R.id.back,R.id.add_ib})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add_ib:
                Intent intent = new Intent(new Intent(ContactActivity.this,
                    ContactDetailActivity.class));
                intent.putExtra("isAdd",true);
                startActivity(intent);
                //finish();
                break;
            /*case R.id.search_bt:
                updateData();
                //ApkApplication.imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;*/
        }
    }

    //调用拨号界面
    public void Dial(String phoneNum){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void getData() {
        String json = new Gson().toJson(new ContactReq(pageNum, 100, "createTime desc",
                new ContactReq.ContactBean("")));
        OkHttpUtils
                .postString()
                .url(Config.QUERY_CONTACT_URL)
                //.addHeader("checkTokenKey", Config.loginback.getToken())
                //.addHeader("sessionKey", Config.loginback.getUserId() + "")
                .content(json)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                sendMsg(GET_DATE_FAIL);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                contactVo = GjsonUtil.parseJsonWithGson(response,ContactVo.class);
                                if (contactVo != null) {
                                    data.clear();
                                    if (contactVo.isSuccess()) {
                                        if (contactVo.getData().getList() != null && contactVo.getData().getList().size() > 0) {
                                            for (int i = 0; i < contactVo.getData().getList().size(); i++) {
                                                Contact mContact = contactVo.getData().getList().get(i);
                                                setLetters(mContact);
                                                data.add(mContact);
                                            }
                                        }
                                        adapter.setNotMoreData(true);
                                        adapter.notifyDataSetChanged();
                                        adapter.notifyItemRemoved(adapter.getItemCount());
                                    }
                                } else {
                                    adapter.notifyDataSetChanged();
                                }
                                sendMsg(GET_DATE_SUCCESS);
                            }
                        }
                );
    }

    public Contact setLetters(Contact contact){
        //汉字转换成拼音
        String pinyin = PinyinUtils.getPingYin(contact.getContactName());
        String sortString = pinyin.substring(0, 1).toUpperCase();

        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            contact.setLetters(sortString.toUpperCase());
        } else {
            contact.setLetters("#");
        }
        return contact;
    }

    /**
     * 根据输入框中的值来过滤数据并更新RecyclerView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<Contact> filterDateList = new ArrayList<>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = data;
        } else {
            filterDateList.clear();
            for (Contact contact : data) {
                String name = contact.getContactName();
                if (name.indexOf(filterStr.toString()) != -1 ||
                        PinyinUtils.getFirstSpell(name).startsWith(filterStr.toString())
                        //不区分大小写
                        || PinyinUtils.getFirstSpell(name).toLowerCase().startsWith(filterStr.toString())
                        || PinyinUtils.getFirstSpell(name).toUpperCase().startsWith(filterStr.toString())
                        ) {
                    filterDateList.add(contact);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, contactPinyinComparator);
        adapter.updateList(filterDateList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterBoradcastReceiver();
    }
}
