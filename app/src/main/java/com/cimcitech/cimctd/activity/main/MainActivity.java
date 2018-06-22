package com.cimcitech.cimctd.activity.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.bean.AreaVo;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.cimcitech.cimctd.utils.ToastUtil;
import com.cimcitech.cimctd.widget.DataGenerator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;


public class MainActivity extends AppCompatActivity {
    //private RadioGroup mRadioGroup;
    private Fragment[] mFragments;
    //private RadioButton mRadioButtonHome;

    private String appAuthString = "";
    private static boolean mBackKeyPressed = false;//记录是否有首次按键
    private long firstTime = 0;

    private BottomBarLayout mBottomBarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mFragments = DataGenerator.getFragments();
        mBottomBarLayout = (BottomBarLayout)findViewById(R.id.bbl);
        // 将activity设置为全屏显示

        //appAuthString = this.getIntent().getStringExtra("AuthString");
        initView2();
        mBottomBarLayout.setSmoothScroll(true);
        mBottomBarLayout.setUnread(0,100);
        mBottomBarLayout.setUnread(1,26);
        mBottomBarLayout.showNotify(2);
        mBottomBarLayout.setMsg(3,"NEW");

        //达到缓存的上限，就清理缓存
        /*Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory();//该手机分配给每个APP的最大内存
        Log.i("heqmom", Long.toString(maxMemory / (1024 * 1024)));
        DataCleanManager manager = new DataCleanManager();
        long maxCache = maxMemory / 8;//缓存的上限
        try {
            long currentCache = manager.getFolderSize(getApplication().getCacheDir());
            if (currentCache >= maxCache) {
                manager.clearAllCache(getApplication());
            }
        } catch (Exception e) {

        }*/
    }

    private void initView() {
        /*mRadioGroup = (RadioGroup) findViewById(R.id.radio_group_button);
        mRadioButtonHome = (RadioButton) findViewById(R.id.radio_button_home);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment mFragment = null;

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button_message:
                        //mFragment = Config.isLeader ? mFragments[4] : mFragments[0];
                        mFragment = mFragments[0];
                        break;
                    case R.id.radio_button_home:
                        mFragment = mFragments[1];
                        break;
                    case R.id.radio_button_schedule:
                        mFragment = mFragments[2];
                        break;
                    case R.id.radio_button_user:
                        mFragment = mFragments[3];
                        break;
                }
                if (mFragments != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container, mFragment).commit();
                }
            }
        });
        mRadioButtonHome.setChecked(true);*/
    }

    public void initView2(){
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            Fragment mFragment = null;
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int i, int position) {
                for(int n = 0; n < 4; n++){
                    mBottomBarLayout.getBottomItem(n).setStatus(false);
                }
                if(position == 0){//消息
                    mFragment = mFragments[0];
                }else if(position == 1){//首页
                    mFragment = mFragments[1];
                }else if(position == 2){//日程
                    mFragment = mFragments[2];
                }else{//我的
                    mFragment = mFragments[3];
                }
                mBottomBarLayout.getBottomItem(position).setStatus(true);
                if (mFragments != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container, mFragment).commit();
                }
            }
        });
        //mBottomBarLayout.getBottomItem(1).setStatus(true);
        mBottomBarLayout.getBottomItem(1).callOnClick();//默认选中首页
    }

    /**
     * 获取省市级联的省市信息
     */
    public void getAreaData() {
        /*if( Config.loginback == null){
            Config.loginback.setToken("2FD08ED0-E53B-48B1-B8E6-E6B4290A2770");
        }*/
        OkHttpUtils
                .post()
                .url(Config.getProviceAndCity)
                .addHeader("checkTokenKey", Config.loginback.getToken())
                .addHeader("sessionKey", Config.loginback.getUserId() + "")
                .build()
                .execute(
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                ToastUtil.showNetError();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                // ToastUtil.showToast(response);
                                try {
                                    Config.areaVo = GjsonUtil.parseJsonWithGson(response, AreaVo.class);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);//防止快速切换Fragment导致的FC-->不保存Fragment的状态，让其与activity一同消亡
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
