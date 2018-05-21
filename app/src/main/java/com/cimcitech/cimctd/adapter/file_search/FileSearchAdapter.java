package com.cimcitech.cimctd.adapter.file_search;

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
import com.cimcitech.cimctd.bean.file_search.MyFile;
import com.cimcitech.cimctd.utils.DateTool;

import java.util.List;

/**
 * Created by cimcitech on 2017/8/1.
 */

public class FileSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<MyFile> data;
    private LayoutInflater inflater;
    private static final int TYPE_END = 2;
    private boolean isNotMoreData = false;

    public FileSearchAdapter(Context context, List<MyFile> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
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
            View view = inflater.inflate(R.layout.file_search_item_view, parent, false);
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
            final MyFile item = data.get(position);
            ((ItemViewHolder) holder).contractNo_Tv.setText(item.getContractNo() != null && !item
                    .getContractNo().equals("") ? "合同号：" + item.getContractNo():"合同号：" + "");
            ((ItemViewHolder) holder).serialNum_Tv.setText(item.getSerialNum() != null && !item
                    .getSerialNum().equals("") ? "生产序列号：" + item.getSerialNum() : "生产序列号：" + "");
            ((ItemViewHolder) holder).custName_Tv.setText(item.getCustName() != null && !item
                    .getCustName().equals("") ? "客户：" + item.getCustName() : "客户：" + "" );
            ((ItemViewHolder) holder).jobNum_Tv.setText(item.getJobNum() != null && !item
                    .getJobNum().equals("") ? "工号：" + item.getJobNum():"工号：" + "");
            ((ItemViewHolder) holder).machineNum_Tv.setText(item.getMachineNum() != null && !item
                    .getMachineNum().equals("") ? "桥号：" + item.getMachineNum() : "桥号：" + "");
            ((ItemViewHolder) holder).productionDate_Tv.setText(item.getProductionDate() != null
                    ? "出厂日：" + DateTool.getDateStr(item.getProductionDate()) : "出厂日：" + "");
            ((ItemViewHolder) holder).mainDate_Tv.setText(item.getMainDate() != null
                    ? "维保起算日：" + DateTool.getDateStr(item.getMainDate()) : "维保起算日：" + "");
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
        TextView contractNo_Tv,serialNum_Tv,custName_Tv,jobNum_Tv,machineNum_Tv,
                productionDate_Tv,mainDate_Tv;

        public ItemViewHolder(View view) {
            super(view);
            contractNo_Tv = view.findViewById(R.id.contractNo_tv);
            serialNum_Tv = view.findViewById(R.id.serialNum_tv);
            custName_Tv = view.findViewById(R.id.custName_tv);
            jobNum_Tv = view.findViewById(R.id.jobNum_tv);
            machineNum_Tv = view.findViewById(R.id.machineNum_tv);
            productionDate_Tv = view.findViewById(R.id.productionDate_tv);
            mainDate_Tv = view.findViewById(R.id.mainDate_tv);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View view) {
            super(view);
        }
    }
}
