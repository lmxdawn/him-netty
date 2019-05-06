package com.lmxdawn.him.api.controller.user;

import com.lmxdawn.him.api.dto.UserLoginDTO;
import com.lmxdawn.him.api.service.user.UserProfileService;
import com.lmxdawn.him.api.service.user.UserService;
import com.lmxdawn.him.api.utils.UserFriendUtils;
import com.lmxdawn.him.api.utils.UserLoginUtils;
import com.lmxdawn.him.api.vo.res.UserInfoResVO;
import com.lmxdawn.him.api.vo.res.UserProfileInfoResVO;
import com.lmxdawn.him.api.vo.res.UserReadResVO;
import com.lmxdawn.him.common.entity.user.User;
import com.lmxdawn.him.common.entity.user.UserProfile;
import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.common.utils.ResultVOUtils;
import com.lmxdawn.him.common.vo.res.BaseResVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@RestController
public class UserIndexController {
    
    @Resource
    private UserService userService;
    
    @Resource
    private UserProfileService userProfileService;
    
    /**
     * 登录用户信息
     * @return
     */
    @GetMapping("/loginInfo")
    public BaseResVO loginInfo(HttpServletRequest request) {
        
        // 验证登录
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
    
        Long uid = userLoginDTO.getUid();
    
        User user = userService.findByUid(uid);
        if (user == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
        
        UserProfile userProfile = userProfileService.findByUid(uid);
    
        UserProfileInfoResVO userProfileInfoResVO = new UserProfileInfoResVO();
        if (userProfile != null) {
            BeanUtils.copyProperties(userProfile, userProfileInfoResVO);
        }

        UserInfoResVO userInfoResVO = new UserInfoResVO();
        BeanUtils.copyProperties(user, userInfoResVO);
        userInfoResVO.setProfile(userProfileInfoResVO);
    
        return ResultVOUtils.success(userInfoResVO);
        
    }
    
    /**
     * 用户信息
     * @return
     */
    @GetMapping("/read")
    public BaseResVO read(@RequestParam(value = "uid") Long uid,
                          HttpServletRequest request) {
        
        // 验证登录
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
    
        User user = userService.findByUid(uid);
    
        UserReadResVO userReadResVO = new UserReadResVO();
        BeanUtils.copyProperties(user, userReadResVO);
    
        return ResultVOUtils.success(userReadResVO);
        
    }
    
    
    
    /**
     * 获取二维码
     *
     * @param request
     * @return
     */
    @GetMapping("/getQRCheckCode")
    public BaseResVO getQRCheckCode(HttpServletRequest request) {
        // 验证登录
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
        
        Long uid = userLoginDTO.getUid();
        
        String checkCode = UserFriendUtils.createCheckCode(uid);
        
        return ResultVOUtils.success(checkCode);
        
    }
    
    
}
