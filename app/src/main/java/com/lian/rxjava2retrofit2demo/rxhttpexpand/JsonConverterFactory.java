package com.lian.rxjava2retrofit2demo.rxhttpexpand;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import com.lian.rxjava2retrofit2demo.Constant;
import com.lian.rxjava2retrofit2demo.rxhttpexpand.encryptdata.EncryptRequestBodyConverter;
import com.lian.rxjava2retrofit2demo.rxhttpexpand.encryptdata.EncryptResponseBodyConverter;
import com.lian.rxjava2retrofit2demo.rxhttpexpand.normaldata.NormalRequestBodyConverter;
import com.lian.rxjava2retrofit2demo.rxhttpexpand.normaldata.NormalResponseBodyConverter;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;


/**
 * Created by lmr
 * Date 2017/8/23.
 * Description
 */

public class JsonConverterFactory extends Converter.Factory {

    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static JsonConverterFactory create() {
        return create(new Gson());
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
    public static JsonConverterFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new JsonConverterFactory(gson);
    }

    private final Gson gson;

    private JsonConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        Log.e("MainActivity", "1111111111111111111111111111");
        if (Constant.isEncrypt) {
            return new EncryptResponseBodyConverter<>(gson, adapter);   //响应数据
        } else {
            return new NormalResponseBodyConverter<>(gson, adapter);   //响应数据
        }
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        //必须要 @Body
        Log.e("MainActivity", "22222222222222222222222222");
        if (Constant.isEncrypt) {
            return new EncryptRequestBodyConverter<>(gson, adapter);  //请求数据
        } else {
            return new NormalRequestBodyConverter<>(gson, adapter);  //请求数据
        }

    }


    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
   //     TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        Log.e("MainActivity", "3333333333333333333333333");
        return new NormalStringConverter<>();
    }

    public static class NormalStringConverter<T> implements Converter<T, String> {
//        private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
//        private static final Charset UTF_8 = Charset.forName("UTF-8");

//        private final Gson gson;
//        private final TypeAdapter<T> adapter;

        public NormalStringConverter() {
//            this.gson = gson;
//            this.adapter = adapter;
        }

        @Override
        public String convert(T value) throws IOException {
            Log.e("MainActivity", "===" + value.toString());
            return value.toString();
        }
    }


}
