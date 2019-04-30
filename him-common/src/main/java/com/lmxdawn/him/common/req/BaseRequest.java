package com.lmxdawn.him.common.req;

import java.util.UUID;

public class BaseRequest {

    /**
     * 唯一请求号
     */
    private String reqNo;

    /**
     * 请求的时间戳
     */
    private Long timeStamp;

    public BaseRequest() {
        this.reqNo = UUID.randomUUID().toString();
        this.timeStamp = System.currentTimeMillis();
    }

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
