package com.wkq.order.modlue.novel.frame.view;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.modlue.novel.ui.fragment.NovelFragment;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-29
 * <p>
 * 用途:
 */


public class NovelView implements MvpView {
    NovelFragment mFragment;

    public NovelView(NovelFragment mFragment) {
        this.mFragment = mFragment;
    }
}
