package com.wkq.order.modlue.web.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.amnix.adblockwebview.ui.AdBlocksWebViewActivity;
import com.wkq.base.adutlis.AdBlocker;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityVideoWebBinding;
import com.wkq.order.modlue.web.presenter.VideoWebPresenter;
import com.wkq.order.modlue.web.view.VideoWebView;


public class VideoWebviewActivity extends MvpBindingActivity<VideoWebView, VideoWebPresenter, ActivityVideoWebBinding> implements View.OnClickListener {

//    String baseUrl= "http://jx.aeidu.cn/index.php?url=";
    String baseUrl= "https://api.spjx.live/?url=";
//    https://www.iqiyi.com/v_19rrk406qo.html

//     <select class="form-control input-lg" id="jk">
//      	<option value="http://jx.du2.cc/?url=" selected="">⑤号通用vip引擎系统【稳定通用】</option>
//      	<option value="http://jx.drgxj.com/?url=" selected="">④号通用vip引擎系统【超级稳定通用】</option>
//	  <option value="http://jx.618ge.com/?url=" selected="">③号通用vip引擎系统【稳定通用】</option>
//    <option value="http://vip.jlsprh.com/?url=" selected="">②号通用vip引擎系统【稳定通用】</option>
//	<option value="http://jx.drgxj.com/?url=" selected=""><span style="color:red;">无广告超速解析</span>【通用】</option>
//      <option value="http://jx.598110.com/?url=" selected="">①号通用vip引擎系统【稳定通用】</option>

    public static void startActivity(Context context){
        Intent intent =new Intent(context, VideoWebviewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_web;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdBlocker.init(this);
        if(getMvpView()!=null)getMvpView().initView();
    }



    @Override
    public void onClick(android.view.View view) {
        switch (view.getId()) {

            case R.id.tv_get_url:

                if (getMvpView() != null)
//                    getMvpView().loadUrl(baseUrl.concat(getMvpView().getWebUrl()));

                AdBlocksWebViewActivity.startWebView(VideoWebviewActivity.this,baseUrl.concat(getMvpView().getWebUrl()),getResources().getColor(R.color.colorPrimary));


                break;
        }
        }
}
