package com.wkq.order.modlue.move.frame.presenter;

import android.util.Log;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.net.ApiRequest;
import com.wkq.net.BaseInfo;
import com.wkq.net.api.ApiMoveDb;
import com.wkq.net.logic.Logic;
import com.wkq.net.logic.callback.DataCallback;
import com.wkq.net.model.MoveDbMoveDetailInfo;
import com.wkq.order.modlue.move.frame.view.MoveDailView;
import com.wkq.order.modlue.move.ui.MoveDetailActivity;

import io.reactivex.disposables.Disposable;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-27
 * <p>
 * 用途:
 */


public class MoveDetailPresenter extends MvpBasePresenter<MoveDailView> {

    public void getMoveDetail(MoveDetailActivity activity, String moveId) {

        Logic.create(moveId).action(new Logic.Action<String, BaseInfo<MoveDbMoveDetailInfo>>() {
            @Override
            public Disposable action(String data, DataCallback<BaseInfo<MoveDbMoveDetailInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.getMovieDetail(data)).subscribe(activity, callback);
            }
        }).<BaseInfo<MoveDbMoveDetailInfo>>event().setFailureCallback((state, message) -> {
            Log.e("", "");
        }).setSuccessCallback(data -> {
            Log.e("", "");
        }).start();

    }
}
