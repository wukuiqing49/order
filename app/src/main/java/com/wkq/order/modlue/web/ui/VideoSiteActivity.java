package com.wkq.order.modlue.web.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



import com.just.agentweb.AgentWeb;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityVideoSiteBinding;
import com.wkq.order.modlue.web.presenter.VideoSitePresenter;
import com.wkq.order.modlue.web.view.VideoSiteView;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-18
 * <p>
 * 用途:
 */


public class VideoSiteActivity extends MvpBindingActivity<VideoSiteView, VideoSitePresenter, ActivityVideoSiteBinding> implements View.OnClickListener {

    public AgentWeb mAgentWeb;

    //String baseUrl= "http://jx.aeidu.cn/index.php?url=";
//    String baseUrl = "http://jx.618ge.com/?url=";
//    String baseUrl = "https://jiexi.bm6ig.cn/?url=";
//    String baseUrl = "https://jx.000180.top/jx/?url";
//    String baseUrl = "https://jiexi.bm6ig.cn/?url";
//    String baseUrl = "http://jx.618ge.com/?url=";
//    String baseUrl = "http://jx.du2.cc/?url=";
    //处理广告
    String baseUrl = "http://jx.du2.cc/?url";
//
//     	<option value="http://jx.du2.cc/?url=" selected="">⑤号通用vip引擎系统【全网解析】</option>
//      	<option value="http://jx.drgxj.com/?url=" selected="">④号通用vip引擎系统【超级稳定通用】</option>
//	  <option value="http://jx.618ge.com/?url=" selected="">③号通用vip引擎系统【稳定通用】</option>
//    <option value="http://vip.jlsprh.com/?url=" selected="">②号通用vip引擎系统【稳定通用】</option>
//	<option value="http://jx.drgxj.com/?url=" selected=""><span style="color:red;">无广告超速解析</span>【通用】</option>
//      <option value="http://jx.598110.com/?url=" selected="">①号通用vip引擎系统【稳定通用】</option>

    public String url;

    public static void startActivity(Context context ,String url) {
        Intent intent = new Intent(context, VideoSiteActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_site;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");
        binding.setListener(this);
        binding.cdPlay.setOnClickListener(this);

        if (getMvpView()!=null)getMvpView().initView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.cd_play:
                VideoWebviewActivity.startActivity(this,baseUrl.concat(getMvpView().getWebUrl()));
                break;

        }
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

}
