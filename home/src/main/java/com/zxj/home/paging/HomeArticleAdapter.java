package com.zxj.home.paging;

import android.content.Context;
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
import com.bumptech.glide.Glide;
import com.zxj.common.bean.HomeArticle;
import com.zxj.common.utils.Constraint;
import com.zxj.home.R;
import com.zxj.home.databinding.FgHomeBinding;
import com.zxj.home.databinding.ItemHomeArticlesBinding;

public class HomeArticleAdapter extends PagedListAdapter<HomeArticle, HomeArticleAdapter.HomeArticleViewHolder> {

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

    public HomeArticleAdapter(){
        super(DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public HomeArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeArticlesBinding homeArticleBinding = (ItemHomeArticlesBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_home_articles, parent, false);
        return new HomeArticleViewHolder(homeArticleBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeArticleViewHolder holder, int position) {
        HomeArticle item = getItem(position);
        if(item != null){
            holder.bind.setHomeArticle(item);
            int imgSrcId = item.getCollect() ? R.drawable.ic_collect : R.drawable.ic_uncollect;
            holder.bind.ivCollect.setImageResource(imgSrcId);

            String author = TextUtils.isEmpty(item.getAuthor())? "佚名" : item.getAuthor();
            holder.bind.tvAuthor.setText(author);

            if(item.getFresh()){
                holder.bind.tvFresh.setVisibility(View.VISIBLE);
            }
            holder.bind.tvTitle.setOnClickListener(v -> {
                ARouter.getInstance().build(Constraint.WEB_PATH)
                        .withString("title",item.getTitle())
                        .withString("webUrl",item.getLink())
                        .navigation();
            });

        }


    }

    protected static class HomeArticleViewHolder extends RecyclerView.ViewHolder{
        ItemHomeArticlesBinding bind;
        public HomeArticleViewHolder(@NonNull ItemHomeArticlesBinding binding) {
            super(binding.getRoot());
            bind = binding;
        }
    }
}
