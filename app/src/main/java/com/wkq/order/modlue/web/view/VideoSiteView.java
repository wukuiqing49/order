package com.wkq.order.modlue.web.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.wkq.base.adutlis.AdBlocker;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.R;
import com.wkq.order.modlue.web.CustomSettings;
import com.wkq.order.modlue.web.ui.VideoSiteActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-18
 * <p>
 * 用途:
 */


public class VideoSiteView implements MvpView {

    VideoSiteActivity mActivity;

    public VideoSiteView(VideoSiteActivity mActivity) {
        this.mActivity=mActivity;
    }

    public void initView() {
        WebChromeClient mWebChromeClient = new WebChromeClient() {


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        };

        WebViewClient webChromeClient = new WebViewClient() {
        };


        mActivity.mAgentWeb = AgentWeb.with(mActivity)
                .setAgentWebParent(mActivity.binding.llRoot, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(mActivity.getResources().getColor(R.color.color_23d41e))
                .setAgentWebWebSettings(new CustomSettings())
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(webChromeClient)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go(mActivity.url);
    }

    public String getWebUrl() {
        return mActivity.mAgentWeb==null?"":mActivity.mAgentWeb.getWebCreator().getWebView().getUrl();
    }
}