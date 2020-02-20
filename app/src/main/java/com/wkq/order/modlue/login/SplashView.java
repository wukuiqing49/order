package com.wkq.order.modlue.login;


import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.order.utils.Constant;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.order.modlue.main.ui.activity.HomeActivity;
import com.wkq.order.utils.MoveDbDataSaveUtlis;

import java.lang.reflect.Method;

import cdc.sed.yff.AdManager;
import cdc.sed.yff.nm.sp.SplashViewSettings;
import cdc.sed.yff.nm.sp.SpotListener;
import cdc.sed.yff.nm.sp.SpotManager;
import cdc.sed.yff.nm.sp.SpotRequestListener;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class SplashView implements MvpView, SpotRequestListener {
    SplashActivity mActivity;

    String TAG = "广告";


    public SplashView(SplashActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        initLoad();

        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setLightMode(mActivity);
        MoveDbDataSaveUtlis.putType(mActivity);


        Log.e("imei:","   "+getIMEI(mActivity));
    }

    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            Method method = manager.getClass().getMethod("getImei", int.class);
            String imei1 = (String) method.invoke(manager, 0);
            String imei2 = (String) method.invoke(manager, 1);
            if(TextUtils.isEmpty(imei2)){
                return imei1;
            }
            if(!TextUtils.isEmpty(imei1)){
                //因为手机卡插在不同位置，获取到的imei1和imei2值会交换，所以取它们的最小值,保证拿到的imei都是同一个
                String imei = "";
                if(imei1.compareTo(imei2) <= 0){
                    imei = imei1;
                }else{
                    imei = imei2;
                }
                return imei;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return manager.getDeviceId();
        }
        return "";
    }

    private void initLoad() {
    }

    public void jumpHomeActivity() {
        HomeActivity.startPlayHelperActivity(mActivity);
        mActivity.finish();
    }


    public void initUMIAd() {
        AdManager.getInstance(mActivity).init(Constant.AD_UMI_APPID, Constant.AD_UMI_PASSWORLD, true);
        SplashViewSettings splashViewSettings = new SplashViewSettings();
        SpotManager.getInstance(mActivity).requestSpot(this);

        splashViewSettings.setAutoJumpToTargetWhenShowFailed(true);
        splashViewSettings.setTargetClass(HomeActivity.class);

        splashViewSettings.setSplashViewContainer(mActivity.binding.splashContainer);
// 使用自定义布局参数
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        splashViewSettings.setSplashViewContainerAndLayoutParams(mActivity.binding.splashContainer, params);

        SpotManager.getInstance(mActivity).showSplash(mActivity,
                splashViewSettings, new SpotListener() {
                    @Override
                    public void onShowSuccess() {
                        Log.e("", "");
                    }

                    @Override
                    public void onShowFailed(int i) {
                        Log.e("", "");
                        showMessage("广告加载失败");
                        jumpHomeActivity();
                    }

                    @Override
                    public void onSpotClosed() {
                        Log.e("", "");
                    }

                    @Override
                    public void onSpotClicked(boolean b) {
                        Log.e("", "");
                    }
                });


    }

    @Override
    public void onRequestSuccess() {
        Log.e("", "");
    }

    @Override
    public void onRequestFailed(int i) {
        Log.e("", "");
        showMessage("预加载失败");
    }

    public void showMessage(String message) {
        if (mActivity == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showDeftToast(mActivity, message);
    }
}
