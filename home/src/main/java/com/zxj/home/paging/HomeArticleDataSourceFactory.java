package com.zxj.home.paging;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import androidx.recyclerview.widget.RecyclerView;

import com.zxj.common.bean.HomeArticle;
import com.zxj.home.databinding.FgHomeBinding;

public class HomeArticleDataSourceFactory extends DataSource.Factory<Integer, HomeArticle> {


    @NonNull
    @Override
    public DataSource<Integer, HomeArticle> create() {
        return new HomeArticleDataSource();
    }
}
