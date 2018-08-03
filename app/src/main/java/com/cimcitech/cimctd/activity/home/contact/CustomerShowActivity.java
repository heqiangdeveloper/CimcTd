package com.cimcitech.cimctd.activity.home.contact;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.adapter.contact.CustomerAdapter;
import com.cimcitech.cimctd.bean.contact.ContactEnumVo;
import com.cimcitech.cimctd.bean.contact.Customer;
import com.cimcitech.cimctd.bean.contact.CustomerBean;
import com.cimcitech.cimctd.bean.contact.CustomerRequestBean;
import com.cimcitech.cimctd.bean.contact.CustomerVo;
import com.cimcitech.cimctd.bean.file_search.FileSearchVo;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.CustomerPinyinComparator;
import com.cimcitech.cimctd.utils.GjsonUtil;
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

public class CustomerShowActivity extends MyBaseActivity {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.sideBar)
    SideBar sideBar;
    @Bind(R.id.dialog)
    TextView dialog;
    @Bind(R.id.filter_edit)
    ClearEditText clearEditText;
    @Bind(R.id.back)
    ImageView back_iv;

    private Handler handler = new Handler();
    private CustomerAdapter adapter;
    private FileSearchVo fileSearchVo;
    private List<Customer> data = new ArrayList<Customer>();
    private int pageNum = 1;
    private boolean isLoading;
    private boolean myData = true;
    private String TAG = "fileSearchlog";
    private int mYear,mMonth,mDay;
    private ContactEnumVo contactEnumVo;
    private CustomerVo customerVo;

    private LinearLayoutManager manager;
    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private CustomerPinyinComparator contactPinyinComparator;
    private final int GET_DATE_SUCCESS = 1;
    private final int GET_DATE_FAIL = 2;

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
                    mLoading.dismiss();
                    Toast.makeText(CustomerShowActivity.this,"数据加载失败",Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_customer_show2);
        ButterKnife.bind(this);
        /*mLoading.show();
        initViewData();
        getData();*/

        mLoading.show();
        //初始化比较器
        contactPinyinComparator = new CustomerPinyinComparator();
        adapter = new CustomerAdapter(CustomerShowActivity.this, data);
        getData();
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

        adapter = new CustomerAdapter(CustomerShowActivity.this, data);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0)
                        ? 0 : recyclerView.getChildAt(0).getTop();
            }
        });
        //给List添加点击事件
        adapter.setOnItemClickListener(new CustomerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CustomerShowActivity.this, ContactDetailActivity.class);
                Customer customer = (Customer) adapter.getAll().get(position);
                intent.putExtra("lettersCustomer",customer);
                //intent.putExtra("isAdd",true);
                //startActivity(intent);
                //startActivityForResult(intent,REQUESTCODE);
                setResult(RESULT_OK,intent);
                finish();//不能省略
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                //do nothing
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

    @Override
    protected void onResume() {
        super.onResume();
        /*if (Config.isAddPerson) {
            Config.isAddPerson = false;
            updateData();
        }*/
    }

    @OnClick({R.id.back})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back:
                //startActivity(new Intent(CustomerShowActivity.this,ContactActivity.class));
                finish();
        }
    }

    public void getData() {
        String json = new Gson().toJson(new CustomerRequestBean(pageNum,300,"",new CustomerBean
                ("")));
        OkHttpUtils
                .postString()
                .url(Config.GET_ALL_CUST_URL)
                //.addParams("codeTypes",param)
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
                                //if(mLoading.isShowing()) mLoading.dismiss();
                                customerVo = GjsonUtil.parseJsonWithGson(response,CustomerVo.class);
                                if (customerVo != null) {
                                    if (customerVo.isSuccess()) {
                                        if (customerVo.getData().getList() != null && customerVo.getData().getList().size() > 0) {
                                            for (int i = 0; i < customerVo.getData().getList().size(); i++) {
                                                //data.add(customerVo.getData().getList().get(i));
                                                Customer mCustomer = customerVo.getData().getList().get(i);
                                                setLetters(mCustomer);
                                                data.add(mCustomer);
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
                        });
    }

    public Customer setLetters(Customer customer){
        //汉字转换成拼音
        String pinyin = PinyinUtils.getPingYin(customer.getCustName());
        String sortString = pinyin.substring(0, 1).toUpperCase();

        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            customer.setLetters(sortString.toUpperCase());
        } else {
            customer.setLetters("#");
        }
        return customer;
    }

    /**
     * 根据输入框中的值来过滤数据并更新RecyclerView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<Customer> filterDateList = new ArrayList<>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = data;
        } else {
            filterDateList.clear();
            for (Customer customer : data) {
                String name = customer.getCustName();
                if (name.indexOf(filterStr.toString()) != -1 ||
                        PinyinUtils.getFirstSpell(name).startsWith(filterStr.toString())
                        //不区分大小写
                        || PinyinUtils.getFirstSpell(name).toLowerCase().startsWith(filterStr.toString())
                        || PinyinUtils.getFirstSpell(name).toUpperCase().startsWith(filterStr.toString())
                        ) {
                    filterDateList.add(customer);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, contactPinyinComparator);
        adapter.updateList(filterDateList);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back_iv.callOnClick();
    }
}
