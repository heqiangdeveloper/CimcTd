package com.cimcitech.cimctd.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.activity.home.annount.AnnounceDetailActivity;
import com.cimcitech.cimctd.activity.home.annount.AnnounceListActivity;
import com.cimcitech.cimctd.activity.home.contact.ContactActivity;
import com.cimcitech.cimctd.activity.home.dispatch.DispatchActivity;
import com.cimcitech.cimctd.activity.home.faulthandle.FaultHandleActivity;
import com.cimcitech.cimctd.activity.home.file_search.FileSearchActivity;
import com.cimcitech.cimctd.activity.home.user_info.UsersInfoActivity;
import com.cimcitech.cimctd.adapter.HomeGridAdapter;
import com.cimcitech.cimctd.adapter.announce.AnnounceHomeAdapter;
import com.cimcitech.cimctd.bean.announce.AnnounceReq;
import com.cimcitech.cimctd.bean.announce.AnnounceVo;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.cimcitech.cimctd.utils.ToastUtil;
import com.cimcitech.cimctd.utils.Utility;
import com.cimcitech.cimctd.widget.MyGridView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class HomeFragment extends Fragment {

    @Bind(R.id.homeGrid)
    MyGridView homeGrid;

    private HomeGridAdapter gridAdapter;
    private AnnounceVo announceVo;
    private AnnounceHomeAdapter adapter;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);
        ButterKnife.bind(this, view);
        initViewData();
        //getData();
        return view;
    }

    public void initViewData() {
        if(null != mContext){
            //listContent.setOnItemClickListener(new listContentItemListener());
            //String appAuthStr = Config.AppAuthStr;
            //gridAdapter = new HomeGridAdapter(mContext,appAuthStr);
            gridAdapter = new HomeGridAdapter(mContext);
            homeGrid.setAdapter(gridAdapter);
            homeGrid.setSelector(R.drawable.hide_listview_yellow_selector);
            homeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textView = (TextView) view.findViewById(R.id.tv_logo);
                    String itemName = textView.getText().toString();
                    switch(itemName){
                        case "用户信息":
                            startActivity(new Intent(mContext, UsersInfoActivity.class));
                            break;
                        case "资料查询":
                            startActivity(new Intent(mContext, FileSearchActivity.class));
                            break;
                        case "联系人":
                            startActivity(new Intent(mContext, ContactActivity.class));
                            break;
                        case "派工":
                            startActivity(new Intent(mContext, DispatchActivity.class));
                            break;
                        case "故障处理":
                            startActivity(new Intent(mContext, FaultHandleActivity.class));
                            break;
                        /*case "订单合同":
                            startActivity(new Intent(mContext, OrderContractActivity.class));
                            break;
                        case "工作汇报":
                            startActivity(new Intent(mContext, WorkWeeklyActivity.class));
                            break;
                        case "我的客户":
                            startActivity(new Intent(mContext, MyClientActivity.class));
                            break;
                        case "联系人":
                            startActivity(new Intent(mContext, ContactPersonActivity.class));
                            break;
                        case "问题反馈":
                            startActivity(new Intent(mContext, FeedBackActivity.class));
                            break;
                        case "回款跟踪":
                            startActivity(new Intent(mContext, PaymentActivity.class));
                            break;
                        case "生产进度":
                            startActivity(new Intent(mContext, ProductionActivity.class));
                            break;
                        case "车辆入库":
                            startActivity(new Intent(mContext, CarInStorageActivity.class));
                            break;
                        case "发车申请":
                            startActivity(new Intent(mContext, DepartRequestActivity.class));
                            break;
                        case "车辆出厂":
                            startActivity(new Intent(mContext, CarOutFactoryActivity.class));
                            break;
                        case "扫码入库":
                            startActivity(new Intent(mContext, QRCodeInStorageActivity.class));
                            break;
                        case "扫码出厂":
                            startActivity(new Intent(mContext, QRCodeOutFactoryActivity.class));
                            break;
                        case "报表":
                            startActivity(new Intent(mContext, ReportMainActivity.class));
                            break;
                        case "检验":
                            startActivity(new Intent(mContext, QRCodeTestActivity.class));
                            break;*/
                    }
                }
            });
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getActivity();
    }

    public void getData() {
        String json = new Gson().toJson(new AnnounceReq(1, 10, ""));
        OkHttpUtils
                .postString()
                .url(Config.announceList)
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
                                announceVo = GjsonUtil.parseJsonWithGson(response, AnnounceVo.class);
                                if (announceVo != null)
                                    if (announceVo.isSuccess())
                                        if (announceVo.getData().getList().size() > 0) {
                                            if(mContext != null){
                                                adapter = new AnnounceHomeAdapter(mContext, announceVo.getData().getList());
                                                /*if(null != listContent){
                                                    listContent.setAdapter(adapter);
                                                    //以下代码不可少，否则页面不能够上下滑动
                                                    new Utility().setListViewHeightBasedOnChildren(listContent);
                                                }*/
                                            }
                                        }
                            }
                        }
                );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
