package com.common.converter;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.crypto.NativeDES;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;


/**
 * Created by john on 2016/10/4.
 */
public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */

    public JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }


    @Override
    public RequestBody convert(T value) throws IOException {
        String jsonData = gson.toJson(value);
        String encryptedData = NativeDES.getInstance().en(jsonData);
        if (encryptedData == null) {
            return RequestBody.create(MEDIA_TYPE, jsonData);
        } else {
            System.out.println("encryptedData:" + encryptedData);
            return RequestBody.create(MEDIA_TYPE, encryptedData);
        }
    }

}
