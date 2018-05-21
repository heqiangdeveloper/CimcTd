package com.cimcitech.cimctd.activity.home.dispatch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.adapter.dispatch.DispatchAdapter;
import com.cimcitech.cimctd.bean.dispatch.Dispatch;
import com.cimcitech.cimctd.bean.dispatch.DispatchVo;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.DateTool;
import com.cimcitech.cimctd.widget.BaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by lyw on 2018/4/23.
 */

public class DispatchDetailActivity extends BaseActivity {
    @Bind(R.id.custName_tv)
    TextView custName_Tv;
    @Bind(R.id.contractNo_tv)
    TextView contractNo_Tv;
    @Bind(R.id.bridgeNum_tv)
    TextView bridgeNum_Tv;
    @Bind(R.id.fseq_tv)
    TextView fseq_Tv;
    @Bind(R.id.maintenanceType_tv)
    TextView maintenanceType_Tv;
    @Bind(R.id.chargeName_tv)
    TextView chargeName_Tv;
    @Bind(R.id.otherPeople_tv)
    TextView otherPeople_Tv;
    @Bind(R.id.proMaintenanceDate_tv)
    TextView proMaintenanceDate_Tv;
    @Bind(R.id.scheduleStartDate_tv)
    TextView scheduleStartDate_Tv;
    @Bind(R.id.proEndDate_tv)
    TextView proEndDate_Tv;
    @Bind(R.id.recComfirmDate_tv)
    TextView recComfirmDate_Tv;
    @Bind(R.id.actualEndDate_tv)
    TextView actualEndDate_Tv;
    @Bind(R.id.actualStartDate_tv)
    TextView actualStartDate_Tv;
    @Bind(R.id.confirmPlan_tv)
    TextView confirmPlan_Tv;
    @Bind(R.id.workOrder_tv)
    TextView workOrder_Tv;

    private int pageNum = 1;
    private DispatchVo dispatchVo;
    private List<Dispatch> data = new ArrayList<>();
    private DispatchAdapter adapter;
    private Handler uiHandler = null;
    private Handler handler = new Handler();
    private String TAG = "dispatchlog";
    private Dispatch dispatch;
    private boolean isSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_detail);
        ButterKnife.bind(this);

        dispatch = (Dispatch) getIntent().getSerializableExtra("dispatch");
        isSale = getIntent().getBooleanExtra("isSale",true);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Config.isAddMyClient) {
            //Config.isAddMyClient = false;
            //updateData();
        }
    }

    @OnClick({R.id.back_rl,R.id.workOrder_tv,R.id.confirmPlan_tv})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                startActivity(new Intent(DispatchDetailActivity.this,
                        DispatchActivity.class));
                finish();
                break;
            case R.id.workOrder_tv://填写工单
                Intent i = new Intent(DispatchDetailActivity.this, WorkOrderActivity.class);
                i.putExtra("dispatch",dispatch);
                startActivity(i);
                finish();
                break;
            case R.id.confirmPlan_tv://确认接收
                mLoading.show();
                confirmPlan();
                break;
        }
    }

    public void initView() {
        confirmPlan_Tv.setVisibility(dispatch.getRecComfirmDate() != null ?
                   View.GONE:View.VISIBLE );
        workOrder_Tv.setVisibility(dispatch.getRecComfirmDate() != null ?
                View.VISIBLE:View.GONE);
        custName_Tv.setText(dispatch.getCustName());
        contractNo_Tv.setText(dispatch.getContractNo());
        bridgeNum_Tv.setText(dispatch.getBridgeNum());
        fseq_Tv.setText(dispatch.getFseq() + "");
        maintenanceType_Tv.setText(dispatch.getMaintenanceTypeValue());
        chargeName_Tv.setText(dispatch.getChargeName());
        otherPeople_Tv.setText(dispatch.getOtherPeopleValue());
        proMaintenanceDate_Tv.setText(dispatch.getProMaintenanceDate() != null ? DateTool.getDateStr
                (dispatch.getProMaintenanceDate()):"");
        scheduleStartDate_Tv.setText(dispatch.getScheduleStartDate() != null ? DateTool.getDateStr(
                dispatch.getProMaintenanceDate()):"");
        proEndDate_Tv.setText(dispatch.getProEndDate() != null ? DateTool.getDateStr(
                dispatch.getProEndDate()):"");
        recComfirmDate_Tv.setText(dispatch.getRecComfirmDate() != null ? DateTool.getDateStr(
                dispatch.getRecComfirmDate()):"");
        actualStartDate_Tv.setText(dispatch.getActualStartDate() != null ? DateTool.getDateStr(
                dispatch.getActualStartDate()):"");
        actualEndDate_Tv.setText(dispatch.getActualEndDate() != null ? DateTool.getDateStr(
                dispatch.getActualEndDate()):"");
    }

    public void confirmPlan(){//确认接收派工单
        int planId = dispatch.getPlanId();
        OkHttpUtils
                .post()
                .url(isSale?Config.SALE_CONFIRM_PLAN_URL:Config.MAIN_CONFIRM_PLAN_URL)
                .addParams("planId",planId + "")
                //.addHeader("checkTokenKey", Config.loginback.getToken())
                //.addHeader("sessionKey", Config.loginback.getUserId() + "")
                //.content(json)
                //.mediaType(MediaType.parse("application/json; charset=utf-8"))
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
                                try{
                                    JSONObject json = new JSONObject(response);
                                    if (json.getBoolean("success")) {
                                        Toast.makeText(DispatchDetailActivity.this, "接收成功", Toast
                                                .LENGTH_SHORT).show();
                                        startActivity(new Intent(DispatchDetailActivity.this,
                                                DispatchActivity.class));
                                        finish();
                                    }else{
                                        Toast.makeText(DispatchDetailActivity.this, json
                                                .getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }catch (JSONException e){
                                    Toast.makeText(DispatchDetailActivity.this, "接收失败", Toast
                                            .LENGTH_SHORT).show();
                                }
                            }
                        }
                );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DispatchDetailActivity.this,
                DispatchActivity.class));
        finish();
    }
}
