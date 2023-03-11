package com.zxj.common.ui.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableField;

import com.zxj.common.base.mvvm.BaseModel;
import com.zxj.common.base.mvvm.BaseViewModel;

import java.lang.ref.WeakReference;

public class BaseTopBarViewModel<M extends BaseModel> extends BaseViewModel<M> {
      public ObservableField<String> title;
      private WeakReference<AppCompatActivity> context;
      public void registerContext(AppCompatActivity activity){
           context = new WeakReference<>(activity);
      }
      public BaseTopBarViewModel(){
          title = new ObservableField<>();
      }

      protected void setTitle(String titleText){
          title.set(titleText);
      }

      public void onPressBack(){
          context.get().finish();
      }
}
