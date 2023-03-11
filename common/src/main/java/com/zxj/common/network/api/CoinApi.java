package com.zxj.common.network.api;

import com.zxj.common.base.bean.BaseListData;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.bean.Coin;
import com.zxj.common.bean.CoinListItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CoinApi {

    /**
     * 获取个人积分
     * @return
     */
    @GET("lg/coin/userinfo/json")
    Call<BaseRespone<Coin>> getSelfCoin();


    /**
     * 获取个人积分列表
     *
     * @param page
     * @return
     */
    @GET("lg/coin/list/{page}/json")
    Call<BaseRespone<BaseListData<CoinListItem>>> getSelfCoinList(@Path("page") int page);


    
}
