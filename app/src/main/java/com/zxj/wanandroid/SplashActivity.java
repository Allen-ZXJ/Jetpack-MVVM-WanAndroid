package com.zxj.wanandroid;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.zxj.common.base.mvvm.BaseMvvmActivity;
import com.zxj.common.utils.ThreadUtils;
import com.zxj.wanandroid.databinding.ActivitySplashBinding;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends BaseMvvmActivity<ActivitySplashBinding,SplashViewModel> {
    MutableLiveData<Integer> numText;

    @Override
    public void initEvents() {
        executeWork(()->{
            for(int i = 3; i >= 0; i--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(isFinishing()){
                    return;
                }
                mViewModel.getNumText().postValue(i);
            }
            if(isFinishing()){
                return;
            }
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        });
    }

    @Override
    public void afterOnCreate(Bundle savedInstanceState) {
        super.afterOnCreate(savedInstanceState);
        mViewModel.registerContext(this);
        numText = mViewModel.getNumText();
        numText.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer textNum) {
                mbind.textNum.setText(String.valueOf(textNum));
            }
        });
    }



//    public void startMain(View view) {
////        Log.d("thread", "startMain: " + t1.getId() + "    " + android.os.Process.myTid());
//        startActivity(new Intent(this,MainActivity.class));
//        finish();
//    }


    private void executeWork(Runnable work){
        ThreadUtils.execute(work);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getVariableId() {
        return BR.splashViewModel;
    }


    private void executeDelayWork(Runnable work,int delay,TimeUnit unit){
        ThreadUtils.executeDelayWork(work,delay,unit);
    }

}
