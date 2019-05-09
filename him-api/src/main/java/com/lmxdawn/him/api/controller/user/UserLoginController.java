package com.lmxdawn.him.api.controller.user;


import com.lmxdawn.him.api.service.other.QqWebAuthService;
import com.lmxdawn.him.api.service.user.UserQqService;
import com.lmxdawn.him.api.utils.UserLoginUtils;
import com.lmxdawn.him.api.vo.req.UserLoginPwdReqVO;
import com.lmxdawn.him.api.vo.res.QqOpenIdResVO;
import com.lmxdawn.him.api.vo.res.QqUserInfoResVO;
import com.lmxdawn.him.api.vo.res.UserLoginResVO;
import com.lmxdawn.him.api.service.user.UserService;
import com.lmxdawn.him.api.utils.PasswordUtils;
import com.lmxdawn.him.common.entity.user.User;
import com.lmxdawn.him.common.entity.user.UserQq;
import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.common.vo.res.BaseResVO;
import com.lmxdawn.him.common.utils.ResultVOUtils;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 登录控制器
 */
@RequestMapping("/user/login")
@RestController
public class UserLoginController {
    
    @Resource
    private UserService userService;
    
    @Resource
    private QqWebAuthService qqWebAuthService;
    
    @Resource
    private UserQqService userQqService;
    
    /**
     * 用户密码登录
     *
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
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "密码或用户名错误~");
        }
        
        String token = UserLoginUtils.createSid(uid);
        
        UserLoginResVO userLoginResVO = new UserLoginResVO();
        userLoginResVO.setUid(uid);
        userLoginResVO.setToken(token);
        return ResultVOUtils.success(userLoginResVO);
    }
    
    /**
     * 用户密码登录
     *
     * @return
     */
    @PostMapping("/byQq")
    public BaseResVO byQq(@RequestParam(value = "code") String code,
                          @RequestParam(value = "redirect_uri") String redirect_uri) {
    
    
        String accessToken = qqWebAuthService.getAccessToken(code, redirect_uri);
        if (accessToken == null || "".equals(accessToken)) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "QQ授权失败~");
        }
    
        QqOpenIdResVO openIdResVO = qqWebAuthService.getOpenID(accessToken);
        if (openIdResVO == null || openIdResVO.getOpenid() == null || "".equals(openIdResVO.getOpenid())) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "QQ授权失败~");
        }
        
        QqUserInfoResVO userInfo = qqWebAuthService.getUserInfo(code, redirect_uri);
        
        if (userInfo == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "QQ授权失败~");
        }
    
        UserQq userQq = userQqService.findByOpenId(openIdResVO.getOpenid());
        
        // 判断是否有过值
        if (userQq == null || userQq.getUid() == null) {
            // 创建用户
            User user = new User();
            user.setName(userInfo.getNickname());
            user.setAvatar(userInfo.getFigureurl());
            boolean b = userService.insertUser(user);
            if (!b) {
                return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "创建用户失败~");
            }
    
            userQq = new UserQq();
            userQq.setUid(user.getUid());
            userQq.setOpenid(openIdResVO.getOpenid());
        }
    
        Long uid = userQq.getUid();
        
        String token = UserLoginUtils.createSid(uid);
        
        UserLoginResVO userLoginResVO = new UserLoginResVO();
        userLoginResVO.setUid(uid);
        userLoginResVO.setToken(token);
        return ResultVOUtils.success(userLoginResVO);
    }
    
    
}
