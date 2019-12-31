package com.wkq.order.modlue.login;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.MainThread;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTSplashAd;
import com.bytedance.sdk.openadsdk.core.splash.TsView;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.base.utlis.TimerHelper;
import com.wkq.order.modlue.main.ui.activity.HomeActivity;
import com.wkq.order.utils.Constant;
import com.wkq.order.utils.MoveDbDataSaveUtlis;
import com.wkq.order.utils.TTAdManagerHolder;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class SplashView implements MvpView {
    SplashActivity mActivity;

    String TAG="广告";

    private TTAdNative mTTAdNative;

    public SplashView(SplashActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        initLoad();

        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setDarkMode(mActivity);
        MoveDbDataSaveUtlis.putType(mActivity);


//        mActivity.timerHelper = new TimerHelper();
//        mActivity.timerHelper.startTiming();
//        mActivity.timerHelper.setSchedule(0);
//        mActivity.timerHelper.setOnTimerListener(new TimerHelper.OnTimerListener() {
//            @Override
//            public void onSchedule(int schedule) {
//                if (schedule == 2) {
//                    jumpHomeActivity();
//                }
//                Log.e("倒计时:", schedule + "");
//            }
//        });
//        mActivity.timerHelper.startTiming();



//        TTAdManagerHolder.get().createAdNative(this);


    }

    private void jumpHomeActivity() {
        HomeActivity.startPlayHelperActivity(mActivity);
        mActivity.finish();
    }

    /**
     * 加载开屏广告
     */
    private void initLoad() {

        mTTAdNative= TTAdManagerHolder.get().createAdNative(mActivity);
            //step3:创建开屏广告请求参数AdSlot,具体参数含义参考文档
            AdSlot adSlot = new AdSlot.Builder()
                    .setCodeId(Constant.MOVE_TT_AD_SPLASH_ID)
                    .setSupportDeepLink(true)

                    .setImageAcceptedSize(1080, 1920)
                    .build();
            //step4:请求广告，调用开屏广告异步请求接口，对请求回调的广告作渲染处理
            mTTAdNative.loadSplashAd(adSlot, new TTAdNative.SplashAdListener() {
                @Override
                @MainThread
                public void onError(int code, String message) {
                    Log.d(TAG, message);
                    jumpHomeActivity();
                }

                @Override
                @MainThread
                public void onTimeout() {

                    Log.d(TAG, "开屏广告加载超时");

                    jumpHomeActivity();
                }

                @Override
                @MainThread
                public void onSplashAdLoad(TTSplashAd ad) {
                    Log.d(TAG, "开屏广告请求成功");

                    if (ad == null) {
                        return;
                    }
                    //获取SplashView
                    View view = ad.getSplashView();
                    if (view != null) {

//                        FrameLayout frameLayout= (FrameLayout) ((TsView) view).getChildAt(0);
//                        View views= frameLayout.getChildAt(3);
//                        FrameLayout.LayoutParams fram= (FrameLayout.LayoutParams) views.getLayoutParams();
//                        fram.height= FrameLayout.LayoutParams.MATCH_PARENT;
//                        fram.width= FrameLayout.LayoutParams.MATCH_PARENT;
////                        fram.topMargin=80;
//                        frameLayout.setLayoutParams(fram);
                        mActivity.binding.root.removeAllViews();
                        //把SplashView 添加到ViewGroup中,注意开屏广告view：width >=70%屏幕宽；height >=50%屏幕高
                        mActivity.binding.root.addView(view);
                        //设置不开启开屏广告倒计时功能以及不显示跳过按钮,如果这么设置，您需要自定义倒计时逻辑
                        //ad.setNotAllowSdkCountdown();
                    }else {
                        jumpHomeActivity();
                    }

                    //设置SplashView的交互监听器
                    ad.setSplashInteractionListener(new TTSplashAd.AdInteractionListener() {
                        @Override
                        public void onAdClicked(View view, int type) {
                            Log.d(TAG, "onAdClicked");

                            Log.d(TAG, "开屏广告点击");
                        }

                        @Override
                        public void onAdShow(View view, int type) {
                            Log.d(TAG, "onAdShow");

                        }

                        @Override
                        public void onAdSkip() {
                            Log.d(TAG, "onAdSkip");

                            jumpHomeActivity();

                        }

                        @Override
                        public void onAdTimeOver() {
                            Log.d(TAG, "onAdTimeOver");

                            jumpHomeActivity();
                        }
                    });
                    if(ad.getInteractionType() == TTAdConstant.INTERACTION_TYPE_DOWNLOAD) {
                        ad.setDownloadListener(new TTAppDownloadListener() {
                            boolean hasShow = false;

                            @Override
                            public void onIdle() {

                            }

                            @Override
                            public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {
                                if (!hasShow) {
                                    Log.d(TAG, "下载");
                                    hasShow = true;
                                }
                            }

                            @Override
                            public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {

                                Log.d(TAG, "下载暂停");

                            }

                            @Override
                            public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {

                                Log.d(TAG, "下载失败");

                            }

                            @Override
                            public void onDownloadFinished(long totalBytes, String fileName, String appName) {

                            }

                            @Override
                            public void onInstalled(String fileName, String appName) {

                            }
                        });
                    }
                }
            }, 3000);

    }


}
