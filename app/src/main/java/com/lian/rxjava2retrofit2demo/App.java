package com.lian.rxjava2retrofit2demo;


import android.app.Application;

import com.lian.rxjava2retrofit2demo.rxhttp.Constants;
import com.lian.rxjava2retrofit2demo.rxhttp.RxHttpFinal;
import com.lian.rxjava2retrofit2demo.rxhttp.RxHttpFinalConfiguration;
import okhttp3.Headers;


/**
 * Created by lmr
 * Date 2017/8/22.
 * Description
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initRxHttpFinal();
    }

    private void initRxHttpFinal() {
        Headers.Builder commonHeaders = new Headers.Builder();
        commonHeaders.add("tk","1324");
        RxHttpFinalConfiguration.Builder builder = new RxHttpFinalConfiguration.Builder()
                .setCommenHeaders(commonHeaders.build())
                .setTimeout(Constants.REQ_TIMEOUT)
                .setBaseUrl(Constant.baseUrl);
        RxHttpFinal.getInstance().init(builder.build());
    }



}
