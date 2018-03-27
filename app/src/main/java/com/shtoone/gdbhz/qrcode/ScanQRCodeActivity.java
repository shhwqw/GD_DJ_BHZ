package com.shtoone.gdbhz.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.sdsmdg.tastytoast.TastyToast;
import com.shtoone.gdbhz.BaseApplication;
import com.shtoone.gdbhz.R;
import com.shtoone.gdbhz.activity.QRCodeRecordActivity;
import com.shtoone.gdbhz.activity.WannengjiDetailActivity;
import com.shtoone.gdbhz.activity.base.BaseActivity;
import com.shtoone.gdbhz.bean.ScanQRCodeData;
import com.shtoone.gdbhz.bean.UserInfoData1;
import com.shtoone.gdbhz.bean.WannengjiDetailData;
import com.shtoone.gdbhz.utils.HttpUtils;
import com.shtoone.gdbhz.utils.NetworkUtils;
import com.shtoone.gdbhz.utils.URL;
import com.socks.library.KLog;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * Created by Administrator on 2017/8/31.
 */

public class ScanQRCodeActivity extends BaseActivity implements QRCodeView.Delegate{

    private QRCodeView mQRCodeView;
    Toolbar toolbarToolbar;
    private boolean isRegistered = false;
    private Gson          mGson;
    private String uuid;
    private ScanQRCodeData mScanQRCodeData;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
       initView();

    }

    private void initView() {
        mQRCodeView = (ZXingView)findViewById(R.id.zxingview);

        toolbarToolbar= (Toolbar)findViewById(R.id.toolbar_toolbar);
        initToolbarBackNavigation(toolbarToolbar);
        setToolbarTitle();
        mGson = new Gson();
        mQRCodeView.setDelegate(this);

    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        vibrate();
        mQRCodeView.startSpot();

        Log.e("mQRCodeView","onScanQRCodeSuccessstopSpot()");
        Toast.makeText(this,"扫码成功",Toast.LENGTH_SHORT).show();
        try {
            Log.e("二维码json数据", URLDecoder.decode(result,"utf-8"));
            if (result!=null){
                uuid = result;
                Intent intent = new Intent();
                intent.putExtra("uuid",uuid);
                setResult(2, intent);
                onBackPressed();

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }



//    private void handleQRCode(BHZDepartData itemsData) {
//
//        Intent mIntent=new Intent(this,BHZCheckActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("result",itemsData);
//        mIntent.putExtras(bundle);
//        startActivity(mIntent);
//    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e("二维码", "打开相机出错");
    }

    @Override
    public void onResume() {
        super.onResume();
        mQRCodeView.startCamera();
        mQRCodeView.startSpotAndShowRect();
        Log.e("mQRCodeView","onResume()startCamera()");
        Log.e("mQRCodeView","onResume()showScanRect()");
        Log.e("mQRCodeView","onResume()startSpot()");

    }

    @Override
    public void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.startSpotAndShowRect();
        Log.e("mQRCodeView","onResume()startCamera()");
        Log.e("mQRCodeView","onResume()mQRCodeView.startSpotAndShowRect();");


    }

    @Override
    public void onStop() {
        super.onStop();
        mQRCodeView.stopSpot();
        mQRCodeView.stopCamera();

        Log.e("mQRCodeView","onStop()stopSpot()");
        Log.e("mQRCodeView","onStop()stopCamera()");

   }

    @Override
    public void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
        Log.e("mQRCodeView","onDestroy()");
    }

    private void vibrate() {
       Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    private void setToolbarTitle() {
        if (null != toolbarToolbar) {

            toolbarToolbar.setTitle("扫描二维码");
       }
    }

//    private void submit(final MaterialDialog progressDialog) {
//        Map<String, String> paramsMap = new HashMap<String, String>();
//        try {
//            paramsMap.put("username", URLEncoder.encode(username,"utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        paramsMap.put("uuid", uuid);
//
//        HttpUtils.postRequest(URL.SYS_QRCODEUPDATE_URL, paramsMap, new HttpUtils.HttpListener() {
//            @Override
//            public void onSuccess(String response) {
//                progressDialog.dismiss();
//                KLog.json(response);
//                Log.e("response",response);
//                if (!TextUtils.isEmpty(response)) {
//                    JSONObject jsonObject = null;
//                    try {
//                        jsonObject = new JSONObject(response);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        TastyToast.makeText(getApplicationContext(), "上传失败，请重试！", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
//                        return;
//                    }
//
//                    if (jsonObject.optBoolean("success"))
//                    {
//
//                        if(jsonObject.opt("status").equals("0"))
//                        {
//
//                            TastyToast.makeText(getApplicationContext(), "平台没有该二维码,请联系项目人员!", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
//                        }else if (jsonObject.opt("status").equals("1"))
//                        {
//                            TastyToast.makeText(getApplicationContext(), "第一次上传", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
//                        }else if (jsonObject.opt("status").equals("2"))
//                        {
//                            //TastyToast.makeText(getApplicationContext(), "养护库有记录", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
//                            mScanQRCodeData = mGson.fromJson(response, ScanQRCodeData.class);
//                            Log.e("response",response);
//                            Log.e("mScanQRCodeData",mScanQRCodeData.getData().getStartTime());
//                            Intent intent = new Intent(getApplication(), QRCodeRecordActivity.class);
//                            intent.putExtra("ScanQRCodeData", mScanQRCodeData.getData());
//                            startActivity(intent);
//                        }
//                        finish();
//
//                    }else {
//                        TastyToast.makeText(getApplicationContext(), "上传失败，请重试！", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
//                    }
//
//                } else {
//                    TastyToast.makeText(getApplicationContext(), "上传失败，请重试！", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
//                }
//            }
//
//            @Override
//            public void onFailed(VolleyError error) {
//                progressDialog.dismiss();
//                if (!NetworkUtils.isConnected(ScanQRCodeActivity.this)) {
//                    //提示网络异常,让用户点击设置网络，
//                    View view = ScanQRCodeActivity.this.getWindow().getDecorView();
//                    Snackbar.make(view, "当前网络已断开！", Snackbar.LENGTH_LONG)
//                            .setAction("设置网络", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    // 跳转到系统的网络设置界面
//                                    NetworkUtils.openSetting(ScanQRCodeActivity.this);
//                                }
//                            }).show();
//                } else {
//                    //服务器异常，展示错误页面，点击刷新
//                    TastyToast.makeText(getApplicationContext(), "服务器异常！", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
//                }
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // 添加返回过渡动画.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
