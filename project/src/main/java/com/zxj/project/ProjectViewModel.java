package com.zxj.project;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.common.bean.HomeArticle;
import com.zxj.common.bean.ProjectClassify;

import java.util.ArrayList;

public class ProjectViewModel extends BaseViewModel<ProjectRepository> {
    private MutableLiveData<ArrayList<ProjectClassify>> classifies;
    private LiveData<PagedList<HomeArticle>> projects;

    public MutableLiveData<ArrayList<ProjectClassify>> getClassifies() {
        return classifies;
    }

    public LiveData<PagedList<HomeArticle>> getProjects() {
        return projects;
    }

    public ProjectViewModel(){
        classifies = new MutableLiveData<>();
        projects = new MutableLiveData<>();
    }
    public void getClassify(ProjectCallBack callBack){
        model.getClassify(callBack);
    }

}
