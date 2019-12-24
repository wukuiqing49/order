package com.wkq.order.modlue.main.frame.presenter;

import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.net.ApiRequest;
import com.wkq.net.BaseInfo;
import com.wkq.net.api.ApiMoveDb;
import com.wkq.net.logic.Logic;
import com.wkq.net.logic.callback.DataCallback;
import com.wkq.net.logic.callback.FailureCallback;
import com.wkq.net.model.MoveDbComingInfo;
import com.wkq.order.modlue.main.frame.view.MoveDbComingView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class MoveDbComingPresenter extends MvpBasePresenter<MoveDbComingView> {
    public void getData(FragmentActivity activity) {


        HashMap<String, String> requestMap = new HashMap<>();
        requestMap.put("page", 1 + "");

        Logic.create(requestMap).action(new Logic.Action<Map<String, String>, BaseInfo<MoveDbComingInfo>>() {
            @Override
            public Disposable action(Map<String, String> data, DataCallback<BaseInfo<MoveDbComingInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.getUpComing(data)).subscribe(activity, callback);
            }
        }).<BaseInfo<MoveDbComingInfo>>event().setFailureCallback(new FailureCallback() {
            @Override
            public void onFailure(int state, String message) {
                Log.e("","");
            }
        }).setSuccessCallback(data -> {
            Log.e("","");

        }).start();


    }
}
