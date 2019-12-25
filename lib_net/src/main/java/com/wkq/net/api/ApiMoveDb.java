package com.wkq.net.api;

import com.wkq.net.BaseInfo;
import com.wkq.net.model.MoveDbComingInfo;
import com.wkq.net.model.MoveDbMoveDetailInfo;
import com.wkq.net.model.MoveDbNowPlayingInfo;
import com.wkq.net.model.MoveDbTopRatedInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    /**
     * 正在热播
     *
     * @param requestMap
     * @return page=1
     * https://api.themoviedb.org/3/movie/now_playing?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh&page=1
     */

    @GET("now_playing")
    Observable<Result<BaseInfo<MoveDbNowPlayingInfo>>> nowPlaying(@QueryMap Map<String, String> requestMap);

    /**
     * 电影详细信息
     *
     * @param movieId
     * @return https://api.themoviedb.org/3/movie/419704?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh
     */
    @GET("{id}")
    Observable<Result<BaseInfo<MoveDbMoveDetailInfo>>> getMovieDetail(@Path("id") String movieId);

    /**
     * top 排行榜
     *
     * @param requestMap
     * @return https://api.themoviedb.org/3/movie/top_rated?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh&page=1
     */
    @GET("top_rated")
    Observable<Result<BaseInfo<MoveDbTopRatedInfo>>> getTopRated(@QueryMap Map<String, String> requestMap);
    /**
     * top 排行榜
     *
     * @param requestMap
     * @return
     * query  查询条件
     *year  查询的时间
     * https://api.themoviedb.org/3/search/movie?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh&query=%E7%A7%91%E5%B9%BB&page=1&include_adult=false
     */

    @GET("top_rated")
    Observable<Result<BaseInfo<MoveDbTopRatedInfo>>> findMovie(@QueryMap Map<String, String> requestMap);

}
