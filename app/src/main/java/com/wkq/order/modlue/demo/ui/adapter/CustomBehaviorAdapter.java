package com.wkq.order.modlue.demo.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.wkq.order.R;
import com.wkq.order.databinding.ItemCustomBehaviorBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.wkq.qr.BR;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-11
 * <p>
 * 用途:
 */


public class CustomBehaviorAdapter extends DataBindingAdapter<String> {

    public CustomBehaviorAdapter(Context context) {
        super(context, R.layout.item_custom_behavior, com.wkq.qr.BR.data);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder bindingViewHolder = (DataBindingViewHolder) holder;

        ItemCustomBehaviorBinding behaviorBinding = (ItemCustomBehaviorBinding) bindingViewHolder.getBinding();

        behaviorBinding.tvContent.setText(getItem(position));


    }

}
