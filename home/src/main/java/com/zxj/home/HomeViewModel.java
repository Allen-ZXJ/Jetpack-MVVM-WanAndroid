package com.zxj.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.common.bean.Banner;
import com.zxj.common.bean.HomeArticle;
import com.zxj.home.paging.HomeArticleDataSource;
import com.zxj.home.paging.HomeArticleDataSourceFactory;

import java.util.ArrayList;

public class HomeViewModel extends BaseViewModel<HomeRepository> {
       private MutableLiveData<ArrayList<Banner>> banners;
       private LiveData<PagedList<HomeArticle>> homeArticles;

    public MutableLiveData<ArrayList<Banner>> getBanners() {
        return banners;
    }

    public LiveData<PagedList<HomeArticle>> getHomeArticles() {
        return homeArticles;
    }

    public HomeViewModel(){
           super();
           banners = new MutableLiveData<>();
           PagedList.Config config = new PagedList.Config.Builder()
                   .setMaxSize(65536 * HomeArticleDataSource.PER_PAGE)
                   .setPageSize(20)
                   .setInitialLoadSizeHint(HomeArticleDataSource.PER_PAGE * 2)
                   .setPrefetchDistance(20)
                   .setEnablePlaceholders(false)
                   .build();
           homeArticles = new LivePagedListBuilder<>(new HomeArticleDataSourceFactory(),config).build();


       }
       public void getBanner(HomeFragment.BannerCallBack callBack){
            model.getBanner(callBack);
       }

}
