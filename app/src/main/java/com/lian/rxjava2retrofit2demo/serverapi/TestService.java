package com.lian.rxjava2retrofit2demo.serverapi;

import com.lian.rxjava2retrofit2demo.entity.BaseEntity;
import com.lian.rxjava2retrofit2demo.entity.BaseEntity1;
import com.lian.rxjava2retrofit2demo.entity.TestEntity;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by lmr
 * Date 2017/8/23.
 * Description
 */

public interface TestService {


    @GET("/mobile/get")
    Observable<BaseEntity> test1(@Query("phone") String phone, @Query("dtype") String dtype, @Query("key") String key);


    @GET("/mobile/get")
    Flowable<BaseEntity> test2(@Query("phone") String phone, @Query("dtype") String dtype, @Query("key") String key);


    @POST("/weixin/query")   //添加（）后，不能与@Url共用
    Flowable<BaseEntity1> test3(@Body TestRequestEntity1 testRequestEntity1);


    @POST("/weixin/query")   //添加（）后，不能与@Url共用
    Flowable<BaseEntity1> test3(@Body String str);

    @FormUrlEncoded
    @POST
    Flowable<BaseEntity1> getweixinjingxuan(@Url String url,@FieldMap Map<String,String> fieldMap);
}
