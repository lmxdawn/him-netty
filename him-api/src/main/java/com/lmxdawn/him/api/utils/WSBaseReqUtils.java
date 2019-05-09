package com.lmxdawn.him.api.utils;

import com.lmxdawn.him.api.vo.req.WSBaseReqVO;
import com.lmxdawn.him.api.vo.req.WSMessageReqVO;
import com.lmxdawn.him.api.vo.req.WSUserReqVO;

public class WSBaseReqUtils {

    /**
     * 创建请求实体类
     * @return
     */
    public static WSBaseReqVO create(Integer type, Long receiveId, Integer msgType, String msgContent, Long uid, String name, String avatar, String remark) {

        // 消息实体
        WSMessageReqVO wsMessageReqVO = new WSMessageReqVO();
        wsMessageReqVO.setReceiveId(receiveId);
        wsMessageReqVO.setMsgType(msgType);
        wsMessageReqVO.setMsgContent(msgContent);

        // 用户信息
        WSUserReqVO wsUserReqVO = new WSUserReqVO();
        wsUserReqVO.setUid(uid);
        wsUserReqVO.setName(name);
        wsUserReqVO.setAvatar(avatar);
        wsUserReqVO.setRemark(remark);

        WSBaseReqVO wsBaseReqVO = new WSBaseReqVO();
        wsBaseReqVO.setType(type);
        wsBaseReqVO.setMessage(wsMessageReqVO);
        wsBaseReqVO.setUser(wsUserReqVO);
        return wsBaseReqVO;
    }

}
