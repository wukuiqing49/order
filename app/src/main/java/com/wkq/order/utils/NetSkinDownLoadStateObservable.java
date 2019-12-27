package com.wkq.order.utils;

import java.util.Observable;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/27
 * <p>
 * 简介:  网络皮肤下载的观察者
 */
public class NetSkinDownLoadStateObservable extends Observable {

    public static NetSkinDownLoadStateObservable instance;


    public NetSkinDownLoadStateObservable getInstance() {
        synchronized (NetSkinDownLoadStateObservable.class) {
            if (instance == null) instance = new NetSkinDownLoadStateObservable();
        }
        return instance;
    }

    /**
     * 是否下载成功
     *
     * @param info
     */
    public void updateDownLoadInfo(DownLoadInfo info) {
        setChanged();
        if (info != null)
            notifyObservers(info);
    }

    /**
     * 下载的对象
     */
    public class DownLoadInfo {
        //下载状态
        boolean isSucess;
        //下载地址
        String filePath;

        public DownLoadInfo(boolean isSucess, String filePath) {
            this.isSucess = isSucess;
            this.filePath = filePath;
        }

        public boolean isSucess() {
            return isSucess;
        }

        public void setSucess(boolean sucess) {
            isSucess = sucess;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }
}
