package com.cimcitech.cimctd.activity.home.file_search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.adapter.file_search.FileSearchAdapter;
import com.cimcitech.cimctd.bean.file_search.MyFile;
import com.cimcitech.cimctd.bean.file_search.FileSearchReq;
import com.cimcitech.cimctd.bean.file_search.FileSearchVo;
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

public class FileSearchActivity extends AppCompatActivity {
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.search_et)
    EditText search_Et;

    private Handler handler = new Handler();
    private FileSearchAdapter adapter;
    private FileSearchVo fileSearchVo;
    private List<MyFile> data = new ArrayList<MyFile>();
    private int pageNum = 1;
    private boolean isLoading;
    private boolean myData = true;
    private String TAG = "fileSearchlog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_search);
        ButterKnife.bind(this);
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

    @Override
    protected void onResume() {
        super.onResume();
        /*if (Config.isAddPerson) {
            Config.isAddPerson = false;
            updateData();
        }*/
    }

    public void initViewData() {
        adapter = new FileSearchAdapter(FileSearchActivity.this, data);
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
        final LinearLayoutManager layoutManager = new LinearLayoutManager(FileSearchActivity.this);
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
                                if (fileSearchVo.getData().isHasNextPage()) {
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
        adapter.setOnItemClickListener(new FileSearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(FileSearchActivity.this, FileSearchDetailActivity.class);
                MyFile myFile = (MyFile) adapter.getAll().get(position);
                intent.putExtra("detailId", myFile.getDetailId());
                startActivity(intent);
            }

            @Override
            public void onItemLongClickListener(View view, int position) {

            }
        });
    }

    @OnClick({R.id.back,R.id.search_bt})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            /*case R.id.my_tv:
                myData = true;
                //myView.setVisibility(View.VISIBLE);
                //xsView.setVisibility(View.INVISIBLE);
                updateData();
                break;*/
            /*case R.id.xs_tv:
                myData = false;
                myView.setVisibility(View.INVISIBLE);
                xsView.setVisibility(View.VISIBLE);
                updateData();
                break;*/
            //case R.id.add_bt:
                /*Intent intent = new Intent(new Intent(UsersInfoActivity.this,
                    ContactPersonAddActivity.class));
                if (custid > 0)
                    intent.putExtra("custid", custid);
                startActivity(intent);*/
               // break;
            case R.id.search_bt:
                updateData();
                //ApkApplication.imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }

    public void getData() {
        String json = new Gson().toJson(new FileSearchReq(pageNum, 10, "",
                new FileSearchReq.SaleContDetBean(search_Et.getText().toString().trim())));
        OkHttpUtils
                .postString()
                .url(Config.FILE_SEARCH_URL)
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
                                fileSearchVo = GjsonUtil.parseJsonWithGson(response,FileSearchVo.class);
                                if (fileSearchVo != null) {
                                    if (fileSearchVo.isSuccess()) {
                                        if (fileSearchVo.getData().getList() != null && fileSearchVo.getData().getList().size() > 0) {
                                            for (int i = 0; i < fileSearchVo.getData().getList().size(); i++) {
                                                data.add(fileSearchVo.getData().getList().get(i));
                                            }
                                        }
                                        if (fileSearchVo.getData().isHasNextPage()) {
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
