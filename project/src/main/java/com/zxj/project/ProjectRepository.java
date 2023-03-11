package com.zxj.project;

import com.zxj.common.base.BaseApp;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.base.mvvm.BaseModel;
import com.zxj.common.bean.ProjectClassify;
import com.zxj.common.network.ApiRetrofit;
import com.zxj.common.network.api.ProjectApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepository extends BaseModel {
    private ProjectApi projectApi;
    public void getClassify(ProjectCallBack callBack){
        if(projectApi == null){
            projectApi = (ProjectApi)ApiRetrofit.getInstance(BaseApp.context).getApiService(ProjectApi.class);
        }
        projectApi.getClassify().enqueue(new Callback<BaseRespone<ArrayList<ProjectClassify>>>() {
            @Override
            public void onResponse(Call<BaseRespone<ArrayList<ProjectClassify>>> call, Response<BaseRespone<ArrayList<ProjectClassify>>> response) {
                if(response.body().getErrorCode() == 0){
                    callBack.onSuccess(response.body().getData());
                }else{
                    callBack.onFailed(response.body().getErrorMsg());
                }
            }

            @Override
            public void onFailure(Call<BaseRespone<ArrayList<ProjectClassify>>> call, Throwable t) {
                callBack.onFailed(t.getMessage());
            }
        });
    }

    public void getProject(ProjectCallBack callBack) {
        if(projectApi == null){
            projectApi = (ProjectApi)ApiRetrofit.getInstance(BaseApp.context).getApiService(ProjectApi.class);
        }

    }
}
