package com.wkq.order.modlue.login;

import android.os.Bundle;
import android.util.Log;

import androidx.databinding.ViewDataBinding;

import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.base.utlis.TimerHelper;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivitySplashBinding;
import com.wkq.order.modlue.main.ui.HomeActivity;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class SplashActivity extends MvpBindingActivity<SplashView, SplashPresenter, ActivitySplashBinding> {

    public TimerHelper timerHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getMvpView() != null) getMvpView().initView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timerHelper != null)
            timerHelper.onTimerCancel();
    }
}
