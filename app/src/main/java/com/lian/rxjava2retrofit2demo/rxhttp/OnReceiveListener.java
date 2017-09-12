

package com.lian.rxjava2retrofit2demo.rxhttp;



/**
 * Desction:请求回调类
 * Author:lmr
 * Date
 */
public abstract class OnReceiveListener<T> {


    private ServerCallback<T> callback;

    public void setServerCallbak(ServerCallback<T> callback) {
        this.callback = callback;
    }

    public ServerCallback<T> getServerCallback() {
        return callback;
    }


    public void onStart() {
    }


    public abstract void onSucceed(T t);


    public void onError(int errorCode, String msg) {
    }

    public void onFinish(T entity, int resultCode, String errorMsg) {
    }

}
