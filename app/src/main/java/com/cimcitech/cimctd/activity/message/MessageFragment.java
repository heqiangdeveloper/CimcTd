package com.cimcitech.cimctd.activity.message;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.activity.home.about.AboutActivity;
import com.cimcitech.cimctd.activity.home.contact.ContactDetailActivity;
import com.cimcitech.cimctd.activity.home.file_search.FileSearchActivity;
import com.cimcitech.cimctd.activity.home.modify_password.ModifyPasswordActivity;
import com.cimcitech.cimctd.activity.main.LoginActivity;
import com.cimcitech.cimctd.activity.main.MainActivity;
import com.cimcitech.cimctd.activity.user.DataCleanManager;
import com.cimcitech.cimctd.adapter.PopupWindowAdapter;
import com.cimcitech.cimctd.adapter.message.MessageAdapter;
import com.cimcitech.cimctd.adapter.message.MessagePopupWindowAdapter;
import com.cimcitech.cimctd.bean.ApkUpdateVo;
import com.cimcitech.cimctd.utils.ApkUpdateUtil;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.cimcitech.cimctd.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by cimcitech on 2017/7/31.
 */

public class MessageFragment extends Fragment {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.message_top_area)
    LinearLayout message_top_Area;
    @Bind(R.id.message_top_category_name)
    TextView message_top_category_Name;
    @Bind(R.id.message_top_category_label)
    TextView message_top_category_Label;
    /*@Bind(R.id.message_top_item_all_tv)
    TextView message_top_item_all_Tv;
    @Bind(R.id.message_top_item_all_label_tv)
    TextView message_top_item_all_label_Tv;
    @Bind(R.id.message_top_item_already_tv)
    TextView message_top_item_already_Tv;
    @Bind(R.id.message_top_item_already_label_tv)
    TextView message_top_item_already_label_Tv;
    @Bind(R.id.message_top_item_no_tv)
    TextView message_top_item_no_Tv;
    @Bind(R.id.message_top_item_no_label_tv)
    TextView message_top_item_no_label_Tv;
    @Bind(R.id.message_popup_menu_layout)
    LinearLayout message_popup_menu_Layout;
    @Bind(R.id.message_top_item_all_rl)
    RelativeLayout message_top_item_all_Rl;*/
    private MessageAdapter adapter;
    private List<String> data;
    private PopupWindow pop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_message, container, false);
        ButterKnife.bind(this,view);

        //message_top_item_all_Rl.callOnClick();
       // message_popup_menu_Layout.setVisibility(View.GONE);
        data = new ArrayList<>();
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("d");
        data.add("e");
        data.add("f");
        data.add("g");
        data.add("h");
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new MessageAdapter(getActivity(),data);
        mRecyclerView.setAdapter(adapter);

        //给List添加点击事件
        adapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),"you clicked: " + data.get(position),Toast
                        .LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                //do nothing
            }
        });

        return view;
    }


    @OnClick({R.id.message_top_area})
    public void onclick(View view) {
        switch (view.getId()){
            case R.id.message_top_area:
                message_top_category_Label.setText(getResources().getString(R.string.
                        message_top_category_label_open));
                List<String> list = new ArrayList<String>();
                list.add("全部");
                list.add("已接收");
                list.add("未接收");
                showContactUsPopWin(getActivity(), "", list);
                pop.showAtLocation(view, Gravity.CENTER, 0, 0);

                /*if(message_popup_menu_Layout.getVisibility() == View.GONE){//open
                    message_top_category_Label.setText(getResources().getString(R.string.
                            message_top_category_label_open));
                    message_popup_menu_Layout.setVisibility(View.VISIBLE);
                }else {//close
                    message_top_category_Label.setText(getResources().getString(R.string
                            .message_top_category_label_close));
                    message_popup_menu_Layout.setVisibility(View.GONE);
                }*/
                break;
            /*case R.id.message_top_item_all_rl:
                //setColorAndVisible(message_top_item_all_Tv,message_top_item_all_label_Tv);
               // hidePopupWindow(message_top_item_all_Tv);
                break;
            case R.id.message_top_item_already_rl:
                //setColorAndVisible(message_top_item_already_Tv,message_top_item_already_label_Tv);
                //hidePopupWindow(message_top_item_already_Tv);
                break;
            case R.id.message_top_item_no_rl:
                //setColorAndVisible(message_top_item_no_Tv,message_top_item_no_label_Tv);
               // hidePopupWindow(message_top_item_no_Tv);
                break;*/
        }
    }

    public void showContactUsPopWin(Context context, String title, final List<String> list) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_message, null);
        //view.getBackground().setAlpha(100);
        // 创建PopupWindow对象
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        View pop_reward_view = view.findViewById(R.id.pop_reward_view);
        TextView name_tv = view.findViewById(R.id.pop_item_name_tv);
        TextView label_tv = view.findViewById(R.id.pop_item_label_tv);
        String content = message_top_category_Name.getText().toString();
        final MessagePopupWindowAdapter adapter = new MessagePopupWindowAdapter(context, list, content);
        ListView listView = view.findViewById(R.id.listContent);
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
            }
        });
    }

    /*public void hidePopupWindow(TextView tv){
        if(message_popup_menu_Layout.getVisibility() == View.VISIBLE){
            message_popup_menu_Layout.setVisibility(View.GONE);
            message_top_category_Label.setText(getResources().getString(R.string
                    .message_top_category_label_close));
            message_top_category_Name.setText(tv.getText());
        }
    }*/

    /*public void setColorAndVisible(String content){
        message_top_item_all_Tv.setTextColor(getResources().getColor(R.color.black));
        message_top_item_already_Tv.setTextColor(getResources().getColor(R.color.black));
        message_top_item_no_Tv.setTextColor(getResources().getColor(R.color.black));

        message_top_item_all_label_Tv.setVisibility(View.INVISIBLE);
        message_top_item_already_label_Tv.setVisibility(View.INVISIBLE);
        message_top_item_no_label_Tv.setVisibility(View.INVISIBLE);

        nameTv.setTextColor(getResources().getColor(R.color.colorPrimary));
        labelTv.setVisibility(View.VISIBLE);
    }*/
}
