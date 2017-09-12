package com.lian.rxjava2retrofit2demo.rxhttp;

import android.os.Build;
import android.util.Log;


import com.lian.rxjava2retrofit2demo.BuildConfig;
import com.lian.rxjava2retrofit2demo.rxhttpexpand.JsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lmr
 * Date 2017/8/22.
 * Description
 */

public class RxHttpFinal {
    private static RxHttpFinal rxHttpFinal;
    private OkHttpClient okHttpClient;
    private RxHttpFinalConfiguration configuration;
    private Retrofit retrofit;

    private RxHttpFinal() {
    }

    public static RxHttpFinal getInstance() {
        if (rxHttpFinal == null) {
            rxHttpFinal = new RxHttpFinal();
        }
        return rxHttpFinal;
    }

    public synchronized void init(RxHttpFinalConfiguration configuration) {
        this.configuration = configuration;
        long timeout = configuration.getTimeout();
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(timeout, TimeUnit.MILLISECONDS).writeTimeout(timeout, TimeUnit.MILLISECONDS).readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                builder.headers(getCommonHeaders());
                Response response=chain.proceed(builder.build());
                return response;
            }
        });
        //DEBUG模式下 添加日志拦截器
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        okHttpClient = builder.build();
        retrofit = new Retrofit.Builder().baseUrl(configuration.getBaseUrl())
                // 添加Gson转换器
                // .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(JsonConverterFactory.create())
                // 添加Retrofit到RxJava的转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


    public Headers getCommonHeaders() {
        return configuration.getCommonHeaders();
    }
}
