package com.wkq.database.utils;

import android.content.Context;

import com.wkq.database.dao.AdTimeInfo;
import com.wkq.database.dao.AdTimeInfoDao;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/26
 * <p>
 * 简介: 数据库操作工具基类
 */
public class DataBaseUtils {
    /**
     * 获取广告点击次数的 数据
     *
     * @param context
     * @param key     主键id
     * @return
     */
    public static AdTimeInfo getAdTimeInfo(Context context, String key) {

        AdTimeInfoDao adTimeInfoDao = DaoHelper.getInstance(context).getAdTimeDao();
        return adTimeInfoDao.load(key);
    }

    /**
     * 存储更新 广告点击的数据
     *
     * @param context
     * @param key     主键
     * @param time    时间
     */
    public static void updateAdTimeInfo(Context context, String key, String time) {
        AdTimeInfoDao adTimeInfoDao = DaoHelper.getInstance(context).getAdTimeDao();
        AdTimeInfo adTimeInfo = adTimeInfoDao.load(key);
        if (adTimeInfo == null) {
            adTimeInfo = new AdTimeInfo();
            adTimeInfo.setAdTimeKey(key);
            adTimeInfo.setAdTime(time);
            adTimeInfo.setAdClickCount(1);
            adTimeInfoDao.insert(adTimeInfo);
        } else {
            adTimeInfo.setAdTime(time);
            adTimeInfo.setAdClickCount(adTimeInfo.getAdClickCount() + 1);
            adTimeInfoDao.insertOrReplace(adTimeInfo);
        }
    }

    /**
     * c插入数据
     *
     * @param context
     * @param key
     * @param time
     * @param count
     */
    public static void insertAdTimeInfo(Context context, String key, String time, int count) {
        AdTimeInfoDao adTimeInfoDao = DaoHelper.getInstance(context).getAdTimeDao();
        AdTimeInfo adTimeInfo = adTimeInfoDao.load(key);
        if (adTimeInfo == null) {
            adTimeInfo = new AdTimeInfo();
            adTimeInfo.setAdTimeKey(key);
            adTimeInfo.setAdTime(time);
            adTimeInfo.setAdClickCount(count);
            adTimeInfoDao.insert(adTimeInfo);
        }
    }
}
