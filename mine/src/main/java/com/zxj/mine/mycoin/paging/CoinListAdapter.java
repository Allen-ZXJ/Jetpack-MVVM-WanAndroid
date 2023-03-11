package com.zxj.mine.mycoin.paging;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.zxj.common.bean.CoinListItem;
import com.zxj.common.utils.LogUtils;
import com.zxj.mine.R;
import com.zxj.mine.databinding.ItemMineMycoinBinding;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CoinListAdapter extends PagedListAdapter<CoinListItem, CoinListAdapter.CoinListViewHoder> {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private CoinListItem item;
    private int mPosition = -1;
    private int VIEW_TYPE = 0;
    private static final DiffUtil.ItemCallback<CoinListItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<CoinListItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull CoinListItem oldItem, @NonNull CoinListItem newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CoinListItem oldItem, @NonNull CoinListItem newItem) {
            return oldItem.equals(newItem);
        }
    };

    public CoinListAdapter() {
        super(DIFF_CALLBACK);

    }


    @NonNull
    @Override
    public CoinListViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtils.d("RV","onCreateViewHolder");
        ItemMineMycoinBinding mbind = (ItemMineMycoinBinding)DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mine_mycoin, parent, false);
        return new CoinListViewHoder(mbind);
    }


    @Override
    public void onBindViewHolder(@NonNull CoinListViewHoder holder, @SuppressLint("RecyclerView") int position) {
            item = getItem(position);
            if(item != null){
                holder.mItemBind.tvTime.setText(simpleDateFormat.format(new Date(item.getDate())));
                holder.mItemBind.tvCoin.setText(String.valueOf(item.getCoinCount()));
                holder.mItemBind.tvTitle.setText(String.valueOf(item.getReason()));
            }
    }

    public static class CoinListViewHoder extends RecyclerView.ViewHolder {
    ItemMineMycoinBinding mItemBind;
    public CoinListViewHoder(@NonNull ItemMineMycoinBinding bind) {
        super(bind.getRoot());
        mItemBind = bind;

    }
}
}
