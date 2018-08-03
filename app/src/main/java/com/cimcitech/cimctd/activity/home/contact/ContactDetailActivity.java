package com.cimcitech.cimctd.activity.home.contact;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.adapter.PopupWindowAdapter;
import com.cimcitech.cimctd.bean.contact.Contact;
import com.cimcitech.cimctd.bean.contact.ContactAddReq;
import com.cimcitech.cimctd.bean.contact.ContactEnumVo;
import com.cimcitech.cimctd.bean.contact.ContactModifyReq;
import com.cimcitech.cimctd.bean.contact.Customer;
import com.cimcitech.cimctd.bean.file_search.MyFile;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.DateTool;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.cimcitech.cimctd.widget.MyBaseActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;

public class ContactDetailActivity extends MyBaseActivity {
    @Bind(R.id.createTime_tv)
    TextView createTime_Tv;
    @Bind(R.id.contactName_et)
    EditText contactName_Et;
    @Bind(R.id.custName_tv)
    TextView custName_Tv;
    @Bind(R.id.deptName_et)
    EditText deptName_Et;
    @Bind(R.id.duties_et)
    EditText duties_Et;
    @Bind(R.id.tel_et)
    EditText tel_Et;
    @Bind(R.id.email_et)
    EditText email_Et;
    @Bind(R.id.fax_et)
    EditText fax_Et;
    @Bind(R.id.birthday_tv)
    TextView birthday_Tv;
    @Bind(R.id.internalRelaValue_tv)
    TextView internalRelaValue_Tv;
    @Bind(R.id.relationLevelValue_tv)
    TextView relationLevelValue_Tv;
    @Bind(R.id.hobbies_et)
    EditText hobbies_Et;
    @Bind(R.id.sex_tv)
    TextView sex_Tv;
    @Bind(R.id.addr1_et)
    EditText addr1_Et;
    @Bind(R.id.addr2_et)
    EditText addr2_Et;
    @Bind(R.id.remark_et)
    EditText remark_Et;
    @Bind(R.id.isPass_tv)
    TextView isPass_Tv;
    @Bind(R.id.isState_tv)
    TextView isState_Tv;
    @Bind(R.id.popup_menu_layout)
    LinearLayout popup_menu;
    @Bind(R.id.contact_more_menu_add)
    TextView contact_more_menu_add_Tv;
    @Bind(R.id.contact_more_menu_save)
    TextView contact_more_menu_save_Tv;
    @Bind(R.id.contact_more_menu_delete)
    TextView contact_more_menu_delete_Tv;
    @Bind(R.id.contact_more_menu_invalid)
    TextView contact_more_menu_invalid_Tv;
    @Bind(R.id.contact_more_menu_finish)
    TextView contact_more_menu_finish_Tv;
    @Bind(R.id.titleName_tv)
    TextView titleName_Tv;
    @Bind(R.id.custName_red_tv)
    TextView custName_red_Tv;

