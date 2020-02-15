package com.wkq.order.modlue.login;


import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.order.modlue.main.ui.activity.HomeActivity;
import com.wkq.order.utils.MoveDbDataSaveUtlis;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class SplashView implements MvpView {
    SplashActivity mActivity;

    String TAG="广告";




    public SplashView(SplashActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        initLoad();

        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setLightMode(mActivity);
        MoveDbDataSaveUtlis.putType(mActivity);

    }

    private void initLoad() {
    }

    public void jumpHomeActivity() {
        HomeActivity.startPlayHelperActivity(mActivity);
        mActivity.finish();
    }


    public void initGoogleAd() {


    }
}
