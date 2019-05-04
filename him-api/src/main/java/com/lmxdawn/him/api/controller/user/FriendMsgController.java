package com.lmxdawn.him.api.controller.user;

import com.lmxdawn.him.api.dto.UserLoginDTO;
import com.lmxdawn.him.api.service.user.UserFriendMsgService;
import com.lmxdawn.him.api.service.user.UserFriendService;
import com.lmxdawn.him.api.service.user.UserService;
import com.lmxdawn.him.api.utils.PageUtils;
import com.lmxdawn.him.api.utils.UserLoginUtils;
import com.lmxdawn.him.api.vo.req.UserFriendAskAckReqVO;
import com.lmxdawn.him.api.vo.req.UserFriendDeleteReqVO;
import com.lmxdawn.him.api.vo.req.UserFriendMsgClearMsgCountReqVO;
import com.lmxdawn.him.api.vo.req.UserFriendMsgSaveReqVO;
import com.lmxdawn.him.api.vo.res.UserFriendListInfoResVO;
import com.lmxdawn.him.api.vo.res.UserInfoListResVO;
import com.lmxdawn.him.common.entity.user.User;
import com.lmxdawn.him.common.entity.user.UserFriend;
import com.lmxdawn.him.common.entity.user.UserFriendMsg;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 朋友相关
 */
@RequestMapping("/user/friendMsg")
@RestController
public class FriendMsgController {
    
    @Resource
    private UserFriendMsgService userFriendMsgService;
    
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
    public BaseResVO lists(@RequestParam(value = "senderUid") Long senderUid,
                           @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                           @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
                           HttpServletRequest request) {
        
        // 验证登录
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null || userLoginDTO.getUid() == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
        
        Long uid = userLoginDTO.getUid();
    
        if (limit > 50) {
            limit = 50;
        }
    
        Integer offset = PageUtils.createOffset(page, limit);
        
        // 把最小的那个 用户ID作为查询条件
        uid = uid > senderUid ? senderUid : uid;
    
        List<UserFriendMsg> userFriendMsgs = userFriendMsgService.listByUid(uid, offset, limit);
    
        return ResultVOUtils.success(userFriendMsgs);
        
    }
    
    /**
     * 发送消息
     * @return
     */
    @PostMapping("/create")
    public BaseResVO create(@Valid @RequestBody UserFriendMsgSaveReqVO userFriendMsgSaveReqVO,
                            BindingResult bindingResult,
                            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }
    
        // 验证登录
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null || userLoginDTO.getUid() == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
    
        Long uid = userLoginDTO.getUid();
    
        Long receiverUid = userFriendMsgSaveReqVO.getReceiverUid();
    
        // 判断是不是朋友
        UserFriend userFriend1 = userFriendService.findByUidAndFriendUid(uid, receiverUid);
        if (userFriend1 == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "该用户还不是你的好友~");
        }
    
        // 追加消息
        UserFriendMsg userFriendMsg = new UserFriendMsg();
        // 把最小的那个 用户ID作为 之后的查询uid
        userFriendMsg.setUid(uid > receiverUid ? receiverUid : uid);
        userFriendMsg.setSenderUid(uid);
        Integer msgType = userFriendMsgSaveReqVO.getMsgType();
        String msgContent = userFriendMsgSaveReqVO.getMsgContent();
        String lastMsgCount = msgContent;
        switch (msgType) {
            case 1:
                lastMsgCount = "[图片消息]";
                break;
            case 2:
                lastMsgCount = "[文件消息]";
                break;
            case 3:
                lastMsgCount = "[语言消息]";
                break;
            case 4:
                lastMsgCount = "[视频消息]";
                break;
        }
        userFriendMsg.setMsgType(msgType);
        userFriendMsg.setMsgContent(msgContent);
        boolean b = userFriendMsgService.insertUserFriendMsg(userFriendMsg);
        if (!b) {
            return ResultVOUtils.error();
        }
        
        // 更新最后一次的消息
        UserFriend userFriend = new UserFriend();
        userFriend.setUid(receiverUid);
        userFriend.setFriendUid(uid);
        userFriend.setUnMsgCount(1);
        userFriend.setLastMsgContent(lastMsgCount);
        userFriendService.updateUserFriend(userFriend);
    
        return ResultVOUtils.success();
    }
    
    
    /**
     * 清空未读的消息数量
     * @return
     */
    @PostMapping("/clearUnMsgCount")
    public BaseResVO clearUnMsgCount(@Valid @RequestBody UserFriendMsgClearMsgCountReqVO msgCountReqVO,
                            BindingResult bindingResult,
                            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }
    
        // 验证登录
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null || userLoginDTO.getUid() == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
    
        Long uid = userLoginDTO.getUid();
    
        Long receiverUid = msgCountReqVO.getReceiverUid();
        
        // 更新最后一次的消息
        UserFriend userFriend = new UserFriend();
        userFriend.setUid(uid);
        userFriend.setFriendUid(receiverUid);
        userFriend.setUnMsgCount(0);
        boolean b = userFriendService.updateUserFriend(userFriend);
        
        return ResultVOUtils.success();
    }
    
}
