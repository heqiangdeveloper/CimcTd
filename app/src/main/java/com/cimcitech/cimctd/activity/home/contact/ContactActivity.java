package com.cimcitech.cimctd.activity.home.contact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.activity.home.file_search.FileSearchActivity;
import com.cimcitech.cimctd.adapter.contact.ContactAdapter;
import com.cimcitech.cimctd.bean.contact.Contact;
import com.cimcitech.cimctd.bean.contact.ContactReq;
import com.cimcitech.cimctd.bean.contact.ContactVo;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class ContactActivity extends AppCompatActivity {
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.search_et)
    EditText search_Et;
    @Bind(R.id.add_tv)
    TextView add_Tv;

    private Handler handler = new Handler();
    private ContactAdapter adapter;
    private ContactVo contactVo;
    private List<Contact> data = new ArrayList<Contact>();
    private int pageNum = 1;
    private boolean isLoading;
    private boolean myData = true;
    private String TAG = "contactlog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        initViewData();
        getData();
        Log.d("mymylog","oncreate()");
    }

    //刷新数据
    private void updateData() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        //清除数据
        adapter.notifyDataSetChanged();
        this.data.clear();
        pageNum = 1;
        /*if (myData)
            getData(); //获取数据
        else
            getSubordinateData();*/
        getData();
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
        adapter = new ContactAdapter(ContactActivity.this, data);
        swipeRefreshLayout.setColorSchemeResources(R.color.blueStatus);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //下拉刷新
                        adapter.notifyDataSetChanged();
                        data.clear(); //清除数据
                        pageNum = 1;
                        isLoading = false;
                        /*if (myData)
                            getData(); //获取数据
                        else
                            getSubordinateData();*/
                        getData();
                    }
                }, 1000);
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(ContactActivity.this);
        recyclerView.setLayoutManager(layoutManager);
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
                if (topRowVerticalPosition > 0) {
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        return;
                    }

                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //上拉加载
                                if (contactVo.getData().isHasNextPage()) {
                                    pageNum++;
                                    /*if (myData)
                                        getData();//添加数据
                                    else
                                        getSubordinateData();*/

                                    getData();
                                }
                                isLoading = false;
                            }
                        }, 1000);
                    }
                }
            }
        });
        //给List添加点击事件
        adapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               Intent intent = new Intent(ContactActivity.this, ContactDetailActivity.class);
                Contact contact = (Contact) adapter.getAll().get(position);
                intent.putExtra("contact",contact);
                startActivity(intent);
                finish();
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
    }

    @OnClick({R.id.back,R.id.search_bt,R.id.add_tv})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add_tv:
                Intent intent = new Intent(new Intent(ContactActivity.this,
                    CustomerShowActivity.class));
                startActivity(intent);
                break;
            case R.id.search_bt:
                updateData();
                //ApkApplication.imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }

    //调用拨号界面
    public void Dial(String phoneNum){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void getData() {
        String json = new Gson().toJson(new ContactReq(pageNum, 10, "createTime desc",
                new ContactReq.ContactBean(search_Et.getText().toString().trim())));
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
                                //ToastUtil.showNetError();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.d(TAG,"response is: " + response);
                                contactVo = GjsonUtil.parseJsonWithGson(response,ContactVo.class);
                                if (contactVo != null) {
                                    if (contactVo.isSuccess()) {
                                        if (contactVo.getData().getList() != null && contactVo.getData().getList().size() > 0) {
                                            for (int i = 0; i < contactVo.getData().getList().size(); i++) {
                                                data.add(contactVo.getData().getList().get(i));
                                            }
                                        }
                                        if (contactVo.getData().isHasNextPage()) {
                                            adapter.setNotMoreData(false);
                                        } else {
                                            adapter.setNotMoreData(true);
                                        }
                                        adapter.notifyDataSetChanged();
                                        swipeRefreshLayout.setRefreshing(false);
                                        adapter.notifyItemRemoved(adapter.getItemCount());
                                    }
                                } else {
                                    adapter.notifyDataSetChanged();
                                    swipeRefreshLayout.setRefreshing(false);
                                }
                            }
                        }
                );
    }
}
