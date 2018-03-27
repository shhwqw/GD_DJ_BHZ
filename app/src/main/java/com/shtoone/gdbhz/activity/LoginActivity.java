package com.shtoone.gdbhz.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.android.volley.VolleyError;
import com.dd.CircularProgressButton;
import com.google.gson.Gson;
import com.shtoone.gdbhz.BaseApplication;
import com.shtoone.gdbhz.R;
import com.shtoone.gdbhz.activity.base.BaseActivity;
import com.shtoone.gdbhz.bean.TodayTestNumberData;
import com.shtoone.gdbhz.bean.UserInfoData1;
import com.shtoone.gdbhz.network.RetrofitService;
import com.shtoone.gdbhz.utils.AESCryptUtils;
import com.shtoone.gdbhz.utils.AnimationUtils;
import com.shtoone.gdbhz.utils.ConstantsUtils;
import com.shtoone.gdbhz.utils.HttpUtils;
import com.shtoone.gdbhz.utils.KeyBoardUtils;
import com.shtoone.gdbhz.utils.NetworkUtils;
import com.shtoone.gdbhz.utils.SharedPreferencesUtils;
import com.shtoone.gdbhz.utils.URL;
import com.socks.library.KLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private TextInputLayout        login_username;
    private TextInputLayout        login_password;
    private CircularProgressButton login_button;
    private UserInfoData1          mUserInfoData;
    private ScrollView             mScrollView;
    private LinearLayout           ll_container;
    private Gson                   mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
    }

    private void initView() {
        mScrollView = (ScrollView) findViewById(R.id.sv_login_activity);
        ll_container = (LinearLayout) findViewById(R.id.ll_container_login_register_activity);
        login_username = (TextInputLayout) findViewById(R.id.login_username);
        login_username.getEditText().clearFocus();
        login_password = (TextInputLayout) findViewById(R.id.login_password);
        login_button = (CircularProgressButton) findViewById(R.id.login_button);
    }

    private void initData() {
        mGson= new Gson();
        login_username.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                login_button.setProgress(0);
                if (TextUtils.isEmpty(s)) {
                    login_username.setError("用户名不能为空");
                    login_username.setErrorEnabled(true);
                } else {
                    login_username.setError("");
                    login_username.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        login_username.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_button.setProgress(0);
                login_username.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                }, 300);
            }
        });

        login_password.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                login_button.setProgress(0);
                if (TextUtils.isEmpty(s)) {
                    login_password.setError("密码不能为空");
                    login_password.setErrorEnabled(true);
                } else {
                    login_password.setError("");
                    login_password.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        login_password.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_button.setProgress(0);
                login_password.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                }, 300);
            }
        });

        login_button.setIndeterminateProgressMode(true);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyBoardUtils.hideKeybord(v, LoginActivity.this);
                String username = login_username.getEditText().getText().toString().trim();
                String password = login_password.getEditText().getText().toString().trim();
                //进行加密
                try {
                    final String usernameEncrypted = AESCryptUtils.encrypt(ConstantsUtils.ENCRYPT_KEY, username);
                    final String passwordEncrypted = AESCryptUtils.encrypt(ConstantsUtils.ENCRYPT_KEY, password);
                    KLog.e("username加密后:" + usernameEncrypted);
                    KLog.e("password加密后:" + passwordEncrypted);
                    if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                        login_button.setProgress(0);
                        login_button.setProgress(50);
                        //联网校对密码正确后保存
                        /******************************************/
                        //retrofit底层用的okHttp,所以设置超时还需要okHttp
                        //然后设置5秒超时
                        //其中DEFAULT_TIMEOUT是我这边定义的一个常量
                        //TimeUnit为java.util.concurrent包下的时间单位
                        //TimeUnit.SECONDS这里为秒的单位
                       /* OkHttpClient client = new OkHttpClient.Builder()
                                .connectTimeout(20, TimeUnit.SECONDS)
                                .writeTimeout(INTERNET_TIMEOUT, TimeUnit.SECONDS)
                                .readTimeout(INTERNET_TIMEOUT, TimeUnit.SECONDS)
                                .retryOnConnectionFailure(true)
                                .build();*/
                        //创建Retrofit实例
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(URL.BaseURL)
                                .addConverterFactory(GsonConverterFactory.create())//配置gson转换
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//配置rxjava转换
//                                .client(client)
                                .build();
                        //创建接口实例
                        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
                        try {
                            retrofitService.login(URLEncoder.encode(username,"utf-8"), password)
    //                        retrofitService.login("systemmanager", "tooneyanfa")
                                    .subscribeOn(Schedulers.newThread())//指定事件订阅在IO线程
                                    .subscribe(new Observer<UserInfoData1>() {
                                        @Override
                                        public void onSubscribe(@NonNull Disposable d) {
                                            //检查网络状态
                                            if (!NetworkUtils.isConnected(LoginActivity.this)) {

                                            }
                                        }

                                        @Override
                                        public void onNext(@NonNull UserInfoData1 userInfoData) {
                                            Log.e(TAG, "onNext");
                                            Log.e(TAG, "userInfoData:" + userInfoData.toString());
                                            if (userInfoData != null) {
                                                mUserInfoData = userInfoData;
                                                if (userInfoData.isSuccess()) {
                                                    BaseApplication.mUserInfoData = userInfoData;
                                                    SharedPreferencesUtils.put(LoginActivity.this, ConstantsUtils.USERNAME, usernameEncrypted);
                                                    SharedPreferencesUtils.put(LoginActivity.this, ConstantsUtils.PASSWORD, passwordEncrypted);
                                                    initParametersData();
                                                    login_button.setProgress(100);
                                                    login_button.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            jumpToMain();
                                                        }
                                                    }, 500);
                                                } else {
                                                    //提示用户名或密码错误
                                                    login_button.setErrorText("用户名或密码错误");
                                                    login_button.setProgress(-1);
                                                }
                                            } else {
                                                //服务器异常
                                                login_button.setErrorText("服务器异常");
                                            }
                                        }

                                        @Override
                                        public void onError(@NonNull Throwable e) {
                                            Log.e(TAG, "onError");
                                            Log.e(TAG, "e：" + e.toString());
                                            //提示网络数据异常。1.可能是本机网络机场。2.可能是服务器异常。
                                            if (!NetworkUtils.isConnected(LoginActivity.this)) {
                                                //提示网络异常
                                                login_button.setErrorText("网络异常");
                                            } else {
                                                //服务器异常
                                                login_button.setErrorText("服务器异常");
                                            }
                                            login_button.setProgress(-1);
                                        }

                                        @Override
                                        public void onComplete() {
                                            Log.e(TAG, "onComplete");

                                        }
                                    });
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    } else if (TextUtils.isEmpty(username)) {
                        login_username.setErrorEnabled(true);
                        login_username.setError("");
                        login_username.setError("用户名不能为空");
                    } else if (TextUtils.isEmpty(password)) {
                        login_username.setErrorEnabled(true);
                        login_username.setError("");
                        login_password.setError("密码不能为空");
                    }

                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //进入MainActivity
    private void jumpToMain() {
        Intent intent = new Intent(this, MainActivity.class);
//        Intent intent = null;
        Log.e(TAG, "mUserInfoData.getType:" + mUserInfoData.getUserType());
       /* if ("GL".equals(mUserInfoData.getUserType())) {
            intent = new Intent(this, MainActivity.class);
        } else if ("SG".equals(mUserInfoData.getUserType())) {
            switch (mUserInfoData.getUserRole()) {
                case "1":
                    intent = new Intent(this, ConcreteMainActivity.class);
                    break;
                case "3":
                    intent = new Intent(this, LaboratoryMainActivity.class);
                    break;
            }
        }*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AnimationUtils.startActivityFinish(this, intent, login_button, R.color.login_reveal, 500);
        } else {
            startActivity(intent);
            finish();
        }
    }

    private void initParametersData() {
      /*  BaseApplication.parametersData.userGroupID = mUserInfoData.getDepartId();
        BaseApplication.mDepartmentData.departmentID = mUserInfoData.getDepartId();
        BaseApplication.mDepartmentData.departmentName = mUserInfoData.getDepartName();
        BaseApplication.parametersData.username = mUserInfoData.getUserFullName();*/
        BaseApplication.parametersData.biaoshiid = mUserInfoData.getBiaoshi();
        BaseApplication.parametersData.userType = mUserInfoData.getUserType();
        BaseApplication.parametersData.shenghe = mUserInfoData.getShenehe();
        BaseApplication.parametersData.chuzhi = mUserInfoData.getChuzhi();
        BaseApplication.parametersData.sysChuzhi = mUserInfoData.getSysChuzhi();
        BaseApplication.parametersData.biaoshiid = mUserInfoData.getBiaoshi();

        BaseApplication.mDepartmentData.departmentID = mUserInfoData.getBiaoshi();
        BaseApplication.mDepartmentData.departtype = mUserInfoData.getUserType();
        BaseApplication.mDepartmentData.lqupdata = mUserInfoData.getLqupdata();
        BaseApplication.mDepartmentData.departmentName = mUserInfoData.getUserFullName();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Calendar rld = Calendar.getInstance();
        Calendar rld1 = Calendar.getInstance();
        rld1.add(Calendar.DAY_OF_MONTH, 1);

        HttpUtils.getRequest(URL.getTODAYTESTNUMBER_URL(sdf.format(rld.getTime()), sdf.format(rld1.getTime()),BaseApplication.mUserInfoData.getUserType(),BaseApplication.mUserInfoData.getBiaoshi()), new HttpUtils.HttpListener() {


            @Override
            public void onSuccess(String response) {
                if (!TextUtils.isEmpty(response)) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        if (jsonObject.optBoolean("success")) {
                            TodayTestNumberData todayTestNumberData = mGson.fromJson(response, TodayTestNumberData.class);
                            BaseApplication.parametersData.todaytestnumber = todayTestNumberData.getData();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                }

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });

    }

    @Override
    public boolean swipeBackPriority() {
        return false;
    }
}
