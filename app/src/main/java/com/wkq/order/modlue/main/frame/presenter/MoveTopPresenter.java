package com.wkq.order.modlue.main.frame.presenter;

import android.content.Context;
import android.util.Log;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.net.ApiRequest;
import com.wkq.net.BaseInfo;
import com.wkq.net.api.ApiMoveDb;
import com.wkq.net.logic.Logic;
import com.wkq.net.logic.callback.DataCallback;
import com.wkq.net.model.MoveDbTopRatedInfo;
import com.wkq.order.modlue.developer.ui.activity.ApiTestActivity;
import com.wkq.order.modlue.main.frame.view.MoveComingView;
import com.wkq.order.modlue.main.frame.view.MoveTopView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class MoveTopPresenter extends MvpBasePresenter<MoveTopView> {


    public void getData(Context context, int page) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("page", page + "");

        Logic.create(requestMap).action(new Logic.Action<Map<String, String>, BaseInfo<MoveDbTopRatedInfo>>() {
            @Override
            public Disposable action(Map<String, String> data, DataCallback<BaseInfo<MoveDbTopRatedInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.getTopRated(data)).subscribe(context, callback);
            }
        }).<BaseInfo<MoveDbTopRatedInfo>>event().setFailureCallback((state, message) -> {
            if (getView() != null) getView().showMessage(message);

        }).setSuccessCallback(data -> {

            if (getView() != null) getView().setData(data);


        }).start();
    }

}
