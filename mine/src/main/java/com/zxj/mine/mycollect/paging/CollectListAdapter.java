package com.zxj.mine.mycollect.paging;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zxj.common.base.BaseApp;
import com.zxj.common.bean.CoinListItem;
import com.zxj.common.bean.CollectArticle;
import com.zxj.common.utils.Constraint;
import com.zxj.common.utils.LogUtils;
import com.zxj.mine.R;
import com.zxj.mine.databinding.ItemMineMycollectImgBinding;
import com.zxj.mine.databinding.ItemMineMycollectTvBinding;

public class CollectListAdapter extends PagedListAdapter<CollectArticle,RecyclerView.ViewHolder> {
    private final int TYPE_IMG_ITEM = -1;
    private final int  TYPE_TV_ITEM = -2;
    private CollectArticle item;
    private int mPosition = -1; //加逻辑，这样就不用在经常调用getItemViewType的时候new 操作
    private int VIEW_TYPE = 0; //加逻辑，避免onBindViewHolder在调用getItemViewType的时候因为position的改变而拿不到TYPE
    private static final DiffUtil.ItemCallback<CollectArticle> DIFF_CALLBACK = new DiffUtil.ItemCallback<CollectArticle>() {
        @Override
        public boolean areItemsTheSame(@NonNull CollectArticle oldItem, @NonNull CollectArticle newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CollectArticle oldItem, @NonNull CollectArticle newItem) {
            return oldItem.equals(newItem);
        }
    };

    public CollectListAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public int getItemViewType(int position) {
            item = getItem(position);
            if(item != null){
                if(!TextUtils.isEmpty(item.getEnvelopePic())){
                    return VIEW_TYPE = TYPE_IMG_ITEM;

                }else{
                    return VIEW_TYPE = TYPE_TV_ITEM;
                }
            }
            return VIEW_TYPE;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             if(viewType == TYPE_IMG_ITEM){
                 LogUtils.d("Image",viewType + "   " + TYPE_IMG_ITEM);
                 ItemMineMycollectImgBinding imgBind = (ItemMineMycollectImgBinding)DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mine_mycollect_img, parent, false);
                 return new ImgCollectArticleViewHolder(imgBind);
             }else{
                 LogUtils.d("Tv",viewType + "   " + TYPE_TV_ITEM);
                 ItemMineMycollectTvBinding tvBind = (ItemMineMycollectTvBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mine_mycollect_tv, parent, false);
                 return new TvCollectArticleViewHolder(tvBind);
             }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        switch (getItemViewType(position)){
            case TYPE_IMG_ITEM:
                ImgCollectArticleViewHolder mImgHolder = (ImgCollectArticleViewHolder) holder;
                mImgHolder.mbind.setArticle(item);
                mImgHolder.mbind.parent.setOnClickListener(l->{
                    ARouter.getInstance().build(Constraint.WEB_PATH)
                            .withString("title",item.getTitle())
                            .withString("webUrl",item.getLink())
                            .navigation();
                });
                break;
            case TYPE_TV_ITEM:
                TvCollectArticleViewHolder mTvHolder = (TvCollectArticleViewHolder) holder;
                mTvHolder.mbind.setArticle(item);
                mTvHolder.mbind.tvTitle.setOnClickListener(l->{
                    ARouter.getInstance().build(Constraint.WEB_PATH)
                            .withString("title",item.getTitle())
                            .withString("webUrl",item.getLink())
                            .navigation();
                });
                break;
            default:
                break;
        }
    }

    public static class ImgCollectArticleViewHolder extends RecyclerView.ViewHolder{
        ItemMineMycollectImgBinding mbind;
        public ImgCollectArticleViewHolder(@NonNull ItemMineMycollectImgBinding binding) {
            super(binding.getRoot());
            mbind = binding;
        }
    }

    public static class TvCollectArticleViewHolder extends RecyclerView.ViewHolder{
        ItemMineMycollectTvBinding mbind;
        public TvCollectArticleViewHolder(@NonNull ItemMineMycollectTvBinding binding) {
            super(binding.getRoot());
            mbind = binding;
        }
    }




}
