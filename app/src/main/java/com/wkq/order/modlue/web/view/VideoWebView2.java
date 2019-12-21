package com.wkq.order.modlue.web.view;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.R;
import com.wkq.order.modlue.web.CustomSettings;
import com.wkq.order.modlue.web.ui.VideoWebviewActivity;
import com.wkq.order.modlue.web.ui.VideoWebviewActivity2;


/**
 *
 */

public class VideoWebView2 implements MvpView {

    VideoWebviewActivity2 mActivity;


    public VideoWebView2(VideoWebviewActivity2 mActivity) {

        this.mActivity = mActivity;
    }


     public void initView() {

        initWebView();
    }

    public void initWebView() {
        WebChromeClient mWebChromeClient = new WebChromeClient() {


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress>80){
//                    mActivity.mAgentWeb.getJsAccessEntrace().callJs("var a=document.getElementById('player').parentNode;var b=a.children; for(var i =b.length-1; i>=0;i--){ if(b[i].id!='player'){a.removeChild(b[i]);}}");
                }
                super.onProgressChanged(view, newProgress);

                Log.e("","");


            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

                if (!TextUtils.isEmpty(message)){
                    VideoWebviewActivity.startActivity(mActivity,message);

//                    mActivity.mAgentWeb.getUrlLoader().loadUrl(message);
                    return  true;
                }else {
                    return super.onJsPrompt(view, url, message, defaultValue, result);
                }
            }
        };

        WebViewClient webChromeClient = new WebViewClient() {


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url=request.getUrl().toString();
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mActivity.binding.ivLoading.setVisibility(View.VISIBLE);
                mActivity.mAgentWeb.getJsAccessEntrace().callJs("document.getElementsByTagName(\"iframe\")[0].src");
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                mActivity.mAgentWeb.getJsAccessEntrace().callJs("var a=document.getElementById('player').parentNode;var b=a.children; for(var i =b.length-1; i>=0;i--){ if(b[i].id!='player'){a.removeChild(b[i]);}}");
                mActivity.binding.ivLoading.setVisibility(View.GONE);
            }



        };


        mActivity.mAgentWeb = AgentWeb.with(mActivity)

                .setAgentWebParent(mActivity.binding.llRoot, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(mActivity.getResources().getColor(R.color.color_23d41e))
                .closeWebViewClientHelper()
                .setAgentWebWebSettings(new CustomSettings())
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(webChromeClient)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(mActivity.url);




    }

    public void startTimer() {
//        mActivity.mAgentWeb.getJsAccessEntrace().callJs("var a=document.getElementById('player').parentNode;var b=a.children; for(var i =b.length-1; i>=0;i--){ if(b[i].id!='player'){a.removeChild(b[i]);}}");

        mActivity.mAgentWeb.getJsAccessEntrace().callJs("var src=document.getElementsByTagName(\"iframe\")[0].src;var result=prompt(src)");



//        mActivity.mAgentWeb.getJsAccessEntrace().callJs("var a=document.getElementById('player').parentNode;var b=a.children; for(var i =b.length-1; i>=0;i--){ if(b[i].id!='player'){a.removeChild(b[i]);}}");












//        TimerUtil timerUtil = new TimerUtil();
//        timerUtil.setTotalTime(30000_000);//设置毫秒数
//        timerUtil.setIntervalTime(5000);//设置间隔数
//        timerUtil.setTimerLiener(new TimerUtil.TimeListener() {
//            @Override
//            public void onFinish() {
//
//            }
//
//            @Override
//            public void onInterval(long remainTime) {
//
//            }
//        });
//        timerUtil.start();
    }

    public void loadUrl(String url) {
        if (mActivity.mAgentWeb != null) mActivity.mAgentWeb.getUrlLoader().loadUrl(url);
    }

    public String getWebUrl() {
        return mActivity.mAgentWeb.getWebCreator().getWebView().getUrl();
    }


}
