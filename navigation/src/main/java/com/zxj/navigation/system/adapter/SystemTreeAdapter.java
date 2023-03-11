package com.zxj.navigation.system.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.zxj.common.bean.SystemTree;
import com.zxj.navigation.R;
import com.zxj.navigation.databinding.ItemNaviBinding;
import com.zxj.navigation.databinding.ItemSystemBinding;
import com.zxj.navigation.system.concrete.SystemArticleActivity;

import java.util.ArrayList;

public class SystemTreeAdapter extends RecyclerView.Adapter<SystemTreeAdapter.SystemTreeViewHolder>{
   private ArrayList<SystemTree> data;
   public SystemTreeAdapter(ArrayList<SystemTree> data){
      this.data = data;
   }
   @NonNull
   @Override
   public SystemTreeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      ItemSystemBinding bind = (ItemSystemBinding)DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_system, parent, false);
      return new SystemTreeViewHolder(bind);
   }

   @Override
   public void onBindViewHolder(@NonNull SystemTreeViewHolder holder, int position) {
      holder.bind.flowLayout.removeAllViews();
      SystemTree tree = data.get(position);
      if(tree != null){
         String title = tree.getName();
         ArrayList<SystemTree> children = tree.getChildren();
         int size = children.size();
         holder.bind.tvTitle.setText(title);
         Context context = holder.bind.flowLayout.getContext();
         for(int i = 0; i < size; i++){
            SystemTree childrenTree = children.get(i);
            TextView view = new TextView(context);
            view.setBackground(holder.bind.flowLayout.getResources().getDrawable(R.drawable.shape_navi_text));
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            view.setText(childrenTree.getName());
            holder.bind.flowLayout.addView(view);
            view.setOnClickListener(v -> {
               Intent intent = new Intent(context,SystemArticleActivity.class);
               intent.putExtra("systemID",childrenTree.getId());
               intent.putExtra("systemName",title);
               context.startActivity(intent);
            });
         }
      }

   }


   @Override
   public int getItemCount() {
      return data == null ? 0 : data.size();
   }

   protected static class SystemTreeViewHolder extends RecyclerView.ViewHolder{
      ItemSystemBinding bind;
      public SystemTreeViewHolder(@NonNull ItemSystemBinding binding) {
         super(binding.getRoot());
         bind = binding;

      }
   }
}
