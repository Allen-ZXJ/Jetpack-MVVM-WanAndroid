package com.zxj.common.network.api;

import com.zxj.common.base.bean.BaseListData;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.bean.CollectArticle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ActicleApi {

    @GET("lg/collect/list/{page}/json")
    Call<BaseRespone<BaseListData<CollectArticle>>>  getCollectActicles(@Path("page") int page);
}
