package com.cimcitech.cimctd.activity.home.faulthandle;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.adapter.faulthandle.FaultHandleAdapter;
import com.cimcitech.cimctd.bean.faulthandle.FaultHandle;
import com.cimcitech.cimctd.bean.faulthandle.FaultHandleImageReq;
import com.cimcitech.cimctd.bean.faulthandle.FaultHandleReq;
import com.cimcitech.cimctd.bean.faulthandle.FaultHandleVo;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.cimcitech.cimctd.widget.BaseActivity;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.DiscCacheUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

import static com.cimcitech.cimctd.R.id.webview;

public class FaultHandleDetailActivity extends BaseActivity {
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
        productName_Tv.setText(faultHandle.getProductName());
        partName_Tv.setText(faultHandle.getPartName());
        placeName_Tv.setText(faultHandle.getPlaceName());
        faultType_Tv.setText(faultHandle.getFaultType());
        drive_Tv.setText(faultHandle.getDrive());
        status_Tv.setText(faultHandle.getStatus() == 0 ? "禁用":"启用");
        approach_Tv.setText(faultHandle.getApproach());
        //approachIcon_Iv.setImageResource(faultHandle.getProductName());

    }

    public void getImage(){
        //String json = new Gson().toJson(new FaultHandleImageReq(faultHandle.getIconUrl()));
        if(null != faultHandle.getIconUrl()){
            options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.ic_launcher)
                    .showImageForEmptyUri(R.mipmap.ic_launcher).showImageOnFail(R.mipmap.ic_launcher)
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
