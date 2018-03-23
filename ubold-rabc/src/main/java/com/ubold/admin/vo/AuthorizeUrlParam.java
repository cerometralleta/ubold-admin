package com.ubold.admin.vo;

import com.ubold.admin.request.Request;

/**
 * Created by ningzuokun on 2018/3/22.
 */
public class AuthorizeUrlParam extends Request {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
