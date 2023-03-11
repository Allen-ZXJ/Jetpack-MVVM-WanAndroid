package com.zxj.common.network.api;

import com.zxj.common.base.bean.BaseListData;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.bean.HomeArticle;
import com.zxj.common.bean.ProjectClassify;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProjectApi {
    @GET("project/tree/json")
    Call<BaseRespone<ArrayList<ProjectClassify>>> getClassify();

    @GET("project/list/{page}/json")
    Call<BaseRespone<BaseListData<HomeArticle>>> getClassifyProjects(@Path("page")int page,  @Query("cid")int cid);
}
