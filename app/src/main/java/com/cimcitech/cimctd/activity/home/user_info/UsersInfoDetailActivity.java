package com.cimcitech.cimctd.activity.home.user_info;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.adapter.user_info.UserInfoAdapter;
import com.cimcitech.cimctd.bean.user_info.User;
import com.cimcitech.cimctd.bean.user_info.UserInfoVo;
import com.cimcitech.cimctd.utils.DateTool;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsersInfoDetailActivity extends AppCompatActivity {
    @Bind(R.id.userId_tv)
    TextView userId_Tv;
    @Bind(R.id.create_time_tv)
    TextView create_time_Tv;
    @Bind(R.id.username_tv)
    TextView username_Tv;
    @Bind(R.id.workcode_tv)
    TextView workcode_Tv;
    @Bind(R.id.phone_tv)
    TextView phone_Tv;
    @Bind(R.id.mobile_tv)
    TextView mobile_Tv;
    @Bind(R.id.email_tv)
    TextView email_Tv;
    @Bind(R.id.unit_tv)
    TextView unit_Tv;
    @Bind(R.id.address_tv)
    TextView address_Tv;
    @Bind(R.id.status_tv)
    TextView status_Tv;

    private Handler handler = new Handler();
    private UserInfoAdapter adapter;
    private UserInfoVo userInfoVo;
    private List<User> data = new ArrayList<User>();
    private int pageNum = 1;
    private boolean isLoading;
    private boolean myData = true;
    private String TAG = "userinfolog";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_info_detail);
        ButterKnife.bind(this);
        user = (User) getIntent().getSerializableExtra("user");
        initViewData();
    }

    public void initViewData() {
        userId_Tv.setText(user.getUserId() + "");//这里要注意将user.getUserId()转为为String型
        create_time_Tv.setText(DateTool.getDateStr(user.getCreateTime()));
        username_Tv.setText(user.getUserName());
        workcode_Tv.setText(user.getEmpNo());
        phone_Tv.setText(user.getTel());
        mobile_Tv.setText(user.getMobile());
        email_Tv.setText(user.getEmail());
        unit_Tv.setText(user.getUnitName());
        address_Tv.setText(user.getAddress());
        status_Tv.setText((user.getIsState() == 1) ? "有效":"无效");
        if(user.getIsState() == 1){
            status_Tv.setTextColor(Color.GREEN);
        }else{
            status_Tv.setTextColor(Color.RED);
        }
    }

    @OnClick({R.id.back_rl, R.id.update_bt})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
            case R.id.update_bt:
                break;
        }
    }

}
