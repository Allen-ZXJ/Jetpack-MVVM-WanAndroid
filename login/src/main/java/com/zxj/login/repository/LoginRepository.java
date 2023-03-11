package com.zxj.login.repository;

import android.util.Log;

import com.zxj.common.base.BaseApp;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.base.mvvm.BaseModel;
import com.zxj.common.bean.UserInfo;
import com.zxj.common.network.ApiRetrofit;
import com.zxj.common.network.api.LoginApi;
import com.zxj.common.utils.LogUtils;
import com.zxj.login.ResultCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository extends BaseModel {
     public void login(String userName,String password,ResultCallBack callBack){
          LoginApi login = (LoginApi) ApiRetrofit.getInstance(BaseApp.context).getApiService(LoginApi.class);
          login.login(userName,password).enqueue(new Callback<BaseRespone<UserInfo>>() {
               @Override
               public void onResponse(Call<BaseRespone<UserInfo>> call, Response<BaseRespone<UserInfo>> response) {
                    LogUtils.d("Login",response.body().getErrorMsg());
                    callBack.onSuccess(response.body().getData());
               }

               @Override
               public void onFailure(Call<BaseRespone<UserInfo>> call, Throwable t) {
                    LogUtils.d("Login",t.getMessage());
                   callBack.onFailed(t.getMessage());
               }
          });
     }

}
