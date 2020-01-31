package com.wkq.order.modlue.novel.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wkq.order.R;
import com.wkq.order.databinding.ItemNovelBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.zia.easybookmodule.bean.rank.HottestRankClassify;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-30
 * <p>
 * 用途:
 */


public class NovelAdapter extends DataBindingAdapter<HottestRankClassify> {
    Context context;

    public NovelAdapter(Context context) {
        super(context, R.layout.item_novel, com.wkq.qr.BR.data);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder dataBindingHolder = (DataBindingViewHolder) holder;
        ItemNovelBinding binding = (ItemNovelBinding) dataBindingHolder.getBinding();
        binding.setData(getItem(position));

        NovelItemAdapter novelItemAdapter = new NovelItemAdapter(context);
        binding.rvContent.setLayoutManager(new LinearLayoutManager(context));
        binding.rvContent.setAdapter(novelItemAdapter);
        novelItemAdapter.addItems(getItem(position).getRankBookList());

    }
}
