package com.cimcitech.cimctd.activity.home.faulthandle;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.bean.faulthandle.FaultHandle;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.widget.MyBaseActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FaultHandleDetailActivity extends MyBaseActivity {
    @Bind(R.id.productName_tv)
    TextView productName_Tv;
    @Bind(R.id.partName_tv)
    TextView partName_Tv;
    @Bind(R.id.placeName_tv)
    TextView placeName_Tv;
    @Bind(R.id.faultType_tv)
    TextView faultType_Tv;
    @Bind(R.id.drive_tv)
    TextView drive_Tv;
    @Bind(R.id.status_tv)
    TextView status_Tv;
    @Bind(R.id.approach_tv)
    TextView approach_Tv;
    @Bind(R.id.approachIcon_iv)
    ImageView approachIcon_Iv;

    private List<FaultHandle> data = new ArrayList<FaultHandle>();
    private boolean isLoading;
    private String TAG = "faultHandleDetailLog";
    private FaultHandle faultHandle;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_handle_detail);
        ButterKnife.bind(this);
        mLoading.show();
        faultHandle = (FaultHandle) getIntent().getSerializableExtra("faultHandle");
        initView();
        getImage();
    }

    public void initView(){
        productName_Tv.setText(faultHandle.getProductName());//所属产品
        partName_Tv.setText(faultHandle.getPartName());//所属部件
        placeName_Tv.setText(faultHandle.getPlaceName());//位置
        faultType_Tv.setText(faultHandle.getFaultType());//故障类型
        drive_Tv.setText(faultHandle.getDrive());//器件
        status_Tv.setText(faultHandle.getStatus() == 0 ? "禁用":"启用");//状态
        approach_Tv.setText(faultHandle.getApproach());//处理办法
        //approachIcon_Iv.setImageResource(faultHandle.getProductName());

    }

    public void getImage(){
        //String json = new Gson().toJson(new FaultHandleImageReq(faultHandle.getIconUrl()));
        if(null != faultHandle.getIconUrl()){
            options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.loading)
                    .showImageForEmptyUri(R.mipmap.loading).showImageOnFail(R.mipmap.loading)
                    .cacheInMemory()
                    .cacheOnDisc()
                    .bitmapConfig(Bitmap.Config.ALPHA_8)
                    .build();
            imageLoader.init(ImageLoaderConfiguration.createDefault(this));
            url = Config.IMAGE_DOWNLOAD_URL + faultHandle.getIconUrl();
            imageLoader.displayImage(url,approachIcon_Iv, options);
            //加载PNG格式的图片，背景变为浅红色了，原因是因为后台强制将所有格式的图片转为jpg了
            //approachIcon_Iv.setBackgroundColor(Color.TRANSPARENT);
        }
        mLoading.dismiss();
    }


    @Override
    protected void onResume() {
        super.onResume();
        /*if (Config.isAddPerson) {
            Config.isAddPerson = false;
            updateData();
        }*/
    }

    @OnClick({R.id.back,R.id.approachIcon_iv})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.approachIcon_iv:
                if(url.length() != 0){
                    Intent intent =new Intent(FaultHandleDetailActivity.this,ShowFaultImageActivity
                            .class);
                    intent.putExtra("url",url);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(FaultHandleDetailActivity.this
                            , approachIcon_Iv, "shareNames").toBundle());
                }
                break;
        }
    }

}
