package com.wkq.order.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-25
 * <p>
 * 用途: 广告点击的数据
 */


public class AdTimeDao extends RealmObject {


    @PrimaryKey
    String key;

    String nowTime;

    int adClickCount;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    public int getAdClickCount() {
        return adClickCount;
    }

    public void setAdClickCount(int adClickCount) {
        this.adClickCount = adClickCount;
    }


}
