package com.wkq.net.interceptor;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-22
 * <p>
 * 用途:
 */


public class HandleLoginInterceptor extends ResponseBodyInterceptor {
    @Override
    Response intercept(Response response, String url, String body) throws IOException {

        JSONObject jsonObject = null;
        if (response.isSuccessful()) {



            try {



                JsonObject returnData = new JsonParser().parse(body).getAsJsonObject();

//
                JsonObject jsonObject1 = new JsonObject();

                jsonObject1.addProperty("errorMessage", "成功");
                jsonObject1.addProperty("errorCode", "200");



                JsonElement jsonElement = new Gson().toJsonTree(returnData);

                jsonObject1.add("data", jsonElement);

                MediaType contentType = response.body().contentType();
                ResponseBody responseBody = ResponseBody.create(contentType, jsonObject1.toString());
                return response.newBuilder().body(responseBody).build();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        return response;
    }
}
