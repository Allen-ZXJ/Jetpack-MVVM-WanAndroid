package com.zxj.common.ui.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.zxj.common.utils.LogUtils;

public class ImageViewBindingAdapter {
    private static final String TAG = "ImageBinding";
    //如果重载方法有多个参数的，会优先调用多个参数的

    //2.这里说一下优先级：从网络加载进来图片后，
    // (1)Picasso.get().load(url).placeholder(R.drawable.ic_launcher_background).into(imageView);
    // (2)优先级是大于imageView.setImageResource(resId);
    //即 使用(1)加载图片后，再使用(2)也无法改变图片内容

    //加载网络图片
    @BindingAdapter("image")
    public static void setImage(ImageView imageView, String url){
        LogUtils.d(TAG, "setImage: "  + "我是网络图片" + imageView.getContext());
        if(!TextUtils.isEmpty(url)){
            Glide.with(imageView.getContext())
                 .load(url)
                 .into(imageView);
        }else{
            imageView.setBackgroundColor(imageView.getResources().getColor(com.zxj.common.R.color.color_FF9A9E));
        }
    }

    //加载本地图片
    @BindingAdapter("defaultImageResource")
    public static void setImage(ImageView imageView, int resId){
        LogUtils.d(TAG, "setImage: "  + "我是本地图片");
        imageView.setImageResource(resId);
    }

    //    //参数可选，网络图片为空时，加载本地图片
    @BindingAdapter(value = {"image", "defaultImageResource"}, requireAll = false)
    public static void setImage(ImageView imageView, String url, int resId){

        if(!TextUtils.isEmpty(url)){
            LogUtils.d(TAG, "setImage: "  + imageView + "   " + imageView.getContext() +"   我是本地+网络图片   " + url);
            Glide.with(imageView.getContext())
                 .load(url)
                 .into(imageView);
        }else{
            imageView.setImageResource(resId);
        }
    }
}
