package com.zxj.common.base.mvvm;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.zxj.common.base.BaseActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseMvvmActivity<V extends ViewDataBinding,VM extends BaseViewModel> extends BaseActivity {
    protected V mbind;
    protected VM mViewModel;
    @Override
    public void afterOnCreate(Bundle savedInstanceState) {
        initDataBinding(savedInstanceState);
        getLifecycle().addObserver(mViewModel);

    }

    private void initDataBinding(Bundle savedInstanceState){
        mbind = DataBindingUtil.setContentView(this,getLayoutId());
        if(mViewModel == null){
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if(type instanceof ParameterizedType){
                modelClass = (Class) ((ParameterizedType)type).getActualTypeArguments()[1];
            }else{
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            mViewModel = (VM) new ViewModelProvider(this,
                              new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                              .get(modelClass);

        }
        if(getVariableId() > 0){
            //为DataBinding设置值
            mbind.setVariable(getVariableId(),mViewModel);
        }


    }
    /**
     * 初始化根布局
     * @return 布局layout的id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化ViewModel的id
     * @return BR的id
     */
    protected abstract int getVariableId();
}
