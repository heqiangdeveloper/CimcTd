package com.cimcitech.cimctd.activity.message;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chaychan.library.BottomBarLayout;
import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.activity.home.dispatch.DispatchDetailActivity;
import com.cimcitech.cimctd.adapter.message.Adapter;
import com.cimcitech.cimctd.adapter.message.MessagePopupWindowAdapter;
import com.cimcitech.cimctd.receiver.MyReceiver;
import com.cimcitech.cimctd.task.message.QueryMessageTask;
import com.cimcitech.cimctd.task.message.RemoveByIdMessageTask;
import com.cimcitech.cimctd.task.message.UpdateMessageOpenedTask;
import com.cimcitech.cimctd.utils.Config;
import com.jimmy.common.listener.OnTaskFinishedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cimcitech on 2017/7/31.
 */

public class MessageFragment extends Fragment{
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.message_top_area)
    LinearLayout message_top_Area;
    @Bind(R.id.message_top_category_name)
    TextView message_top_category_Name;
    @Bind(R.id.message_top_category_label)
    TextView message_top_category_Label;

    private Adapter adapter;
    private List<MessageData> data;
    private PopupWindow pop;
    private ListView listView;

    private BottomBarLayout mBottomBarLayout;
    public static final String KEY_MESSAGE = "content";
    public static final String KEY_EXTRAS = "title";
    private IntentFilter myIntentFilter1;
    private IntentFilter myIntentFilter2;
    private Bundle savedInstanceState;
    private TextView opened_Tv;
    private TextView remove_Tv;
    private LinearLayout view2;
    private AlertDialog dialog;
    private Context mContext;

    public final static String CALL_MAINACTIVITY_REFRESH_COUNT = "com.cimcitech.cimctd.receiver" +
            "mainactivity.refresh.count";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_message, container, false);
        ButterKnife.bind(this,view);
        this.savedInstanceState = savedInstanceState;
        //initDialog();
        registBroadcastReceiver();

        data = new ArrayList<MessageData>();
        Config.type = 0;//初始化时，默认显示“全部”信息
        getData();
        return view;
    }

    //最先执行onAttach()
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public Context getmContext(){
        return mContext;
    }

    public void registBroadcastReceiver(){
        myIntentFilter1 = new IntentFilter();
        myIntentFilter1.addAction(DispatchDetailActivity.REFRESH_MESSAGE_STATE);
        myIntentFilter1.addAction(MyReceiver.NEW_MSG_COMING);
        LocalBroadcastManager.getInstance(getmContext()).registerReceiver(mBroadcastReceiver,
                myIntentFilter1);
    }

    public  void unRegistBroadcastReceiver(){
        LocalBroadcastManager.getInstance(getmContext()).unregisterReceiver(mBroadcastReceiver);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
//            if(action.equals(DispatchDetailActivity.REFRESH_MESSAGE_STATE)){
//                getData();
//                adapter.updateList(data);
//            }
            getData();
            adapter.updateList(data);
        }
    };

    public void getData(){
        new QueryMessageTask(getmContext(), new OnTaskFinishedListener<List<MessageData>>() {
            @Override
            public void onTaskFinished(final List<MessageData> datas) {
                sendRefreshUnReadMsgBroadcast();//通知MainActivity更新未读信息数
                data = datas;
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getmContext());
                mRecyclerView.setLayoutManager(layoutManager);
                adapter = new Adapter(getmContext(),data);
                mRecyclerView.setAdapter(adapter);
                //给List添加点击事件
                adapter.setOnItemClickListener(new Adapter.IonSlidingViewClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        MessageData messageData = data.get(position);
                        int id = messageData.getId();
                        int opened = messageData.getOpened();
                        String contractType = messageData.getMessageContent().getContractType();
                        String planId = messageData.getMessageContent().getPlanId();
                        changeUnReadMsgAndOpened(id,opened);
                        Intent intent = new Intent(getmContext(), DispatchDetailActivity.class);
                        intent.putExtra("contractType",contractType);
                        intent.putExtra("planId",planId);
                        intent.putExtra("id",id);
                        getmContext().startActivity(intent);
                    }

                    //设置已读/未读
                    @Override
                    public void onSetBtnCilck(View view, int position) {
                        int id = data.get(position).getId();
                        int opened = data.get(position).getOpened();
                        changeMsgOpened(id,opened);
                    }

                    //删除消息
                    @Override
                    public void onDeleteBtnCilck(View view,final int position,final boolean isAccept) {
                        new AlertDialog.Builder(getmContext())
                                .setTitle("提示")
                                .setMessage("您确定要删除吗？")
                                .setCancelable(false)
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        int id = data.get(position).getId();
                                        if(!isAccept){//未接单，不能删除
                                            Toast.makeText(getmContext(),"未接单的消息，不能删除！",Toast.LENGTH_SHORT).show();
                                        }else{//已接单，可以删除
                                            removeMsg(id);
                                        }
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).create().show();
                    }
                });
                adapter.updateList(data);
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void changeUnReadMsgAndOpened(int id,int opened){
        if(opened == 0){
            new UpdateMessageOpenedTask(getmContext(), new OnTaskFinishedListener<Boolean>() {
                @Override
                public void onTaskFinished(Boolean data) {
                    if(data){
                        getData();
                        //sendRefreshUnReadMsgBroadcast();
                    }
                }
            },id,1).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    //通知MainActivity的底部tab，更新消息数
    public void sendRefreshUnReadMsgBroadcast(){
        Intent intent = new Intent();
        //这里不能再通知MessageFragment进行更新，否则会造成死循环，不停地发消息。
        //当从消息详情页面跳转回来后，MessageFragment会重新onCreate(),重新读取消息的状态
        intent.setAction(CALL_MAINACTIVITY_REFRESH_COUNT);
        LocalBroadcastManager.getInstance(getmContext()).sendBroadcast(intent);
    }

    public void showDialog(final int id,final int opened){
        String[] items = new String[2];
        items[0] = (opened == 0) ? getResources().getString(R.string.message_dialog_is_opend):
                getResources().getString(R.string.message_dialog_not_opend);
        items[1] = getResources().getString(R.string.message_dialog_remove);

        new AlertDialog.Builder(getmContext())
                //.setTitle()
        .setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0://标为已读/未读
                        dialog.dismiss();
                        changeMsgOpened(id,opened);
                        break;
                    case 1://移除
                        dialog.dismiss();
                        removeMsg(id);
                        break;
                }
            }
        }).create().show();
    }

    public void changeMsgOpened(int id,int opened){
        int mOpened = (opened == 0) ? 1:0;
        new UpdateMessageOpenedTask(getmContext(), new OnTaskFinishedListener<Boolean>() {
            @Override
            public void onTaskFinished(Boolean data) {
                if(data){
                    getData();
                }
            }
        },id,mOpened).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void removeMsg(int id){
        new RemoveByIdMessageTask(getmContext(), new OnTaskFinishedListener<Boolean>() {
            @Override
            public void onTaskFinished(Boolean data) {
                if(data){
                    getData();
                }
            }
        },id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @OnClick({R.id.message_top_area})
    public void onclick(View view) {
        switch (view.getId()){
            case R.id.message_top_area:
                message_top_category_Label.setText(getResources().getString(R.string.
                        message_top_category_label_open));
                List<String> list = new ArrayList<String>();
                list.add(getmContext().getResources().getString(R.string.message_all));
                list.add(getmContext().getResources().getString(R.string.message_accept));
                list.add(getmContext().getResources().getString(R.string.message_no_accept));
                showContactUsPopWin(getmContext(), "", list);
                pop.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
        }
    }

    public void showContactUsPopWin(Context context, String title, final List<String> list) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_message, null);
        //view.getBackground().setAlpha(100);
        // 创建PopupWindow对象
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        View pop_reward_view = view.findViewById(R.id.pop_reward_view);
        String content = message_top_category_Name.getText().toString();
        final MessagePopupWindowAdapter adapter = new MessagePopupWindowAdapter(context, list, content);
        listView = view.findViewById(R.id.listContent);
        listView.setAdapter(adapter);
        // 需要设置一下此参数，点击外边可消失
        pop.setBackgroundDrawable(new BitmapDrawable());
        // 设置点击窗口外边窗口消失
        pop.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        pop.setFocusable(true);
        pop_reward_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message_top_category_Label.setText(getResources().getString(R.string.
                        message_top_category_label_close));
                pop.dismiss();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                message_top_category_Name.setText(list.get(i));
                message_top_category_Label.setText(getResources().getString(R.string.
                        message_top_category_label_close));
                pop.dismiss();
                int index = 0;
                switch(i){
                    case 0:
                        index = 0;
                        break;
                    case 1:
                        index = 1;
                        break;
                    case 2:
                        index = 2;
                        break;
                }
                grepMessages(index);
            }
        });
    }

    public void grepMessages(final int index){
        new QueryMessageTask(getmContext(), new OnTaskFinishedListener<List<MessageData>>() {
            @Override
            public void onTaskFinished(final List<MessageData> datas) {
                //sendRefreshUnReadMsgBroadcast();//更新未读信息数
                data = datas;
                if(null != data && data.size() != 0){
                    switch (index){
                        case 0:
                            Config.type = 0;
                            break;
                        case 1://已接单
                            Config.type = 1;
                            break;
                        case 2://未接单
                            Config.type = 2;
                            break;
                    }
                    adapter.updateList(data);
                }
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegistBroadcastReceiver();
    }
}
