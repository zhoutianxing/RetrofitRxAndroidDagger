package com.micky.retrofit;

import com.Config;
import com.common.converter.JsonConverterFactory;
import com.common.utils.JsonUtils;
import com.api.req.GetQiniuTokenReq;
import com.api.req.LoginReq;
import com.api.rsp.GetQiniuTokenRsp;
import com.api.rsp.LoginRsp;

import org.junit.Test;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    //配置okhttp3客户端
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
//                            .addHeader("isDes", "1")
                            .build();
                    return chain.proceed(request);
                }

            })
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    //构建Retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.SERVER_ADDRESS)  //.baseUrl  是设置连接的地址
            .client(okHttpClient)
            .addConverterFactory(JsonConverterFactory.create())//添加自定义转换器
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rxjava适配器
            .build();
//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl(Config.SERVER_ADDRESS)
//
//            .addConverterFactory(JsonConverterFactory.create())
//            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//            .build();
    @Test
    public void addition_isCorrect() throws Exception {
        System.out.println("addition_isCorrect ,ok !");
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_LoginReq() throws Exception{
        LoginReq req = retrofit.create(LoginReq.class);
        req.setParameters(new LoginReq.Reviews("15080814083","123456",""))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginRsp>() {

//kk
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage() != null) {
                            System.out.println("error:" + e.getMessage());

                        }
                    }

                    @Override
                    public void onNext(LoginRsp rsp) {
                        System.out.println(JsonUtils.toJson(rsp));
                    }
                });
    }
    @Test
    public void test_GetQiniuTokenReq() throws Exception{
        GetQiniuTokenReq req = retrofit.create(GetQiniuTokenReq.class);
        req.setParameters()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetQiniuTokenRsp>() {


                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage() != null) {
                            System.out.println("error:" + e.getMessage());

                        }
                    }

                    @Override
                    public void onNext(GetQiniuTokenRsp rsp) {
                        System.out.println(JsonUtils.toJson(rsp));
                    }
                });
    }

}