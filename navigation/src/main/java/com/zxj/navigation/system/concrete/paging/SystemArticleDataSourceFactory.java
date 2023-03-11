package com.zxj.navigation.system.concrete.paging;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.zxj.common.bean.HomeArticle;

public class SystemArticleDataSourceFactory extends DataSource.Factory<Integer, HomeArticle>{
    private int cid;
    public SystemArticleDataSourceFactory(int cid){
        this.cid = cid;
    }
    @NonNull
    @Override
    public DataSource<Integer, HomeArticle> create() {
        return new SystemArticleDataSource(cid);
    }
}
