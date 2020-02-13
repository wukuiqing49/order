package com.wkq.order.modlue.login;

import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.order.modlue.main.ui.activity.HomeActivity;
import com.wkq.order.utils.Constant;
import com.wkq.order.utils.MoveDbDataSaveUtlis;

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



    public SplashView(SplashActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        initLoad();

        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setLightMode(mActivity);
        MoveDbDataSaveUtlis.putType(mActivity);

    }

    private void initLoad() {
    }

    private void jumpHomeActivity() {
        HomeActivity.startPlayHelperActivity(mActivity);
        mActivity.finish();
    }


    public void initGoogleAd() {
        MobileAds.initialize(mActivity, Constant.GOOGLE_APPID);


        InterstitialAd mInterstitialAd = new InterstitialAd(mActivity);
        mInterstitialAd.setAdUnitId(Constant.GOOGLE_AD_SPLASH);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.show();

        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e("","");
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.e("","");
            }
        });

    }
}
