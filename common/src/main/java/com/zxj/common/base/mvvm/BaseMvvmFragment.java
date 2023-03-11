package com.zxj.common.base.mvvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.zxj.common.base.BaseFragment;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseMvvmFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment {
    protected V mbind;
    protected VM mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mbind = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        return mbind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewDataBinding();
        getLifecycle().addObserver(mViewModel);
        initViews();
        initEvent();
    }

    protected void initEvent(){

    }

    protected void initViews(){}

    private void initViewDataBinding() {
          if(mViewModel == null){
              Class modelClass;
              Type type = getClass().getGenericSuperclass();
              if(type instanceof ParameterizedType){
                  modelClass =  (Class)((ParameterizedType) type).getActualTypeArguments()[1];
              }else{
                  modelClass = BaseViewModel.class;
              }
              mViewModel = (VM)new ViewModelProvider(this,
                           new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication()))
                          .get(modelClass);
          }
         if(initVariableId() > 0){
             mbind.setVariable(initVariableId(),mViewModel);
         }
    }

    /**
     * 初始化根布局
     * @return 布局layout的id
     */
    protected abstract int getLayoutId();


    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    protected abstract int initVariableId();
}
