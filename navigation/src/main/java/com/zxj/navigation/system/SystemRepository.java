package com.zxj.navigation.system;

import androidx.lifecycle.MutableLiveData;

import com.zxj.common.base.BaseApp;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.base.mvvm.BaseModel;
import com.zxj.common.bean.SystemTree;
import com.zxj.common.network.ApiRetrofit;
import com.zxj.common.network.api.NaviApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SystemRepository extends BaseModel {
     private NaviApi systemApi;
     public void getSystemTrees(SystemFragment.SystemDataCallBack callBack){
           if(systemApi == null){
                systemApi = (NaviApi) ApiRetrofit.getInstance(BaseApp.context).getApiService(NaviApi.class);
           }
           systemApi.getSystemTree().enqueue(new Callback<BaseRespone<ArrayList<SystemTree>>>() {
                @Override
                public void onResponse(Call<BaseRespone<ArrayList<SystemTree>>> call, Response<BaseRespone<ArrayList<SystemTree>>> response) {
                     if(response.body().getErrorCode() == 0){
                          callBack.onSuccess(response.body().getData());
                     }else{
                         callBack.onFailed(response.body().getErrorMsg());
                     }
                }

                @Override
                public void onFailure(Call<BaseRespone<ArrayList<SystemTree>>> call, Throwable t) {
                    callBack.onFailed(t.getMessage());
                }
           });

     }
}
