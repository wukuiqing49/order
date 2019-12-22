package com.wkq.order.modlue.main.frame.presenter;

import android.app.Activity;
import android.util.Log;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.net.ApiRequest;
import com.wkq.net.BaseInfo;
import com.wkq.net.api.ApiDemo;
import com.wkq.net.logic.Logic;
import com.wkq.net.logic.callback.DataCallback;
import com.wkq.net.logic.callback.FailureCallback;
import com.wkq.net.logic.callback.SuccessCallback;
import com.wkq.net.util.StringUtils;
import com.wkq.order.modlue.main.frame.view.MoviesView;
import com.wkq.net.model.MoviesInfo;

import java.net.URLEncoder;
import java.util.HashMap;

import io.reactivex.disposables.Disposable;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class MoviesPresenter extends MvpBasePresenter<MoviesView> {

    public void initData(Activity activity, String xx) {
//        https://api.douban.com/v2/movie/in_theaters?
//     city=%E6%A1%82%E6%9E%97&start=0&count=9&apikey=0df993c66c0c636e29ecbb5344252a4a

        String urlStr = "";
        try {
            urlStr = StringUtils.encode("北京");
        } catch (Exception e) {

        }

        if (xx.equals("1")){
            return;
        }

        HashMap<String, String> requestMap = new HashMap<>();
        requestMap.put("city", urlStr);
        requestMap.put("start", "0");
        requestMap.put("count", "10");
        requestMap.put("apikey", "0df993c66c0c636e29ecbb5344252a4a");

        Logic.create(requestMap).action(new Logic.Action<HashMap<String, String>, BaseInfo<MoviesInfo>>() {
            @Override
            public Disposable action(HashMap<String, String> data, DataCallback<BaseInfo<MoviesInfo>> callback) {
                return ApiRequest.service(ApiDemo.class, apiDemo -> apiDemo.getMovieNewMovies("0df993c66c0c636e29ecbb5344252a4a")).subscribe(activity, callback);

            }
        }).<BaseInfo<MoviesInfo>>event().setFailureCallback(new FailureCallback() {
            @Override
            public void onFailure(int state, String message) {
                Log.e("", "");

            }
        }).setSuccessCallback(new SuccessCallback<BaseInfo<MoviesInfo>>() {
            @Override
            public void onSuccess(BaseInfo<MoviesInfo> data) {

            }
        }) .start();

    }
}
