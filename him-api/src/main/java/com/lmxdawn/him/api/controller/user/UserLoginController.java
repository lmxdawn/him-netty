package com.lmxdawn.him.api.controller.user;


import com.lmxdawn.him.api.vo.req.UserLoginPwdReqVO;
import com.lmxdawn.him.api.vo.res.UserLoginResVO;
import com.lmxdawn.him.api.service.user.UserService;
import com.lmxdawn.him.api.utils.JwtUtils;
import com.lmxdawn.him.api.utils.PasswordUtils;
import com.lmxdawn.him.common.entity.user.User;
import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.common.vo.res.BaseResVO;
import com.lmxdawn.him.common.utils.ResultVOUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 */
@RequestMapping("/user/login")
@RestController
public class UserLoginController {
    
    @Resource
    private UserService userService;
    
    /**
     * 用户密码登录
     * @param userLoginPwdReqVO
     * @param bindingResult
     * @return
     */
    @PostMapping("/byPwd")
    public BaseResVO byPwd(@Valid @RequestBody UserLoginPwdReqVO userLoginPwdReqVO,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }
    
        Long uid = userLoginPwdReqVO.getUid();
        User user = userService.findPwdByUid(uid);
        String md5Pwd = PasswordUtils.md52md5(userLoginPwdReqVO.getPwd());
        if (!md5Pwd.equals(user.getPwd())) {
            return  ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "密码或用户名错误~");
        }
        
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", uid);
        String token = JwtUtils.createToken(claims, 86400L); // 一天后过期
    
        UserLoginResVO userLoginResVO = new UserLoginResVO();
        userLoginResVO.setUid(uid);
        userLoginResVO.setToken(token);
        return ResultVOUtils.success(userLoginResVO);
    }
    
    
}
