package com.wkq.order.modlue.web.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.just.agentweb.AgentWeb;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityVideoWeb2Binding;
import com.wkq.order.modlue.web.presenter.VideoWebPresenter2;
import com.wkq.order.modlue.web.view.VideoWebView2;


public class VideoWebviewActivity2 extends MvpBindingActivity<VideoWebView2, VideoWebPresenter2, ActivityVideoWeb2Binding>  {

    public AgentWeb mAgentWeb;

    public String url;



    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, VideoWebviewActivity2.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_web2;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        url = getIntent().getStringExtra("url");

        if (getMvpView() != null) getMvpView().initView();
        binding.btAd.setOnClickListener(v -> {
            getMvpView().startTimer();
        });
    }



    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {

        if (!mAgentWeb.back()){
            this.finish();
        }else {
            mAgentWeb.getWebCreator().getWebView().goBack();
        }

    }
}
