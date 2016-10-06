package com.api.rsp;

public class BaseRsp {

    public static final int ERROR_CODE_OK = 0;
    public static final int ERROR_CODE_NO_VERSION = 1;
    public static final int ERROR_CODE_DECRYPT_ERROR = 1009;
    public static final int ERROR_CODE_NEED_LOGIN = 1007;


    private Integer errorCode;
    private String errorInfo;

    public BaseRsp() {
    }

    public BaseRsp(Integer errorCode, String errorInfo) {
        setErrorCode(errorCode);
        setErrorInfo(errorInfo);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}