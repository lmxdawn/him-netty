package com.lmxdawn.him.common.vo.req;

import java.util.UUID;

public class BaseReqVO {

    /**
     * 唯一请求号
     */
    private String reqNo;

    /**
     * 请求的时间戳
     */
    private Long timeStamp;

    public BaseReqVO() {
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
