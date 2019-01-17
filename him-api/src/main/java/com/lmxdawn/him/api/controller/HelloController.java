package com.lmxdawn.him.api.controller;

import com.lmxdawn.him.api.utils.JwtUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam("uid") Long uid) {

        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        String token = JwtUtils.createToken(map);

        return token;
    }

}
