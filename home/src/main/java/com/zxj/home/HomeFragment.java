package com.zxj.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.youth.banner.indicator.CircleIndicator;
import com.zxj.common.base.BaseApp;
import com.zxj.common.base.mvvm.BaseMvvmFragment;
import com.zxj.common.bean.Banner;
import com.zxj.common.utils.ThreadUtils;
import com.zxj.home.banner.MyBannerAdapter;
import com.zxj.home.databinding.FgHomeBinding;
import com.zxj.home.paging.HomeArticleAdapter;

import java.util.ArrayList;

public class HomeFragment extends BaseMvvmFragment<FgHomeBinding,HomeViewModel> {
    private MyBannerAdapter mBannerAdapter;
    private HomeArticleAdapter mHomeArticleAdapter;
    private boolean isInit = false;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initViews() {
        mHomeArticleAdapter = new HomeArticleAdapter();
        mbind.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mbind.mRecyclerView.setAdapter(mHomeArticleAdapter);
        mViewModel.getHomeArticles().observe(this,homeArticles -> {
            mHomeArticleAdapter.submitList(homeArticles);
        });

        mViewModel.getBanners().observe(this,banners -> {
             if(!isInit){
                 mBannerAdapter = new MyBannerAdapter(banners);
                 mbind.mbanner.setAdapter(mBannerAdapter,true)
                              .setIndicator(new CircleIndicator(getActivity()))
                         .setIndicatorNormalColor(getResources().getColor(com.zxj.common.R.color.color_ffffff))
                         .setIndicatorSelectedColor(getResources().getColor(com.zxj.common.R.color.color_FF9A9E))
                         .setIndicatorHeight(10)
                         .setIndicatorWidth(20,50);
             }
        });
        mViewModel.getBanner(new BannerCallBack() {
            @Override
            public void onSuccess(ArrayList<Banner> banners) {
                mViewModel.getBanners().setValue(banners);
            }

            @Override
            public void onFailed(String message) {

            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_home;
    }

    @Override
    protected int initVariableId() {
        return 0;
    }
    public interface BannerCallBack{
        void onSuccess(ArrayList<Banner> banners);
        void onFailed(String message);
    }
}
