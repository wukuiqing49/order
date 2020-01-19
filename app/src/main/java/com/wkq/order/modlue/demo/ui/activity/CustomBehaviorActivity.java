package com.wkq.order.modlue.demo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityCustomBehaviorBinding;
import com.wkq.order.modlue.demo.presenter.CustomBehaviorPresenter;
import com.wkq.order.modlue.demo.view.CustomBehaviorView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-11
 * <p>
 * 用途:自定义behavior
 */


public class CustomBehaviorActivity extends MvpBindingActivity<CustomBehaviorView, CustomBehaviorPresenter, ActivityCustomBehaviorBinding> {

    public static void startActivity(Context context) {

        Intent intent = new Intent();
        intent.setClass(context, CustomBehaviorActivity.class);
        context.startActivity(intent);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_behavior;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getMvpView()!=null)getMvpView().initView();
    }
}
