package com.wkq.order.modlue.web.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;

import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.IAgentWebSettings;
import com.just.agentweb.WebChromeClient;

import com.just.agentweb.WebViewClient;
import com.wkq.base.adutlis.AdBlocker;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.R;
import com.wkq.order.modlue.web.CustomSettings;
import com.wkq.order.modlue.web.ui.VideoWebviewActivity;


import java.util.HashMap;
import java.util.Map;


/**
 *
 */

public class VideoWebView implements MvpView {

    VideoWebviewActivity mActivity;


    public VideoWebView(VideoWebviewActivity mActivity) {

        this.mActivity = mActivity;
    }


    public void initView() {

        initWebView();
    }

    public void initWebView() {
        WebChromeClient mWebChromeClient = new WebChromeClient() {


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }


        };

        WebViewClient webChromeClient = new WebViewClient() {


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mActivity.binding.ivLoading.setVisibility(View.VISIBLE);
                Log.e("kid", "onPageStarted=" + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);


                mActivity.mAgentWeb.getJsAccessEntrace().callJs("var a=document.getElementById(\"player\").parentNode;var b=a.children;\n" +
                        " for(var i =b.length-1; i>=0;i--){\n" +
                        "    if(b[i].id!=\"player\"){a.removeChild(b[i]);}\n" +
                        " }");
                mActivity.mAgentWeb.getJsAccessEntrace().callJs("var a=document.getElementsByTagName(\"__if_\");for(var i= a.length-1;i>=0;i--){a.removeChild[i]})");

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

        mActivity.mAgentWeb.getJsAccessEntrace().callJs("document.getElementsByTagName(\"iframe\")[0].src");
    }


    public void loadUrl(String url) {
        if (mActivity.mAgentWeb != null) mActivity.mAgentWeb.getUrlLoader().loadUrl(url);
    }

    public String getWebUrl() {
        return mActivity.mAgentWeb.getWebCreator().getWebView().getUrl();
    }


}
