package com.zxj.mine.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.tencent.mmkv.MMKV;
import com.zxj.common.base.mvvm.BaseMvvmFragment;
import com.zxj.common.bean.Coin;
import com.zxj.common.utils.CookieUtils;
import com.zxj.common.utils.LogUtils;
import com.zxj.common.utils.MMkvUtils;
import com.zxj.mine.BR;
import com.zxj.mine.R;
import com.zxj.mine.databinding.FgMineMainBinding;


public class MineFragment extends BaseMvvmFragment<FgMineMainBinding, MineViewModel>  {
    private String TAG = "Coin";
    private boolean refresh = true;
    @Override
    public int getLayoutId() {
        return R.layout.fg_mine_main;
    }

    @Override
    protected int initVariableId() {
        return BR.mineViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestNetwork();
    }

    @Override
    protected void initViews() {
        mViewModel.registerContext(getActivity());
        LogUtils.d(TAG, "this is: " + Thread.currentThread().getName());
        mViewModel.getmCoin().observe(this, (coin -> {
            if(coin != null){
                mbind.tvId.setText("ID:"+coin.getUserId());
                mbind.tvMyCoin.setText("当前积分: " +coin.getCoinCount());
                mbind.tvName.setText(coin.getUserName());
                mbind.tvRank.setText("rank:"+coin.getRank());
                mbind.tvRank.setVisibility(View.VISIBLE);

            }
        }));

        if(CookieUtils.isExpired()){
            mbind.tvId.setText("ID: ");
            mbind.tvMyCoin.setText("当前积分: ");
            mbind.tvName.setText("未登录");
        }

        Coin mCoin = MMkvUtils.getMyCoin();
        if(mCoin != null){
             mViewModel.getmCoin().setValue(mCoin);
        }else {
              requestNetwork();
        }
        //第一次进来请求一次网络更新数据
        if(refresh){
            requestNetwork();
            refresh = false;
        }

    }


    /**
     * 切换到Fragment后执行一次网络请求，可以更新数据
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        requestNetwork();
    }

    private void requestNetwork(){
        mViewModel.getCoin(new CoinCallBack() {
            @Override
            public void onSuccess(Coin coin) {
                LogUtils.d(TAG, "onSuccess: " + Thread.currentThread().getName() + "   " + coin);
                MMkvUtils.saveMyCoin(coin);
                mViewModel.getmCoin().postValue(coin);
            }
            @Override
            public void onFailed(String message) {
                LogUtils.d(TAG, "onFailed: " + message);
            }
        });
    }


    /**
     * 不用担心内存泄漏问题，因为已经默认是static了，并不会持有外部类对象
     */
    protected interface CoinCallBack{
        void onSuccess(Coin coin);
        void onFailed(String message);
    }


}
