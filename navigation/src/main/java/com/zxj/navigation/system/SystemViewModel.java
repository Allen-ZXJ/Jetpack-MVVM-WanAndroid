package com.zxj.navigation.system;

import androidx.lifecycle.MutableLiveData;

import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.common.bean.SystemTree;

import java.util.ArrayList;

public class SystemViewModel extends BaseViewModel<SystemRepository> {
    private MutableLiveData<ArrayList<SystemTree>> systemTree;

    public MutableLiveData<ArrayList<SystemTree>> getSystemTree() {
        return systemTree;
    }
    public SystemViewModel(){
        systemTree = new MutableLiveData<>();
    }
    public void getSystemTrees(SystemFragment.SystemDataCallBack callBack){
        model.getSystemTrees(callBack);
    }

}
