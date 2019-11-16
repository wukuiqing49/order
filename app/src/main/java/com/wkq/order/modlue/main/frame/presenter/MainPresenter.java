package com.wkq.order.modlue.main.frame.presenter;

import android.util.Log;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.lib_net.ApiRequest;
import com.wkq.lib_net.BaseInfo;
import com.wkq.lib_net.api.ApiDemo;
import com.wkq.lib_net.logic.Logic;
import com.wkq.lib_net.logic.callback.DataCallback;
import com.wkq.lib_net.logic.callback.FailureCallback;
import com.wkq.lib_net.logic.callback.SuccessCallback;
import com.wkq.order.MainActivity;
import com.wkq.order.modlue.main.frame.view.MainView;

import java.util.HashMap;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/11/9
 * <p>
 * 简介:
 */
public class MainPresenter  extends MvpBasePresenter<MainView> {

    public void getData(MainActivity activity) {


        Logic.create().action(new Logic.Action<HashMap<String,String>, BaseInfo>() {
            @Override
            public io.reactivex.disposables.Disposable action(HashMap<String, String> data, DataCallback<BaseInfo> callback) {
                return ApiRequest.service(ApiDemo.class, apiDemo -> apiDemo.test()).subscribe(activity,callback);
            }
        }).<BaseInfo>event().setFailureCallback(new FailureCallback() {
            @Override
            public void onFailure(int state, String message) {
                Log.e("","");
            }
        }).setSuccessCallback(new SuccessCallback<BaseInfo>() {
            @Override
            public void onSuccess(BaseInfo data) {
                Log.e("","");
            }
        }).start();


    }
}
