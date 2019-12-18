package com.wkq.order.modlue.web.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.just.agentweb.AgentWeb;
import com.wkq.base.adutlis.AdBlocker;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityVideoWebBinding;
import com.wkq.order.modlue.web.presenter.VideoWebPresenter;
import com.wkq.order.modlue.web.view.VideoWebView;


public class VideoWebviewActivity extends MvpBindingActivity<VideoWebView, VideoWebPresenter, ActivityVideoWebBinding> implements View.OnClickListener {

    public AgentWeb mAgentWeb;

    //    String baseUrl= "http://jx.aeidu.cn/index.php?url=";
    String baseUrl = "http://jx.618ge.com/?url=";
//    https://www.iqiyi.com/v_19rrk406qo.html

//     <select class="form-control input-lg" id="jk">
//      <option value="http://jx.du2.cc/?url=" selected="">⑤号通用vip引擎系统【稳定通用】</option>
//      <option value="http://jx.drgxj.com/?url=" selected="">④号通用vip引擎系统【超级稳定通用】</option>
//	  <option value="http://jx.618ge.com/?url=" selected="">③号通用vip引擎系统【稳定通用】</option>
//    <option value="http://vip.jlsprh.com/?url=" selected="">②号通用vip引擎系统【稳定通用】</option>
//	<option value="http://jx.drgxj.com/?url=" selected=""><span style="color:red;">无广告超速解析</span>【通用】</option>
//      <option value="http://jx.598110.com/?url=" selected="">①号通用vip引擎系统【稳定通用】</option>


//    https://cdn.yangju.vip/k/?url=
//    https://jx.lache.me/cc/?url=
//    https://api.653520.top/vip/?url=
//    https://jx.ab33.top/vip/?url=
//    https://vip.mpos.ren/v/?url=
//    https://jx.000180.top/jx/?url=
//    https://jx.km58.top/jx/?url=
//    https://api.smq1.com/?url=
//    https://jx.hezeshi.net/ce/jlexi.php?url=
//    https://www.kkflv.com/?url=		    //Btjson智能解析
//    https://jx.618g.com/?url=

//知乎帖子
//    https://zhuanlan.zhihu.com/p/34401379


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, VideoWebviewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_web;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        AdBlocker.init(this);
        if (getMvpView() != null) getMvpView().initView();
    }


    @Override
    public void onClick(android.view.View view) {

        getMvpView().loadUrl(baseUrl.concat(getMvpView().getWebUrl()));
        switch (view.getId()) {




//            case R.id.tv_get_url:
//
//                if (getMvpView() != null)
//
//
////                FullVideoActivity.startActivity(this,"");
//
////                AdBlocksWebViewActivity.startWebView(VideoWebviewActivity.this,baseUrl.concat(getMvpView().getWebUrl()),getResources().getColor(R.color.colorPrimary));
//
//
//                break;
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
