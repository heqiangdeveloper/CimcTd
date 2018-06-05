package com.cimcitech.cimctd.activity.home.modify_password;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.rsa.RSAUtils;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.ToastUtil;
import com.cimcitech.cimctd.widget.MyBaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ModifyPasswordActivity extends MyBaseActivity {

    @Bind(R.id.back_rl)
    RelativeLayout backRl;
    @Bind(R.id.old_psd_tv)
    EditText old_psd_Tv;
    @Bind(R.id.new_psd_tv1)
    EditText new_psd_Tv1;
    @Bind(R.id.new_psd_tv2)
    EditText new_psd_Tv2;
    @Bind(R.id.commit_bt)
    Button commit_Bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psd);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.commit_bt,R.id.back_rl})
    public void onclick(View view) {
        switch (view.getId()){
            case R.id.back_rl:
                finish();
                break;
            case R.id.commit_bt:
                //格式检查
                if(!checkInput()){
                    return;
                }
                mLoading.show();
                //提交至服务器
                commitData(Config.userId,new_psd_Tv2.getText().toString().trim());
                break;
        }
    }

    public boolean checkInput(){
        String oldPsd = old_psd_Tv.getText().toString().trim();
        String newPsd1 = new_psd_Tv1.getText().toString().trim();
        String newPsd2 = new_psd_Tv2.getText().toString().trim();
        if(oldPsd.length() == 0){
            Toast.makeText(ModifyPasswordActivity.this,"请输入原密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(newPsd1.length() == 0 ){
            Toast.makeText(ModifyPasswordActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(newPsd2.length() == 0 ){
            Toast.makeText(ModifyPasswordActivity.this,"请再次输入新密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(Config.password.length() != 0 && !oldPsd.equals(Config.password)){
            Toast.makeText(ModifyPasswordActivity.this,"原密码错误，请确认！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!newPsd1.equals(newPsd2)){
            Toast.makeText(ModifyPasswordActivity.this,"两次输入的新密码不一致，请确认！",Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }

    public void commitData(Long userId,String newPsd){
        //对新密码进行加密
        try{
            newPsd = RSAUtils.encrypt(newPsd,Config.PUBLIC_KEY);
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"rsa 加密失败！！",Toast.LENGTH_SHORT).show();
        }
        OkHttpUtils
                .post()
                .url(Config.MODIFY_PASSWORD)
                .addParams("userId", userId + "")
                .addParams("password", newPsd)
                .build()
                .execute(
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                mLoading.dismiss();
                                ToastUtil.showNetError();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                mLoading.dismiss();
                                Log.d(TAG,"modify psd response is:" + response);
                                try{
                                    JSONObject json = new JSONObject(response);
                                    if (json.getBoolean("success")) {
                                        Toast.makeText(ModifyPasswordActivity.this, "密码修改成功", Toast
                                                .LENGTH_SHORT).show();
                                        finish();
                                    }else{
                                        Toast.makeText(ModifyPasswordActivity.this, json
                                                .getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }catch (JSONException e){
                                    Toast.makeText(ModifyPasswordActivity.this, "密码修改失败", Toast
                                            .LENGTH_SHORT).show();
                                }
                            }
                        });
    }
}
