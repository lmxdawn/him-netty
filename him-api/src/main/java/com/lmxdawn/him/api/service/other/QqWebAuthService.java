package com.lmxdawn.him.api.service.other;

import com.lmxdawn.him.api.vo.res.QqOpenIdResVO;
import com.lmxdawn.him.api.vo.res.QqUserInfoResVO;

public interface QqWebAuthService {
    
    /**
     * 获取 access_token
     *
     * @return
     */
    public String getAccessToken(String code, String redirect_uri) ;
    
    /**
     * 获取 openID
     *
     * @return
     */
    public QqOpenIdResVO getOpenID(String accessToken) ;
    
    /**
     * 获取用户信息
     * @return
     */
    public QqUserInfoResVO getUserInfo(String code, String redirect_uri);
}
