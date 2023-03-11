package com.zxj.mine.mycollect.paging;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.zxj.common.bean.CollectArticle;

public class CollectListDataSourceFactory extends DataSource.Factory<Integer, CollectArticle> {

    @NonNull
    @Override
    public DataSource<Integer, CollectArticle> create() {
        return new CollectListDataSource();
    }
}
