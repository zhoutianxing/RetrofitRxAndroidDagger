package com.api.rsp;

import com.common.tool.RspChecker;

public class GetQiniuTokenRsp extends BaseRsp {

	@RspChecker.Need
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}