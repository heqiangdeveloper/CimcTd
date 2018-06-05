package com.cimcitech.cimctd.adapter.faulthandle;

/**
 * Created by cimcitech on 2017/8/2.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.bean.contact.Contact;
import com.cimcitech.cimctd.bean.faulthandle.FaultHandle;

import java.util.List;

/**
 * Created by cimcitech on 2017/8/1.
 */

public class FaultHandleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<FaultHandle> data;
    private LayoutInflater inflater;
    private static final int TYPE_END = 2;
    private boolean isNotMoreData = false;
    private Context context;

    public FaultHandleAdapter(Context context, List<FaultHandle> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
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

        //void onStateButtonClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.fault_handle_item_view, parent, false);
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
            final FaultHandle item = data.get(position);
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

            ((ItemViewHolder) holder).partName_Tv.setText(item.getPartName() != null?
                    getSubPartName(item.getPartName()):"未知");//所属部件
            ((ItemViewHolder) holder).partName_Tv.setBackgroundResource(R.mipmap.green_circle);
            ((ItemViewHolder) holder).productName_Tv.setText(item.getProductName() != null && !item
                    .getProductName().equals("") ? "" + item.getProductName():"" + "");
            ((ItemViewHolder) holder).placeName_Tv.setText(item.getPlaceName() != null && !item
                    .getPlaceName().equals("") ? "" + item.getPlaceName() : "" + "");
            ((ItemViewHolder) holder).faultType_Tv.setText(item.getFaultType() != null && !item
                    .getFaultType().equals("") ? "" + item.getFaultType() : "" + "");
        }
    }

    public String getSubPartName(String str){
        String subStr = "";
        if(str.length() == 0){
            subStr = "未知";
        }else if(str.length() == 1){
            subStr = str;
        }else{
            subStr = str.substring(0,2);
        }
        return subStr;
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
        TextView productName_Tv,partName_Tv,faultType_Tv,placeName_Tv;

        public ItemViewHolder(View view) {
            super(view);
            productName_Tv = view.findViewById(R.id.productName_tv);
            partName_Tv = view.findViewById(R.id.partName_tv);
            faultType_Tv = view.findViewById(R.id.faultType_tv);
            placeName_Tv = view.findViewById(R.id.placeName_tv);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View view) {
            super(view);
        }
    }

    /**
     * 提供给Activity刷新数据
     * @param list
     */
    public void updateList(List<FaultHandle> list){
        this.data = list;
        notifyDataSetChanged();
    }
}
