package com.cimcitech.cimctd.adapter.dispatch;

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
import com.cimcitech.cimctd.bean.dispatch.Dispatch;

import java.util.List;

/**
 * Created by cimcitech on 2017/8/1.
 */

public class DispatchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private LayoutInflater inflater;
    private List<Dispatch> data;
    private static final int TYPE_END = 2;
    private boolean isNotMoreData = false;
    private Context context;

    public DispatchAdapter(Context context, List<Dispatch> data) {
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
            View view = inflater.inflate(R.layout.dispatch_item_view, parent, false);
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
            final Dispatch item = data.get(position);
            ((ItemViewHolder) holder).custName_Tv.setText(item.getCustName() != null && !item
                    .getCustName().equals("") ? "客户：" + item.getCustName():"客户：" + "");
            ((ItemViewHolder) holder).contractNo_Tv.setText(item.getContractNo() != null && !item
                    .getContractNo().equals("") ? "合同号：" + item.getContractNo() : "合同号：" + "");
            ((ItemViewHolder) holder).isDispatch_Tv.setText(item.getChargeName() != null && !item
                    .getChargeName().equals("")?"是":"否");
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
        TextView custName_Tv,contractNo_Tv,isDispatch_Tv;

        public ItemViewHolder(View view) {
            super(view);
            custName_Tv = view.findViewById(R.id.custName_tv);
            contractNo_Tv = view.findViewById(R.id.contractNo_tv);
            isDispatch_Tv = view.findViewById(R.id.isDispatch_tv);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View view) {
            super(view);
        }
    }
}
