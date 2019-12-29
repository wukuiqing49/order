package com.wkq.order.modlue.main.ui.adapter;

import android.content.Context;

import androidx.databinding.Bindable;
import androidx.recyclerview.widget.RecyclerView;

import com.wkq.net.model.MoveDbTopRatedInfo;
import com.wkq.order.BR;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemMoveTopBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.wkq.order.utils.GlideUtlis;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class MoveTopAdapter extends DataBindingAdapter<MoveDbTopRatedInfo.ResultsBean> {
    Context context;

    public MoveTopAdapter(Context context) {
        super(context, R.layout.item_move_top, com.wkq.order.BR.data);
        this.context = context;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder bindingViewHolder = (DataBindingViewHolder) holder;
        ItemMoveTopBinding binding = (ItemMoveTopBinding) bindingViewHolder.getBinding();

        GlideUtlis.loadMoveImg200Round(context, getItem(position).getPoster_path(), binding.ivMovePoster);
    }
}
