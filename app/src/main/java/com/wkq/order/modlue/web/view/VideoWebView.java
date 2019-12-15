package com.wkq.order.modlue.web.view;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
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
import com.wkq.order.modlue.web.CustomSettings;
import com.wkq.order.modlue.web.ui.VideoWebviewActivity;

import java.util.HashMap;
import java.util.Map;


/**
 *
 */

public class VideoWebView implements MvpView {

    VideoWebviewActivity mActivity;
    private AgentWeb mAgentWeb;


    public VideoWebView(VideoWebviewActivity mActivity) {

        this.mActivity = mActivity;
    }


    public void initView() {
        mActivity.binding.toolbar.setTitleTextColor(Color.WHITE);
        mActivity.binding.toolbar.setTitle("");
        mActivity.binding.setOnClicker(mActivity);
        initWebView();
    }

    public void initWebView() {
        WebChromeClient mWebChromeClient = new WebChromeClient() {


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //do you work
            }
        };

        WebViewClient webChromeClient = new WebViewClient() {




            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.endsWith(".mp4")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(url), "video/*");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);

                    return true;
                } else if (url.startsWith("tel:") || url.startsWith("sms:") || url.startsWith("smsto:")
                        || url.startsWith("mms:") || url.startsWith("mmsto:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);

                    return true;
                } else {
                    return super.shouldOverrideUrlLoading(view, url);
                }
            }


            private Map<String, Boolean> loadedUrls = new HashMap<>();

            @SuppressWarnings("deprecation")
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                boolean ad;
                if (!loadedUrls.containsKey(url)) {
                    ad = AdBlocker.isAd(url);
                    loadedUrls.put(url, ad);
                } else {
                    ad = loadedUrls.get(url);
                }
                return ad ? AdBlocker.createEmptyResource() :
                        super.shouldInterceptRequest(view, url);
            }
        };


        mAgentWeb = AgentWeb.with(mActivity)

                .setAgentWebParent(mActivity.binding.llRoot, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setAgentWebWebSettings(new CustomSettings())
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(webChromeClient)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .createAgentWeb()
                .ready()
                .go("https://www.iqiyi.com/");
    }


    public void loadUrl(String url) {
        if (mAgentWeb != null) mAgentWeb.getUrlLoader().loadUrl(url);
    }

    public String getWebUrl() {
        return mAgentWeb.getWebCreator().getWebView().getUrl();
    }


}
