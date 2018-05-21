package com.cimcitech.cimctd.adapter.announce;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.bean.announce.AnnounceVo;
import com.cimcitech.cimctd.utils.DateTool;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by cimcitech on 2017/8/1.
 */

public class AnnounceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<AnnounceVo.DataBean.ListBean> data;
    private LayoutInflater inflater;
    private static final int TYPE_END = 2;
    private boolean isNotMoreData = false;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;

    public AnnounceAdapter(Context context, List<AnnounceVo.DataBean.ListBean> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher).showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory()
                .cacheOnDisc()
                .bitmapConfig(Bitmap.Config.ALPHA_8)
                .build();
    }

    public void setNotMoreData(boolean b) {
        this.isNotMoreData = b;
    }

    public boolean isNotMoreData() {
        return isNotMoreData;
    }

    public List getAll() {
        return data;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClickListener(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.announce_home_item_view, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = inflater.inflate(R.layout.recycler_foot_view, parent, false);
            return new FootViewHolder(view);
        } else if (viewType == TYPE_END) {
            View view = inflater.inflate(R.layout.recycler_end_view, parent, false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View view) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                        return false;
                    }
                });
            }
            final AnnounceVo.DataBean.ListBean item = data.get(position);
            ((ItemViewHolder) holder).titleTv.setText(item.getAnntitle());
            ((ItemViewHolder) holder).timeTv.setText(DateTool.getDateStr(item.getCreatedate()));
            ((ItemViewHolder) holder).contentTv.setText(item.getAnnouncedesc());
            imageLoader.displayImage(item.getAnntitlepic(), ((ItemViewHolder) holder).anntitlepicTv, options);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            if (isNotMoreData())
                return TYPE_END;
            else
                return TYPE_FOOTER;
        } else
            return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size() + 1;
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView anntitlepicTv;
        TextView titleTv;
        TextView timeTv;
        TextView contentTv;

        public ItemViewHolder(View view) {
            super(view);
            titleTv = view.findViewById(R.id.title_tv);
            timeTv = view.findViewById(R.id.time_tv);
            contentTv = view.findViewById(R.id.content_tv);
            anntitlepicTv = view.findViewById(R.id.anntitlepic_tv);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View view) {
            super(view);
        }
    }
}
