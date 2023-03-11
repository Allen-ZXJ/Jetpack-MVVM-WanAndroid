package com.zxj.home;

import com.zxj.common.base.BaseApp;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.base.mvvm.BaseModel;
import com.zxj.common.bean.Banner;
import com.zxj.common.network.ApiRetrofit;
import com.zxj.common.network.api.HomeApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class HomeRepository extends BaseModel {
     void getBanner(HomeFragment.BannerCallBack callBack){
          HomeApi homeApi = (HomeApi) ApiRetrofit.getInstance(BaseApp.context).getApiService(HomeApi.class);
          homeApi.getBanner().enqueue(new Callback<BaseRespone<ArrayList<Banner>>>() {
               @Override
               public void onResponse(Call<BaseRespone<ArrayList<Banner>>> call, Response<BaseRespone<ArrayList<Banner>>> response) {
                    if(response.body().getErrorCode() == 0){
                         callBack.onSuccess(response.body().getData());
                    }else{
                         callBack.onFailed(response.body().getErrorMsg());
                    }
               }

               @Override
               public void onFailure(Call<BaseRespone<ArrayList<Banner>>> call, Throwable t) {
                    callBack.onFailed(t.getMessage());
               }
          });
     }
}
