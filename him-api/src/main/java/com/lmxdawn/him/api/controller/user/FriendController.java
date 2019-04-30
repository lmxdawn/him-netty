package com.lmxdawn.him.api.controller.user;

import com.lmxdawn.him.api.annotation.CheckLoginAnnotation;
import com.lmxdawn.him.api.service.user.UserFriendService;
import com.lmxdawn.him.api.req.UserFriendSaveRequest;
import com.lmxdawn.him.api.req.UserFriendListRequest;
import com.lmxdawn.him.api.res.UserFriendListInfoResponse;
import com.lmxdawn.him.common.entity.user.UserFriend;
import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.common.utils.ResultVOUtils;
import com.lmxdawn.him.common.res.BaseResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 朋友相关
 */
@RequestMapping("/user/friend")
@RestController
public class FriendController {

    @Resource
    private UserFriendService userFriendService;

    /**
     * 获取朋友列表
     * @return
     */
    @CheckLoginAnnotation
    @GetMapping("/lists")
    public BaseResponse lists(@RequestParam("uid") Long uid,
                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                              @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit) {

        if (limit > 50) {
            limit = 50;
        }

        UserFriendListRequest userFriendListRequest = new UserFriendListRequest();
        userFriendListRequest.setUid(uid);
        userFriendListRequest.setPage(page);
        userFriendListRequest.setLimit(limit);
        List<UserFriendListInfoResponse> userFriendListInfoResVOList = userFriendService.listByUid(userFriendListRequest);


        return ResultVOUtils.success(userFriendListInfoResVOList);

    }


    /**
     * 创建朋友
     * @param userFriendSaveRequest
     * @param bindingResult
     * @return
     */
    @CheckLoginAnnotation
    @PostMapping("/create")
    public BaseResponse create(@Valid @RequestBody UserFriendSaveRequest userFriendSaveRequest,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        // 查找是否是好友关系
        UserFriend byUidAndFriendUid = userFriendService.findByUidAndFriendUid(userFriendSaveRequest.getUid(), userFriendSaveRequest.getFriendUid());
        if (null != byUidAndFriendUid.getId()) {
            return ResultVOUtils.success();
        }

        UserFriend userFriend = new UserFriend();
        userFriend.setFriendUid(userFriendSaveRequest.getUid());
        userFriend.setFriendUid(userFriendSaveRequest.getFriendUid());
        userFriend.setRemark(userFriendSaveRequest.getRemark());

        boolean b = userFriendService.insertUserFriend(userFriend);

        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }

    /**
     * 删除好友
     * @return
     */
    @CheckLoginAnnotation
    @PostMapping("/delete")
    public BaseResponse delete(@Valid @RequestBody UserFriendSaveRequest userFriendSaveRequest,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        boolean b = userFriendService.deleteByUidAndFriendUid(userFriendSaveRequest.getUid(), userFriendSaveRequest.getFriendUid());

        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }

}
