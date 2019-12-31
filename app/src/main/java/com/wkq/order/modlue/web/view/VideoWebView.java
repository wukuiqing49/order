package com.wkq.order.modlue.web.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdDislike;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTFullScreenVideoAd;
import com.bytedance.sdk.openadsdk.TTImage;
import com.bytedance.sdk.openadsdk.TTNativeAd;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.squareup.picasso.Picasso;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.AlertUtil;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.utils.CustomSettings;
import com.wkq.order.modlue.web.ui.VideoWebviewActivity;
import com.wkq.order.utils.TTAdManagerHolder;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.wkq.order.utils.Constant.MOVE_TT_AD_STREAM_ID;
import static com.wkq.order.utils.Constant.MOVE_TT_AD_VIDEO_ID;


/**
 *
 */

public class VideoWebView implements MvpView {

    VideoWebviewActivity mActivity;

    TTAdNative mTTAdNative;

    private TTNativeExpressAd mTTAd;
    private long startTime = 0;

    private boolean isOclick = false;


    private TTFullScreenVideoAd mttFullVideoAd;

    public VideoWebView(VideoWebviewActivity mActivity) {

        this.mActivity = mActivity;
    }


    public void initView() {

        initAd();

        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setDarkMode(mActivity);

        initWaitingBg();
        initWebView();
    }


    private void initAd() {
        //step1:初始化sdk
        mTTAdNative = TTAdManagerHolder.get().createAdNative(mActivity);

//step3:可选，申请部分权限，如read_phone_state,防止获取不了imei时候，下载类广告没有填充的问题。
        TTAdManagerHolder.get().requestPermissionIfNecessary(mActivity);


        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId("943999020") //广告位id
                .setSupportDeepLink(true)
                .setAdCount(1) //请求广告数量为1到3条
                .setExpressViewAcceptedSize(600, 400) //期望模板广告view的size,单位dp
                .setImageAcceptedSize(600, 400)//这个参数设置即可，不影响模板广告的size
                .build();
        mTTAdNative.loadInteractionExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {
            @Override
            public void onError(int code, String message) {
                AlertUtil.showDeftToast(mActivity, message);
                mActivity.finish();
            }

            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                if (ads == null || ads.size() == 0) {
                    return;
                }
                mTTAd = ads.get(0);
                bindAdListener(mTTAd);
                startTime = System.currentTimeMillis();
                mTTAd.render();
            }
        });


    }

    private void bindAdListener(TTNativeExpressAd ad) {
        ad.setExpressInteractionListener(new TTNativeExpressAd.AdInteractionListener() {
            @Override
            public void onAdDismiss() {

//                showMessage("广告关闭");

                if (mActivity.mAgentWeb != null)
                    mActivity.mAgentWeb.getUrlLoader().loadUrl(mActivity.url);


            }

            @Override
            public void onAdClicked(View view, int type) {
                isOclick = true;

            }

            @Override
            public void onAdShow(View view, int type) {

//                showMessage("广告展示");
            }

            @Override
            public void onRenderFail(View view, String msg, int code) {

//                showMessage(msg);
                mActivity.finish();
            }

            @Override
            public void onRenderSuccess(View view, float width, float height) {
                //返回view的宽高 单位 dp

//                showMessage("渲染成功");
                mTTAd.showInteractionExpressAd(mActivity);

            }
        });

//
//        if (ad.getInteractionType() != TTAdConstant.INTERACTION_TYPE_DOWNLOAD) {
//            return;
//        }
//        ad.setDownloadListener(new TTAppDownloadListener() {
//            @Override
//            public void onIdle() {
////                TToast.show(InteractionExpressActivity.this, "点击开始下载", Toast.LENGTH_LONG);
//                showMessage("点击开始下载");
//            }
//
//            @Override
//            public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {
//                if (!mHasShowDownloadActive) {
//                    mHasShowDownloadActive = true;
////                    TToast.show(InteractionExpressActivity.this, "下载中，点击暂停", Toast.LENGTH_LONG);
//                    showMessage("下载中，点击暂停");
//                }
//            }
//
//            @Override
//            public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {
////                TToast.show(InteractionExpressActivity.this, "下载暂停，点击继续", Toast.LENGTH_LONG);
//
//                showMessage("下载暂停，点击继续");
//            }
//
//            @Override
//            public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {
////                TToast.show(InteractionExpressActivity.this, "下载失败，点击重新下载", Toast.LENGTH_LONG);
//                showMessage("下载失败，点击重新下载");
//            }
//
//            @Override
//            public void onInstalled(String fileName, String appName) {
////                TToast.show(InteractionExpressActivity.this, "安装完成，点击图片打开", Toast.LENGTH_LONG);
//                showMessage("安装完成，点击图片打开");
//            }
//
//            @Override
//            public void onDownloadFinished(long totalBytes, String fileName, String appName) {
////                TToast.show(InteractionExpressActivity.this, "点击安装", Toast.LENGTH_LONG);
//
//                showMessage("点击安装");
//            }
//        });


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

        int END = pics.size() - 1;
        //产生随机数
        int number = random.nextInt(END - START + 1) + START;
        Integer x = pics.get(number);
        Picasso.with(mActivity).load(x).into(mActivity.binding.ivBg);

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

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) return false;
                if (url.startsWith("http:") || url.startsWith("https:") ){
                    view.loadUrl(url);
                    return false;
                }else{
                    try{
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        mActivity.startActivity(intent);
                    }catch (Exception e){
//                    ToastUtils.showShort("暂无应用打开此链接");
                    }
                    return true;
                }

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

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
                .ready().get();


        mActivity.mAgentWeb.getJsAccessEntrace().callJs("document.getElementsByTagName(\"iframe\")[0].src");


    }

    public void startTimer() {

        mActivity.mAgentWeb.getJsAccessEntrace().callJs("var src=document.getElementsByTagName(\"iframe\")[0].src;var result=prompt(src)");


    }

    public void showMessage(String message) {
        if (mActivity == null) return;
        AlertUtil.showDeftToast(mActivity, message);
    }

    public void loadUrl(String url) {
        if (mActivity.mAgentWeb != null) mActivity.mAgentWeb.getUrlLoader().loadUrl(url);
    }

    public String getWebUrl() {
        return mActivity.mAgentWeb.getWebCreator().getWebView().getUrl();
    }


    public void cancelAd() {
        if (mTTAd != null) {
            mTTAd.destroy();
        }
    }

    public void onResume() {
        if (mActivity.mAgentWeb != null && isOclick)
            mActivity.mAgentWeb.getUrlLoader().loadUrl(mActivity.url);
    }
}
