package com.wkq.order.modlue.main.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;

import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityHomeBinding;
import com.wkq.order.modlue.main.frame.presenter.HomePresenter;
import com.wkq.order.modlue.main.frame.view.HomeView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class HomeActivity  extends MvpBindingActivity<HomeView, HomePresenter, ActivityHomeBinding> {

    public static void startPlayHelperActivity(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getMvpView()!=null)getMvpView().initView();
    }
}