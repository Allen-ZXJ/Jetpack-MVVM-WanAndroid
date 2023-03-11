package com.zxj.login.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import com.zxj.common.base.BaseApp;
import com.zxj.common.base.bean.BaseRespone;
import com.zxj.common.base.mvvm.BaseMvvmFragment;
import com.zxj.common.bean.UserInfo;
import com.zxj.common.network.ApiRetrofit;
import com.zxj.common.network.api.LoginApi;
import com.zxj.common.utils.LogUtils;
import com.zxj.login.BR;
import com.zxj.login.R;
import com.zxj.login.ResultCallBack;
import com.zxj.login.databinding.FgLoginLoginBinding;
import com.zxj.login.ui.viewmodel.LoginViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends BaseMvvmFragment<FgLoginLoginBinding, LoginViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.fg_login_login;
    }

    @Override
    protected int initVariableId() {
       return BR.loginViewModel;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected void initEvent() {
        mbind.setLoginEventHandler(new LoginEventHandler());
    }

    @Override
    protected void initViews() {
        mViewModel.registerContext(getActivity());
    }


    private void onPressBack() {
        getActivity().finish();
    }

    private void onPressLogin() {
         String username = mbind.etUsername.getText().toString().trim();
         String password = mbind.etPassword.getText().toString().trim();
         LogUtils.d("Login",username + "    " + password);
         mViewModel.login(username, password, new ResultCallBack() {
             @Override
             public void onSuccess(UserInfo info) {
                 //userInfo暂时不用
                 LogUtils.d("Login", info.getNickname());
                 Toast.makeText(getActivity(),"登陆成功",Toast.LENGTH_LONG).show();
             }

             @Override
             public void onFailed(String message) {
                 Toast.makeText(getActivity(),"登陆失败： " + message,Toast.LENGTH_LONG).show();
             }
         });

    }


       public class LoginEventHandler{

            public void onClick(int pos){
                switch (pos){
                    case 1:
                        onPressBack();
                        break;
                    case 2:
                        onPressRegister();

                        break;
                    case 3:
                        onPressLogin();
                        break;
                    default:
                        break;
                }
            }

           private void onPressRegister() {
               //LoginFragment.this是在内部类调用外部类方法的时候用的
               NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_registerFragment);
           }

       }


}
