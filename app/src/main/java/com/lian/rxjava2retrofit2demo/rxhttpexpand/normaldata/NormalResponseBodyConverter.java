package com.lian.rxjava2retrofit2demo.rxhttpexpand.normaldata;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.lian.rxjava2retrofit2demo.entity.BaseEntity;
import com.lian.rxjava2retrofit2demo.entity.BaseEntity1;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by lmr
 * Date 2017/8/23.
 * Description
 */

public class NormalResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    public NormalResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }

        //自定义
//        BaseEntity rawResult = new Gson().fromJson(value.string(), BaseEntity.class);
//        return (T) rawResult;
    }
}
