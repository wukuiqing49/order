package com.wkq.order.modlue.main.frame.view;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.MainActivity;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/11/9
 * <p>
 * 简介:
 */
public class MainView implements MvpView {
    MainActivity mActivity;

    public MainView(MainActivity activity) {
        mActivity = activity;
    }
}
