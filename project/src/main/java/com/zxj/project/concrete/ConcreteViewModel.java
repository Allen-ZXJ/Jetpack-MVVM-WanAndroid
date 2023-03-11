package com.zxj.project.concrete;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.common.bean.HomeArticle;
import com.zxj.project.concrete.paging.ProjectDataSource;
import com.zxj.project.concrete.paging.ProjectDataSourceFactory;

public class ConcreteViewModel extends BaseViewModel<ConcreteRepository> {
    private LiveData<PagedList<HomeArticle>> projects;
    private int cid;

    public LiveData<PagedList<HomeArticle>> getProjects() {
        return projects;
    }

    public void setCid(int cid) {
        this.cid = cid;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPrefetchDistance(2)
                .setInitialLoadSizeHint(2 * ProjectDataSource.PER_PAGE)
                .setMaxSize(65536)
                .setPageSize(ProjectDataSource.PER_PAGE)
                .build();
        projects = new LivePagedListBuilder<>(new ProjectDataSourceFactory(cid),config).build();
    }
}
