package com.cimcitech.cimctd.activity.home.dispatch;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.activity.main.LoginActivity;
import com.cimcitech.cimctd.activity.message.MessageFragment;
import com.cimcitech.cimctd.adapter.dispatch.DispatchAdapter;
import com.cimcitech.cimctd.bean.dispatch.Dispatch;
import com.cimcitech.cimctd.bean.dispatch.DispatchVo;
import com.cimcitech.cimctd.bean.dispatch.MainDispatchReq;
import com.cimcitech.cimctd.bean.dispatch.MaintenancePlanVo;
import com.cimcitech.cimctd.bean.dispatch.SaleDispatchReq;
import com.cimcitech.cimctd.bean.dispatch.saleMaintenPlanVo;
import com.cimcitech.cimctd.receiver.MyReceiver;
import com.cimcitech.cimctd.task.message.UpdateMessageAcceptTask;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.DateTool;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.cimcitech.cimctd.widget.MyBaseActivity;
import com.google.gson.Gson;
import com.jimmy.common.listener.OnTaskFinishedListener;
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
import okhttp3.MediaType;

/**
 * Created by lyw on 2018/4/23.
 */

public class DispatchDetailActivity extends MyBaseActivity {
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
    @Bind(R.id.back_iv)
    ImageView back_Iv;

    @Bind(R.id.more_tv)
    TextView more_Tv;
    @Bind(R.id.titleName_tv)
    TextView titleName_Tv;
    private int pageNum = 1;
    private DispatchVo dispatchVo;
    private List<Dispatch> data = new ArrayList<>();
    private Handler uiHandler = null;
    private Handler handler = new Handler();
    private String TAG = "dispatchlog";
    private Dispatch dispatch = null;
    private boolean isSale = true;
    private String contractType = "";
    private String planId = "";
    private int id = -2;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_detail);
        ButterKnife.bind(this);
        setTitle();
        sp = this.getSharedPreferences(Config.KEY_LOGIN_AUTO, MODE_PRIVATE);

        //由“区域经理派工”页面跳转而来
        dispatch = (Dispatch) getIntent().getSerializableExtra("dispatch");
        isSale = getIntent().getBooleanExtra("isSale",true);

        //由消息页面跳转而来
        contractType = getIntent().getStringExtra("contractType");//若不是，则等号右边的值为null
        planId = getIntent().getStringExtra("planId");
        id = getIntent().getIntExtra("id",-1);

        if(null != contractType && null != planId){//由消息页面跳转而来
            mLoading.show();
            getData();
        }else{
            initView();
        }
    }

    public void setTitle(){
        more_Tv.setVisibility(View.GONE);
        titleName_Tv.setText("派工详情");
    }

    public void getData(){
        String json = "";
        String url = "";
        if(contractType.equals("0")){//销售类合同
            //查询    已派工  +  未接收
            json = new Gson().toJson(new SaleDispatchReq(pageNum,10, null,
                    new saleMaintenPlanVo("isSend",
                            "all",
                            "all",
                            null,
                            null,
                            null,
                            null,
                            sp.getLong("userId",0))));
            url = Config.QUERY_DISPATCH_SALE_URL;
        }else{//维保类合同
            //查询    已派工  +  未接收
            json = new Gson().toJson(new MainDispatchReq(pageNum, 10, "createTime desc",
                    new MaintenancePlanVo("isSend",
                            "all",
                            "all",
                            null,
                            null,
                            null,
                            null,
                            sp.getLong("userId",0))));
            url = Config.QUERY_DISPATCH_MAIN_URL;
        }

        Log.d(TAG,"json is: " + json);
        OkHttpUtils
                .postString()
                .url(url)
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
                                                data.add(dispatchVo.getData().getList().get(i));//.getData().getList().get(i)
                                            }
                                        }
                                    }
                                }

                                //过滤出当前的派工单
                                if(null != data && data.size() != 0){
                                    for(int n = 0 ; n < data.size(); n++){
                                        if(Integer.parseInt(planId) == data.get(n).getPlanId()){
                                            dispatch = data.get(n);
                                            dispatch.setContractType(contractType);
                                            break;
                                        }
                                    }
                                }

                                initView();
                            }
                        }
                );
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Config.isAddMyClient) {
            //Config.isAddMyClient = false;
            //updateData();
        }
    }

    @OnClick({R.id.back_iv,R.id.workOrder_tv,R.id.confirmPlan_tv})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back_iv:
                if(null != contractType && null != planId){
                    //donothing
                }else{
                    startActivity(new Intent(DispatchDetailActivity.this, DispatchActivity.class));
                }
                finish();
                break;
            case R.id.workOrder_tv://填写工单
                Intent i = new Intent(DispatchDetailActivity.this, WorkOrderActivity.class);
                i.putExtra("dispatch",dispatch);
                startActivity(i);
                //finish();
                break;
            case R.id.confirmPlan_tv://确认接收
                new AlertDialog.Builder(DispatchDetailActivity.this)
                        //.setTitle("提示")
                        .setMessage("您确定要接单吗？")
                        .setCancelable(false)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mLoading.show();
                                confirmPlan();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back_Iv.callOnClick();
    }

    public void initView() {
        if(null != dispatch){
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
                    dispatch.getScheduleStartDate()):"");
            proEndDate_Tv.setText(dispatch.getProEndDate() != null ? DateTool.getDateStr(
                    dispatch.getProEndDate()):"");
            recComfirmDate_Tv.setText(dispatch.getRecComfirmDate() != null ? DateTool.getDateStr(
                    dispatch.getRecComfirmDate()):"");
            actualStartDate_Tv.setText(dispatch.getActualStartDate() != null ? DateTool.getDateStr(
                    dispatch.getActualStartDate()):"");
            actualEndDate_Tv.setText(dispatch.getActualEndDate() != null ? DateTool.getDateStr(
                    dispatch.getActualEndDate()):"");
        }
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
                                        updateAccept();
                                    }else{
                                        Toast.makeText(DispatchDetailActivity.this, json
                                                .getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }catch (JSONException e){
                                    Toast.makeText(DispatchDetailActivity.this, "接单失败", Toast
                                            .LENGTH_SHORT).show();
                                }
                            }
                        }
                );
    }

    public void updateAccept(){
        new UpdateMessageAcceptTask(DispatchDetailActivity.this, new OnTaskFinishedListener<Boolean>() {
            @Override
            public void onTaskFinished(Boolean data) {
                sendRefreshAcceptBroadcast();
                Toast.makeText(DispatchDetailActivity.this, "接单成功", Toast
                        .LENGTH_SHORT).show();
                //startActivity(new Intent(DispatchDetailActivity.this, DispatchActivity.class));
                //finish();
                confirmPlan_Tv.setVisibility(View.GONE);
                workOrder_Tv.setVisibility(View.VISIBLE);
            }
        },id,1,dispatch.getPlanId(),dispatch.getContractType()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    //通知消息页面，更新该派工单的接收状态
    public void sendRefreshAcceptBroadcast(){
        Intent intent = new Intent();
        intent.setAction(MyReceiver.REFRESH_MESSAGE);
        LocalBroadcastManager.getInstance(DispatchDetailActivity.this).sendBroadcast(intent);
    }
}
