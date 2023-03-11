package com.zxj.mine.mine;



import android.content.Context;
import android.content.Intent;

import com.zxj.common.base.mvvm.BaseViewModel;
import com.zxj.common.base.mvvm.BaseMvvmActivity;
import com.zxj.mine.R;
import com.zxj.mine.databinding.AcMineMainBinding;

public class MineActivity extends BaseMvvmActivity<AcMineMainBinding, BaseViewModel> {

    public static void launch(Context context){
        context.startActivity(new Intent(context,MineActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_mine_main;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}