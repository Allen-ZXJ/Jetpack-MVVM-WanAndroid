package com.zxj.common.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.zxj.common.utils.LogUtils;

public abstract class BaseFragment extends Fragment {
    String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected void init(){

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        LogUtils.d(TAG,"onAttach  ");
        mContext = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtils.d(TAG,"onViewCreated  ");
        init();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.d(TAG,"onDetach  ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG,"onDestroyView  ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG,"onDestroy  ");
    }
}
