package com.lmxdawn.him.api.vo.res;

/**
 * QQ 授权获取 openID
 */
public class QqOpenIdResVO {
    
    /**
     * client_id : YOUR_APPID
     * openid : YOUR_OPENID
     */
    
    private String client_id;
    private String openid;
    
    public String getClient_id() {
        return client_id;
    }
    
    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
    
    public String getOpenid() {
        return openid;
    }
    
    public void setOpenid(String openid) {
        this.openid = openid;
    }

}
