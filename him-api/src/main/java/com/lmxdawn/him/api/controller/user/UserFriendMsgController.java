package com.lmxdawn.him.api.controller.user;

import com.lmxdawn.him.api.dto.UserLoginDTO;
import com.lmxdawn.him.api.constant.WSMsgTypeConstant;
import com.lmxdawn.him.api.constant.WSResTypeConstant;
import com.lmxdawn.him.api.service.user.UserFriendMsgService;
import com.lmxdawn.him.api.service.user.UserFriendService;
import com.lmxdawn.him.api.service.user.UserService;
import com.lmxdawn.him.api.ws.WSServer;
import com.lmxdawn.him.api.utils.PageUtils;
import com.lmxdawn.him.api.utils.UserLoginUtils;
import com.lmxdawn.him.api.utils.WSBaseReqUtils;
import com.lmxdawn.him.api.vo.req.UserFriendMsgClearMsgCountReqVO;
import com.lmxdawn.him.api.vo.req.UserFriendMsgSaveReqVO;
import com.lmxdawn.him.api.vo.req.WSBaseReqVO;
import com.lmxdawn.him.common.entity.user.User;
import com.lmxdawn.him.common.entity.user.UserFriend;
import com.lmxdawn.him.common.entity.user.UserFriendMsg;
import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.common.utils.ResultVOUtils;
import com.lmxdawn.him.common.vo.res.BaseResVO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 朋友相关
 */
@RequestMapping("/user/friendMsg")
@RestController
public class UserFriendMsgController {
    
    @Resource
    private UserFriendMsgService userFriendMsgService;
    
    @Resource
    private UserFriendService userFriendService;
    
    @Resource
    private UserService userService;

    @Resource
    private WSServer wsServer;
    
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
        if (userLoginDTO == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
        
        Long uid = userLoginDTO.getUid();
        
        if (limit > 50) {
            limit = 50;
        }
        
        Integer offset = PageUtils.createOffset(page, limit);
        
        // 把最小的那个 用户ID作为查询条件
        Long toUid = senderUid;
        if (uid > senderUid) {
            toUid = uid;
            uid = senderUid;
        }
        
        List<UserFriendMsg> userFriendMsgs = userFriendMsgService.listByUidAndToUid(uid, toUid, offset, limit);
        
        return ResultVOUtils.success(userFriendMsgs);
        
    }
    
    /**
     * 发送消息
     *
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
        if (userLoginDTO == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }
        
        Long uid = userLoginDTO.getUid();
        
        Long receiverUid = userFriendMsgSaveReqVO.getReceiverUid();
        
        // 判断是不是朋友
        UserFriend userFriend = userFriendService.findByUidAndFriendUid(uid, receiverUid);
        if (userFriend == null) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "该用户还不是你的好友~");
        }
    
        Long senderUid = uid;
        // 追加消息
        UserFriendMsg userFriendMsg = new UserFriendMsg();
        // 把最小的那个 用户ID作为 之后的查询uid
        Long toUid = receiverUid;
        if (uid > receiverUid) {
            toUid = uid;
            uid = receiverUid;
        }
        userFriendMsg.setUid(uid);
        userFriendMsg.setToUid(toUid);
        userFriendMsg.setSenderUid(senderUid);
        Integer msgType = userFriendMsgSaveReqVO.getMsgType();
        String msgContent = userFriendMsgSaveReqVO.getMsgContent();
        String lastMsgContent = msgContent;
        switch (msgType) {
            case WSMsgTypeConstant.TEXT:
                break;
            case WSMsgTypeConstant.IMAGE:
                lastMsgContent = "[图片消息]";
                break;
            case WSMsgTypeConstant.FILE:
                lastMsgContent = "[文件消息]";
                break;
            case WSMsgTypeConstant.VOICE:
                lastMsgContent = "[语言消息]";
                break;
            case WSMsgTypeConstant.VIDEO:
                lastMsgContent = "[视频消息]";
                break;
            default:
                return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "位置消息类型");
        }
        userFriendMsg.setMsgType(msgType);
        userFriendMsg.setMsgContent(msgContent);
        boolean b = userFriendMsgService.insertUserFriendMsg(userFriendMsg);
        if (!b) {
            return ResultVOUtils.error();
        }
        
        List<UserFriend> userFriends = new ArrayList<>();
        // 给接收者更新最后一次的消息
        UserFriend userFriend1 = new UserFriend();
        userFriend1.setUid(receiverUid);
        userFriend1.setFriendUid(senderUid);
        userFriend1.setUnMsgCount(1);
        userFriend1.setLastMsgContent(lastMsgContent);
        userFriend1.setCreateTime(new Date());
        userFriend1.setModifiedTime(new Date());
        userFriends.add(userFriend1);
        // 给当前用户更新最后一次的消息
        UserFriend userFriend2 = new UserFriend();
        userFriend2.setUid(senderUid);
        userFriend2.setFriendUid(receiverUid);
        userFriend2.setUnMsgCount(0);
        userFriend2.setLastMsgContent(lastMsgContent);
        userFriend2.setCreateTime(new Date());
        userFriend2.setModifiedTime(new Date());
        userFriends.add(userFriend2);
        
        userFriendService.insertUserFriendAll(userFriends);

        // 发送在线消息
        // 查询用户信息
        User user = userService.findByUid(senderUid);
        Long sUid = user.getUid();
        String name = user.getName();
        String avatar = user.getAvatar();
        String remark = user.getRemark();
        WSBaseReqVO wsBaseReqVO = WSBaseReqUtils.create(WSResTypeConstant.FRIEND, receiverUid, msgType, msgContent, sUid, name, avatar, remark);
        wsServer.sendMsg(receiverUid, wsBaseReqVO);
        
        return ResultVOUtils.success();
    }
    
    
    /**
     * 清空未读的消息数量
     *
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
        if (userLoginDTO == null) {
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
