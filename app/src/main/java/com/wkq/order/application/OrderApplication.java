package com.wkq.order.application;

import androidx.multidex.MultiDexApplication;


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
    }

    /**
     * 初始化数据库
     */
    private void initDb() {

    }
}