    private Handler handler = new Handler();
    private List<MyFile> data = new ArrayList<MyFile>();
    private int pageNum = 1;
    private boolean isLoading;
    private boolean myData = true;
    private String TAG = "fileSearchlog";
    private Contact contact;
    private Customer customer;
    private PopupWindow pop;
    private int intValue = 0;
    private List list;
    private final static int DATE_DIALOG = 1;
    private ContactEnumVo contactEnumVo;
    private final static int SEXVALUE = 1;
    private final static int ISPASSVALUE = 2;
    private final static int ISSTATEVALUE = 3;
    private final static int INTERNALRELAVALUE = 4;
    private final static int RELATIONLEVELVALUE = 5;
    private int mYear,mMonth,mDay;
    private Map<String,String> NGMap = null;
    private Map<String,String> RLMap = null;
    private boolean isAdd;
    public static String REFRESHCONTACTDATA = "com.cimcitech.cimctd.refresh.contact";
    private final int REQUESTCODE = 1;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail_66);
        ButterKnife.bind(this);
        isAdd = getIntent().getBooleanExtra("isAdd",true);
        if(isAdd){//新增
            //lettersCustomer = (LettersCustomer) getIntent().getSerializableExtra
            //        ("lettersCustomer");
            custName_red_Tv.setVisibility(View.VISIBLE);
            custName_Tv.setClickable(true);
            titleName_Tv.setText("添加联系人");
            contact_more_menu_add_Tv.setVisibility(View.VISIBLE);
            contact_more_menu_save_Tv.setVisibility(View.GONE);
            contact_more_menu_invalid_Tv.setVisibility(View.GONE);
            contact_more_menu_delete_Tv.setVisibility(View.GONE);
        }else {
            custName_red_Tv.setVisibility(View.VISIBLE);
            custName_Tv.setClickable(false);
            contact = (Contact)getIntent().getSerializableExtra("LettersContact");
            titleName_Tv.setText("联系人信息详情");
            contact_more_menu_add_Tv.setVisibility(View.GONE);
            contact_more_menu_save_Tv.setVisibility(View.VISIBLE);
            contact_more_menu_invalid_Tv.setVisibility(View.VISIBLE);
            contact_more_menu_delete_Tv.setVisibility(View.VISIBLE);
        }
        contact_more_menu_finish_Tv.setVisibility(View.GONE);
        mLoading.show();
        getEnumData();//获取下拉数据
        initViewData();

        //初始化日历选择器
        if(birthday_Tv.getText().toString().trim().length() == 0){
            final Calendar ca = Calendar.getInstance();
            mYear = ca.get(Calendar.YEAR);
            mMonth = ca.get(Calendar.MONTH);
            mDay = ca.get(Calendar.DAY_OF_MONTH);
        }else {
            String dateStr = birthday_Tv.getText().toString().trim();
            String [] dateStrs = dateStr.split("-");
            mYear = Integer.parseInt(dateStrs[0]);
            mMonth = Integer.parseInt(dateStrs[1]) - 1;
            mDay = Integer.parseInt(dateStrs[2]);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == REQUESTCODE){
            //注意不能使用下面这段代码，这是接收startActivity()方法的代码
            //lettersCustomer = (LettersCustomer) getIntent().getSerializableExtra
            //        ("lettersCustomer");
            customer = (Customer)data.getSerializableExtra("lettersCustomer");
            custName_Tv.setText(customer.getCustName());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if (Config.isAddPerson) {
            Config.isAddPerson = false;
            updateData();
        }*/
    }

    public void initViewData() {
        if(!isAdd){
            createTime_Tv.setText(contact.getCreateTime() != null?DateTool.getDateStr(contact
                    .getCreateTime()):"");
            contactName_Et.setText(contact.getContactName());
            custName_Tv.setText(contact.getCustName());
            deptName_Et.setText(contact.getDeptName());
            duties_Et.setText(contact.getDuties());
            tel_Et.setText(contact.getTel());
            email_Et.setText(contact.getEmail());
            fax_Et.setText(contact.getFax());
            birthday_Tv.setText(contact.getBirthday() != null?DateTool.getDateStr(contact.getBirthday()):"");
            internalRelaValue_Tv.setText(contact.getInternalRelaValue());
            relationLevelValue_Tv.setText(contact.getRelationLevelValue());
            hobbies_Et.setText(contact.getHobbies());
            sex_Tv.setText(contact.getSex().equals("1")?"男":"女");
            addr1_Et.setText(contact.getAddr1());
            addr2_Et.setText(contact.getAddr2());
            remark_Et.setText(contact.getRemark());
            isPass_Tv.setText(contact.getIsPass() == 1?"是":"否");//1：确认，0：未确认
            isState_Tv.setText(contact.getIsState() == 1?"禁用":"启用");//1:禁用，0：启用
        }else{
            createTime_Tv.setText(DateTool.getDateStr(System.currentTimeMillis()));
            //custName_Tv.setText(lettersCustomer.getCustName());

            sex_Tv.setText("男");
            isState_Tv.setText("启用");
            isPass_Tv.setText("是");
        }
    }

    @OnClick({R.id.back,R.id.sex_tv,R.id.isPass_tv,R.id.isState_tv,R.id.internalRelaValue_tv,R.id
            .relationLevelValue_tv,R.id.birthday_tv,R.id.more_tv,R.id.contact_more_menu_delete,
            R.id.contact_more_menu_invalid,R.id.contact_more_menu_save,R.id.contact_more_menu_add,
            R.id.custName_tv})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.custName_tv:
                startActivityForResult(new Intent(ContactDetailActivity.this,CustomerShowActivity
                        .class),REQUESTCODE);
                break;
            case R.id.back:
                startActivity(new Intent(ContactDetailActivity.this,ContactActivity.class));
                //finish();
                break;
            case R.id.sex_tv://性别
                intValue = SEXVALUE;
                list = new ArrayList<>();
                list.add("男");
                list.add("女");
                showContactUsPopWin(ContactDetailActivity.this, "选择性别", list);
                pop.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
            case R.id.isPass_tv://确认
                intValue = ISPASSVALUE;
                list = new ArrayList<>();
                list.add("是");
                list.add("否");
                showContactUsPopWin(ContactDetailActivity.this, "选择是否确认", list);
                pop.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
            case R.id.isState_tv://状态
                intValue = ISSTATEVALUE;
                list = new ArrayList<>();
                list.add("启用");
                list.add("禁用");
                showContactUsPopWin(ContactDetailActivity.this, "选择状态", list);
                pop.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
            case R.id.internalRelaValue_tv://内部关系
                intValue = INTERNALRELAVALUE;
                list = new ArrayList<>();
                if (contactEnumVo != null) {
                    if(contactEnumVo.isSuccess()){
                        for (int i = 0; i < contactEnumVo.getData().getNG().size(); i++) {
                            list.add(contactEnumVo.getData().getNG().get(i).getCodeValue());
                        }
                    }
                }
                showContactUsPopWin(ContactDetailActivity.this, "选择内部关系", list);
                pop.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
            case R.id.relationLevelValue_tv://关系级别
                intValue = RELATIONLEVELVALUE;
                list = new ArrayList<>();
                if (contactEnumVo != null) {
                    if(contactEnumVo.isSuccess()){
                        for (int i = 0; i < contactEnumVo.getData().getRL().size(); i++) {
                            list.add(contactEnumVo.getData().getRL().get(i).getCodeValue());
                        }
                    }
                }
                showContactUsPopWin(ContactDetailActivity.this, "选择关系级别", list);
                pop.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
            case R.id.birthday_tv:
                showDialog(DATE_DIALOG);
                break;
            case R.id.more_tv://更多
                showPopupMenu();
                break;
            case R.id.contact_more_menu_add:
                hidePopupWindow();
                new AlertDialog.Builder(ContactDetailActivity.this)
                        //.setTitle("提示")
                        .setMessage("是否添加该联系人？")
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(!checkInput())  return;
                                mCommittingDialog.show();
                                commitData();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
                break;
            case R.id.contact_more_menu_save://保存修改
                hidePopupWindow();
                new AlertDialog.Builder(ContactDetailActivity.this)
                        //.setTitle("提示")
                        .setMessage("是否保存对该联系人的修改？")
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(!checkInput())  return;
                                mCommittingDialog.show();
                                saveData();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
                break;
            case R.id.contact_more_menu_delete://删除
                hidePopupWindow();
                new AlertDialog.Builder(ContactDetailActivity.this)
                        //.setTitle("提示")
                        .setMessage("是否删除该联系人？")
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mCommittingDialog.show();
                                deleteData();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
                break;
            case R.id.contact_more_menu_invalid://作废
                hidePopupWindow();
                new AlertDialog.Builder(ContactDetailActivity.this)
                        //.setTitle("提示")
                        .setMessage("是否确定将该联系人作废？")
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mCommittingDialog.show();
                                invalidData();
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

    public void sendRefreshContactBroadcast(){
        Intent intent = new Intent();
        intent.setAction(REFRESHCONTACTDATA);
        LocalBroadcastManager.getInstance(ContactDetailActivity.this).sendBroadcast(intent);
    }

    public void showPopupMenu(){
        popup_menu.setVisibility(View.VISIBLE);
    }

    public void hidePopupWindow(){
        if(popup_menu.getVisibility() == View.VISIBLE){
            popup_menu.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) ev.getX();
            int y = (int) ev.getY();

            if (null != popup_menu && popup_menu.getVisibility() == View.VISIBLE) {
                Rect hitRect = new Rect();
                popup_menu.getGlobalVisibleRect(hitRect);
                if (!hitRect.contains(x, y)) {
                    popup_menu.setVisibility(View.GONE);
                    return true;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean checkInput(){
        if(contactName_Et.getText().toString().trim().length() == 0){
            Toast.makeText(ContactDetailActivity.this,"请填写姓名",Toast.LENGTH_SHORT).show();
            return false;
        }
        /*if(custName_Tv.getText().toString().trim().length() == 0){
            Toast.makeText(ContactDetailActivity.this,"请填写所属客户",Toast.LENGTH_SHORT).show();
            return false;
        }*/
        if(addr1_Et.getText().toString().trim().length() == 0){
            Toast.makeText(ContactDetailActivity.this,"请填写地址1",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    /*******时间空间start********/

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        String dateStr = (new StringBuffer()
                .append(mYear).append("-")
                .append(mMonth + 1).append("-")
                .append(mDay).append(""))
                .toString();
        SimpleDateFormat myForamt = new SimpleDateFormat("yyyy-MM-dd");
        try{
            //将2017-3-4类型的格式转换为标准格式2017-03-04
            dateStr = myForamt.format(myForamt.parse(dateStr));
        }catch (ParseException e){
            e.printStackTrace();
        }
        birthday_Tv.setText(dateStr);
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            display();
        }
    };

    /*******时间空间end********/

    public void showContactUsPopWin(Context context, String title, final List<String> list) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // 引入窗口配置文件
        View view = inflater.inflate(R.layout.dialog_add_client_view, null);
        view.getBackground().setAlpha(100);
        // 创建PopupWindow对象
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        View pop_reward_view = view.findViewById(R.id.pop_reward_view);
        TextView title_tv = view.findViewById(R.id.title_tv);
        title_tv.setText(title);
        final PopupWindowAdapter adapter = new PopupWindowAdapter(context, list);
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
                pop.dismiss();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*if (intValue == 0) {//签到地址
                    Poi poi = pois.get(i);
                    locationTv.setText(poi.getName());
                    pop.dismiss();
                }*/
                switch (intValue){
                    case SEXVALUE:
                        sex_Tv.setText(list.get(i));
                        break;
                    case ISPASSVALUE:
                        isPass_Tv.setText(list.get(i));
                        break;
                    case ISSTATEVALUE:
                        isState_Tv.setText(list.get(i));
                        break;
                    case INTERNALRELAVALUE:
                        internalRelaValue_Tv.setText(list.get(i));
                        break;
                    case RELATIONLEVELVALUE:
                        relationLevelValue_Tv.setText(list.get(i));
                        break;
                }
                pop.dismiss();
            }
        });
    }

    public void getEnumData(){
        String param = "RL,NG";
        OkHttpUtils
                .get()
                .url(Config.GET_POPWIN_DATA_URL)
                .addParams("codeTypes",param)
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
                                contactEnumVo = GjsonUtil.parseJsonWithGson(response, ContactEnumVo.class);
                                NGMap = new HashMap<String, String>();
                                RLMap = new HashMap<String, String>();
                                if(null != contactEnumVo){
                                    //内部关系
                                    for(int i = 0; i < contactEnumVo.getData().getNG().size(); i++){
                                        NGMap.put(contactEnumVo.getData().getNG().get(i)
                                                .getCodeValue(),contactEnumVo.getData().getNG().get(i)
                                                .getCodeId());
                                    }
                                    //关系级别
                                    for(int i = 0; i < contactEnumVo.getData().getRL().size(); i++){
                                        RLMap.put(contactEnumVo.getData().getRL().get(i)
                                                .getCodeValue(),contactEnumVo.getData().getRL().get(i)
                                                .getCodeId());
                                    }
                                }
                                Log.d("responde","");
                            }
                        });
    }

    //添加联系人
    public void commitData() {
        Long contactId = null;
        Long custId = customer.getCustId();
        String custName = customer.getCustName();
        String contactName = contactName_Et.getText().toString().trim();
        String sex = sex_Tv.getText().toString().trim();
        String deptName = deptName_Et.getText().toString().trim();
        String duties = duties_Et.getText().toString().trim();
        String tel = tel_Et.getText().toString().trim();
        String email = email_Et.getText().toString().trim();
        String fax = fax_Et.getText().toString().trim();
        String hobbies = hobbies_Et.getText().toString().trim();
        String birthdayStr = birthday_Tv.getText().toString().trim();
        Long birthday;
        if(birthdayStr.length() != 0 ){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date =null;
            try{
                date = (Date) sdf.parse(birthdayStr);
            }catch (ParseException e){
                e.printStackTrace();
            }
            birthday = date.getTime();
        }else{
            birthday = null;
        }
        String addr1 = addr1_Et.getText().toString().trim();
        String addr2 = addr2_Et.getText().toString().trim();
        int isPass = isPass_Tv.getText().toString().trim().equals("是")?1:0;
        //Long creater = Config.userId;
        //String createName = Config.userName;
        sp = getSharedPreferences(Config.KEY_LOGIN_AUTO, MODE_PRIVATE);
        Long creater = sp.getLong("userId",0);
        String createName = sp.getString("user_name","");
        Long createTime = System.currentTimeMillis();
        Long updater = creater;
        String updateName = createName;
        Long updateTime = createTime;
        String internalRelaValue = internalRelaValue_Tv.getText().toString().trim();
        String internalRela = getInternalRela(internalRelaValue);
        int isState = isState_Tv.getText().toString().trim().equals("禁用")?1:0;
        String relationLevelValue = relationLevelValue_Tv.getText().toString().trim();
        String relationLevel = getInternalRela(relationLevelValue);
        String remark = remark_Et.getText().toString().trim();

        String json = new Gson().toJson(new ContactAddReq(addr1,
                addr2,
                birthday,
                contactName,
                createName,
                createTime,
                creater,
                custName,
                deptName,
                duties,
                email,
                fax,
                hobbies,
                internalRela,
                internalRelaValue,
                isPass,
                isState,
                relationLevel,
                remark,
                sex,
                tel,
                updateName,
                updateTime,
                updater,
                custId,
                contactId));
        OkHttpUtils
                .postString()
                .url(Config.ADD_CONTACT_URL)
                //.addHeader("checkTokenKey", Config.loginback.getToken())
                //.addHeader("sessionKey", Config.loginback.getUserId() + "")
                .content(json)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                if(mCommittingDialog.isShowing()) mCommittingDialog.dismiss();
                                Toast.makeText(ContactDetailActivity.this,"添加联系人失败，请检查网络", Toast
                                        .LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                if(mCommittingDialog.isShowing()) mCommittingDialog.dismiss();
                                try{
                                    JSONObject json = new JSONObject(response);
                                    if (json.getBoolean("success")) {
                                        Toast.makeText(ContactDetailActivity.this, "添加联系人成功", Toast
                                                .LENGTH_SHORT).show();
                                        sendRefreshContactBroadcast();
                                        finish();
                                    }else{
                                        Toast.makeText(ContactDetailActivity.this, json
                                                .getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }catch (JSONException e){
                                    Toast.makeText(ContactDetailActivity.this, "添加联系人失败", Toast
                                            .LENGTH_SHORT).show();
                                }
                            }
                        });
    }

    //修改联系人
    public void saveData(){
        Long contactId = contact.getContactId();
        Long custId = contact.getCustId();
        String custName = custName_Tv.getText().toString().trim();
        String contactName = contactName_Et.getText().toString().trim();
        String sex = sex_Tv.getText().toString().trim().equals("男")?"1":"0";
        String deptName = deptName_Et.getText().toString().trim();
        String duties = duties_Et.getText().toString().trim();
        String tel = tel_Et.getText().toString().trim();
        String email = email_Et.getText().toString().trim();
        String fax = fax_Et.getText().toString().trim();

        String hobbies = hobbies_Et.getText().toString().trim();
        String birthdayStr = birthday_Tv.getText().toString().trim();
        Long birthday = null;
        if(birthdayStr.length() != 0){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date =null;
            try{
                date = (Date) sdf.parse(birthdayStr);
            }catch (ParseException e){
                e.printStackTrace();
            }
            birthday = date.getTime();
        }
        String addr1 = addr1_Et.getText().toString().trim();
        String addr2 = addr2_Et.getText().toString().trim();
        int isPass = isPass_Tv.getText().toString().trim().equals("是")?1:0;
        Long creater = contact.getCreater();
        String createName = contact.getCreateName();
        Long createTime = contact.getCreateTime();
        //Long updater = Config.userId;
        //String updateName = Config.realName;
        sp = getSharedPreferences(Config.KEY_LOGIN_AUTO, MODE_PRIVATE);
        Long updater = sp.getLong("userId",0);
        String updateName = sp.getString("realName","");
        Long updateTime = System.currentTimeMillis();
        String internalRelaValue = internalRelaValue_Tv.getText().toString().trim();//内部关系
        String internalRela = getInternalRela(internalRelaValue);
        int isState = isState_Tv.getText().toString().trim().equals("禁用")?1:0;
        String relationLevelValue = relationLevelValue_Tv.getText().toString().trim();//关系级别
        String relationLevel = getRelationLevel(relationLevelValue);
        String remark = remark_Et.getText().toString().trim();

        String json = new Gson().toJson(new ContactModifyReq(addr1,
                addr2,
                birthday,
                contactName,
                createName,
                createTime,
                creater,
                custName,
                deptName,
                duties,
                email,
                fax,
                hobbies,
                internalRela,
                internalRelaValue,
                isPass,
                isState,
                relationLevel,
                remark,
                sex,
                tel,
                updateName,
                updateTime,
                updater,
                custId,
                contactId
                ));
        Log.d(TAG,"json is: " + json);
        OkHttpUtils
                .postString()
                .url(Config.UPDATE_CONTACT_URL)
                //.addParams("codeTypes",param)
                //.addHeader("checkTokenKey", Config.loginback.getToken())
                //.addHeader("sessionKey", Config.loginback.getUserId() + "")
                .content(json)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                if(mCommittingDialog.isShowing()) mCommittingDialog.dismiss();
                                Toast.makeText(ContactDetailActivity.this, "网络错误", Toast
                                        .LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                if(mCommittingDialog.isShowing()) mCommittingDialog.dismiss();
                                try{
                                    JSONObject json = new JSONObject(response);
                                    if (json.getBoolean("success")) {
                                        Toast.makeText(ContactDetailActivity.this, "修改成功", Toast
                                                .LENGTH_SHORT).show();
                                        sendRefreshContactBroadcast();
                                        finish();
                                    }else{
                                        Toast.makeText(ContactDetailActivity.this, json
                                                .getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }catch (JSONException e){
                                    Toast.makeText(ContactDetailActivity.this, "修改失败", Toast
                                            .LENGTH_SHORT).show();
                                }
                            }
                        });
    }

    //关系级别：很好
    public String  getRelationLevel(String relationLevelValue){
        return RLMap.get(relationLevelValue);
    }

    //内部关系：维护部门
    public String  getInternalRela(String internalRela){
        return NGMap.get(internalRela);
    }

    //作废
    public void invalidData(){
        Long contactId = contact.getContactId();
        OkHttpUtils
                .get()
                .url(Config.UPDATE_CONTACT_STATE_URL)
                .addParams("contactId",contactId + "")
                //.addHeader("checkTokenKey", Config.loginback.getToken())
                //.addHeader("sessionKey", Config.loginback.getUserId() + "")
                //.content(json)
                //.mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                if(mCommittingDialog.isShowing()) mCommittingDialog.dismiss();
                                Toast.makeText(ContactDetailActivity.this, "网络错误", Toast
                                        .LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                if(mCommittingDialog.isShowing()) mCommittingDialog.dismiss();
                                try{
                                    JSONObject json = new JSONObject(response);
                                    if (json.getBoolean("success")) {
                                        Toast.makeText(ContactDetailActivity.this, "作废成功", Toast
                                                .LENGTH_SHORT).show();
                                        sendRefreshContactBroadcast();
                                        finish();
                                    }else{
                                        Toast.makeText(ContactDetailActivity.this, json
                                                .getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }catch (JSONException e){
                                    Toast.makeText(ContactDetailActivity.this, "作废失败", Toast
                                            .LENGTH_SHORT).show();
                                }
                            }
                        });
    }

    //删除联系人
    public void deleteData(){
        Long contactId = contact.getContactId();
        //String json = new Gson().toJson(new ContactDeleteReq(contactId));
        //注意：这里采用post方式，传递的是Params，而不是json,即POST  http://localhost:8080/tdsh/contact/deleteContact?contactId=1
        OkHttpUtils
                .post()
                .url(Config.DELETE_CONTACT_URL)
                .addParams("contactId",contactId + "")
                //.addHeader("checkTokenKey", Config.loginback.getToken())
                //.addHeader("sessionKey", Config.loginback.getUserId() + "")
                //.content(json)
                //.mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                if(mCommittingDialog.isShowing()) mCommittingDialog.dismiss();
                                Toast.makeText(ContactDetailActivity.this, "网络错误", Toast
                                        .LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                if(mCommittingDialog.isShowing()) mCommittingDialog.dismiss();
                                try{
                                    JSONObject json = new JSONObject(response);
                                    if (json.getBoolean("success")) {
                                        Toast.makeText(ContactDetailActivity.this, "删除成功", Toast
                                                .LENGTH_SHORT).show();
                                        sendRefreshContactBroadcast();
                                        finish();
                                    }else{
                                        Toast.makeText(ContactDetailActivity.this, json
                                                .getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                }catch (JSONException e){
                                    Toast.makeText(ContactDetailActivity.this, "删除失败", Toast
                                            .LENGTH_SHORT).show();
                                }
                            }
                        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(popup_menu.getVisibility() == View.VISIBLE){
            popup_menu.setVisibility(View.GONE);
        }else{
            finish();
        }
    }
}
