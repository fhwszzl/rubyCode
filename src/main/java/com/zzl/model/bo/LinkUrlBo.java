package com.zzl.model.bo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LinkUrl")
public class LinkUrlBo {
    /**
     * KeyID.
     */
    private String code;

    /**
     * 原始地址
     */
    private String baseUrl;

    /**
     * 次数
     */
    private int count;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
