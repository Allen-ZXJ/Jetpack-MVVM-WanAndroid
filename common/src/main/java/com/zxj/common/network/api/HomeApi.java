package com.zxj.common.network.api;

import com.zxj.common.base.bean.BaseListData;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.bean.Banner;
import com.zxj.common.bean.HomeArticle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeApi {

    @GET("article/list/{page}/json")
    Call<BaseRespone<BaseListData<HomeArticle>>> getHomeArticles(@Path("page")int page);
    @GET("banner/json")
    Call<BaseRespone<ArrayList<Banner>>> getBanner();


}
