package com.lmxdawn.him.api.controller.user;

import com.lmxdawn.him.api.dto.UserLoginDTO;
import com.lmxdawn.him.api.constant.WSMsgTypeConstant;
import com.lmxdawn.him.api.constant.WSResTypeConstant;
import com.lmxdawn.him.api.service.user.*;
import com.lmxdawn.him.api.ws.WSServer;
import com.lmxdawn.him.api.utils.UserFriendUtils;
import com.lmxdawn.him.api.utils.UserLoginUtils;
import com.lmxdawn.him.api.utils.WSBaseReqUtils;
import com.lmxdawn.him.api.vo.req.UserFriendAskAckReqVO;
import com.lmxdawn.him.api.vo.req.WSBaseReqVO;
import com.lmxdawn.him.api.vo.res.UserFriendAskListResVO;
import com.lmxdawn.him.api.vo.res.UserInfoListResVO;
import com.lmxdawn.him.common.entity.user.*;
import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.common.utils.ResultVOUtils;
import com.lmxdawn.him.common.vo.res.BaseResVO;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 好友请求
 */
@RequestMapping("/user/friendAsk")
@RestController
public class UserFriendAskController {
  
  @Resource
  private UserFriendAskService userFriendAskService;
  
  @Resource
  private UserProfileService userProfileService;
  
  @Resource
  private UserFriendService userFriendService;
  
  @Resource
  private UserService userService;
  
  @Resource
  private WSServer wsServer;
  
  @Resource
  private UserFriendMsgService userFriendMsgService;
  
