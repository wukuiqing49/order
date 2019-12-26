package com.wkq.net;

import android.content.Context;
import android.util.Log;

import com.wkq.net.api.ApiDemo;
import com.wkq.net.api.ApiMoveDb;
import com.wkq.net.interceptor.DESEncryptInterceptor;
import com.wkq.net.interceptor.DefaultEncryptInterceptor;
import com.wkq.net.interceptor.HandleLoginInterceptor;
import com.wkq.net.interceptor.MoveDbInterceptor;
import com.wkq.net.interceptor.NetPPEncryptInterceptor;
import com.wkq.net.interceptor.NetPPPayEncryptInterceptor;
import com.wkq.net.interceptor.SignEncrypt2Interceptor;
import com.wkq.net.interceptor.SignEncryptInterceptor;
import com.wkq.net.logic.callback.DataCallback;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.adapter.rxjava2.Result;

import static com.wkq.net.logic.Config.SUCCESS;


/**
 * Created by xiansong on 2017/9/8.
 */

public class ApiRequest<Observable extends io.reactivex.Observable<Result<BaseData>>, BaseData extends BaseInfo> {

    private static final int OKHTTP_KEEP_ALIVE_DURATION_SECONDS = 10;

    //网++ 接口域名
    public static final String BASE_URL = "https://douban-api.uieee.com/v2/movie/";
    //网++ 测试环境 接口域名
    public static final String DEBUG_URL = "https://douban-api.uieee.com/v2/movie/";

    //The Move DB  的api
    //文档地址 https://developers.themoviedb.org/3/movies/get-top-rated-movies

//

    //IM 接口域名
    private static final String BASE_MOVE_DB = "https://api.themoviedb.org/3/";
    //IM 接口域名
    private static final String BASE_MOVE_DB_SEARCH = "https://api.themoviedb.org/3/search/";

//    //IM 测试环境 接口域名
//    public static final String IM_DEBUG_URL = "http://imtest.cnlive.com/";
//    //OPEN API 接口域名
//    private static final String OPEN_URL = "https://api.cnlive.com/";
//    //WJJ JSON
//    private static final String JSON_URL = "https://wjj.ys1.cnliveimg.com/";
//
//    private static final String DEBUG_CMS_URL = "http://cmstest.cnlive.com:8768/";
//    // TODO: 2018/11/27 cms正式地址
//    private static final String CMS_URL = "http://cms.cnlive.com:8768/";
//
////    测试数据
// private  static final String  TESTHOST= "http://cmstest.cnlive.com/";


    public static <T> String baseEndpoint() {
        return true ? DEBUG_URL : BASE_URL;
    }

//    public static <T> String baseIMEndpoint() {
//        return true ? IM_DEBUG_URL : IM_URL;
//    }
//
//    public static <T> String baseCMSEndpoint() {
//        return true ? DEBUG_CMS_URL : CMS_URL;
//    }

    //动态配置BaseUrl
    private static <T> String serviceEndpoint(Class<T> clazz) {
        if (clazz == ApiMoveDb.class) return BASE_MOVE_DB;
//        else if (clazz == ApiDemo.class) return baseIMEndpoint();
//        else
        return baseEndpoint();
    }

    public static <Service, Observable extends io.reactivex.Observable<Result<BaseData>>, BaseData extends BaseInfo>
    ApiRequest<Observable, BaseData> observable(Class<Service> clazz, ApiBuild.Api<Service, Observable, BaseData> api) {
        return observable(clazz, false, api, null);
    }

    public static <Service, Observable extends io.reactivex.Observable<Result<BaseData>>, BaseData extends BaseInfo>
    ApiRequest<Observable, BaseData> observable(Class<Service> clazz, ApiBuild.Api<Service, Observable, BaseData> api, Interceptor interceptor) {
        return observable(clazz, false, api, interceptor);
    }

    public static <Service, Observable extends io.reactivex.Observable<Result<BaseData>>, BaseData extends BaseInfo>
    ApiRequest<Observable, BaseData> observable(Class<Service> clazz, boolean decode, ApiBuild.Api<Service, Observable, BaseData> api, Interceptor interceptor) {
        ApiRequest<Observable, BaseData> request = new ApiRequest<>();
        ApiBuild<Service, Observable, BaseData> build = ApiBuild.service(clazz, api);
        build.setEndpoint(serviceEndpoint(clazz));
        if (interceptor != null) build.addInterceptor(interceptor);
        request.observable = build.build(decode, OKHTTP_KEEP_ALIVE_DURATION_SECONDS);
        return request;
    }

    public static <Service> Service serviceNoSign(Class<Service> clazz) {
        return service(clazz, (Interceptor) new DefaultEncryptInterceptor());
    }

    public static <Service> Service service(Class<Service> clazz) {
        return service(clazz, new SignEncryptInterceptor());
    }

    public static <Service> Service service(Class<Service> clazz, boolean valueUrlencode, boolean responseUrldecode) {
        return service(clazz, responseUrldecode, new SignEncryptInterceptor(valueUrlencode));
    }

    public static <Service> Service serviceDES(Class<Service> clazz) {
        return service(clazz, new DESEncryptInterceptor());
    }

    public static <Service> Service serviceNetPP(Class<Service> clazz) {
        return service(clazz, new NetPPEncryptInterceptor());
    }

