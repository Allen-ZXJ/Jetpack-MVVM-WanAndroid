package com.zxj.home.banner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;

import com.youth.banner.adapter.BannerAdapter;
import com.zxj.common.bean.Banner;
import com.zxj.common.utils.Constraint;


import java.util.ArrayList;


public class MyBannerAdapter extends BannerAdapter<com.zxj.common.bean.Banner, MyBannerAdapter.BannerViewHolder> {

   public MyBannerAdapter(ArrayList<Banner> datas) {
      super(datas);
   }

   @Override
   public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
      ImageView imageView = new ImageView(parent.getContext());
      imageView.setLayoutParams(new ViewGroup.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT,
              ViewGroup.LayoutParams.MATCH_PARENT));
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      return new BannerViewHolder(imageView);
   }

   @Override
   public void onBindView(BannerViewHolder holder, Banner data, int position, int size) {
         Glide.with(holder.imageView)
              .load(data.getImagePath())
              .into(holder.imageView);
         holder.imageView.setOnClickListener(v->{
            ARouter.getInstance().build(Constraint.WEB_PATH)
                    .withString("title",data.getTitle())
                    .withString("webUrl",data.getUrl())
                    .navigation();
         });
   }

   protected static  class BannerViewHolder extends RecyclerView.ViewHolder{
      ImageView imageView;
      public BannerViewHolder(@NonNull ImageView view) {
         super(view);
         this.imageView = view;
      }
   }
}
