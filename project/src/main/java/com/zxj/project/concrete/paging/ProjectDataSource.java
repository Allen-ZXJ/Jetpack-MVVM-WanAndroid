package com.zxj.project.concrete.paging;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zxj.common.base.BaseApp;
import com.zxj.common.base.bean.BaseListData;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.bean.HomeArticle;
import com.zxj.common.network.ApiRetrofit;
import com.zxj.common.network.api.HomeApi;
import com.zxj.common.network.api.ProjectApi;
import com.zxj.common.utils.LogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectDataSource extends PageKeyedDataSource<Integer, HomeArticle> {

    public static final int PER_PAGE = 20;
    public static final int FIRST_PAGE = 1;
    private ProjectApi projectApi;
    private int cid;
    public ProjectDataSource(int cid){
        this.cid = cid;
    }
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, HomeArticle> callback) {
        if(projectApi == null){
            projectApi = (ProjectApi)ApiRetrofit.getInstance(BaseApp.context).getApiService(ProjectApi.class);
        }
        projectApi.getClassifyProjects(FIRST_PAGE,cid).enqueue(new Callback<BaseRespone<BaseListData<HomeArticle>>>() {
            @Override
            public void onResponse(Call<BaseRespone<BaseListData<HomeArticle>>> call, Response<BaseRespone<BaseListData<HomeArticle>>> response) {
                if(response.body().getErrorCode() == 0){
                    callback.onResult(response.body().getData().getData(), null,FIRST_PAGE+1);
                }else{
                    Toast.makeText(BaseApp.context,response.body().getErrorMsg(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<BaseRespone<BaseListData<HomeArticle>>> call, Throwable t) {
                Toast.makeText(BaseApp.context,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, HomeArticle> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, HomeArticle> callback) {
        if(projectApi == null){
            projectApi = (ProjectApi)ApiRetrofit.getInstance(BaseApp.context).getApiService(ProjectApi.class);
        }
        projectApi.getClassifyProjects(params.key,cid).enqueue(new Callback<BaseRespone<BaseListData<HomeArticle>>>() {
            @Override
            public void onResponse(Call<BaseRespone<BaseListData<HomeArticle>>> call, Response<BaseRespone<BaseListData<HomeArticle>>> response) {
                if(response.body().getErrorCode() == 0){
                    Integer nextKey = response.body().getData().getPageCount() > params.key ? params.key + 1: null;
                    callback.onResult(response.body().getData().getData(), nextKey);
                }else{
                    Toast.makeText(BaseApp.context,response.body().getErrorMsg(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<BaseRespone<BaseListData<HomeArticle>>> call, Throwable t) {
                Toast.makeText(BaseApp.context,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