  @GetMapping("/lists")
  public BaseResVO lists(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                         @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
                         HttpServletRequest request) {
    
    // 验证登录
    UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
    if (userLoginDTO == null) {
      return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
    }
    
    Long uid = userLoginDTO.getUid();
    
    limit = limit > 50 ? 50 : limit;


    List<UserFriendAskListResVO> userFriendAskListResVOS = new ArrayList<>();
    List<UserFriendAsk> userFriendAsks = userFriendAskService.listByUid(uid, page, limit);

    if (userFriendAsks.size() == 0) {
      return ResultVOUtils.success(userFriendAskListResVOS);
    }

    List<Long> uids = userFriendAsks.stream().map(UserFriendAsk::getFriendUid).collect(Collectors.toList());

    Map<Long, UserInfoListResVO> userInfoListResVOMap = userService.listByUidIn(uids);

    userFriendAsks.forEach(v -> {
      UserFriendAskListResVO userFriendAskListResVO = new UserFriendAskListResVO();
      BeanUtils.copyProperties(v, userFriendAskListResVO);
      userFriendAskListResVO.setUser(userInfoListResVOMap.get(v.getFriendUid()));
      userFriendAskListResVOS.add(userFriendAskListResVO);
    });

    return ResultVOUtils.success(userFriendAskListResVOS);
    
  }
  
  
  @PostMapping("/create")
  public BaseResVO create(@RequestParam(value = "checkCode", required = false, defaultValue = "") String checkCode,
                          @RequestParam(value = "friendUid", required = false, defaultValue = "0L") Long friendUid,
                          @RequestParam(value = "remark", required = false, defaultValue = "") String remark,
                          HttpServletRequest request) {
    // 验证登录
    UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
    if (userLoginDTO == null) {
      return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
    }
    
    Long uid = userLoginDTO.getUid();
    if (friendUid == null || friendUid <= 0) {
        friendUid = UserFriendUtils.checkCode(checkCode);
    }
    // 验证用户是否合法加入
    if (friendUid == null) {
      return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "二维码过期~");
    }
    if (uid.equals(friendUid)) {
      return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "不能自己加自己~");
    }
    
    UserFriend userFriend = userFriendService.findByUidAndFriendUid(uid, friendUid);
    if (userFriend != null) {
      return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "已经是好友了~");
    }
    
    User friendUser = userService.findByUid(friendUid);
    if (friendUser == null) {
      return ResultVOUtils.error(ResultEnum.DATA_NOT);
    }
    
    // 判断好友数量
    List<UserProfile> userProfiles = userProfileService.listByUidIn(Arrays.asList(uid, friendUid));
    for (UserProfile userProfile : userProfiles) {
      if (userProfile != null
        && userProfile.getFriendCount() != null
        && userProfile.getFriendCount() >= 2000) {
        return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "好友数量已到上限~");
      }
    }
  
    UserFriendAsk userFriendAsk = new UserFriendAsk();
    userFriendAsk.setUid(friendUid);
    userFriendAsk.setFriendUid(uid);
    userFriendAsk.setRemark(remark);
    userFriendAsk.setStatus(0);
    
    boolean b = userFriendAskService.insertUserFriendAsk(userFriendAsk);
    if (!b) {
      return ResultVOUtils.error();
    }
    
    // 增加朋友请求数量
    userProfileService.incFriendAskCount(friendUid, 1);

    // 发送在线消息
    // 查询用户信息
    User user = userService.findByUid(uid);
    Integer msgType = WSMsgTypeConstant.TEXT;
    String msgContent = "请求加为好友";
    Long sUid = user.getUid();
    String name = user.getName();
    String avatar = user.getAvatar();
    String remark1 = user.getRemark();
    WSBaseReqVO wsBaseReqVO = WSBaseReqUtils.create(WSResTypeConstant.FRIEND_ASK, friendUid, msgType, msgContent, sUid, name, avatar, remark1);

    wsServer.sendMsg(friendUid, wsBaseReqVO);
    
    return ResultVOUtils.success();
    
  }
  
  /**
   * 好友请求
   *
   * @param userFriendAskAckReqVO
   * @param bindingResult
   * @param request
   * @return
   */
  @PostMapping("/ack")
  public BaseResVO ack(@Valid @RequestBody UserFriendAskAckReqVO userFriendAskAckReqVO,
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
    Long id = userFriendAskAckReqVO.getId();
    
    UserFriendAsk userFriendAsk = userFriendAskService.findById(id);
    // 已经添加过了
    if (userFriendAsk == null || !uid.equals(userFriendAsk.getUid()) || userFriendAsk.getStatus() != 0) {
      return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "请勿重复添加~");
    }
    
    UserFriendAsk upUserFriendAsk = new UserFriendAsk();
    upUserFriendAsk.setId(id);
    upUserFriendAsk.setStatus(userFriendAskAckReqVO.getStatus());
    boolean b = userFriendAskService.updateUserFriendAsk(upUserFriendAsk);

    // 拒绝添加
    if (!b) {
      return ResultVOUtils.error();
    }

    // 如果是拒绝
    if (userFriendAskAckReqVO.getStatus() == 2) {
      return ResultVOUtils.success();
    }

    Long friendUid = userFriendAsk.getFriendUid();
    // 判断是不是好友
    UserFriend userFriend = userFriendService.findByUidAndFriendUid(uid, friendUid);
    if (userFriend != null && userFriend.getId() != null) {
      return ResultVOUtils.success();
    }

    // 判断好友数量
    List<UserProfile> userProfiles = userProfileService.listByUidIn(Arrays.asList(uid, friendUid));
    for (UserProfile userProfile : userProfiles) {
      if (userProfile != null
              && userProfile.getFriendCount() != null
              && userProfile.getFriendCount() >= 2000) {
        return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "好友数量已到上限~");
      }
    }
    String msgContent = userFriendAsk.getRemark();

    msgContent = msgContent != null && !"".equals(msgContent) ? msgContent : "成为好友，现在开始聊吧~";

    // 增加朋友
    List<UserFriend> userFriends = new ArrayList<>();

    // 增加当前用户的好友列表
    UserFriend userFriend1 = new UserFriend();
    userFriend1.setUid(uid);
    userFriend1.setFriendUid(friendUid);
    userFriend1.setRemark("");
    userFriend1.setLastMsgContent(msgContent);
    userFriend1.setCreateTime(new Date());
    userFriend1.setModifiedTime(new Date());
    userFriends.add(userFriend1);

    // 增加申请人的好友列表
    UserFriend userFriend2 = new UserFriend();
    userFriend2.setUid(friendUid);
    userFriend2.setFriendUid(uid);
    userFriend2.setRemark("");
    userFriend2.setLastMsgContent(msgContent);
    userFriend2.setCreateTime(new Date());
    userFriend2.setModifiedTime(new Date());
    userFriends.add(userFriend2);


    boolean b1 = userFriendService.insertUserFriendAll(userFriends);

    // 更新好友数量
    List<UserProfile> userProfileArrayList = new ArrayList<>();
    UserProfile userProfile1 = new UserProfile();
    userProfile1.setUid(uid);
    userProfile1.setFriendCount(1);
    userProfile1.setCreateTime(new Date());
    userProfile1.setModifiedTime(new Date());
    userProfileArrayList.add(userProfile1);
    UserProfile userProfile2 = new UserProfile();
    userProfile2.setUid(friendUid);
    userProfile2.setFriendCount(1);
    userProfile2.setCreateTime(new Date());
    userProfile2.setModifiedTime(new Date());
    userProfileArrayList.add(userProfile2);
    boolean b2 = userProfileService.incFriendCountAll(userProfileArrayList);
  
    Long senderUid = uid;
    
    // 追加消息
    UserFriendMsg userFriendMsg = new UserFriendMsg();
    // 把最小的那个 用户ID作为 之后的查询uid
    Long toUid = friendUid;
    if (uid > friendUid) {
      toUid = uid;
      uid = friendUid;
    }
    userFriendMsg.setUid(uid);
    userFriendMsg.setToUid(toUid);
    userFriendMsg.setSenderUid(senderUid);
    userFriendMsg.setMsgContent(msgContent);
    userFriendMsg.setMsgType(1);
    userFriendMsgService.insertUserFriendMsg(userFriendMsg);

    // 发送在线消息
    // 查询用户信息
    User user = userService.findByUid(senderUid);
    Integer msgType = WSMsgTypeConstant.TEXT;
    Long sUid = user.getUid();
    String name = user.getName();
    String avatar = user.getAvatar();
    String remark1 = user.getRemark();
    WSBaseReqVO wsBaseReqVO = WSBaseReqUtils.create(WSResTypeConstant.FRIEND_ACK, friendUid, msgType, msgContent, sUid, name, avatar, remark1);
    wsServer.sendMsg(friendUid, wsBaseReqVO);

    return ResultVOUtils.success();
    
  }
  
  /**
   * 清空请求数量
   * @return
   */
  @PostMapping("/clearFriendAskCount")
  public BaseResVO clearFriendAskCount(HttpServletRequest request) {
  
    // 验证登录
    UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
    if (userLoginDTO == null) {
      return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
    }
  
    Long uid = userLoginDTO.getUid();
  
    boolean b = userProfileService.clearFriendAskCount(uid);
    
    if (!b) {
      return ResultVOUtils.error();
    }
    
    return ResultVOUtils.success();
    
  }
  
}
