package com.cimcitech.cimctd.activity.home.contact;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.adapter.contact.CustomerAdapter;
import com.cimcitech.cimctd.bean.contact.ContactEnumVo;
import com.cimcitech.cimctd.bean.contact.Customer;
import com.cimcitech.cimctd.bean.contact.CustomerBean;
import com.cimcitech.cimctd.bean.contact.CustomerRequestBean;
import com.cimcitech.cimctd.bean.contact.CustomerVo;
import com.cimcitech.cimctd.bean.file_search.FileSearchVo;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.cimcitech.cimctd.widget.BaseActivity;
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

public class CustomerShowActivity extends BaseActivity {
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.search_et)
    EditText search_Et;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_show);
        ButterKnife.bind(this);
        //mLoading.show();
        initViewData();
        getData();
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

    public void initViewData() {
        adapter = new CustomerAdapter(CustomerShowActivity.this, data);
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
        final LinearLayoutManager layoutManager = new LinearLayoutManager(CustomerShowActivity.this);
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
                                if (customerVo.getData().isHasNextPage()) {
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
        adapter.setOnItemClickListener(new CustomerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CustomerShowActivity.this, ContactAddActivity.class);
                Customer customer = (Customer) adapter.getAll().get(position);
                intent.putExtra("customer",customer);
                startActivity(intent);
                finish();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                //do nothing
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

    @OnClick({R.id.back,R.id.search_bt})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
            case R.id.search_bt:
                updateData();
                break;
        }
    }

    public void getData() {
        String json = new Gson().toJson(new CustomerRequestBean(pageNum,10,"",new CustomerBean
                (search_Et.getText().toString().trim())));
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
                                Log.d("testlog",e + "");
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                //if(mLoading.isShowing()) mLoading.dismiss();
                                customerVo = GjsonUtil.parseJsonWithGson(response,CustomerVo.class);
                                if (customerVo != null) {
                                    if (customerVo.isSuccess()) {
                                        if (customerVo.getData().getList() != null && customerVo.getData().getList().size() > 0) {
                                            for (int i = 0; i < customerVo.getData().getList().size(); i++) {
                                                data.add(customerVo.getData().getList().get(i));
                                            }
                                        }
                                        if (customerVo.getData().isHasNextPage()) {
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
                        });
    }
}
