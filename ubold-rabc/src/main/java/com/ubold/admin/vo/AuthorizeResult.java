package com.ubold.admin.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 授权结果
 * Created by ningzuokun on 2017/12/21.
 */
public class AuthorizeResult implements Serializable{

    private List<authorizeMenu> authorizeMenuList;
    private List<String> authorizeCodeList;

    public List<authorizeMenu> getAuthorizeMenuList() {
        return authorizeMenuList;
    }

    public void setAuthorizeMenuList(List<authorizeMenu> authorizeMenuList) {
        this.authorizeMenuList = authorizeMenuList;
    }

    public List<String> getAuthorizeCodeList() {
        return authorizeCodeList;
    }

    public void setAuthorizeCodeList(List<String> authorizeCodeList) {
        this.authorizeCodeList = authorizeCodeList;
    }

    /**
     * 授权菜单信息
     */
    public static class authorizeMenu{

        private String code;
        private String name;
        private String url;
        private List<authorizeMenu> childs;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<authorizeMenu> getChilds() {
            return childs;
        }

        public void setChilds(List<authorizeMenu> childs) {
            this.childs = childs;
        }
    }
}
