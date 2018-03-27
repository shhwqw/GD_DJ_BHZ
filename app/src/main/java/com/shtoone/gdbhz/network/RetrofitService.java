package com.shtoone.gdbhz.network;

import com.shtoone.gdbhz.bean.UpLoadBean;
import com.shtoone.gdbhz.bean.UserInfoData1;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by liangfeng on 2017/11/10.
 */

public interface RetrofitService {
    //BaseURL + "app.do?AppLogin&userName=123&userPwd=456&OSType=2"
//    @GET("app.do?AppLogin&OSType=2")
//    Observable<UserInfoData> login(@Query("userName") String userName, @Query("userPwd") String passWord);
//http://192.168.1.111:8888/zsfcsmss/appHnt/AppLogin?userName=systemmanager&userPwd=tooneyanfa&OSType=2
    @GET("appHnt/AppLogin?OSType=2")
    Observable<UserInfoData1> login(@Query("userName") String userName, @Query("userPwd") String passWord);

    @Multipart
    @POST("appHnt/AppHntChaobiaoChuzhi")
    Observable<UpLoadBean> uploadImage(@Part List<MultipartBody.Part> partList);
    /*Call<UpLoadBean> uploadImage(@QueryMap() Map<String, String> params,
                                 @Part MultipartBody.Part file);*/
}
