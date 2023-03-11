package com.zxj.common.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.zxj.common.utils.LogUtils;

public abstract class BaseActivity extends AppCompatActivity implements IAcView {
   String TAG = this.getClass().getSimpleName();

   @Override
   public void initViews() {

   }

   @Override
   public void initEvents() {

   }

   @Override
   public void beforeOnCreate() {

   }

   @Override
   public void afterOnCreate(Bundle savedInstanceState) {

   }

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      beforeOnCreate();
      super.onCreate(savedInstanceState);
      LogUtils.d(TAG,"onCreate     :" + this.getClass().getSimpleName());
      setTranslucentStatus();
      afterOnCreate(savedInstanceState);
      initViews();
      initEvents();
   }

   @Override
   protected void onStart() {
      super.onStart();
      LogUtils.d(TAG,"onStart     :" + this.getClass().getSimpleName());
   }

   @Override
   protected void onResume() {
      super.onResume();
      LogUtils.d(TAG,"onResume     :" + this.getClass().getSimpleName());
   }

   @Override
   protected void onPause() {
      super.onPause();
      LogUtils.d(TAG,"onPause     :" + this.getClass().getSimpleName());
   }

   @Override
   protected void onStop() {
      super.onStop();
      LogUtils.d(TAG,"onStop     :" + this.getClass().getSimpleName());
   }

   @Override
   protected void onDestroy() {
      super.onDestroy();
      LogUtils.d(TAG,"onDestroy     :" + this.getClass().getSimpleName());
   }

   @Override
   protected void onSaveInstanceState(@NonNull Bundle outState) {
      super.onSaveInstanceState(outState);
      LogUtils.d(TAG,"onSaveInstanceState     :" + this.getClass().getSimpleName());
   }

   @Override
   protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
      super.onRestoreInstanceState(savedInstanceState);
      LogUtils.d(TAG,"onRestoreInstanceState     :" + this.getClass().getSimpleName());
   }

   //设置状态栏透明
   private void setTranslucentStatus() {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
         Window window = this.getWindow();
         View decorView = window.getDecorView();
         //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
         int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                 | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
         decorView.setSystemUiVisibility(option);
         window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
         window.setStatusBarColor(Color.TRANSPARENT);
         //导航栏颜色也可以正常设置
         //window.setNavigationBarColor(Color.TRANSPARENT);
      } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
         Window window = this.getWindow();
         WindowManager.LayoutParams attributes = window.getAttributes();
         int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
         attributes.flags |= flagTranslucentStatus;
         //int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
         //attributes.flags |= flagTranslucentNavigation;
         window.setAttributes(attributes);
      }
   }


}
