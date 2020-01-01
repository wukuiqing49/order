package com.wkq.order.application;

import androidx.multidex.MultiDexApplication;

import com.umeng.commonsdk.UMConfigure;


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
        initDb();
        initYm();
    }

    private void initYm() {
        UMConfigure.init(this, "5e0b1445cb23d211150005fc", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, null);
    }

    /**
     * 初始化数据库
     */
    private void initDb() {
        //强烈建议在应用对应的Application#onCreate()方法中调用，避免出现content为null的异常

}}


