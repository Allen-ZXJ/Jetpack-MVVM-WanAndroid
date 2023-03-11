package com.zxj.mine.myshare;



import com.zxj.common.base.mvvm.BaseMvvmActivity;
import com.zxj.mine.BR;
import com.zxj.mine.R;
import com.zxj.mine.databinding.AcMineMyshareBinding;

public class MyShareActivity extends BaseMvvmActivity<AcMineMyshareBinding,MyShareViewModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.ac_mine_myshare;
    }

    @Override
    protected int getVariableId() {
        return BR.myShareViewModel;
    }
}
