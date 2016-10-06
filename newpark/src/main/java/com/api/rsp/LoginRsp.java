package com.api.rsp;


import com.common.tool.RspChecker.*;

public class LoginRsp extends BaseRsp {

    @Need
    private String userId;

    private Boolean isFirstReg;

//    private String promptMsg;// 如果用户存在商家，或者员工身份 提示

    private String accessToken, sinaUname, sinaUid;

    private Boolean isReward;

    public Boolean getIsReward() {
        return isReward == null ? false : isReward;
    }

    public void setIsReward(Boolean isReward) {
        this.isReward = isReward;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSinaUname() {
        return sinaUname;
    }

    public void setSinaUname(String sinaUname) {
        this.sinaUname = sinaUname;
    }

    public String getSinaUid() {
        return sinaUid;
    }

    public void setSinaUid(String sinaUid) {
        this.sinaUid = sinaUid;
    }

//    public String getPromptMsg() {
//        return promptMsg;
//    }
//
//    public void setPromptMsg(String promptMsg) {
//        this.promptMsg = promptMsg;
//    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getIsFirstReg() {
        return isFirstReg != null ? isFirstReg : false;
    }

    public void setIsFirstReg(Boolean isFirstReg) {
        this.isFirstReg = isFirstReg;
    }

}