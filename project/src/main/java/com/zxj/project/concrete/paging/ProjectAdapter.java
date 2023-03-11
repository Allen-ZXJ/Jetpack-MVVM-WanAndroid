package com.zxj.project.concrete.paging;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zxj.common.bean.HomeArticle;
import com.zxj.common.utils.Constraint;
import com.zxj.project.R;
import com.zxj.project.databinding.ItemConcreteProjectBinding;

public class ProjectAdapter extends PagedListAdapter<HomeArticle, ProjectAdapter.ProjectViewHolder> {
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

    public ProjectAdapter(){
        super(DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemConcreteProjectBinding concreteProjectBinding= (ItemConcreteProjectBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_concrete_project, parent, false);
        return new ProjectViewHolder(concreteProjectBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        HomeArticle item = getItem(position);
        if(item != null){
            holder.bind.setProject(item);
            int imgSrcId = item.getCollect() ? R.drawable.ic_collect : R.drawable.ic_uncollect;
            holder.bind.ivCollect.setImageResource(imgSrcId);

            String author = TextUtils.isEmpty(item.getAuthor())? "佚名" : item.getAuthor();
            holder.bind.tvAuthor.setText(author);

            if(item.getFresh()){
                holder.bind.tvFresh.setVisibility(View.VISIBLE);
            }

            holder.bind.parent.setOnClickListener(l->{
                ARouter.getInstance().build(Constraint.WEB_PATH)
                        .withString("title",item.getTitle())
                        .withString("webUrl",item.getLink())
                        .navigation();
            });

        }


    }

    protected static class ProjectViewHolder extends RecyclerView.ViewHolder{
        ItemConcreteProjectBinding bind;
        public ProjectViewHolder(@NonNull ItemConcreteProjectBinding binding) {
            super(binding.getRoot());
            bind = binding;
        }
    }
}
