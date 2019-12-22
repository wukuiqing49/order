package com.wkq.net.api;

import com.wkq.net.BaseInfo;
import com.wkq.net.model.MoviesInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/11/9
 * <p>
 * 简介:
 */
public interface ApiDemo {

    /**
     * 检查敏感词
     */
    @POST("Daren/announcementCheck/checkFeaturedGroup.action")
    Observable<Result<BaseInfo>> checkSensitiveWord(@QueryMap() Map<String, String> maps);

    /**
     * 检查敏感词
     */
    @GET("contentApi/test?status=true&ts=2")
    Observable<Result<BaseInfo>> test();
//
//    /**
//     /* *
//     *
//     * @return
//     */
//
//    @GET("v2/movie/in_theaters")
//    Observable<Result<BaseInfo<MoviesInfo>>> getHotMovies(@QueryMap() Map<String, String> map);
//

    @GET("in_theaters")
    Observable<Result<BaseInfo>> getMovieInTheaters(@Query("apikey") String apikey);

    @GET("new_movies")
    Observable<Result<BaseInfo<MoviesInfo>>> getMovieNewMovies(@Query("apikey") String apikey);

//    /**
//     * 分类二级页面达人列表
//     *
//     * @return
//     */
//    @Headers("cache:5")
//    @POST("Daren/classify/getNewDarensByCategory.action")
//    Observable<Result<BaseInfo<CategoryDarensData>>> getDarensByCategory(@QueryMap() Map<String, String> map);
//
//
//    /**
//     * 发送文本消息
//     *
//     * @param maps
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("CnliveIM/notify/textMsgPush.action")
//    Observable<Result<BaseInfo>> textMsgPush(@FieldMap Map<String, String> maps);
//
//    /**
//     * 发送文本消息
//     *
//     * @param maps
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("CnliveIM/notify/textMsgPush.action")
//    Observable<Result<BaseInfo>> textMsgPush(@FieldMap Map<String, String> maps);
//
//    /**
//     * 积分明细
//     */
//    @GET("Daren/integral/integralRecord.action")
//    Observable<Result<BaseInfo<IntegraContentData>>> getIntegraContentList(@QueryMap() Map<String, String> map);


}