    public static <Service> Service serviceNetPPPay(Class<Service> clazz) {
        return service(clazz, new NetPPPayEncryptInterceptor());
    }


    private static <Service> Service service(Class<Service> clazz, Interceptor... interceptor) {
        return service(clazz, false, interceptor);
    }

    private static <Service> Service service(Class<Service> clazz, boolean decode, Interceptor... interceptor) {
        return ApiBuild.service(clazz, null)
                .setEndpoint(serviceEndpoint(clazz))
                .addInterceptor(interceptor)
                .service(decode, OKHTTP_KEEP_ALIVE_DURATION_SECONDS);
    }


    public static <Service, Observable extends io.reactivex.Observable<Result<BaseData>>, BaseData extends BaseInfo>
    ApiRequest<Observable, BaseData> serviceDouBan(Class<Service> clazz, ApiBuild.Api<Service, Observable, BaseData> api) {
        return service(clazz, api, new HandleLoginInterceptor());
    }

    public static <Service, Observable extends io.reactivex.Observable<Result<BaseData>>, BaseData extends BaseInfo>
    ApiRequest<Observable, BaseData> serviceDefult(Class<Service> clazz, ApiBuild.Api<Service, Observable, BaseData> api) {
        return service(clazz, api, new DefaultEncryptInterceptor());
    }
    public static <Service, Observable extends io.reactivex.Observable<Result<BaseData>>, BaseData extends BaseInfo>
    ApiRequest<Observable, BaseData> serviceMoveDb(Class<Service> clazz, ApiBuild.Api<Service, Observable, BaseData> api) {
        return service(clazz, api, new MoveDbInterceptor());
    }

    private static <Service, Observable extends io.reactivex.Observable<Result<BaseData>>, BaseData extends BaseInfo>
    ApiRequest<Observable, BaseData> service(Class<Service> clazz, ApiBuild.Api<Service, Observable, BaseData> api, Interceptor... interceptor) {
        return service(clazz, false, api, interceptor);
    }

    private static <Service, Observable extends io.reactivex.Observable<Result<BaseData>>, BaseData extends BaseInfo>
    ApiRequest<Observable, BaseData> service(Class<Service> clazz, boolean decode, ApiBuild.Api<Service, Observable, BaseData> api, Interceptor... interceptor) {

        ApiRequest<Observable, BaseData> request = new ApiRequest<>();

        request.observable = ApiBuild.service(clazz, api)
                .setEndpoint(serviceEndpoint(clazz))
                .addInterceptor(interceptor)
                .build(decode, OKHTTP_KEEP_ALIVE_DURATION_SECONDS);

        return request;
    }


    private static <Service, Observable extends io.reactivex.Observable<Result<BaseData>>, BaseData extends BaseInfo>
    ApiRequest<Observable, BaseData> serviceNewVersion(Class<Service> clazz, boolean decode, ApiBuild.Api<Service, Observable, BaseData> api, long connectTimeout, long readTimeout, Interceptor... interceptor) {

        ApiRequest<Observable, BaseData> request = new ApiRequest<>();

        request.observable = ApiBuild.service(clazz, api)
                .setEndpoint(serviceEndpoint(clazz))
                .setConnectTimeout(connectTimeout)
                .setReadTimeout(readTimeout)
                .addInterceptor(interceptor)
                .build(decode);

        return request;
    }

    private Observable observable;

    private ApiRequest() {
    }

    public static <Observable extends io.reactivex.Observable<Result<BaseData>>, BaseData extends BaseInfo>
    Disposable subscribe(Observable observable, Subscriber<BaseData> subscriber) {
        return observable.take(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber.next, subscriber.error, subscriber.complete, subscriber.subscribe);
    }

    public Disposable subscribe(Subscriber<BaseData> subscriber) {
        return observable.take(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber.next, subscriber.error, subscriber.complete, subscriber.subscribe);
    }

    public Disposable subscribe(Context context, DataCallback<BaseData> callback) {
        Subscriber<BaseData> subscriber = new Subscriber<BaseData>(context) {
            @Override
            public void onCompleted(String s, String s1, BaseData data) {
                if (callback == null) return;
                callback.callback(SUCCESS, "", data);
            }

            @Override
            public void onError(String code, String message) {
                if (callback == null) return;
                int errorCode = -1;
                try {
                    errorCode = Integer.valueOf(code);
                } catch (Exception ignored) {
                }

                Log.e("LynnTest read", code + " \n " + message);
                callback.callback(errorCode, message, null);
            }
        };

        return observable.take(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber.next, subscriber.error, subscriber.complete, subscriber.subscribe);
    }

    public static Disposable loadData(String url, DataCallback<String> callback) {
        return io.reactivex.Observable
                .just(url)
                .map(s -> {
                    Response response = null;
                    String data = null;
                    try {
                        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                        Request request = new Request.Builder().url(url).build();
                        response = client.newCall(request).execute();//得到Response 对
                        if (response.isSuccessful()) data = response.body().string();
                    } catch (Exception e) {
                        Log.e("Api", "", e);
                    }

                    if (data != null) {
//                        Log.e("DemoView", "response.code()==" + response.code());
//                        Log.e("DemoView", "response.message()==" + response.message());
//                        Log.e("DemoView", "res==" + data);
                        //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    }
                    return data;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> callback.callback(0, "", s));
    }
}
