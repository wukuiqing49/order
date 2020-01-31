package com.wkq.order.modlue.novel.frame.view;

import android.text.TextUtils;

import com.squareup.picasso.Picasso;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.order.modlue.novel.ui.activity.NovelInfoActivity;
import com.zia.easybookmodule.bean.Book;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-22
 * <p>
 * 用途:
 */


public class NoveInfolView implements MvpView {

    NovelInfoActivity mActivity;


    public NoveInfolView(NovelInfoActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
    }

    public void setBookData(Book bookData) {
        if (bookData == null) return;
        mActivity.binding.setData(bookData);
        if (!TextUtils.isEmpty(bookData.getImageUrl())) {
            Picasso.with(mActivity).load(bookData.getImageUrl()).into(mActivity.binding.ivDrop);
            Picasso.with(mActivity).load(bookData.getImageUrl()).into(mActivity.binding.ivIcon);
        }
    }
}
