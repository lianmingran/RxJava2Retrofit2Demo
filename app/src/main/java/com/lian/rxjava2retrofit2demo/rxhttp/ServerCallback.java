package com.lian.rxjava2retrofit2demo.rxhttp;


/**
 * @author lmr
 *         date: 2016/8/26 15:46
 *         class:ServerCallback
 *         description: 逻辑层callback
 */

public abstract class ServerCallback<T> {

    public void onStart() {
    }

    public abstract void onSucceed(T t);


    public void onError(int resultCode, String errorMsg) {
    }

    public void onFinish(T entity, int resultCode, String errorMsg) {
    }




}
