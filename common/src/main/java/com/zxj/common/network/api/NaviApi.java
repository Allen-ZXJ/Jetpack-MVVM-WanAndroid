package com.zxj.common.network.api;

import com.zxj.common.base.bean.BaseListData;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.bean.HomeArticle;
import com.zxj.common.bean.NavArticles;
import com.zxj.common.bean.SystemTree;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NaviApi {
    /**
     * 获取体系数据
     */
   @GET("tree/json")
   Call<BaseRespone<ArrayList<SystemTree>>> getSystemTree();

   /**
    *  获取体系下的文章
    */
   @GET("article/list/{page}/json")
   Call<BaseRespone<BaseListData<HomeArticle>>> getSystemArticles(@Path("page")int page, @Query("cid")int cid);

    /**
     * 获取导航数据
     */
   @GET("navi/json")
   Call<BaseRespone<ArrayList<NavArticles>>> getNavData();
}
