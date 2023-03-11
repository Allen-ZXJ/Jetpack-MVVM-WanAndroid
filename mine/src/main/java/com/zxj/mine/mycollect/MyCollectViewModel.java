package com.zxj.mine.mycollect;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zxj.common.bean.CollectArticle;
import com.zxj.common.ui.viewmodel.BaseTopBarViewModel;
import com.zxj.mine.mycollect.paging.CollectListDataSource;
import com.zxj.mine.mycollect.paging.CollectListDataSourceFactory;

public class MyCollectViewModel extends BaseTopBarViewModel<MyCollectRepository> {
     private LiveData<PagedList<CollectArticle>> mArticleList;
     public MyCollectViewModel(){
         super();
         setTitle("我的收藏");
         initPagedList();
     }

    public LiveData<PagedList<CollectArticle>> getArticles() {
        return mArticleList;
    }

    private void initPagedList() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(CollectListDataSource.PER_PAGE)
                .setPageSize(CollectListDataSource.PER_PAGE)
                .setPrefetchDistance(2)
                .setMaxSize(65536 * CollectListDataSource.PER_PAGE)
                .build();
             mArticleList = new LivePagedListBuilder<>(new CollectListDataSourceFactory(),config).build();
    }
}
