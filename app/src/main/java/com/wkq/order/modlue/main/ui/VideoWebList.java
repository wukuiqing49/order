package com.wkq.order.modlue.main.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityMainTestBinding;
import com.wkq.order.modlue.main.frame.presenter.VideoWebPresenter;
import com.wkq.order.modlue.main.frame.view.VideoWebView;

public class VideoWebList extends MvpBindingActivity<VideoWebView, VideoWebPresenter, ActivityMainTestBinding> implements View.OnClickListener {


    public static void startVideo(Context context) {
        Intent intent = new Intent(context, VideoWebList.class);
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
            case R.id.tv_play_help:

                PlayHelperActivity.startPlayHelperActivity(this);
                break;
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
