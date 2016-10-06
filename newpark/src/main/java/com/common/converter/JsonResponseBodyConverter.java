package com.common.converter;


import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * Created by john on 2016/10/4.
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson mGson;//gson对象
    private final TypeAdapter<T> adapter;
    private Type type;

    /**
     * 构造器
     */
    public JsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter,Type type) {
        this.mGson = gson;
        this.adapter = adapter;
        this.type = type;
    }

    /**
     * 转换
     *
     * @param responseBody
     * @return
     * @throws IOException
     */
    @Override
    public T convert(ResponseBody responseBody) throws IOException {

        String response = responseBody.string();

//        String strResult = response.substring(1, response.length() - 1);
//        String result = XXTEA.Decrypt(strResult, HttpConstant.KEY);//解密
//        Log.i("xiaozhang", "解密的服务器数据：" + result);
//        LoginRsp pageBean = mGson.fromJson(response, LoginRsp.class);
//       return adapter.fromJson(response);
//        return (T) pageBean;
        return  mGson.fromJson(response,type);


    }

}
