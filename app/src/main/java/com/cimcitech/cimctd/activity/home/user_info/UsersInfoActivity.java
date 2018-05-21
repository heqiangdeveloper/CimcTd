package com.cimcitech.cimctd.activity.home.user_info;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.adapter.user_info.UserInfoAdapter;
import com.cimcitech.cimctd.bean.user_info.User;
import com.cimcitech.cimctd.bean.user_info.UserInfoReq;
import com.cimcitech.cimctd.bean.user_info.UserInfoVo;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.cimcitech.cimctd.utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class UsersInfoActivity extends AppCompatActivity {
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.add_bt)
    Button add_Bt;

    private Handler handler = new Handler();
    private UserInfoAdapter adapter;
    private UserInfoVo userInfoVo;
    private List<User> data = new ArrayList<User>();
    private int pageNum = 1;
    private boolean isLoading;
    private boolean myData = true;
    private String TAG = "userinfolog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_info);
        ButterKnife.bind(this);
        //custid = this.getIntent().getLongExtra("custid", 0);
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
        adapter = new UserInfoAdapter(UsersInfoActivity.this, data);
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
        final LinearLayoutManager layoutManager = new LinearLayoutManager(UsersInfoActivity.this);
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
                                if (userInfoVo.getData().isHasNextPage()) {
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
        adapter.setOnItemClickListener(new UserInfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(UsersInfoActivity.this, UsersInfoDetailActivity.class);
                User user = (User) adapter.getAll().get(position);
                intent.putExtra("user",user);
                startActivity(intent);
            }

            @Override
            public void onItemLongClickListener(View view, int position) {

            }
        });
    }

    @OnClick({R.id.back_rl, R.id.add_bt, R.id.search_bt})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
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
            case R.id.add_bt:
                /*Intent intent = new Intent(new Intent(UsersInfoActivity.this,
                    ContactPersonAddActivity.class));
                if (custid > 0)
                    intent.putExtra("custid", custid);
                startActivity(intent);*/
                break;
            case R.id.search_bt:
                updateData();
                //ApkApplication.imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }

    public void getData() {
        String json = new Gson().toJson(new UserInfoReq(pageNum, 10));
        OkHttpUtils
                .postString()
                .url(Config.QUERY_USERINFO_URL)
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
                                userInfoVo = GjsonUtil.parseJsonWithGson(response,UserInfoVo.class);
                                if (userInfoVo != null) {
                                    if (userInfoVo.isSuccess()) {
                                        if (userInfoVo.getData().getList() != null && userInfoVo.getData().getList().size() > 0) {
                                            for (int i = 0; i < userInfoVo.getData().getList().size(); i++) {
                                                data.add(userInfoVo.getData().getList().get(i));
                                            }
                                        }
                                        if (userInfoVo.getData().isHasNextPage()) {
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

    /*public void getSubordinateData() {
        String json = new Gson().toJson(new ContactReq(pageNum, 10, "",
                new ContactReq.ContactReqBean(Config.loginback.getUserId() + "", searchEt.getText().toString().trim())));
        OkHttpUtils
                .postString()
                .url(Config.subPageList)
                .addHeader("checkTokenKey", Config.loginback.getToken())
                .addHeader("sessionKey", Config.loginback.getUserId() + "")
                .content(json)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                ToastUtil.showNetError();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                //ToastUtil.showToast(response);
                                Type userlistType = new TypeToken<Result<ListPagers<Contact>>>() {
                                }.getType();
                                status = new Gson().fromJson(response, userlistType);
                                if (status != null) {
                                    if (status.isSuccess()) {
                                        if (status.getData().getList() != null && status.getData().getList().size() > 0) {
                                            for (int i = 0; i < status.getData().getList().size(); i++) {
                                                data.add(status.getData().getList().get(i));//.getData().getList().get(i)
                                            }
                                        }
                                        if (status.getData().isHasNextPage()) {
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
    }*/
}
