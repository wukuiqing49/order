package com.wkq.order.modlue.main.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityMainTestBinding;
import com.wkq.order.modlue.main.frame.presenter.MainPresenter;
import com.wkq.order.modlue.main.frame.view.MainView;
import com.wkq.order.modlue.web.ui.AdbWebActivity;
import com.wkq.order.modlue.web.ui.VideoWebviewActivity;
import com.wkq.qr.ui.activity.ScanActivity;

public class MainActivity extends MvpBindingActivity<MainView, MainPresenter, ActivityMainTestBinding> implements View.OnClickListener {


    public static void startUpdateActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setOnClicker(this);
        if (getMvpView()!=null)getMvpView().initData();
        if (getMvpView()!=null)getMvpView().initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_test;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
//
//            case R.id.tv_cam:
//
//                if (getMvpView() != null) getMvpView().openCam();
//                break;
//            case R.id.tv_scan:
//                ScanActivity.startScanActivity(this);
//
//                break;
//
//            case R.id.tv_web:
//                VideoWebviewActivity.startActivity(this);
//                break;
//
//            case R.id.tv_data:
////                if (getPresenter() != null) getPresenter().getData(this);
//                AdbWebActivity.startActivity(this,"");
//                break;

        }

    }
}
