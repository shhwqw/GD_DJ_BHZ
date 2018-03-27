package com.shtoone.gdbhz;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.shtoone.gdbhz.bean.DepartmentData;
import com.shtoone.gdbhz.bean.ParametersData;
import com.shtoone.gdbhz.bean.UserInfoData1;
import com.socks.library.KLog;
import com.squareup.otto.Bus;

import org.litepal.LitePalApplication;

//import com.squareup.leakcanary.LeakCanary;
//import com.squareup.leakcanary.RefWatcher;

/**
 * Created by leguang on 2016/5/20 0031.
 */
public class BaseApplication extends LitePalApplication {
    private static final String TAG = BaseApplication.class.getSimpleName();
    public static BaseApplication application;
    public static Context context;
    public static final Bus bus = new Bus();
    public static ParametersData parametersData = new ParametersData();
    public static RequestQueue mRequestQueue;
    public static UserInfoData1 mUserInfoData;
    public static DepartmentData mDepartmentData = new DepartmentData();
    public static boolean isExpand;
    //    public RefWatcher mRefWatcher;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //日志的开关和全局标签初始化
        KLog.init(false, "SHTW混凝土");
        application = this;
        context = getApplicationContext();
        // 程序异常交由AppExceptionHandler来处理
//        Thread.setDefaultUncaughtExceptionHandler(AppExceptionHandler.getInstance(this));
        //创建Volley的请求队列
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        //创建LeakCanary对象，观察内存泄漏
//        mRefWatcher = LeakCanary.install(this);
        InitializeService.start(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }


}
