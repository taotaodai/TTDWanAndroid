package com.ttd.wanandroid.bean;

import java.io.Serializable;

/**
 * Created by wt on 2018/7/11.
 */

public class BaseBean implements Serializable {
    private int errorCode;
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
