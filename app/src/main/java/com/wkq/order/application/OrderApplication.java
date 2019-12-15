package com.wkq.order.application;


import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.tencent.smtt.sdk.QbSdk;
import com.wkq.order.R;


import org.adblockplus.libadblockplus.android.AdblockEngine;
import org.adblockplus.libadblockplus.android.AndroidHttpClientResourceWrapper;
import org.adblockplus.libadblockplus.android.SingleInstanceEngineProvider;
import org.adblockplus.libadblockplus.android.settings.AdblockHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/11/11
 * <p>
 * 简介:
 */
public class OrderApplication extends MultiDexApplication {




    private final SingleInstanceEngineProvider.EngineCreatedListener engineCreatedListener =
            new SingleInstanceEngineProvider.EngineCreatedListener()
            {
                @Override
                public void onAdblockEngineCreated(AdblockEngine engine)
                {
                    // put your Adblock FilterEngine init here
                }
            };

    private final SingleInstanceEngineProvider.EngineDisposedListener engineDisposedListener =
            new SingleInstanceEngineProvider.EngineDisposedListener()
            {
                @Override
                public void onAdblockEngineDisposed()
                {
                    // put your Adblock FilterEngine deinit here
                }
            };

    @Override
    public void onCreate()
    {
        super.onCreate();

        // it's not initialized here but we check it just to show API usage
        if (!AdblockHelper.get().isInit())
        {
            // init Adblock
            String basePath = getDir(AdblockEngine.BASE_PATH_DIRECTORY, Context.MODE_PRIVATE).getAbsolutePath();

            // provide preloaded subscriptions
            Map<String, Integer> map = new HashMap<String, Integer>();
            map.put(AndroidHttpClientResourceWrapper.EASYLIST, R.raw.easylist);
            map.put(AndroidHttpClientResourceWrapper.EASYLIST_RUSSIAN, R.raw.easylist);
            map.put(AndroidHttpClientResourceWrapper.EASYLIST_CHINESE, R.raw.easylist);
            map.put(AndroidHttpClientResourceWrapper.ACCEPTABLE_ADS, R.raw.exceptionrules);

            AdblockHelper
                    .get()
                    .init(this, basePath, true, AdblockHelper.PREFERENCE_NAME)
                    .preloadSubscriptions(AdblockHelper.PRELOAD_PREFERENCE_NAME, map)
                    .addEngineCreatedListener(engineCreatedListener)
                    .addEngineDisposedListener(engineDisposedListener);
        }

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);


            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
    }
}


