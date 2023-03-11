package com.zxj.mine.mycollect.paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zxj.common.base.BaseApp;
import com.zxj.common.base.bean.BaseListData;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.bean.CollectArticle;
import com.zxj.common.network.ApiRetrofit;
import com.zxj.common.network.api.ActicleApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectListDataSource extends PageKeyedDataSource<Integer, CollectArticle> {
    public static final int PER_PAGE = 20;
    public static final int FIRST_PAGE = 0;
    private ActicleApi acticleApi;
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, CollectArticle> callback) {
        if(acticleApi == null){
             acticleApi = (ActicleApi) ApiRetrofit.getInstance(BaseApp.context).getApiService(ActicleApi.class);
        }
        acticleApi.getCollectActicles(FIRST_PAGE).enqueue(new Callback<BaseRespone<BaseListData<CollectArticle>>>() {
            @Override
            public void onResponse(Call<BaseRespone<BaseListData<CollectArticle>>> call, Response<BaseRespone<BaseListData<CollectArticle>>> response) {
                     callback.onResult(response.body().getData().getData(), null,FIRST_PAGE+1);
            }

            @Override
            public void onFailure(Call<BaseRespone<BaseListData<CollectArticle>>> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, CollectArticle> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, CollectArticle> callback) {
        if(acticleApi == null){
            acticleApi = (ActicleApi) ApiRetrofit.getInstance(BaseApp.context).getApiService(ActicleApi.class);
        }
        acticleApi.getCollectActicles(params.key).enqueue(new Callback<BaseRespone<BaseListData<CollectArticle>>>() {
            @Override
            public void onResponse(Call<BaseRespone<BaseListData<CollectArticle>>> call, Response<BaseRespone<BaseListData<CollectArticle>>> response) {
                Integer nextKey = response.body().getData().getPageCount() > params.key + 1? params.key + 1 : null;
                callback.onResult(response.body().getData().getData(), nextKey);
            }

            @Override
            public void onFailure(Call<BaseRespone<BaseListData<CollectArticle>>> call, Throwable t) {

            }
        });
    }
}
