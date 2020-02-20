package com.wkq.order.modlue.web.view;

import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.utils.CustomSettings;
import com.wkq.order.modlue.web.ui.VideoSiteActivity;

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
        this.mActivity = mActivity;
    }

    public void initView() {
        StatusBarUtil.setStatusBarWrite(mActivity);
        StatusBarUtil.setColor(mActivity, mActivity.getResources().getColor(R.color.white), 0);
        StatusBarUtil.setLightMode(mActivity);
        WebChromeClient mWebChromeClient = new WebChromeClient() {


            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress > 80 && mActivity.binding.cdPlay.getVisibility() == View.GONE) {
                    mActivity.binding.cdPlay.setVisibility(View.VISIBLE);
                }
            }
        };

//        WebViewClient webChromeClient = new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(request.getUrl().toString());
//                if (request.getUrl().toString().startsWith("http") || request.getUrl().toString().startsWith("https")) {
//                    return false;
//                } else {
//                    return true;
//                }
//
//
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                mActivity.mAgentWeb.getJsAccessEntrace().callJs("var a=document.getElementById('open_app_iframe').parentNode;var b=a.children; for(var i =b.length-1; i>=0;i--){ if(b[i].id!='open_app_iframe'){a.removeChild(b[i]);}}");
//                mActivity.mAgentWeb.getJsAccessEntrace().callJs("var elements = document.getElementsByClassName('bottom_fixed');\n" +
//                        "while(elements.length > 0){\n" +
//                        "elements[0].parentNode.removeChild(elements[0]);\n" +
//                        "}");
//            }
//
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//                Log.e("", "");
//            }
//        };


        mActivity.mAgentWeb = AgentWeb.with(mActivity)
                .setAgentWebParent(mActivity.binding.llRoot, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(mActivity.getResources().getColor(R.color.color_23d41e))
                .setAgentWebWebSettings(new CustomSettings())
                .setWebChromeClient(mWebChromeClient)
//                .setWebViewClient(webChromeClient)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .createAgentWeb()
                .ready()
                .go("https://m.iqiyi.com/v_19rrok4nt0.html");

//        mActivity.mAgentWeb.getJsAccessEntrace().callJs("document.getElementsByTagName(\"body\")[0].removeChild(document.getElementsByTagName(\"iframe\")[0])");

    }

    public String getWebUrl() {
        return mActivity.mAgentWeb == null ? "" : mActivity.mAgentWeb.getWebCreator().getWebView().getUrl();
    }
}
