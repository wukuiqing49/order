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

    @Override
    public void onCreate() {
        super.onCreate();
    }
}


