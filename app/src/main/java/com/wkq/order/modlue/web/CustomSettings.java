package com.wkq.order.modlue.web;


import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.just.agentweb.AbsAgentWebSettings;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultDownloadImpl;
import com.just.agentweb.IAgentWebSettings;
import com.just.agentweb.WebListenerManager;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-12
 * <p>
 * 用途:
 */
public class CustomSettings extends AbsAgentWebSettings {
    public CustomSettings() {
        super();
    }

    @Override
    protected void bindAgentWebSupport(AgentWeb agentWeb) {

    }


    @Override
    public IAgentWebSettings toSetting(WebView webView) {
        super.toSetting(webView);

        getWebSettings().setBlockNetworkImage(false);//是否阻塞加载网络图片  协议http or https
        getWebSettings().setAllowFileAccess(false); //允许加载本地文件html  file协议, 这可能会造成不安全 , 建议重写关闭
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            getWebSettings().setAllowFileAccessFromFileURLs(false); //通过 file mUrl 加载的 Javascript 读取其他的本地文件 .建议关闭
//            getWebSettings().setAllowUniversalAccessFromFileURLs(false);//允许通过 file mUrl 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
//        }
        getWebSettings().setJavaScriptEnabled(true);
        getWebSettings().setAppCacheEnabled(false);
        getWebSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getWebSettings().setDisplayZoomControls(false);
            getWebSettings().setAllowUniversalAccessFromFileURLs(false);//允许通过 file mUrl 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源

        }
//        getWebSettings().setDefaultTextEncodingName("gb2312");//设置编码格式
//        getWebSettings().setDefaultFontSize(16);
//        getWebSettings().setMinimumFontSize(12);//设置 WebView 支持的最小字体大小，默认为 8
//        getWebSettings().setGeolocationEnabled(true);

        getWebSettings().setBuiltInZoomControls(true);
        getWebSettings().setSupportZoom(true);
        getWebSettings().setDomStorageEnabled(true);

//        getWebSettings().setJavaScriptEnabled(true);
//        getWebSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        getWebSettings().setSupportZoom(false); //支持缩放
//        getWebSettings().setBuiltInZoomControls(false); //支持手势缩放
//        getWebSettings().setDisplayZoomControls(false); //是否显示缩放按钮
//        getWebSettings().setDefaultTextEncodingName("utf-8"); //设置文本编码
//        getWebSettings().setUseWideViewPort(false); //将图片调整到适合WebView的大小
//        getWebSettings().setLoadWithOverviewMode(true); //自适应屏幕
//        getWebSettings().setDomStorageEnabled(true);
//        getWebSettings().setSaveFormData(true);
//        getWebSettings().setSupportMultipleWindows(true);
//        getWebSettings().setAppCacheEnabled(true);
//        getWebSettings().setPluginState(WebSettings.PluginState.ON);//支持插件
//        getWebSettings().setCacheMode(WebSettings.LOAD_NO_CACHE); //不适用缓存
//        getWebSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局
//        webView.setOverScrollMode(View.OVER_SCROLL_NEVER); // 取消WebView中滚动或拖动到顶部、底部时的阴影
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); // 取消滚动条白边效果
//        // >= 19(SDK4.4)启动硬件加速，否则启动软件加速
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//            getWebSettings().setLoadsImagesAutomatically(true); //支持自动加载图片
//        } else {
//            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//            getWebSettings().setLoadsImagesAutomatically(false);
//        }





        return this;
    }

    @Override
    public WebListenerManager setDownloader(WebView webView, DownloadListener downloadListener) {
        return super.setDownloader(webView,
                DefaultDownloadImpl.create((Activity) webView.getContext()
                        , webView, mAgentWeb.getPermissionInterceptor()));
    }
}