package com.api.req;

import com.api.rsp.GetQiniuTokenRsp;

import retrofit2.http.POST;
import rx.Observable;


public interface GetQiniuTokenReq {
    @POST("userController!getQiniuToken.spr")
    Observable<GetQiniuTokenRsp> setParameters();
}
