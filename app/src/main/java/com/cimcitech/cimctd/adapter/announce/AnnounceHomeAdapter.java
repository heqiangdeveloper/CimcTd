package com.cimcitech.cimctd.adapter.announce;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.bean.announce.AnnounceVo;
import com.cimcitech.cimctd.utils.DateTool;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by apple on 2017/8/11.
 */

public class AnnounceHomeAdapter extends BaseAdapter {

    private List<AnnounceVo.DataBean.ListBean> data;
    private LayoutInflater inflater;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    public AnnounceHomeAdapter(Context context, List<AnnounceVo.DataBean.ListBean> data) {
        if(null != context)  inflater = LayoutInflater.from(context);//防止快速切换Fragment导致的FC
        this.data = data;
        options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher).showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory()
                .cacheOnDisc()
                .bitmapConfig(Bitmap.Config.ALPHA_8)
                .build();
    }

    public List<AnnounceVo.DataBean.ListBean> getAll() {
        return data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        AnnounceVo.DataBean.ListBean listBean = data.get(i);
        if (viewHolder == null && null != inflater) {
            view = inflater.inflate(R.layout.announce_home_item_view, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.titleTv.setText(listBean.getAnntitle());
        viewHolder.timeTv.setText(DateTool.getDateStr(listBean.getCreatedate()));
        viewHolder.contentTv.setText(listBean.getAnnouncedesc());
        imageLoader.displayImage(listBean.getAnntitlepic(), viewHolder.anntitlepicTv, options);
        return view;
    }

    class ViewHolder {
        @Bind(R.id.anntitlepic_tv)
        ImageView anntitlepicTv;
        @Bind(R.id.title_tv)
        TextView titleTv;
        @Bind(R.id.time_tv)
        TextView timeTv;
        @Bind(R.id.content_tv)
        TextView contentTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
