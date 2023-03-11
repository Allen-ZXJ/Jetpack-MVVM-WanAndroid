package com.zxj.login.ui.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.navigation.fragment.NavHostFragment;

import com.zxj.common.base.mvvm.BaseMvvmFragment;
import com.zxj.common.bean.UserInfo;
import com.zxj.login.R;
import com.zxj.login.ResultCallBack;
import com.zxj.login.databinding.FgLoginRegisterBinding;
import com.zxj.login.ui.viewmodel.RegisterViewModel;

public class RegisterFragment extends BaseMvvmFragment<FgLoginRegisterBinding, RegisterViewModel> {
    @Override
    public int getLayoutId() {
        return R.layout.fg_login_register;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void initEvent() {
        mbind.setRegisterHandler(new RegisterEventHandler());
    }

    @Override
    protected void initViews() {
        mViewModel.registerContext(getActivity());
    }

    @Override
    protected int initVariableId() {
        return BR.registerViewModel;
    }

    private void onPressRegister() {
        String userName = mbind.etUsername.getText().toString().trim();
        String password = mbind.etPassword.getText().toString().trim();
        String repassword = mbind.etRepassword.getText().toString().trim();
        mViewModel.register(userName, password, repassword, new ResultCallBack() {
            @Override
            public void onSuccess(UserInfo info) {
                Toast.makeText(getActivity(),"注册成功!!!",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailed(String message) {
                Toast.makeText(getActivity(),"注册失败:  " + message,Toast.LENGTH_LONG).show();
            }
        });
    }
    public class RegisterEventHandler{
        public void onClick(int pos){
            switch (pos){
                case 1:
                    onPressBack();
                    break;
                case 2:
                    onPressRegister();
                    break;
                default:
                    break;
            }
        }
        private void onPressBack() {
            NavHostFragment.findNavController(RegisterFragment.this).navigateUp();
        }
    }
}
