package com.lian.rxjava2retrofit2demo.presenter;

import com.lian.rxjava2retrofit2demo.entity.BaseEntity;
import com.lian.rxjava2retrofit2demo.entity.BaseEntity1;
import com.lian.rxjava2retrofit2demo.rxhttp.OnReceiveListener;
import com.lian.rxjava2retrofit2demo.rxhttp.ServerApi;
import com.lian.rxjava2retrofit2demo.rxhttp.ServerCallback;
import com.lian.rxjava2retrofit2demo.entity.TestEntity;
import com.lian.rxjava2retrofit2demo.serverapi.TestRequestEntity1;
import com.lian.rxjava2retrofit2demo.serverapi.TestService;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/8/22.
 */

public class TestPresenter {
    private static TestPresenter testPresenter;

    public static TestPresenter getInstance() {
        if (testPresenter == null) {
            testPresenter = new TestPresenter();
        }
        return testPresenter;
    }


    public void test1(RxAppCompatActivity rxAppCompatActivity, OnReceiveListener<BaseEntity> onReceiveListener) {
        Retrofit retrofit = ServerApi.getInstance().getRetrofit();
        TestService testService = retrofit.create(TestService.class);
        Observable<BaseEntity> observable = testService.test1("18950492157", "json", "b2ad61d4add1c84cf5fd4bb4058ebb3b");
        /*在这里，可以做各种observable的操作符，比如zip,maap,faltmap等等*/
        onReceiveListener.setServerCallbak(new ServerCallback<BaseEntity>() {
            @Override
            public void onSucceed(BaseEntity baseEntity) {

            }
        });
        ServerApi.getInstance().toObservableSubscribe(rxAppCompatActivity, observable, onReceiveListener);
    }


    public void test2(RxAppCompatActivity rxAppCompatActivity, OnReceiveListener<BaseEntity> onReceiveListener) {
        TestService testService = ServerApi.getInstance().getRetrofit().create(TestService.class);
        Flowable<BaseEntity> flowable = testService.test2("18950492157", "json", "b2ad61d4add1c84cf5fd4bb4058ebb3b");
        /*在这里，可以做各种observable的操作符，比如zip,maap,faltmap等等*/
        onReceiveListener.setServerCallbak(new ServerCallback<BaseEntity>() {
            @Override
            public void onSucceed(BaseEntity baseEntity) {

            }
        });
        ServerApi.getInstance().toFlowableSubscribe(rxAppCompatActivity, flowable, onReceiveListener);
    }


    public void getweixinjingxuan(RxAppCompatActivity rxAppCompatActivity, OnReceiveListener<BaseEntity1> onReceiveListener) {
        TestService testService = ServerApi.getInstance().getRetrofit().create(TestService.class);
        Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("pno", "1");
        fieldMap.put("ps", "10");
        fieldMap.put("dtype", "json");
        fieldMap.put("key", "0abd40df13b2c287cfffbdccd43943bd");
        Flowable<BaseEntity1> flowable = testService.getweixinjingxuan("http://v.juhe.cn/weixin/query",fieldMap);
        /*在这里，可以做各种observable的操作符，比如zip,maap,faltmap等等*/
        onReceiveListener.setServerCallbak(new ServerCallback<BaseEntity1>() {
            @Override
            public void onSucceed(BaseEntity1 baseEntity1) {

            }
        });
        ServerApi.getInstance().toFlowableSubscribe(rxAppCompatActivity, flowable, onReceiveListener);
    }

}
