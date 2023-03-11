package com.zxj.login.repository;

import com.zxj.common.base.BaseApp;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.base.mvvm.BaseModel;
import com.zxj.common.bean.UserInfo;
import com.zxj.common.network.ApiRetrofit;
import com.zxj.common.network.api.LoginApi;
import com.zxj.login.ResultCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository extends BaseModel {
    public void register(String userName, String password, String rePassword, ResultCallBack callBack){
        LoginApi register = (LoginApi) ApiRetrofit.getInstance(BaseApp.context).getApiService(LoginApi.class);
        register.register(userName,password,rePassword).enqueue(new Callback<BaseRespone<UserInfo>>() {
            @Override
            public void onResponse(Call<BaseRespone<UserInfo>> call, Response<BaseRespone<UserInfo>> response) {
                callBack.onSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<BaseRespone<UserInfo>> call, Throwable t) {
                callBack.onFailed(t.getMessage());
            }
        });
    }
}
