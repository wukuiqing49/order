package com.wkq.order.modlue.main.frame.presenter;

import android.util.Log;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.net.ApiRequest;
import com.wkq.net.BaseInfo;
import com.wkq.net.api.ApiMoveDb;
import com.wkq.net.logic.Logic;
import com.wkq.net.logic.callback.DataCallback;
import com.wkq.net.model.MoveDataInfo;
import com.wkq.net.model.MoveDbSearchInfo;
import com.wkq.order.modlue.developer.ui.activity.ApiTestActivity;
import com.wkq.order.modlue.main.frame.view.SearchView;
import com.wkq.order.modlue.main.ui.SearchActivity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/26
 * <p>
 * 简介:
 */
public class SearchPresenter extends MvpBasePresenter<SearchView> {


    public void searchData(SearchActivity mActivity, String searchContent) {

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("page", 1 + "");


        requestMap.put("query", searchContent);

        Logic.create(requestMap).action(new Logic.Action<Map<String, String>, BaseInfo<MoveDataInfo>>() {
            @Override
            public Disposable action(Map<String, String> data, DataCallback<BaseInfo<MoveDataInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.searchMovies(data)).subscribe(mActivity, callback);
            }
        }).<BaseInfo<MoveDataInfo>>event().setFailureCallback((state, message) -> {
            Log.e("", "");

        }).setSuccessCallback(data -> {
            Log.e("", "");

        }).start();

    }
}
