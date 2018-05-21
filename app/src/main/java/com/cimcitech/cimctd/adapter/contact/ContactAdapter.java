package com.cimcitech.cimctd.adapter.contact;

/**
 * Created by cimcitech on 2017/8/2.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.bean.contact.Contact;

import java.util.List;

/**
 * Created by cimcitech on 2017/8/1.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<Contact> data;
    private LayoutInflater inflater;
    private static final int TYPE_END = 2;
    private boolean isNotMoreData = false;
    private Context context;

    public ContactAdapter(Context context, List<Contact> data) {
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

        void onTelClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.contact_item_view2, parent, false);
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
            final Contact item = data.get(position);
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

                //点击电话拨号
                ((ItemViewHolder) holder).tel_Tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(context,"clicked!",Toast.LENGTH_SHORT).show();
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onTelClick(((ItemViewHolder) holder).tel_Tv, position);
                    }
                });
            }

            ((ItemViewHolder) holder).contactName_Tv.setText(item.getContactName() != null && !item
                    .getContactName().equals("") ? "" + item.getContactName():"" + "");
            ((ItemViewHolder) holder).custName_Tv.setText(item.getCustName() != null && !item
                    .getCustName().equals("") ? "" + item.getCustName() : "" + "");
            /*((ItemViewHolder) holder).isPass_tv.setText(item.getIsPass() == 1 ? "已确认" : "未确认");
            ((ItemViewHolder) holder).isPass_tv.setTextColor(item.getIsPass() == 1 ?
                    Color.rgb(0,255,0):Color.rgb(255,0,0));
            ((ItemViewHolder) holder).isPass_tv.setBackgroundColor(item.getIsPass() == 1 ?
                    Color.rgb(72,209,204):Color.rgb(252,157,154));//浅红色,浅绿色*/
            ((ItemViewHolder) holder).tel_Tv.setText(item.getTel() != null && !item
                    .getTel().equals("") ? "" + item.getTel() : "");//电话

            ((ItemViewHolder) holder).isState_Tv.setText(item.getIsState() == 1?
                    "禁用":"启用");
            ((ItemViewHolder) holder).isState_Tv.setBackgroundResource(item.getIsState() == 1?
                    R.mipmap.red_circle:R.mipmap.green_circle);
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
        TextView contactName_Tv,custName_Tv,isState_Tv,tel_Tv;

        public ItemViewHolder(View view) {
            super(view);
            contactName_Tv = view.findViewById(R.id.contactName_tv);
            custName_Tv = view.findViewById(R.id.custName_tv);
            isState_Tv = view.findViewById(R.id.isState_tv);
            tel_Tv = view.findViewById(R.id.tel_tv);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View view) {
            super(view);
        }
    }
}
