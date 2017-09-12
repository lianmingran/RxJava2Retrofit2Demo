package com.lian.rxjava2retrofit2demo.rxhttp;

import android.content.Context;
import android.widget.Toast;

import com.lian.rxjava2retrofit2demo.Constant;
import com.lian.rxjava2retrofit2demo.entity.BaseEntity;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by lmr
 * Date 2017/8/22.
 * Description   网络层对外接口
 */

public class ServerApi<T> {
    private static ServerApi serverApi;

    public static ServerApi getInstance() {
        if (serverApi == null) {
            serverApi = new ServerApi();
        }
        return serverApi;
    }

    public Retrofit getRetrofit() {
        return RxHttpFinal.getInstance().getRetrofit();
    }

    //普通模式
    public void toObservableSubscribe(final RxAppCompatActivity rxAppCompatActivity, Observable<T> observable, final OnReceiveListener<T> onReceiveListener) {
        observable.compose(rxAppCompatActivity.<T>bindToLifecycle()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                if (!Utils.isNetworkAvailable(rxAppCompatActivity)) {
                    if (onReceiveListener.getServerCallback() != null) {
                        onReceiveListener.getServerCallback().onError(ResultCode.NET_DISCONNECTED.code, ResultCode.NET_DISCONNECTED.errorMsg);
                        onReceiveListener.getServerCallback().onFinish(null, ResultCode.NET_DISCONNECTED.code, ResultCode.NET_DISCONNECTED.errorMsg);
                    }
                    onReceiveListener.onError(ResultCode.NET_DISCONNECTED.code, ResultCode.NET_DISCONNECTED.errorMsg);
                    onReceiveListener.onFinish(null, ResultCode.NET_DISCONNECTED.code, ResultCode.NET_DISCONNECTED.errorMsg);
                    disposable.dispose();
                }
            }
        }).subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull T t) {
                if (onReceiveListener.getServerCallback() != null) {
                    onReceiveListener.getServerCallback().onSucceed(t);
                    onReceiveListener.getServerCallback().onFinish(t, ResultCode.OK.code, ResultCode.OK.errorMsg);
                }
                onReceiveListener.onSucceed(t);
                onReceiveListener.onFinish(t, ResultCode.OK.code, ResultCode.OK.errorMsg);
            }

            @Override
            public void onError(@NonNull Throwable t) {
                if (onReceiveListener.getServerCallback() != null) {
                    onReceiveListener.getServerCallback().onError(ResultCode.ERROR_RESPONSE_UNKNOWN.code, t.getMessage());
                    onReceiveListener.getServerCallback().onFinish(null, ResultCode.ERROR_RESPONSE_UNKNOWN.code, t.getMessage());
                }
                onReceiveListener.onError(ResultCode.ERROR_RESPONSE_UNKNOWN.code, t.getMessage());
                onReceiveListener.onFinish(null, ResultCode.ERROR_RESPONSE_UNKNOWN.code, t.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }


    //背压模式
    public void toFlowableSubscribe(final RxAppCompatActivity rxAppCompatActivity, Flowable<T> flowable, final OnReceiveListener<T> onReceiveListener) {

        flowable.compose(rxAppCompatActivity.<T>bindToLifecycle()).onBackpressureBuffer()//背压模式选择
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) throws Exception {
                if (!Utils.isNetworkAvailable(rxAppCompatActivity)) {
                    if (onReceiveListener.getServerCallback() != null) {
                        onReceiveListener.getServerCallback().onError(ResultCode.NET_DISCONNECTED.code, ResultCode.NET_DISCONNECTED.errorMsg);
                        onReceiveListener.getServerCallback().onFinish(null, ResultCode.NET_DISCONNECTED.code, ResultCode.NET_DISCONNECTED.errorMsg);
                    }
                    onReceiveListener.onError(ResultCode.NET_DISCONNECTED.code, ResultCode.NET_DISCONNECTED.errorMsg);
                    onReceiveListener.onFinish(null, ResultCode.NET_DISCONNECTED.code, ResultCode.NET_DISCONNECTED.errorMsg);
                    subscription.cancel();
                }
            }
        }).subscribe(new Subscriber<T>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }


            @Override
            public void onNext(T t) {
                if (onReceiveListener.getServerCallback() != null) {
                    onReceiveListener.getServerCallback().onSucceed(t);
                    onReceiveListener.getServerCallback().onFinish(t, ResultCode.OK.code, ResultCode.OK.errorMsg);
                }
                onReceiveListener.onSucceed(t);
                onReceiveListener.onFinish(t, ResultCode.OK.code, ResultCode.OK.errorMsg);
            }

            @Override
            public void onError(Throwable t) {
                if (onReceiveListener.getServerCallback() != null) {
                    onReceiveListener.getServerCallback().onError(ResultCode.ERROR_RESPONSE_UNKNOWN.code, t.getMessage());
                    onReceiveListener.getServerCallback().onFinish(null, ResultCode.ERROR_RESPONSE_UNKNOWN.code, t.getMessage());
                }
                onReceiveListener.onError(ResultCode.ERROR_RESPONSE_UNKNOWN.code, t.getMessage());
                onReceiveListener.onFinish(null, ResultCode.ERROR_RESPONSE_UNKNOWN.code, t.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }


}
