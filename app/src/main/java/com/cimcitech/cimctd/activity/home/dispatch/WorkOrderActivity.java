package com.cimcitech.cimctd.activity.home.dispatch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.adapter.dispatch.DispatchAdapter;
import com.cimcitech.cimctd.bean.dispatch.Dispatch;
import com.cimcitech.cimctd.bean.dispatch.DispatchVo;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.widget.MyBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyw on 2018/4/23.
 */

public class WorkOrderActivity extends MyBaseActivity {
    @Bind(R.id.custName_tv)
    TextView custName_tv;
    @Bind(R.id.titleName_tv)
    TextView titleName_Tv;
    @Bind(R.id.more_tv)
    TextView more_Tv;

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
    private Dispatch dispatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_hb_bill);
        ButterKnife.bind(this);
        setTitle();
        dispatch = (Dispatch) getIntent().getSerializableExtra("dispatch");
        initView();
    }

    public void setTitle(){
        titleName_Tv.setText("登机桥定检表");
        more_Tv.setVisibility(View.GONE);
    }

    public void initView(){
        custName_tv.setText(dispatch.getCustName() + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Config.isAddMyClient) {
            //Config.isAddMyClient = false;
            //updateData();
        }
    }

    @OnClick({R.id.back})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
