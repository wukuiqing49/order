package com.wkq.order.modlue.web.view;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.squareup.picasso.Picasso;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.R;
import com.wkq.order.modlue.web.CustomSettings;
import com.wkq.order.modlue.web.ui.VideoWebviewActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *
 */

public class VideoWebView implements MvpView {

    VideoWebviewActivity mActivity;


    public VideoWebView(VideoWebviewActivity mActivity) {

        this.mActivity = mActivity;
    }


    public void initView() {
        initWaitingBg();
        initWebView();
    }

    private void initWaitingBg() {
        List<Integer> pics = new ArrayList<>();
        pics.add(R.mipmap.movie_1);
        pics.add(R.mipmap.movie_2);
        pics.add(R.mipmap.movie_3);
        pics.add(R.mipmap.movie_4);
        pics.add(R.mipmap.movie_5);
        pics.add(R.mipmap.movie_6);
        pics.add(R.mipmap.movie_7);
        pics.add(R.mipmap.movie_8);
        pics.add(R.mipmap.movie_9);

        //创建Random类对象
        Random random = new Random();
        int START = 0;   //定义范围开始数字

        int END = pics.size()-1;
        //产生随机数
        int number = random.nextInt(END - START + 1) + START;

        Integer x = pics.get(number);

        Picasso.with(mActivity).load(x).into(mActivity.binding.ivLoading);

    }

    public void initWebView() {
        WebChromeClient mWebChromeClient = new WebChromeClient() {
//
//            @Override
//            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
//
//
//                WebView newWebView = new WebView(mActivity);
//                view.addView(newWebView);
//
//                WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
//                transport.setWebView(newWebView);
//                resultMsg.sendToTarget();
//
//                newWebView.setWebViewClient(new WebViewClient(){
//
//                    @Override
//                    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
//                        //TODO 在这里实现你的拦截方法
//                        webView.loadUrl(url);
//                        return true;
//                    }
//
//                    @Override
//                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                        super.onPageStarted(view, url, favicon);
//                        mActivity.mAgentWeb.getJsAccessEntrace().callJs("document.getElementsByTagName(\"iframe\")[0].src");
//
//
//                    }
//
//                    @Override
//                    public void onPageFinished(WebView view, String url) {
//                        super.onPageFinished(view, url);
//                        mActivity.mAgentWeb.getJsAccessEntrace().callJs("var a=document.getElementById('player').parentNode;var b=a.children; for(var i =b.length-1; i>=0;i--){ if(b[i].id!='player'){a.removeChild(b[i]);}}");
//                        mActivity.binding.ivLoading.setVisibility(View.GONE);
//                    }
//                });
//                return true;
//            }


//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress>80){
////                    mActivity.mAgentWeb.getJsAccessEntrace().callJs("var a=document.getElementById('player').parentNode;var b=a.children; for(var i =b.length-1; i>=0;i--){ if(b[i].id!='player'){a.removeChild(b[i]);}}");
//                }
//                super.onProgressChanged(view, newProgress);
//
//                Log.e("","");
//
//
//            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

                if (!TextUtils.isEmpty(message)) {


//                    mActivity.mAgentWeb.getUrlLoader().loadUrl(message);
                    return true;
                } else {
                    return super.onJsPrompt(view, url, message, defaultValue, result);
                }
            }
        };

        WebViewClient webChromeClient = new WebViewClient() {


//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                view.loadUrl(url);
//                return false;
//            }
//
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                String url=request.getUrl().toString();
////                view.loadUrl(url);
//                return false;
//            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mActivity.binding.ivLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mActivity.mAgentWeb.getJsAccessEntrace().callJs("var a=document.getElementById('player').parentNode;var b=a.children; for(var i =b.length-1; i>=0;i--){ if(b[i].id!='player'){a.removeChild(b[i]);}}");
                mActivity.binding.ivLoading.setVisibility(View.GONE);
            }


        };


        mActivity.mAgentWeb = AgentWeb.with(mActivity)

                .setAgentWebParent(mActivity.binding.llRoot, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(mActivity.getResources().getColor(R.color.color_1aad19))
                .closeWebViewClientHelper()
                .setAgentWebWebSettings(new CustomSettings())
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(webChromeClient)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(mActivity.url);

        mActivity.mAgentWeb.getJsAccessEntrace().callJs("document.getElementsByTagName(\"iframe\")[0].src");


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
