package com.cimcitech.cimctd.activity.home.file_search;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.adapter.file_search.FileSearchDetailAdapter;
import com.cimcitech.cimctd.bean.file_search.FileDetail;
import com.cimcitech.cimctd.bean.file_search.FileSearchDetailReq;
import com.cimcitech.cimctd.bean.file_search.FileSearchDetailVo;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.cimcitech.cimctd.widget.ClearEditText;
import com.cimcitech.cimctd.widget.MyBaseActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
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

public class FileSearchDetailActivity extends MyBaseActivity {
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.search_bar)
    ClearEditText search_Bar;
    private FileSearchDetailAdapter adapter;
    private FileSearchDetailVo fileSearchDetailVo;
    private List<FileDetail> data = new ArrayList<FileDetail>();
    private int pageNum = 1;
    private boolean isLoading;
    private boolean myData = true;
    private int detailId;
    private String TAG = "downloadlog";
    private ProgressDialog pd;
    private java.io.File mFile = null;
    public int MAXSIZE = 0;
    public int TOTALSIZE = 0;
    public final int UPDATE_APK = 1;
    public final int TIMEOUT = 2;
    public final int DOWNLOAD_ERROR = 3;
    public final int DOWNLOAD_SUCCESS = 4;
    public final int MAX_PRO_LENGTH = 5;
    public final int UPDATEPROGRESS = 6;
    private final String PDF_INTENT = "application/pdf";
    private final String WORD_INTENT = "application/msword";
    private final String EXCEL_INTENT = "application/vnd.ms-excel";
    private final String PPT_INTENT = "application/vnd.ms-powerpoint";
    private final String TXT_INTENT = "text/plain";

    private final String[][] MIME_MapTable = {
            // {后缀名， MIME类型}
            { ".doc", "application/msword" },
            { ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" },
            { ".xls", "application/vnd.ms-excel" },
            { ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" },
            { ".pdf", "application/pdf" },
            { ".ppt", "application/vnd.ms-powerpoint" },
            { ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation" },
            { ".txt", "text/plain" },
            { ".wps", "application/vnd.ms-works" },
            { ".jpg", "image/*" },
            { ".jpeg", "image/*" },
            { ".png", "image/*" },
            { ".zip", "application/x-gzip" },
            { ".rar", "application/x-gzip" },
            { ".html", "text/html" },
            { ".rmvb", "video/*" },
            { ".avi", "video/*" },
            { ".3gp", "video/*" },
            { ".mp4", "video/*" },
            { ".wmv", "video/*" },
            { ".mp3", "audio/*" },
            { ".mid", "audio/*" },
            { ".wav", "audio/*" },
            { ".ogg", "audio/*" },
            { "", "*/*" } };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_APK:
                    break;
                case TIMEOUT:
                    Toast.makeText(FileSearchDetailActivity.this, "连接超时，请检查网络", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case DOWNLOAD_ERROR:
                    Toast.makeText(FileSearchDetailActivity.this, "文件不存在，下载失败！", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case DOWNLOAD_SUCCESS:
                    openFile(mFile);
                    break;
                case MAX_PRO_LENGTH:
                    if (MAXSIZE != 0) {
                        pd.setMax(MAXSIZE);
                    }
                    break;
                case UPDATEPROGRESS:
                    pd.setProgress(TOTALSIZE);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_search_detail);
        ButterKnife.bind(this);
        detailId = getIntent().getIntExtra("detailId",0);
        Log.d(TAG,"detailId is: " + detailId);
        mLoading.show();
        initViewData();
        getData();
    }

    //刷新数据
    private void updateData() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        //清除数据
        adapter.notifyDataSetChanged();
        this.data.clear();
        pageNum = 1;
        /*if (myData)
            getData(); //获取数据
        else
            getSubordinateData();*/
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if (Config.isAddPerson) {
            Config.isAddPerson = false;
            updateData();
        }*/
    }

    public void initViewData() {
        adapter = new FileSearchDetailAdapter(FileSearchDetailActivity.this, data);
        swipeRefreshLayout.setColorSchemeResources(R.color.blueStatus);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //下拉刷新
                        adapter.notifyDataSetChanged();
                        data.clear(); //清除数据
                        pageNum = 1;
                        isLoading = false;
                        /*if (myData)
                            getData(); //获取数据
                        else
                            getSubordinateData();*/
                        getData();
                    }
                }, 1000);
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(FileSearchDetailActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0)
                        ? 0 : recyclerView.getChildAt(0).getTop();
                if (topRowVerticalPosition > 0) {
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        swipeRefreshLayout.setRefreshing(false);
                        return;
                    }

                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //上拉加载
                                if (fileSearchDetailVo.getData().isHasNextPage()) {
                                    pageNum++;
                                    /*if (myData)
                                        getData();//添加数据
                                    else
                                        getSubordinateData();*/

                                    getData();
                                }
                                isLoading = false;
                            }
                        }, 1000);
                    }
                }
            }
        });
        //给List添加点击事件
        adapter.setOnItemClickListener(new FileSearchDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String filePath = Environment.getExternalStorageDirectory().getPath();
                Log.d(TAG, filePath);
                FileDetail fileDetail = (FileDetail) adapter.getAll().get(position);
                final int fileId = fileDetail.getFileId();
                final String fileName = fileDetail.getNewFileName();
                //文件下载
                if (ifFileExist(fileName)) {
                    //Toast.makeText(FileSearchDetailActivity.this, "文件已下载！", Toast.LENGTH_SHORT)
                    //        .show();
                    openFile(new java.io.File(getExternalFilesDir(Environment
                           .DIRECTORY_DOWNLOADS), fileName));
                } else {
                    if (!isNetworkAvalible(FileSearchDetailActivity.this)) {
                        Toast.makeText(FileSearchDetailActivity.this, "无法连接网络，请检查网络！", Toast.LENGTH_SHORT).show();
                    } else {
                        new AlertDialog.Builder(FileSearchDetailActivity.this)
                                .setTitle("提示")
                                .setMessage("查看前需要先下载？")
                                .setCancelable(false)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        startToDownload(fileId,fileName);
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).create().show();
                    }
                }
            }

            @Override
            public void onItemLongClickListener(View view, int position) {

            }
        });

        //根据输入框输入值的改变来过滤搜索
        search_Bar.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 根据输入框中的值来过滤数据并更新RecyclerView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<FileDetail> filterDateList = new ArrayList<>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = data;
        } else {
            filterDateList.clear();
            for (FileDetail fileDetail : data) {
                String name = fileDetail.getNewFileName();//文件名称
                if (name.startsWith(filterStr.toString())){
                    filterDateList.add(fileDetail);
                }
            }
        }
        adapter.updateList(filterDateList);
    }

    public boolean ifFileExist(String fileName){
        //java.io.File file = new java.io.File(Environment.getExternalStorageDirectory(), fileName);
        java.io.File file = new java.io.File(getExternalFilesDir(Environment
                           .DIRECTORY_DOWNLOADS), fileName);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isNetworkAvalible(Context context) {
        ConnectivityManager manager = (ConnectivityManager) (context.getSystemService(Context
                .CONNECTIVITY_SERVICE));
        NetworkInfo[] infos = manager.getAllNetworkInfo();
        for (int i = 0; i < infos.length; i++) {
            if (infos[i].getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    public void startToDownload(final int fileId,final String fileName) {
        pd = new ProgressDialog(FileSearchDetailActivity.this);
        pd.setTitle("正在下载");
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCancelable(false);
        pd.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mFile = getFileFromServer(fileId,fileName);
                    Thread.sleep(1000);
                    pd.dismiss();
                    sendMsg(DOWNLOAD_SUCCESS);
                } catch (Exception e) {
                    pd.dismiss();
                    sendMsg(DOWNLOAD_ERROR);
                }
            }
        }).start();
    }

    public java.io.File getFileFromServer(int fileId,String fileName) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(Config.FILE_DOWNLOAD_URL + "?fileId=" + fileId);
            Log.d(TAG,"download url is: " + url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            //pd.setMax(conn.getContentLength());
            MAXSIZE = conn.getContentLength();
            sendMsg(MAX_PRO_LENGTH);
            InputStream is = conn.getInputStream();
            //java.io.File file = new java.io.File(getCacheDir(),
            //       fileName);
            java.io.File file = new java.io.File(getExternalFilesDir(Environment
                    .DIRECTORY_DOWNLOADS), fileName);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                //pd.setProgress(total);
                TOTALSIZE = total;
                sendMsg(UPDATEPROGRESS);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    public void sendMsg(int flag) {
        Message message = new Message();
        message.what = flag;
        handler.sendMessage(message);
    }

    protected void openFile(java.io.File file) {
        String name = file.getName();
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        Uri fileUri = null;
        String authority = "com.cimcitech.cimctd" + ".fileprovider";
        if (Build.VERSION.SDK_INT >= 24)//7.0
        {
            try{
                fileUri = FileProvider.getUriForFile(FileSearchDetailActivity.this, authority, file);
            }catch (Exception e){
                e.printStackTrace();
            }
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(fileUri, getMIMEType(file));
        } else {
            intent.setDataAndType(Uri.fromFile(file), PDF_INTENT);
        }
        if(getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)!=null){
            try{
                startActivity(intent);
            }catch (ActivityNotFoundException e){
                Toast.makeText(FileSearchDetailActivity.this,"没有可以打开此文件的应用！",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(FileSearchDetailActivity.this,"没有可以打开此文件的应用！",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param file
     */
    private String getMIMEType(java.io.File file) {
        String type = "*/*";
        String fName = file.getName();
        // 获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名 */
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "")
            return type;
        // 在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) {
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    @OnClick({R.id.back})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    public void getData() {
        String json = new Gson().toJson(new FileSearchDetailReq(pageNum, 10, "",
                new FileSearchDetailReq.filesVoBean(detailId,"")));
        OkHttpUtils
                .postString()
                .url(Config.FILE_SEARCH_DETAIL_URL)
                //.addHeader("checkTokenKey", Config.loginback.getToken())
                //.addHeader("sessionKey", Config.loginback.getUserId() + "")
                .content(json)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                if(mLoading.isShowing()) mLoading.dismiss();
                                boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                                if (isRefreshing) {
                                    swipeRefreshLayout.setRefreshing(false);
                                }
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                if(mLoading.isShowing()) mLoading.dismiss();
                                Log.d(TAG,"response is: " + response);
                                fileSearchDetailVo = GjsonUtil.parseJsonWithGson(response, FileSearchDetailVo.class);
                                if (fileSearchDetailVo != null) {
                                    if (fileSearchDetailVo.isSuccess()) {
                                        if (fileSearchDetailVo.getData().getList() != null && fileSearchDetailVo.getData().getList().size() > 0) {
                                            for (int i = 0; i < fileSearchDetailVo.getData().getList().size(); i++) {
                                                data.add(fileSearchDetailVo.getData().getList().get(i));
                                            }
                                        }
                                        if (fileSearchDetailVo.getData().isHasNextPage()) {
                                            adapter.setNotMoreData(false);
                                        } else {
                                            adapter.setNotMoreData(true);
                                        }
                                        adapter.notifyDataSetChanged();
                                        swipeRefreshLayout.setRefreshing(false);
                                        adapter.notifyItemRemoved(adapter.getItemCount());
                                    }
                                } else {
                                    adapter.notifyDataSetChanged();
                                    swipeRefreshLayout.setRefreshing(false);
                                }
                            }
                        }
                );
    }
}
