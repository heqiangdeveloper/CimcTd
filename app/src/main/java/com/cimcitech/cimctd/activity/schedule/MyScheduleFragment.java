package com.cimcitech.cimctd.activity.schedule;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.adapter.schedual.EventSetAdapter;
import com.cimcitech.cimctd.fragment.EventSetFragment;
import com.cimcitech.cimctd.fragment.ScheduleFragment;
import com.cimcitech.cimctd.task.eventset.LoadEventSetTask;
import com.cimcitech.cimctd.utils.Config;
import com.jimmy.common.base.app.BaseFragment;
import com.jimmy.common.bean.EventSet;
import com.jimmy.common.listener.OnTaskFinishedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cimcitech on 2017/7/31.
 */

public class MyScheduleFragment extends Fragment implements View.OnClickListener, OnTaskFinishedListener<List<EventSet>> {
    @Bind(R.id.dlMain)
    DrawerLayout dlMain;
    @Bind(R.id.llTitleDate)
    LinearLayout llTitleDate;
    @Bind(R.id.tvTitleMonth)
    TextView tvTitleMonth;
    @Bind(R.id.tvTitleDay)
    TextView tvTitleDay;
    @Bind(R.id.tvTitle)
    TextView tvTitle;

    @Bind(R.id.rvMenuEventSetList)
    RecyclerView rvMenuEventSetList;

    public static int ADD_EVENT_SET_CODE = 1;
    public static String ADD_EVENT_SET_ACTION = "action.add.event.set";
    //注册广播
    public static final String ADAPTER_BROADCAST = "com.cimcitech.cimctd.ADAPTER_BROADCAST";
    public static final String CALLSETCURRENTSELECTDATE_BROADCAST  = "com.cimcitech.cimctd.CALLSETCURRENTSELECTDATE_BROADCAST";

    private EventSetAdapter mEventSetAdapter;
    private List<EventSet> mEventSets;

    private BaseFragment mScheduleFragment, mEventSetFragment;
    private EventSet mCurrentEventSet;
    private AddEventSetBroadcastReceiver mAddEventSetBroadcastReceiver;
    private AddAdapterBroadcastReceiver mAddAdapterBroadcastReceiver;
    private AddCallSetCurrentSelectDateBroadcastReceiver mAddCallSetCurrentSelectDateBroadcastReceiver;

