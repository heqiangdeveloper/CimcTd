package com.cimcitech.cimctd.activity.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
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
import com.cimcitech.cimctd.activity.message.MessageData;
import com.cimcitech.cimctd.activity.message.MessageFragment;
import com.cimcitech.cimctd.bean.AreaVo;
import com.cimcitech.cimctd.receiver.MyReceiver;
import com.cimcitech.cimctd.task.message.QueryUnReadMessageTask;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.cimcitech.cimctd.utils.ToastUtil;
import com.cimcitech.cimctd.widget.DataGenerator;
import com.jimmy.common.listener.OnTaskFinishedListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;


public class MainActivity extends AppCompatActivity {
    //private RadioGroup mRadioGroup;
    private Fragment[] mFragments;
    //private RadioButton mRadioButtonHome;

    private String appAuthString = "";
    private static boolean mBackKeyPressed = false;//记录是否有首次按键
    private long firstTime = 0;

    private BottomBarLayout mBottomBarLayout;
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public int unReadMsg  = 0;
    public IntentFilter filter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mFragments = DataGenerator.getFragments();
        mBottomBarLayout = (BottomBarLayout)findViewById(R.id.bbl);

        //appAuthString = this.getIntent().getStringExtra("AuthString");
        initView2();
        mBottomBarLayout.setSmoothScroll(true);

        getUnreadMsg();
        mBottomBarLayout.setUnread(1,0);
        mBottomBarLayout.showNotify(2);
        mBottomBarLayout.setMsg(3,"NEW");

        //设置极光推送
        //初始化sdk，如果推送已被关闭，就恢复推送服务
        if(JPushInterface.isPushStopped(MainActivity.this)){
            JPushInterface.resumePush(MainActivity.this);
        }
        JPushInterface.setDebugMode(true);//正式版的时候设置false，关闭调试
        JPushInterface.init(this);
        //建议添加tag标签，发送消息的之后就可以指定tag标签来发送了
        Set<String> setTags = new HashSet<>();
        setTags.add("debug");//名字任意，可多添加几个
        JPushInterface.setTags(this, setTags, null);//设置标签
        JPushInterface.setAlias(this, String.valueOf(Config.USERID), null);

        String content = getIntent().getStringExtra("content");
        if (null != content && !content.equals("")){
            mBottomBarLayout.getBottomItem(0).callOnClick();//选中消息页
        }
        registRefreshReceiver();
        Log.d("aliaslog","alias is: " + Config.USERID);
    }

    public void registRefreshReceiver(){
        filter = new IntentFilter();
        filter.addAction(MyReceiver.NEW_MSG_COMING);
        filter.addAction(MessageFragment.CALL_MAINACTIVITY_REFRESH_COUNT);
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(refreshReceiver,filter);
    }

    public BroadcastReceiver refreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            if(intent.getAction().equals(MyReceiver.NEW_MSG_COMING)){
//                getUnreadMsg();
//            }
            getUnreadMsg();
        }
    };

    public void unRegistRefreshReceiver(){
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(refreshReceiver);
    }

    public void getUnreadMsg(){
        new QueryUnReadMessageTask(MainActivity.this, new OnTaskFinishedListener<Integer>() {
            @Override
            public void onTaskFinished(Integer data) {
                mBottomBarLayout.setUnread(0,data);
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //activity的getIntent()方法只是获取activity原来的intent。
        //我们还需要在onNewIntent()方法中重载一下：setIntent(intent);，要不然，接收到的数据依然为空。
        setIntent(intent);//不能省
        getUnreadMsg();
        mBottomBarLayout.getBottomItem(0).callOnClick();//选中消息页
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
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_container,
                            mFragment).commitAllowingStateLoss();
                }
            }
        });
        //mBottomBarLayout.getBottomItem(1).setStatus(true);
        mBottomBarLayout.getBottomItem(1).callOnClick();//默认选中首页
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegistRefreshReceiver();
    }
}
