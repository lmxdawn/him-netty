package com.lmxdawn.him.api.controller.user;

import com.lmxdawn.him.api.dto.UserLoginDTO;
import com.lmxdawn.him.api.service.user.*;
import com.lmxdawn.him.api.utils.PageUtils;
import com.lmxdawn.him.api.utils.UserLoginUtils;
import com.lmxdawn.him.api.vo.req.UserFriendAskAckReqVO;
import com.lmxdawn.him.api.vo.req.UserFriendAskSaveReqVO;
import com.lmxdawn.him.api.vo.res.UserFriendAskListResVO;
import com.lmxdawn.him.api.vo.res.UserInfoListResVO;
import com.lmxdawn.him.common.entity.user.*;
import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.common.utils.ResultVOUtils;
import com.lmxdawn.him.common.vo.res.BaseResVO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

/**
 * 好友请求
 */
@RequestMapping("/user/friendAsk")
@RestController
public class FriendAskController {
  
  @Resource
  private UserFriendAskService userFriendAskService;
  
  @Resource
  private UserProfileService userProfileService;
  
  @Resource
  private UserFriendService userFriendService;
  
  @Resource
  private UserService userService;
  
  @Resource
  private UserFriendMsgService userFriendMsgService;
  
  @GetMapping("/lists")
  public BaseResVO lists(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                         @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
                         HttpServletRequest request) {
    
    // 验证登录
    UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
    if (userLoginDTO == null || userLoginDTO.getUid() == null) {
      return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
    }
    
    Long uid = userLoginDTO.getUid();
    
    limit = limit > 50 ? 50 : limit;
    
    Integer offset = PageUtils.createOffset(page, limit);
    
    List<UserFriendAskListResVO> userFriendAskListResVOS = userFriendAskService.listByUid(uid, offset, limit);
    
    return ResultVOUtils.success(userFriendAskListResVOS);
    
  }
  
  
  @PostMapping("/create")
  public BaseResVO create(@Valid @RequestBody UserFriendAskSaveReqVO userFriendAskSaveReqVO,
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
    Long friendUid = userFriendAskSaveReqVO.getFriendUid();
    if (uid.equals(friendUid)) {
      return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "不能自己加自己~");
    }
    
    UserFriend userFriend = userFriendService.findByUidAndFriendUid(uid, friendUid);
    if (userFriend != null) {
      return ResultVOUtils.error(ResultEnum.DATA_REPEAT, "已经是好友了~");
    }
    
    User user = userService.findByUid(friendUid);
    if (user == null) {
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
    userFriendAsk.setRemark(userFriendAskSaveReqVO.getRemark());
    userFriendAsk.setStatus(0);
    
    boolean b = userFriendAskService.insertUserFriendAsk(userFriendAsk);
    if (!b) {
      return ResultVOUtils.error();
    }
    System.out.println(userFriendAsk);
    
    // 增加朋友请求数量
    userProfileService.incFriendAskCount(friendUid, 1);
    
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
    if (userLoginDTO == null || userLoginDTO.getUid() == null) {
      return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
    }
    
    Long uid = userLoginDTO.getUid();
    Long id = userFriendAskAckReqVO.getId();
    
    UserFriendAsk userFriendAsk = userFriendAskService.findById(id);
    if (userFriendAsk == null || !uid.equals(userFriendAsk.getUid()) || userFriendAsk.getStatus() != 0) {
      return ResultVOUtils.success();
    }
    Long friendUid = userFriendAsk.getFriendUid();
    // 判断好友数量
    List<UserProfile> userProfiles = userProfileService.listByUidIn(Arrays.asList(uid, friendUid));
    for (UserProfile userProfile : userProfiles) {
      if (userProfile != null
        && userProfile.getFriendCount() != null
        && userProfile.getFriendCount() >= 2000) {
        return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "好友数量已到上限~");
      }
    }
    
    UserFriendAsk upUserFriendAsk = new UserFriendAsk();
    upUserFriendAsk.setId(id);
    upUserFriendAsk.setStatus(userFriendAskAckReqVO.getStatus());
    boolean b = userFriendAskService.updateUserFriendAsk(upUserFriendAsk);
    
    if (!b || userFriendAskAckReqVO.getStatus() == 2) {
      return ResultVOUtils.success();
    }
    
    // 增加朋友
    List<UserFriend> userFriends = new ArrayList<>();
    
    // 增加当前用户的好友列表
    UserFriend userFriend1 = new UserFriend();
    userFriend1.setUid(uid);
    userFriend1.setFriendUid(friendUid);
    userFriend1.setRemark("");
    userFriend1.setLastMsgContent(userFriendAsk.getRemark());
    userFriend1.setCreateTime(new Date());
    userFriend1.setModifiedTime(new Date());
    userFriends.add(userFriend1);
    
    // 增加申请人的好友列表
    UserFriend userFriend2 = new UserFriend();
    userFriend2.setUid(friendUid);
    userFriend2.setFriendUid(uid);
    userFriend2.setRemark("");
    userFriend2.setLastMsgContent("");
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
    
    // 追加消息
    UserFriendMsg userFriendMsg = new UserFriendMsg();
    userFriendMsg.setSenderUid(friendUid);
    userFriendMsg.setReceiverUid(uid);
    userFriendMsg.setMsgContent(userFriendAsk.getRemark());
    userFriendMsg.setMsgType(1);
    userFriendMsgService.insertUserFriendMsg(userFriendMsg);
    
    Map<Long, UserInfoListResVO> userInfoListResVOMap = userService.listByUidIn(Collections.singletonList(friendUid));
    
    return ResultVOUtils.success(userInfoListResVOMap.get(friendUid));
    
  }
  
  /**
   * 清空请求数量
   * @return
   */
  @PostMapping("/clearFriendAskCount")
  public BaseResVO clearFriendAskCount(HttpServletRequest request) {
  
    // 验证登录
    UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
    if (userLoginDTO == null || userLoginDTO.getUid() == null) {
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
