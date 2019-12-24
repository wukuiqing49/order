package com.wkq.net.api;

import com.wkq.net.BaseInfo;
import com.wkq.net.model.MoveDbComingInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public interface ApiMoveDb {

//    top_rated?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh&page=1

    //即将上映的大片
    //https://api.themoviedb.org/3/movie/upcoming?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh&page=1

    @GET("upcoming")
    Observable<Result<BaseInfo<MoveDbComingInfo>>> getUpComing(@QueryMap Map<String, String> requestMap);

}
