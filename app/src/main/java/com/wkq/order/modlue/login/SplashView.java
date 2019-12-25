package com.wkq.order.modlue.login;

import android.util.Log;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.base.utlis.TimerHelper;
import com.wkq.order.modlue.main.ui.HomeActivity;
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

    public SplashView(SplashActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {


        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setDarkMode(mActivity);
        MoveDbDataSaveUtlis.putType(mActivity);


        mActivity.timerHelper = new TimerHelper();
        mActivity.timerHelper.startTiming();
        mActivity.timerHelper.setSchedule(0);
        mActivity.timerHelper.setOnTimerListener(new TimerHelper.OnTimerListener() {
            @Override
            public void onSchedule(int schedule) {
                if (schedule == 2) {
                    HomeActivity.startPlayHelperActivity(mActivity);
                    mActivity.finish();
                }
                Log.e("倒计时:", schedule + "");
            }
        });
        mActivity.timerHelper.startTiming();

    }
}
