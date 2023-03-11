package com.zxj.mine.mycoin.paging;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zxj.common.base.BaseApp;
import com.zxj.common.base.bean.BaseListData;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.bean.CoinListItem;
import com.zxj.common.network.ApiRetrofit;
import com.zxj.common.network.api.CoinApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoinListDataSource extends PageKeyedDataSource<Integer, CoinListItem> {
    public static final int PER_PAGE = 20;
    public static final int FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, CoinListItem> callback) {
        CoinApi coinApi = (CoinApi) ApiRetrofit.getInstance(BaseApp.context).getApiService(CoinApi.class);
        coinApi.getSelfCoinList(FIRST_PAGE).enqueue(new Callback<BaseRespone<BaseListData<CoinListItem>>>() {
            @Override
            public void onResponse(Call<BaseRespone<BaseListData<CoinListItem>>> call, Response<BaseRespone<BaseListData<CoinListItem>>> response) {
                callback.onResult(response.body().getData().getData(),null,FIRST_PAGE+1);
            }

            @Override
            public void onFailure(Call<BaseRespone<BaseListData<CoinListItem>>> call, Throwable t) {
                Toast.makeText(BaseApp.context,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, CoinListItem> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, CoinListItem> callback) {
        CoinApi coinApi = (CoinApi) ApiRetrofit.getInstance(BaseApp.context).getApiService(CoinApi.class);
        coinApi.getSelfCoinList(params.key).enqueue(new Callback<BaseRespone<BaseListData<CoinListItem>>>() {
            @Override
            public void onResponse(Call<BaseRespone<BaseListData<CoinListItem>>> call, Response<BaseRespone<BaseListData<CoinListItem>>> response) {
                    if(response.body()!=null){
                        Integer nextPage = response.body().getData().getPageCount() > params.key ? params.key + 1: null;
                        callback.onResult(response.body().getData().getData(), nextPage);
                    }
            }

            @Override
            public void onFailure(Call<BaseRespone<BaseListData<CoinListItem>>> call, Throwable t) {
                Toast.makeText(BaseApp.context,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
