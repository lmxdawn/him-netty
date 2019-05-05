package com.lmxdawn.him.api.controller.user;

import com.lmxdawn.him.api.dto.UserLoginDTO;
import com.lmxdawn.him.api.service.user.UserFriendService;
import com.lmxdawn.him.api.service.user.UserService;
import com.lmxdawn.him.api.utils.UserLoginUtils;
import com.lmxdawn.him.api.vo.req.UserFriendDeleteReqVO;
import com.lmxdawn.him.api.vo.res.UserFriendListInfoResVO;
import com.lmxdawn.him.api.vo.res.UserInfoListResVO;
import com.lmxdawn.him.common.entity.user.UserFriend;
import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.common.utils.ResultVOUtils;
import com.lmxdawn.him.common.vo.res.BaseResVO;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 朋友相关
 */
@RequestMapping("/user/friend")
@RestController
public class UserFriendController {
    
    @Resource
    private UserFriendService userFriendService;
    
    @Resource
    private UserService userService;
    
    /**
     * 获取朋友列表
     *
     * @return
     */
    @GetMapping("/lists")
    public BaseResVO lists(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                           @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
                           HttpServletRequest request) {
        
        // 验证登录
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }

        limit = limit > 50 ? 50 : limit;

        Long uid = userLoginDTO.getUid();


        List<UserFriend> userFriends = userFriendService.listByUid(uid, page, limit);
    
        List<Long> uids = userFriends.stream().map(UserFriend::getFriendUid).collect(Collectors.toList());
    
        Map<Long, UserInfoListResVO> userInfoListResVOMap = userService.listByUidIn(uids);
        
        List<UserFriendListInfoResVO> userFriendListInfoResVOS = new ArrayList<>();
        userFriends.forEach(v -> {
            UserFriendListInfoResVO userFriendListInfoResVO = new UserFriendListInfoResVO();
            BeanUtils.copyProperties(v, userFriendListInfoResVO);
            userFriendListInfoResVO.setUser(userInfoListResVOMap.get(v.getFriendUid()));
            userFriendListInfoResVOS.add(userFriendListInfoResVO);
        });
    
        return ResultVOUtils.success(userFriendListInfoResVOS);
        
    }
    
    
    /**
     * 删除好友
     *
     * @return
     */
    @PostMapping("/delete")
    public BaseResVO delete(@Valid @RequestBody UserFriendDeleteReqVO userFriendDeleteReqVO,
                            BindingResult bindingResult,
                            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }
        
        // 验证登录
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
        
        Long uid = userLoginDTO.getUid();
        Long friendUid = userFriendDeleteReqVO.getFriendUid();

        boolean b = userFriendService.deleteByUidAndFriendUid(uid, friendUid);
        boolean b1 = userFriendService.deleteByUidAndFriendUid(friendUid, uid);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }
        
        return ResultVOUtils.success();
    }
}
