package com.micky.retrofit;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.Config;
import com.common.converter.JsonConverterFactory;
import com.common.utils.JsonUtils;
import com.api.req.LoginReq;
import com.api.rsp.LoginRsp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class TestSubject extends InstrumentationTestCase {
    private static final String TAG = "TestSubject";
    //配置okhttp3客户端
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("isDes", "1")
                            .build();
                    return chain.proceed(request);
                }

            })
            .build();
    //构建Retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Config.SERVER_ADDRESS)  //.baseUrl  是设置连接的地址
            .client(okHttpClient)
            .addConverterFactory(JsonConverterFactory.create())//添加自定义转换器
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rxjava适配器
            .build();

    public void testPublishSubject() {
        PublishSubject<String> stringPublishSubject = PublishSubject.create();
        stringPublishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Observable completed");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "Observer consumed " + s);
            }
        });
        stringPublishSubject.onNext("hello world");
        stringPublishSubject.onCompleted();
    }

    public void testLogin() {
        LoginReq req = retrofit.create(LoginReq.class);
        req.setParameters(new LoginReq.Reviews("15080814083", "123456", ""))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginRsp>() {


                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage() != null) {
                            Log.e(TAG, "error:" + e.getMessage());

                        }
                    }

                    @Override
                    public void onNext(LoginRsp rsp) {
                        Log.e(TAG, "rsp:" + JsonUtils.toJson(rsp));
                    }
                });
    }
}