package com.zxj.navigation.system.concrete;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zxj.common.bean.HomeArticle;
import com.zxj.common.ui.viewmodel.BaseTopBarViewModel;
import com.zxj.navigation.system.concrete.paging.SystemArticleDataSourceFactory;

public class SystemArticleViewModel extends BaseTopBarViewModel<SystemArticleRepository> {
      private LiveData<PagedList<HomeArticle>> articles;
      private int PAGE_SIZE = 20;

      public LiveData<PagedList<HomeArticle>> getArticles() {
        return articles;
     }
      public void setSelfTitle(String title){
          setTitle(title);
      }
      public void setSystemId(int cid){
          PagedList.Config config = new PagedList.Config.Builder()
                  .setPageSize(PAGE_SIZE)
                  .setMaxSize(65536 * PAGE_SIZE)
                  .setPrefetchDistance(5)
                  .setInitialLoadSizeHint(PAGE_SIZE * 2)
                  .setEnablePlaceholders(false)
                  .build();
          articles = new LivePagedListBuilder<>(new SystemArticleDataSourceFactory(cid),config).build();
      }
}
