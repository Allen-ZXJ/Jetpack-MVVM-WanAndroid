package com.zxj.navigation.system.concrete.paging;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.alibaba.android.arouter.launcher.ARouter;
import com.zxj.common.bean.HomeArticle;
import com.zxj.common.utils.Constraint;
import com.zxj.navigation.R;
import com.zxj.navigation.databinding.ItemConcreteSystemBinding;

public class SystemArticleAdapter extends PagedListAdapter<HomeArticle, SystemArticleAdapter.SystemArticleViewHolder> {

    private static final DiffUtil.ItemCallback<HomeArticle> DIFF_CALLBACK = new DiffUtil.ItemCallback<HomeArticle>() {
        @Override
        public boolean areItemsTheSame(@NonNull HomeArticle oldItem, @NonNull HomeArticle newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull HomeArticle oldItem, @NonNull HomeArticle newItem) {
            return oldItem.equals(newItem);
        }
    };

    public SystemArticleAdapter(){
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public SystemArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemConcreteSystemBinding binding = (ItemConcreteSystemBinding)DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_concrete_system, parent, false);
        return new SystemArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemArticleViewHolder holder, int position) {
        HomeArticle item = getItem(position);
        if(item != null){
            holder.bind.setArticle(item);
            if(item.getFresh()){
                holder.bind.tvRefresh.setVisibility(View.VISIBLE);
            }
            if(item.getCollect()){
                holder.bind.ivCollect.setImageResource(R.drawable.ic_collect);
            }else{
                holder.bind.ivCollect.setImageResource(R.drawable.ic_uncollect);
            }
            String author = TextUtils.isEmpty(item.getAuthor())?"佚名":item.getAuthor();
            holder.bind.tvAuthor.setText(author);

            holder.bind.tvTitle.setOnClickListener(l->{
                ARouter.getInstance().build(Constraint.WEB_PATH)
                        .withString("title",item.getTitle())
                        .withString("webUrl",item.getLink())
                        .navigation();
            });

        }
    }

    public static class SystemArticleViewHolder extends RecyclerView.ViewHolder{

     ItemConcreteSystemBinding bind;
     public SystemArticleViewHolder(@NonNull ItemConcreteSystemBinding binding) {
         super(binding.getRoot());
         bind = binding;
     }
 }

}
