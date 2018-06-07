package com.cimcitech.cimctd.activity.home.faulthandle;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.widget.MyBaseActivity;
import com.github.chrisbanes.photoview.PhotoView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ShowFaultImageActivity extends MyBaseActivity {
    @Bind(R.id.approachIcon_pv)
    PhotoView approachIcon_Pv;

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_fault_image);
        ButterKnife.bind(this);

        String url = getIntent().getStringExtra("url");

        options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.loading)
                .showImageForEmptyUri(R.mipmap.loading).showImageOnFail(R.mipmap.loading)
                .cacheInMemory()
                .cacheOnDisc()
                .bitmapConfig(Bitmap.Config.ALPHA_8)
                .build();
        imageLoader.init(ImageLoaderConfiguration.createDefault(ShowFaultImageActivity.this));
        //String url = "http://pic2.16pic.com/00/15/80/16pic_1580359_b.jpg";
        imageLoader.displayImage(url,approachIcon_Pv, options);

        approachIcon_Pv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                //overridePendingTransition(0,R.anim.exitanim);
                //overridePendingTransition(0,0);
                onBackPressed();
            }
        });

    }

}
