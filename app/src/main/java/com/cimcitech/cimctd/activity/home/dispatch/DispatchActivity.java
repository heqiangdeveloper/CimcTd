package com.cimcitech.cimctd.activity.home.dispatch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.adapter.dispatch.DispatchAdapter;
import com.cimcitech.cimctd.bean.dispatch.Dispatch;
import com.cimcitech.cimctd.bean.dispatch.DispatchVo;
import com.cimcitech.cimctd.bean.dispatch.MainDispatchReq;
import com.cimcitech.cimctd.bean.dispatch.MaintenancePlanVo;
import com.cimcitech.cimctd.bean.dispatch.SaleDispatchReq;
import com.cimcitech.cimctd.bean.dispatch.saleMaintenPlanVo;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.cimcitech.cimctd.widget.MyBaseActivity;
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

/**
 * Created by lyw on 2018/4/23.
 */

public class DispatchActivity extends MyBaseActivity {
    @Bind(R.id.isReceive_spinner)
    Spinner isReceive_Spinner;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recycler_view_layout)
    CoordinatorLayout recyclerViewLayout;
    @Bind(R.id.my_view)
    View my_View;
    @Bind(R.id.xs_view)
    View xs_View;

    private int pageNum = 1;
    private DispatchVo dispatchVo;
    private List<Dispatch> data = new ArrayList<>();
    private DispatchAdapter adapter;
    private Handler uiHandler = null;
    private Handler handler = new Handler();
    private final int INIT_DATA = 1003;
    private boolean isLoading;
    private boolean isSale = true;
    private String TAG = "dispatchlog";
    private int index = 0;
    private String isSendJobsStr = "";
    private final String CONTRACTTYPESTR_SALE = "0";
    private final String CONTRACTTYPESTR_MAIN = "1";
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        ButterKnife.bind(this);
        sp = this.getSharedPreferences(Config.KEY_LOGIN_AUTO, MODE_PRIVATE);
        initView();//初始化Spinner
        initViewData();//获取数据
        //getSaleData();
    }

    private void initView(){
        isReceive_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //isReceiveStr = (String) isReceive_Spinner.getAdapter().getItem(position);
                index = position;
                updateData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        if (isSale)
            getSaleData(); //获取数据
        else
            getMainData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Config.isAddMyClient) {
            Config.isAddMyClient = false;
            updateData();
        }
    }

    @OnClick({R.id.back_iv, R.id.my_tv, R.id.xs_tv})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                finish();
                break;
            case R.id.my_tv:
                isSale = true;
                my_View.setVisibility(View.VISIBLE);
                xs_View.setVisibility(View.INVISIBLE);
                updateData();
                break;
            case R.id.xs_tv:
                isSale = false;
                my_View.setVisibility(View.INVISIBLE);
                xs_View.setVisibility(View.VISIBLE);
                updateData();
                break;
            /*case R.id.search_bt:
                updateData();
                //ApkApplication.imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;*/
        }
    }

    public void initViewData() {
        adapter = new DispatchAdapter(DispatchActivity.this, data);
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
                        if (isSale)
                            getSaleData(); //当合同类型=销售合同时
                        else
                            getMainData();//当合同类型=维保合同时
                    }
                }, 1000);
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(DispatchActivity.this);
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
                /*int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }*/
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
                                if (null != dispatchVo.getData() && dispatchVo.getData().isHasNextPage()) {
                                    pageNum++;
                                    if (isSale)
                                        getSaleData();//当合同类型=销售合同时
                                    else
                                        getMainData();//当合同类型=维保合同时
                                }
                                isLoading = false;
                            }
                        }, 1000);
                    }
                }
            }
        });
        //给List添加点击事件
        adapter.setOnItemClickListener(new DispatchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(DispatchActivity.this, DispatchDetailActivity.class);
                Dispatch dispatch = (Dispatch) adapter.getAll().get(position);
                intent.putExtra("dispatch",dispatch);
                intent.putExtra("isSale",isSale);
                startActivity(intent);
                finish();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                //do nothing
            }
        });
    }

    //获取 当合同类型=销售合同时数据
    public void getSaleData() {
        String json = new Gson().toJson(new SaleDispatchReq(pageNum,10,
                null,
                new saleMaintenPlanVo("isSend",
                        getReceive(index),
                        "all",
                        null,
                        null,
                        null,
                        null,
                        sp.getLong("userId",0))));
        Log.d(TAG,"json is: " + json);
        OkHttpUtils
                .postString()
                .url(Config.QUERY_DISPATCH_SALE_URL)
                //.addHeader("checkTokenKey", Config.loginback.getToken())
                //.addHeader("sessionKey", Config.loginback.getUserId() + "")
                .content(json)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                if(mLoading.isShowing()) mLoading.dismiss();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                if(mLoading.isShowing()) mLoading.dismiss();
                                data.clear();
                                dispatchVo = GjsonUtil.parseJsonWithGson(response, DispatchVo.class);
                                if (dispatchVo != null) {
                                    if (dispatchVo.isSuccess()) {
                                        if (dispatchVo.getData().getList() != null && dispatchVo.getData().getList().size() > 0) {
                                            for (int i = 0; i < dispatchVo.getData().getList().size(); i++) {
                                                Dispatch dispatch = dispatchVo.getData().getList().get(i);
                                                dispatch.setContractType(CONTRACTTYPESTR_SALE);
                                                data.add(dispatch);
                                            }
                                        }
                                        if (dispatchVo.getData().isHasNextPage()) {
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

    /**
     * 获取当合同类型=维保合同时数据
     */
    public void getMainData() {
        String json1 = new Gson().toJson(new MainDispatchReq(pageNum,
                10,
                "createTime desc",
                new MaintenancePlanVo("isSend",
                        getReceive(index),
                        "all",
                        null,
                        null,
                        null,
                        null,
                        sp.getLong("userId",0))));
        Log.d(TAG,"json2 is: " + json1);
        OkHttpUtils
                .postString()
                .url(Config.QUERY_DISPATCH_MAIN_URL)
                //.addHeader("checkTokenKey", Config.loginback.getToken())
                //.addHeader("sessionKey", Config.loginback.getUserId() + "")
                .content(json1)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                if(mLoading.isShowing()) mLoading.dismiss();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                if(mLoading.isShowing()) mLoading.dismiss();
                                dispatchVo = GjsonUtil.parseJsonWithGson(response,DispatchVo.class);
                                if (dispatchVo != null) {
                                    if (dispatchVo.isSuccess()) {
                                        if (dispatchVo.getData().getList() != null && dispatchVo.getData().getList().size() > 0) {
                                            for (int i = 0; i < dispatchVo.getData().getList().size(); i++) {
                                                Dispatch dispatch = dispatchVo.getData().getList().get(i);
                                                dispatch.setContractType(CONTRACTTYPESTR_MAIN);
                                                data.add(dispatch);
                                            }
                                        }
                                        if (dispatchVo.getData().isHasNextPage()) {
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

    public String getSendJobs(String str){
        String s = "";
        switch (str){
            case "所有状态":
                s = "all";
                break;
            case "已派工":
                s = "isSend";
                break;
            case "未派工":
                s = "isNotSend";
                break;
            default:
                s = "all";
                break;
        }
        return s;
    }

    public String getReceive(int index){
        String s = "";
        switch (index){
            case 0:
                s = "all";
                break;
            case 1:
                s = "isReceive";
                break;
            case 2:
                s = "isNotReceive";
                break;
            default:
                s = "all";
                break;
        }
        return s;
    }
}
