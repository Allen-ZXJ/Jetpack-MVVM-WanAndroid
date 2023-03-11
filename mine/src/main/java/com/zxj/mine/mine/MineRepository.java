package com.zxj.mine.mine;

import com.zxj.common.base.BaseApp;
import com.zxj.common.base.mvvm.BaseModel;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.bean.Coin;
import com.zxj.common.network.ApiRetrofit;
import com.zxj.common.network.api.CoinApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MineRepository extends BaseModel {
//    public void
public void getMyCoin(MineFragment.CoinCallBack callBack){
    CoinApi mCoinApi = (CoinApi) ApiRetrofit.getInstance(BaseApp.context).getApiService(CoinApi.class);
    mCoinApi.getSelfCoin().enqueue(new Callback<BaseRespone<Coin>>() {
        @Override
        public void onResponse(Call<BaseRespone<Coin>> call, Response<BaseRespone<Coin>> response) {
               callBack.onSuccess((Coin) response.body().getData());
        }

        @Override
        public void onFailure(Call<BaseRespone<Coin>> call, Throwable t) {
            callBack.onFailed(t.getMessage());
        }
    });

}
}
