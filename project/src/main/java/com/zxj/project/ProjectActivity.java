package com.zxj.project;

import com.zxj.common.base.mvvm.BaseMvvmActivity;
import com.zxj.project.databinding.AcProjectBinding;

public class ProjectActivity extends BaseMvvmActivity<AcProjectBinding,ProjectViewModel> {


    @Override
    protected int getLayoutId() {
        return R.layout.ac_project;
    }

    @Override
    protected int getVariableId() {
        return 0;
    }
}