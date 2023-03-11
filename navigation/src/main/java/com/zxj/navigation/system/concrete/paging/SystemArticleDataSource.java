package com.zxj.navigation.system.concrete.paging;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zxj.common.base.BaseApp;
import com.zxj.common.base.bean.BaseListData;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.bean.HomeArticle;
import com.zxj.common.network.ApiRetrofit;
import com.zxj.common.network.api.NaviApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SystemArticleDataSource extends PageKeyedDataSource<Integer, HomeArticle> {
    private int cid;
    private NaviApi systemApi;
    private int FIRST_PAGE = 0; //接口以0开始，数据以1开始
    public SystemArticleDataSource(int id){
        cid = id;
    }
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, HomeArticle> callback) {
           if(systemApi == null){
               systemApi = (NaviApi) ApiRetrofit.getInstance(BaseApp.context).getApiService(NaviApi.class);
           }
           systemApi.getSystemArticles(FIRST_PAGE,cid).enqueue(new Callback<BaseRespone<BaseListData<HomeArticle>>>() {
               @Override
               public void onResponse(Call<BaseRespone<BaseListData<HomeArticle>>> call, Response<BaseRespone<BaseListData<HomeArticle>>> response) {
                   if(response.body().getErrorCode() == 0){
                       callback.onResult(response.body().getData().getData(), null,FIRST_PAGE + 1);
                   }
               }

               @Override
               public void onFailure(Call<BaseRespone<BaseListData<HomeArticle>>> call, Throwable t) {
                   Toast.makeText(BaseApp.context, t.getMessage(), Toast.LENGTH_SHORT).show();
               }
           });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, HomeArticle> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, HomeArticle> callback) {
        if(systemApi == null){
            systemApi = (NaviApi) ApiRetrofit.getInstance(BaseApp.context).getApiService(NaviApi.class);
        }
        systemApi.getSystemArticles(params.key,cid).enqueue(new Callback<BaseRespone<BaseListData<HomeArticle>>>() {
            @Override
            public void onResponse(Call<BaseRespone<BaseListData<HomeArticle>>> call, Response<BaseRespone<BaseListData<HomeArticle>>> response) {
                if(response.body().getErrorCode() == 0){
                    Integer nextKey = response.body().getData().getPageCount() > params.key + 1? params.key + 1: null;
                    callback.onResult(response.body().getData().getData(), nextKey);
                }else{
                    Toast.makeText(BaseApp.context, response.body().getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseRespone<BaseListData<HomeArticle>>> call, Throwable t) {
                Toast.makeText(BaseApp.context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
