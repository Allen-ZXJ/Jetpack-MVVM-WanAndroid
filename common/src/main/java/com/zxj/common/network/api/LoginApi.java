package com.zxj.common.network.api;

import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.bean.UserInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginApi {
    @FormUrlEncoded
    @POST("user/login")
    Call<BaseRespone<UserInfo>> login(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("user/register")
    Call<BaseRespone<UserInfo>> register(@Field("username") String username,@Field("password")String password,@Field("repassword")String repasswor);

    @GET("user/logout/json")
    Call<BaseRespone<Object>> logout();
}
