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
import com.cimcitech.cimctd.utils.DateTool;
import com.cimcitech.cimctd.widget.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lyw on 2018/4/23.
 */

public class WorkOrderActivity extends BaseActivity {
    @Bind(R.id.custName_tv)
    TextView custName_tv;

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

        dispatch = (Dispatch) getIntent().getSerializableExtra("dispatch");
        initView();
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

    @OnClick({R.id.back_rl})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(WorkOrderActivity.this, DispatchActivity.class));
        finish();
    }
}
