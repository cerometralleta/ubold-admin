package com.ubold.admin.request;

import java.io.Serializable;

/**
 * Created by ningzuokun on 2018/3/17.
 */
public class Request implements Serializable {

    private String serialNo;
    private String sessionUserId;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getSessionUserId() {
        return sessionUserId;
    }

    public void setSessionUserId(String sessionUserId) {
        this.sessionUserId = sessionUserId;
    }
}
