package com.wkq.order.modlue.web.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;

import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityFullVideoBinding;
import com.wkq.order.modlue.web.presenter.FullVideoPresenter;
import com.wkq.order.modlue.web.view.FullVideoView;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-12
 * <p>
 * 用途:
 */


public class FullVideoActivity extends MvpBindingActivity<FullVideoView, FullVideoPresenter, ActivityFullVideoBinding> {
    String s3 = "http://v.yongjiujiexi.com/20180304/B0cYHQvY/index.m3u8";
    private String url;


    public static void startActivity(Context context,String url){
        Intent intent =new Intent(context, FullVideoActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_full_video;
    }

    private void getdata() {
        binding.jiecaoPlayer.setUp(url,binding.jiecaoPlayer.SCREEN_LAYOUT_NORMAL,"视频标题");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        url = getIntent().getStringExtra("url");

        getdata();
    }



    @Override
    public void onResume() {
        // TODO Auto-generated method stub
//        getdata();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayerStandard.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            binding.jiecaoPlayer.releaseAllVideos();
        } catch (Exception e) {
        }
    }

}
