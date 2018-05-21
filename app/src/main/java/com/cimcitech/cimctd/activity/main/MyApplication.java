package com.cimcitech.cimctd.activity.main;

import android.app.Application;
import android.util.Log;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

/**
 * Created by lyw on 2018/4/3.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PushAgent mPushAgent = PushAgent.getInstance(this);
        Log.d("mtoken","device");
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.d("mtoken",deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.d("mtoken","s is:" + s);
                Log.d("mtoken","s1 is:" + s1);
            }
        });
    }
}
