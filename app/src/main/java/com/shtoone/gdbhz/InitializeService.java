package com.shtoone.gdbhz;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.shtoone.gdbhz.bean.FBDataBean;
import com.shtoone.gdbhz.bean.FBProjectBean;
import com.shtoone.gdbhz.utils.HttpUtils;
import com.socks.library.KLog;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/9/28.
 */

public class InitializeService extends IntentService {
    private static final String ACTION_INIT = "initApplication";
    private static final String TAG = "InitializeService";

    private FBProjectBean data;

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication();
            }
        }
    }

    private int testB = 0;

    public void initApplication() {
        String URL = com.shtoone.gdbhz.utils.URL.FB_PROJECT;
        if ((DataSupport.count(FBDataBean.class)>0)){
            DataSupport.deleteAll(FBDataBean.class);
        }
        //联网获取数据
        //还没有判断url，用户再判断
        HttpUtils.getRequest(URL, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String response) {
                KLog.json(response);
                if (!TextUtils.isEmpty(response)) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
//                        mPageStateLayout.showError();
                        return;
                    }
                    if (jsonObject.optBoolean("success")) {
                        data = new Gson().fromJson(response, FBProjectBean.class);
                        if (null != data) {
                            if (data.isSuccess()) {
                               if (data.getData().size()>0){
                                   FBDataBean bean;
                                   DataSupport.deleteAll(FBDataBean.class);//防止出现重复数据(onSuccess会走两次，目前没有找到原因)
                                   for (FBDataBean dataBean :data.getData()){
                                       bean = new FBDataBean();
                                       bean.setParentNo(dataBean.getParentNo());
                                       bean.setProjectName(dataBean.getProjectName());
                                       bean.setProjectNo(dataBean.getProjectNo());
                                       bean.save();
                                   }
                               }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailed(VolleyError error) {
                KLog.d(error);
            }
        });
    }
}
