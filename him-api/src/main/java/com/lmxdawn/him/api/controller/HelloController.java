package com.lmxdawn.him.api.controller;

import com.lmxdawn.him.api.service.ws.WSService;
import com.lmxdawn.him.api.utils.JwtUtils;
import com.lmxdawn.him.api.vo.req.WSMessageReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @Autowired
    private WSService wsService;

    @GetMapping("/hello")
    public String hello(@RequestParam("uid") Long uid) {

        // 测试发送 websocket 数据
        WSMessageReqVO wsMessageReqVO = new WSMessageReqVO();
        wsMessageReqVO.setType(1);
        wsMessageReqVO.setSenderId(1L);
        wsMessageReqVO.setReceiveId(uid);
        wsMessageReqVO.setMsgType(1);
        wsMessageReqVO.setMsgContent("嘿嘿舒服了的空间十分了解三法师");

        System.out.println(wsMessageReqVO);

        Boolean aBoolean = wsService.sendMsg(uid, wsMessageReqVO);

        return aBoolean ? "success" : "客户端不在线";
    }

}
