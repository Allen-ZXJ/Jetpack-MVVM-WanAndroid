package com.zxj.mine.mine;


import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;

import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.common.bean.Coin;
import com.zxj.common.utils.LogUtils;
import com.zxj.library_aop.checklogin.annotation.CheckLogin;
import com.zxj.mine.mycoin.MyCoinActivity;
import com.zxj.mine.mycollect.MyCollectActivity;

import java.lang.ref.WeakReference;

public class MineViewModel extends BaseViewModel<MineRepository> {
    private WeakReference<FragmentActivity> mContext;
    private MutableLiveData<Coin> mCoin = new MutableLiveData<>();
    public void registerContext(FragmentActivity activity){
        mContext = new WeakReference<>(activity);
    }
    public void onSetClick(){
          onItemClick(11);
    }

    public MutableLiveData<Coin> getmCoin() {
        return mCoin;
    }

    public void setmCoin(MutableLiveData<Coin> mCoin) {
        this.mCoin = mCoin;
    }
    public void getCoin(MineFragment.CoinCallBack callBack){
        model.getMyCoin(callBack);
    }

    @CheckLogin
    public void onItemClick(int pos){
        switch (pos){
            case 0:
                //我的积分
                MyCoinActivity.launch(mContext.get());
                break;
            case 1:
                //我的收藏
                MyCollectActivity.launch(mContext.get());
                break;
            case 2:
                //我的分享
                LogUtils.d("arouter","2已登录");
                break;
            case 3:
                //开源项目
                LogUtils.d("arouter","3已登录");
                break;
            case 4:
                //关于作者
                LogUtils.d("arouter","4已登录");
                break;
            case 5:
                //设置
                LogUtils.d("arouter","5已登录");
                break;
            case 6:
                //积分排行榜
                LogUtils.d("arouter","7已登录");
                break;
            default:
                break;

        }
    }




 }