    private long[] mNotes = new long[2];
    private String[] mMonthText;
    private int mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay;

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*view = inflater.inflate(R.layout.schedule_main, container, false);
        ButterKnife.bind(this, view);
        bindView();
        initData();*/

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            ButterKnife.bind(this, view);
            bindView();
            initData();
            return view;
        }
        view = inflater.inflate(R.layout.schedule_main, container, false);

        ButterKnife.bind(this, view);
        bindView();
        initData();
        return view;
    }



    protected void bindView() {
        initUi();
        initEventSetList();
        gotoScheduleFragment();
        initBroadcastReceiver();
        //关闭抽屉菜单，禁止手势滑动
        //dlMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void initBroadcastReceiver() {
        if (mAddEventSetBroadcastReceiver == null) {
            mAddEventSetBroadcastReceiver = new AddEventSetBroadcastReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ADD_EVENT_SET_ACTION);
            getActivity().registerReceiver(mAddEventSetBroadcastReceiver, filter);
        }
        if (mAddAdapterBroadcastReceiver == null) {
            mAddAdapterBroadcastReceiver = new AddAdapterBroadcastReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ADAPTER_BROADCAST);
            getActivity().registerReceiver(mAddAdapterBroadcastReceiver, filter);
        }
        if (mAddCallSetCurrentSelectDateBroadcastReceiver == null) {
            mAddCallSetCurrentSelectDateBroadcastReceiver = new AddCallSetCurrentSelectDateBroadcastReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(CALLSETCURRENTSELECTDATE_BROADCAST);
            getActivity().registerReceiver(mAddCallSetCurrentSelectDateBroadcastReceiver, filter);
        }
    }

    private void initEventSetList() {
        mEventSets = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMenuEventSetList.setLayoutManager(manager);
        DefaultItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setSupportsChangeAnimations(false);
        rvMenuEventSetList.setItemAnimator(itemAnimator);
        mEventSetAdapter = new EventSetAdapter(getContext(), mEventSets);
        rvMenuEventSetList.setAdapter(mEventSetAdapter);
    }

    private void initUi() {
        dlMain.setScrimColor(Color.TRANSPARENT);
        mMonthText = getResources().getStringArray(R.array.calendar_month);
        llTitleDate.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.GONE);
        tvTitleMonth.setText(mMonthText[Calendar.getInstance().get(Calendar.MONTH)]);
        tvTitleDay.setText(getString(R.string.calendar_today));
        if (Build.VERSION.SDK_INT < 19) {
            //TextView tvMenuTitle = searchViewById(R.id.tvMenuTitle);
            //tvMenuTitle.setGravity(Gravity.CENTER_VERTICAL);
        }
    }

    protected void initData() {
        resetMainTitleDate(mCurrentSelectYear, mCurrentSelectMonth, mCurrentSelectDay);
        new LoadEventSetTask(getContext(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void resetMainTitleDate(int year, int month, int day) {
        llTitleDate.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.GONE);
        Calendar calendar = Calendar.getInstance();
        if (year == calendar.get(Calendar.YEAR) &&
                month == calendar.get(Calendar.MONTH) &&
                day == calendar.get(Calendar.DAY_OF_MONTH)) {
            tvTitleMonth.setText(mMonthText[month]);
            tvTitleDay.setText(getString(R.string.calendar_today));
        } else {
            if (year == calendar.get(Calendar.YEAR)) {
                tvTitleMonth.setText(mMonthText[month]);
            } else {
                tvTitleMonth.setText(String.format("%s%s", String.format(getString(R.string.calendar_year), year),
                        mMonthText[month]));
            }
            tvTitleDay.setText(String.format(getString(R.string.calendar_day), day));
        }
        setCurrentSelectDate(year, month, day);
    }

    private void resetTitleText(String name) {
        llTitleDate.setVisibility(View.GONE);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(name);
    }

    private void setCurrentSelectDate(int year, int month, int day) {
        mCurrentSelectYear = year;
        mCurrentSelectMonth = month;
        mCurrentSelectDay = day;
    }


    @OnClick({R.id.ivMainMenu,R.id.llMenuSchedule,R.id.llMenuNoCategory,R.id.tvMenuAddEventSet})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivMainMenu:
                dlMain.openDrawer(Gravity.START);
                break;
            case R.id.llMenuSchedule:
                gotoScheduleFragment();
                break;
            case R.id.llMenuNoCategory:
                mCurrentEventSet = new EventSet();
                mCurrentEventSet.setName(getString(R.string.menu_no_category));
                gotoEventSetFragment(mCurrentEventSet);
                break;
            case R.id.tvMenuAddEventSet:
                gotoAddEventSetActivity();
                break;
        }
    }

    public void gotoScheduleFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
       // FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        if (mScheduleFragment == null) {
            mScheduleFragment = ScheduleFragment.getInstance();
            ft.add(R.id.flMainContainer, mScheduleFragment);
        }
        if (mEventSetFragment != null)
            ft.hide(mEventSetFragment);
        ft.show(mScheduleFragment);
        ft.commit();
        llTitleDate.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.GONE);
        dlMain.closeDrawer(Gravity.START);
    }

    public  void gotoEventSetFragment(EventSet eventSet) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        //FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_NONE);
        if (mCurrentEventSet != eventSet || eventSet.getId() == 0) {
            if (mEventSetFragment != null)
                ft.remove(mEventSetFragment);
            mEventSetFragment = EventSetFragment.getInstance(eventSet);
            ft.add(R.id.flMainContainer, mEventSetFragment);
        }
        ft.hide(mScheduleFragment);
        ft.show(mEventSetFragment);
        ft.commit();
        resetTitleText(eventSet.getName());
        dlMain.closeDrawer(Gravity.START);
        mCurrentEventSet = eventSet;
    }

    public void gotoAddEventSetActivity() {
        startActivityForResult(new Intent(getActivity(), AddEventSetActivity.class), ADD_EVENT_SET_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_EVENT_SET_CODE) {
            if (resultCode == AddEventSetActivity.ADD_EVENT_SET_FINISH) {
                EventSet eventSet = (EventSet) data.getSerializableExtra(AddEventSetActivity.EVENT_SET_OBJ);
                if (eventSet != null)
                    mEventSetAdapter.insertItem(eventSet);
            }
        }
    }


    /*public void onBackPressed() {
        if (dlMain.isDrawerOpen(Gravity.START)) {
            dlMain.closeDrawer(Gravity.START);
        } else {
            System.arraycopy(mNotes, 1, mNotes, 0, mNotes.length - 1);
            mNotes[mNotes.length - 1] = SystemClock.uptimeMillis();
            if (SystemClock.uptimeMillis() - mNotes[0] < 1000) {
                //finish();
            } else {
                Toast.makeText(getActivity(), getString(R.string.exit_app_hint), Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAddEventSetBroadcastReceiver != null) {
            getActivity().unregisterReceiver(mAddEventSetBroadcastReceiver);
            mAddEventSetBroadcastReceiver = null;
        }
        if (mAddAdapterBroadcastReceiver != null) {
            getActivity().unregisterReceiver(mAddAdapterBroadcastReceiver);
            mAddAdapterBroadcastReceiver = null;
        }
        if (mAddCallSetCurrentSelectDateBroadcastReceiver != null) {
            getActivity().unregisterReceiver(mAddCallSetCurrentSelectDateBroadcastReceiver);
            mAddCallSetCurrentSelectDateBroadcastReceiver = null;
        }
        ButterKnife.unbind(this);
    }

    @Override
    public void onTaskFinished(List<EventSet> data) {
        mEventSetAdapter.changeAllData(data);
    }

    private class AddEventSetBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (ADD_EVENT_SET_ACTION.equals(intent.getAction())) {
                EventSet eventSet = (EventSet) intent.getSerializableExtra(AddEventSetActivity.EVENT_SET_OBJ);
                if (eventSet != null)
                    mEventSetAdapter.insertItem(eventSet);
            }
        }
    }

    private class AddAdapterBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(ADAPTER_BROADCAST.equals(intent.getAction())){
                EventSet eventSet = (EventSet) intent.getSerializableExtra("eventSet");
                if (eventSet != null)
                    gotoEventSetFragment(eventSet);
            }
        }
    }

    private class AddCallSetCurrentSelectDateBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(CALLSETCURRENTSELECTDATE_BROADCAST.equals(intent.getAction())){
                int year = intent.getIntExtra("year",0);
                int month = intent.getIntExtra("month",0);
                int day = intent.getIntExtra("day",0);
                resetMainTitleDate(year,month,day);
            }
        }
    }

}
