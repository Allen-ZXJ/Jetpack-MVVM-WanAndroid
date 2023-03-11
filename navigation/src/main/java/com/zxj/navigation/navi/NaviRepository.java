package com.zxj.navigation.navi;

import com.zxj.common.base.BaseApp;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.base.mvvm.BaseModel;
import com.zxj.common.bean.NavArticles;
import com.zxj.common.network.ApiRetrofit;
import com.zxj.common.network.api.NaviApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaviRepository extends BaseModel {
    private NaviApi naviApi;
    public void getNavData(NaviFragment.NavDataCallBack callBack){
        if(naviApi == null){
            naviApi = (NaviApi) ApiRetrofit.getInstance(BaseApp.context).getApiService(NaviApi.class);
        }
     naviApi.getNavData().enqueue(new Callback<BaseRespone<ArrayList<NavArticles>>>() {
         @Override
         public void onResponse(Call<BaseRespone<ArrayList<NavArticles>>> call, Response<BaseRespone<ArrayList<NavArticles>>> response) {
             if(response.body().getErrorCode() == 0){
                 callBack.onSuccess(response.body().getData());
             }else{
                 callBack.onFailed(response.body().getErrorMsg());
             }
         }

         @Override
         public void onFailure(Call<BaseRespone<ArrayList<NavArticles>>> call, Throwable t) {
                 callBack.onFailed(t.getMessage());
         }
     });
    }
}
