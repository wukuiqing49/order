package com.wkq.order.modlue.login;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.domob.sdk.common.util.AdError;
import com.domob.sdk.unionads.splash.UnionSplashAD;
import com.domob.sdk.unionads.splash.UnionSplashAdListener;
import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivitySplashBinding;
import com.wkq.order.modlue.main.ui.activity.HomeActivity;
import com.wkq.order.utils.Constant;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class SplashActivity extends MvpBindingActivity<SplashView, SplashPresenter, ActivitySplashBinding> implements UnionSplashAdListener {


    private TextView skipView;
    private ImageView splashHolder;
    private static final String SKIP_TEXT = "点击跳过 %d";

    public boolean canJump = false;
    private boolean needStartDemoList = true;

    /**
     * 为防止无广告时造成视觉上类似于"闪退"的情况，设定无广告时页面跳转根据需要延迟一定时间，demo
     * 给出的延时逻辑是从拉取广告开始算开屏最少持续多久，仅供参考，开发者可自定义延时逻辑，如果开发者采用demo
     * 中给出的延时逻辑，也建议开发者考虑自定义minSplashTimeWhenNoAD的值（单位ms）
     **/
    private int minSplashTimeWhenNoAD = 2000;
    /**
     * 记录拉取广告的时间
     */
    private long fetchSplashADTime = 0;
    private UnionSplashAD splashAD;
    private Handler handler = new Handler(Looper.getMainLooper());


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getMvpView() != null) getMvpView().initGoogleAd();
        if (getMvpView()!=null)getMvpView().jumpHomeActivity();
        splashAD = new UnionSplashAD(this, Constant.DOMOB_APPID, Constant.DOMOB_SPLASH_AD, this, 5000);

        if (getMvpView() != null) getMvpView().initView();


        if (Build.VERSION.SDK_INT >= 23) {
            checkAndRequestPermission();
        } else {
            splashAD.fetchAndShowIn(binding.splashContainer);
        }


    }


    @TargetApi(Build.VERSION_CODES.M)
    private void checkAndRequestPermission() {
        List<String> lackedPermission = new ArrayList<String>();
        if (!(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (!(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            lackedPermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        // 权限都已经有了，那么直接调用SDK
        if (lackedPermission.size() == 0) {
            splashAD.fetchAndShowIn(binding.splashContainer);
        } else {
            // 请求所缺少的权限，在onRequestPermissionsResult中再看是否获得权限，如果获得权限就可以调用SDK，否则不要调用SDK。
            String[] requestPermissions = new String[lackedPermission.size()];
            lackedPermission.toArray(requestPermissions);
            requestPermissions(requestPermissions, 1024);
        }
    }

    private boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1024 && hasAllPermissionsGranted(grantResults)) {
//            fetchSplashAD(this, container, skipView,  MOVE_GDT_APP_ID, MOVE_TT_GDT_AD_SPLASH_ID,this, 0);
        } else {
            // 如果用户没有授权，那么应该说明意图，引导用户去设置里面授权。
            Toast.makeText(this, "应用缺少必要的权限！请点击\"权限\"，打开所需要的权限。", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            finish();
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        canJump = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //此步骤主要是处理点击广告之后，退出当前的开屏页面，开发者可以根据自己的需求自己处理，不一定按照此方式处理
        if (canJump){
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

    }

    /**
     * 开屏页一定要禁止用户对返回按钮的控制，否则将可能导致用户手动退出了App而广告无法正常曝光和计费
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onAdDismissed() {
        Log.d("广告:", "点击跳转");
        this.finish();
    }

    @Override
    public void onNoAd(AdError error) {
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                SplashActivity.this.finish();
//            }
//        }, 3000);
        HomeActivity.startPlayHelperActivity(this);
        Log.d("广告:", error.getErrorMsg() + "		" + error.getErrorCode());//ٍ֛


    }



    @Override
    public void onAdPresent() {
        Log.d("广告:", "展现");
    }

    @Override
    public void onAdClicked() {
        Log.d("广告:", "点击");
    }
}
