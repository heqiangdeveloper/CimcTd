package com.cimcitech.cimctd.activity.main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.cimcitech.cimctd.R;
import com.cimcitech.cimctd.bean.ApkUpdateVo;
import com.cimcitech.cimctd.utils.ApkUpdateUtil;
import com.cimcitech.cimctd.utils.Config;
import com.cimcitech.cimctd.utils.GjsonUtil;
import com.cimcitech.cimctd.utils.ToastUtil;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class WelcomeActivity extends AppCompatActivity {

    @Bind(R.id.welcome)
    View welcome;

    public File mFile = null;
    public ProgressDialog pd;
    public int MAXSIZE = 0;
    public int TOTALSIZE = 0;
    public boolean isNewVersion = false;//是否是最新版本
    public final int UPDATE_APK = 1;
    public final int TIMEOUT = 2;
    public final int DOWNLOAD_ERROR = 3;
    public final int DOWNLOAD_SUCCESS = 4;
    public final int MAX_PRO_LENGTH = 5;
    public final int UPDATEPROGRESS = 6;
    private ApkUpdateVo apkUpdateVo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        getData();
        //startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        //finish();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_APK:
                    break;
                case TIMEOUT:
                    Toast.makeText(WelcomeActivity.this, "连接超时，请检查网络", Toast.LENGTH_SHORT)
                            .show();
                    break;
                case DOWNLOAD_ERROR:
                    finish();
                    break;
                case DOWNLOAD_SUCCESS:
                    installApk(mFile);
                    finish();
                    break;
                case MAX_PRO_LENGTH:
                    if (MAXSIZE != 0) {
                        pd.setMax(MAXSIZE);
                    }
                    break;
                case UPDATEPROGRESS:
                    pd.setProgress(TOTALSIZE);
                    break;
                case 1001:
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    finish();
                    break;
            }
        }
    };

    public void startToDownload() {
        String filePath = Environment.getExternalStorageDirectory() + "/newversion.apk";
        Log.d("heqiang", filePath);
        pd = new ProgressDialog(WelcomeActivity.this);
        pd.setTitle("正在下载");
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCancelable(false);
        pd.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mFile = getFileFromServer(apkUpdateVo.getData().getApkurl(), WelcomeActivity.this);
                    Thread.sleep(1000);
                    pd.dismiss();
                    sendMsg(DOWNLOAD_SUCCESS);
                } catch (Exception e) {
                    sendMsg(DOWNLOAD_ERROR);
                }
            }
        }).start();
    }

    public File getFileFromServer(String path, Context context) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            //pd.setMax(conn.getContentLength());
            MAXSIZE = conn.getContentLength();
            sendMsg(MAX_PRO_LENGTH);
            InputStream is = conn.getInputStream();
            File file = new File(Environment.getExternalStorageDirectory(), "newversion.apk");
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

    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        if (Build.VERSION.SDK_INT >= 24)//7.0
        {
            Uri apkUri =
                    FileProvider.getUriForFile(WelcomeActivity.this, "com.cimcitech.cimcly" +
                            ".fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        startActivity(intent);
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

    public void sendMsg(int flag) {
        Message message = new Message();
        message.what = flag;
        handler.sendMessage(message);
    }

    public void getData() {
        OkHttpUtils
                .post()
                .url(Config.updateApk)
                .build()
                .execute(
                        new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                //ToastUtil.showNetError();
                                handler.sendEmptyMessage(1001);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.d("hqtest","response is: " + response);
                                apkUpdateVo = GjsonUtil.parseJsonWithGson(response, ApkUpdateVo.class);
                                if (apkUpdateVo != null)
                                    if (apkUpdateVo.isSuccess()) {
                                        int versionCode = Integer.parseInt(
                                                ApkUpdateUtil.getVersionCode(WelcomeActivity.this));
                                        if (apkUpdateVo.getData() != null && apkUpdateVo.getData()
                                                .getVersioncode() > versionCode) {
                                            if (!isNetworkAvalible(WelcomeActivity.this)) {
                                                Toast.makeText(WelcomeActivity.this, "无法连接网络，请检查网络！", Toast.LENGTH_SHORT).show();
                                                handler.sendEmptyMessage(1001);
                                            } else {
                                                new AlertDialog.Builder(WelcomeActivity.this)
                                                        .setTitle("提示")
                                                        .setMessage("检测到新版本是否下载？")
                                                        .setCancelable(false)
                                                        .setPositiveButton("下载安装", new DialogInterface.OnClickListener() {

                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                startToDownload();
                                                            }
                                                        })
                                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                new Handler().postDelayed(new Runnable() {
                                                                    public void run() {
                                                                        handler.sendEmptyMessage(1001);
                                                                    }
                                                                }, 3000);
                                                            }
                                                        }).create().show();

                                            }
                                        } else {
                                            new Handler().postDelayed(new Runnable() {
                                                public void run() {
                                                    handler.sendEmptyMessage(1001);
                                                }
                                            }, 3000);
                                        }
                                    }
                            }
                        }
                );
    }
}
