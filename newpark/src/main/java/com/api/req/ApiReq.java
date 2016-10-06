package com.api.req;

import com.api.rsp.GetIpInfoRsp;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @Project retrofitrxandroiddagger2
 * @Packate com.micky.retrofitrxandroiddagger2.data.api
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-21 17:22
 * @Version 0.1
 */
public interface ApiReq {
  /*  @GET("service/getIpInfo.php")
    Call<GetIpInfoRsp> getIpInfo(@Query("ip") String ip);*/

    @GET("service/getIpInfo.php")
    Observable<GetIpInfoRsp> getIpInfo(@Query("ip") String ip);
}
