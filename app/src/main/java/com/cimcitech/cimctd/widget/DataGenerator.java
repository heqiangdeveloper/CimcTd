package com.cimcitech.cimctd.widget;

import android.support.v4.app.Fragment;

import com.cimcitech.cimctd.activity.home.HomeFragment;
import com.cimcitech.cimctd.activity.message.MessageFragment;
import com.cimcitech.cimctd.activity.schedule.MyScheduleFragment;
import com.cimcitech.cimctd.activity.user.UserFragment;

/**
 * Created by zhouwei on 17/4/23.
 */

public class DataGenerator {
    public static Fragment[] getFragments() {
        Fragment fragments[] = new Fragment[4];
        fragments[0] = new MessageFragment();//消息
        fragments[1] = new HomeFragment();//首页
        fragments[2] = new MyScheduleFragment();//日程
        fragments[3] = new UserFragment();//我的
        return fragments;
    }
}
