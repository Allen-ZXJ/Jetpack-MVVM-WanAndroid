package com.zxj.navigation.navi.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zxj.common.base.BaseApp;
import com.zxj.common.bean.HomeArticle;
import com.zxj.common.bean.NavArticles;
import com.zxj.common.utils.Constraint;
import com.zxj.common.utils.LogUtils;
import com.zxj.navigation.R;
import com.zxj.navigation.databinding.ItemNaviBinding;

import java.util.ArrayList;

public class NaviItemAdapter extends RecyclerView.Adapter<NaviItemAdapter.NavItemViewHolder>{
    private ArrayList<NavArticles> data;

    public NaviItemAdapter(ArrayList<NavArticles> data){
        this.data = data;
    }
    @NonNull
    @Override
    public NavItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNaviBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_navi, parent, false);
        return new NavItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NavItemViewHolder holder, int position) {
         holder.bind.flowLayout.removeAllViews();
         if(data != null){
             NavArticles item = data.get(position);
             holder.bind.tvTitle.setText(item.getName());
             ArrayList<HomeArticle> children = item.getArticles();
              if(children != null){
                  int childrenSize = children.size();
                   for(int j = 0; j < childrenSize; j++){
                       HomeArticle childrenItem = children.get(j);
                       TextView view = new TextView(holder.itemView.getContext());
                       view.setText(childrenItem.getTitle());
                       view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                       view.setBackground(holder.itemView.getResources().getDrawable(R.drawable.shape_navi_text));
                       holder.bind.flowLayout.addView(view);
                       view.setOnClickListener(v -> {
                           ARouter.getInstance().build(Constraint.WEB_PATH)
                                   .withString("title",childrenItem.getTitle())
                                   .withString("webUrl",childrenItem.getLink())
                                   .navigation();
                         });
                     }
                 }
             }

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class NavItemViewHolder extends RecyclerView.ViewHolder{
        ItemNaviBinding bind;
        public NavItemViewHolder(@NonNull ItemNaviBinding binding) {
            super(binding.getRoot());
            bind = binding;
        }
    }
}
