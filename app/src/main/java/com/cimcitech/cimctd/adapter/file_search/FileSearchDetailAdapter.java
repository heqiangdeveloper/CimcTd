package com.cimcitech.cimctd.adapter.file_search;

/**
 * Created by cimcitech on 2017/8/2.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.bean.file_search.FileDetail;
import com.cimcitech.cimctd.utils.DateTool;

import java.util.List;

/**
 * Created by cimcitech on 2017/8/1.
 */

public class FileSearchDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<FileDetail> data;
    private LayoutInflater inflater;
    private static final int TYPE_END = 2;
    private boolean isNotMoreData = false;

    public FileSearchDetailAdapter(Context context, List<FileDetail> data) {
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
            View view = inflater.inflate(R.layout.file_search_detail_item_view2, parent, false);
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
            }
            final FileDetail item = data.get(position);
            //上传时间
            ((ItemViewHolder) holder).uploadTime_Tv.setText(item.getUploadTime() != null ?
                    DateTool.getDateStr(item.getUploadTime()) : "");
            //文件大小
            ((ItemViewHolder) holder).fileSize_Tv.setText(item.getSize() != null && !item
                    .getSize().equals("") ? item.getSize():"未知大小");
            //文件类型
            ((ItemViewHolder) holder).fileTypeName_Tv.setText(item.getFileTypeName() != null && !item
                    .getFileTypeName().equals("") ? item.getFileTypeName():"未知类型");
            //文件名称
            ((ItemViewHolder) holder).newFileName_Tv.setText(item.getNewFileName() != null && !item
                    .getNewFileName().equals("") ? item.getNewFileName():"未知名称");
            //文件类型
            ((ItemViewHolder) holder).fileType_Iv.setBackgroundResource(getImage(item.getNewFileName()));

        }
    }

    public int getImage(String fileName){
        int resourceId ;
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex < 0) {
            return R.drawable.format_unkown;
        }
        /* 获取文件的后缀名 */
        String end = fileName.substring(dotIndex, fileName.length()).toLowerCase();
        switch (end){
            //word
            case ".doc":
            case ".docx":
                resourceId = R.drawable.format_word;
                break;
            //excel
            case ".xls":
            case ".xlsx":
                resourceId = R.drawable.format_excel;
                break;
            //ppt
            case ".ppt":
            case ".pptx":
                resourceId = R.drawable.format_ppt;
                break;
            //pdf
            case ".pdf":
                resourceId = R.drawable.format_pdf;
                break;
            //txt
            case ".txt":
                resourceId = R.drawable.format_text;
                break;
            //picture
            case ".jpg":
            case ".jpeg":
            case ".png":
                resourceId = R.drawable.format_picture;
                break;
            //zip,rar
            case ".zip":
            case ".rar":
                resourceId = R.drawable.format_zip;
                break;
            //flash
            case ".swf":
            case ".gif":
                resourceId = R.drawable.format_flash;
                break;
            //html
            case ".html":
                resourceId = R.drawable.format_html;
                break;
            //chm
            case ".chm":
                resourceId = R.drawable.format_chm;
                break;
            //vedio
            case ".rmvb":
            case ".wmv":
            case ".avi":
            case ".3gp":
            case ".mp4":
                resourceId = R.drawable.format_media;
                break;
            //audio
            case ".mp3":
                resourceId = R.drawable.format_music;
                break;
            case ".wav":
            case ".mid":
            case ".ogg":
                resourceId = R.drawable.file_audio;
                break;
            default:
                resourceId = R.drawable.format_unkown;
                break;
        }
        return resourceId;
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
        TextView uploadTime_Tv,fileSize_Tv,fileTypeName_Tv,newFileName_Tv;
        ImageView fileType_Iv;

        public ItemViewHolder(View view) {
            super(view);
            uploadTime_Tv = view.findViewById(R.id.uploadTime_tv);
            fileSize_Tv = view.findViewById(R.id.fileSize_tv);
            fileTypeName_Tv = view.findViewById(R.id.fileTypeName_tv);
            newFileName_Tv = view.findViewById(R.id.newFileName_tv);
            fileType_Iv = view.findViewById(R.id.fileType_iv);
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
    public void updateList(List<FileDetail> list){
        this.data = list;
        notifyDataSetChanged();
    }

}
