package com.cimcitech.cimctd.activity.home.faulthandle;

import android.content.Intent;
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

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.activity.home.contact.ContactDetailActivity;
import com.cimcitech.cimctd.activity.home.contact.CustomerShowActivity;
import com.cimcitech.cimctd.adapter.contact.ContactAdapter;
import com.cimcitech.cimctd.adapter.faulthandle.FaultHandleAdapter;
import com.cimcitech.cimctd.bean.contact.Contact;
import com.cimcitech.cimctd.bean.contact.ContactReq;
import com.cimcitech.cimctd.bean.contact.ContactVo;
import com.cimcitech.cimctd.bean.faulthandle.FaultHandle;
import com.cimcitech.cimctd.bean.faulthandle.FaultHandleReq;
import com.cimcitech.cimctd.bean.faulthandle.FaultHandleVo;
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

public class FaultHandleActivity extends BaseActivity {
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.search_et)
    EditText search_Et;

    private Handler handler = new Handler();
    private FaultHandleAdapter adapter;
    private FaultHandleVo faultHandleVo;
    private List<FaultHandle> data = new ArrayList<FaultHandle>();
    private int pageNum = 1;
    private boolean isLoading;
    private boolean myData = true;
    private String TAG = "contactlog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_handle);
        ButterKnife.bind(this);
        //mLoading.show();
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
        adapter = new FaultHandleAdapter(FaultHandleActivity.this, data);
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
        final LinearLayoutManager layoutManager = new LinearLayoutManager(FaultHandleActivity.this);
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
                                if (faultHandleVo.getData().isHasNextPage()) {
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
        adapter.setOnItemClickListener(new FaultHandleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(FaultHandleActivity.this, FaultHandleDetailActivity.class);
                FaultHandle faultHandle = (FaultHandle) adapter.getAll().get(position);
                intent.putExtra("faultHandle",faultHandle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                //do nothing
            }

        });
    }

    @OnClick({R.id.back,R.id.search_bt})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.search_bt:
                String faultTypeStr = search_Et.getText().toString().trim();
                if(faultTypeStr.length() != 0){
                    updateData();
                }
                //ApkApplication.imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }

    public void getData() {
        String json = new Gson().toJson(new FaultHandleReq(pageNum, 10, null,
                new FaultHandleReq.FaultHandleBean(null,null,null,
                        search_Et.getText().toString().trim(),null,null)));
        OkHttpUtils
                .postString()
                .url(Config.QUERY_FAULT_HANDLE)
                //.addHeader("checkTokenKey", Config.loginback.getToken())
                //.addHeader("sessionKey", Config.loginback.getUserId() + "")
                .content(json)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                swipeRefreshLayout.setRefreshing(false);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                if(mLoading.isShowing())  mLoading.dismiss();
                                Log.d(TAG,"response is: " + response);
                                faultHandleVo = GjsonUtil.parseJsonWithGson(response, FaultHandleVo.class);
                                if (faultHandleVo != null) {
                                    if (faultHandleVo.isSuccess()) {
                                        if (faultHandleVo.getData().getList() != null && faultHandleVo.getData().getList().size() > 0) {
                                            for (int i = 0; i < faultHandleVo.getData().getList().size(); i++) {
                                                data.add(faultHandleVo.getData().getList().get(i));
                                            }
                                        }
                                        if (faultHandleVo.getData().isHasNextPage()) {
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
