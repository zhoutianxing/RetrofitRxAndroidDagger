package com.api.req;

import com.api.rsp.LoginRsp;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;


public interface LoginReq {
    @POST("userController!userLogin.spr")
    Observable<LoginRsp> setParameters(@Body Reviews reviews);
    public static class Reviews {
        public Reviews(String mobile,String password,String iosPushToken   ){
            this.mobile = mobile;
            this.password=password;
            this.iosPushToken = iosPushToken;
        }
        public String mobile;
        public String password;
        public String iosPushToken;
    }
}
