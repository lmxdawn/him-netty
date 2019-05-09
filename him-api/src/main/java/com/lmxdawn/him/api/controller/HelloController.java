package com.lmxdawn.him.api.controller;

import com.lmxdawn.him.api.service.ws.WSService;
import com.lmxdawn.him.api.utils.JwtUtils;
import com.lmxdawn.him.api.utils.WSBaseReqUtils;
import com.lmxdawn.him.api.vo.req.WSBaseReqVO;
import com.lmxdawn.him.api.vo.req.WSMessageReqVO;
import com.lmxdawn.him.api.vo.req.WSUserReqVO;
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

        Integer type = 1;
        Long id = uid;
        Integer msgType = 0;
        String msgContent = "嘿嘿测试";
        Long sUid = 1L;
        String name = "测试";
        String avatar = "头像";
        String remark = "说明";

        WSBaseReqVO wsBaseReqVO = WSBaseReqUtils.create(type, id, msgType, msgContent, sUid, name, avatar, remark);

        Boolean aBoolean = wsService.sendMsg(uid, wsBaseReqVO);

        return aBoolean ? "success" : "客户端不在线";
    }

}
