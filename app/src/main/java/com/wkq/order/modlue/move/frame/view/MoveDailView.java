package com.wkq.order.modlue.move.frame.view;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.AlertUtil;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.order.modlue.move.ui.MoveDetailActivity;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-27
 * <p>
 * 用途:
 */


public class MoveDailView implements MvpView {


    MoveDetailActivity mActivity;

    public MoveDailView(MoveDetailActivity activity) {
        mActivity = activity;
    }

    public void initView() {
        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setDarkMode(mActivity);

    }


    public void showMessage(String message) {
        if (mActivity == null || mActivity.isFinishing()) return;
        AlertUtil.showDeftToast(mActivity, message);
    }
}
