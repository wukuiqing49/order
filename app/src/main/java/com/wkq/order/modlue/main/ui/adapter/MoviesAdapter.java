package com.wkq.order.modlue.main.ui.adapter;

import android.content.Context;

import com.wkq.order.R;
import com.wkq.net.model.MoviesInfo;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class MoviesAdapter  extends DataBindingAdapter<MoviesInfo>{
    public MoviesAdapter(Context context) {
        super(context, R.layout.item_movie_info, com.wkq.order.BR.data);
    }
}
