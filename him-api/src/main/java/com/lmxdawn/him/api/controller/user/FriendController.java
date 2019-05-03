package com.lmxdawn.him.api.controller.user;

import com.lmxdawn.him.api.annotation.CheckLoginAnnotation;
import com.lmxdawn.him.api.service.user.UserFriendService;
import com.lmxdawn.him.api.vo.req.UserFriendSaveReqVO;
import com.lmxdawn.him.api.vo.req.UserFriendListReqVO;
import com.lmxdawn.him.api.vo.res.UserFriendListInfoResVO;
import com.lmxdawn.him.common.entity.user.UserFriend;
import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.common.utils.ResultVOUtils;
import com.lmxdawn.him.common.vo.res.BaseResVO;
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
    public BaseResVO lists(@RequestParam("uid") Long uid,
                           @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                           @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit) {

        if (limit > 50) {
            limit = 50;
        }

        UserFriendListReqVO userFriendListRequest = new UserFriendListReqVO();
        userFriendListRequest.setUid(uid);
        userFriendListRequest.setPage(page);
        userFriendListRequest.setLimit(limit);
        List<UserFriendListInfoResVO> userFriendListInfoResVOList = userFriendService.listByUid(userFriendListRequest);


        return ResultVOUtils.success(userFriendListInfoResVOList);

    }


    /**
     * 发起朋友请求
     * @param userFriendSaveReqVO
     * @param bindingResult
     * @return
     */
    @CheckLoginAnnotation
    @PostMapping("/ask")
    public BaseResVO create(@Valid @RequestBody UserFriendSaveReqVO userFriendSaveReqVO,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        // 查找是否是好友关系
        UserFriend byUidAndFriendUid = userFriendService.findByUidAndFriendUid(userFriendSaveReqVO.getUid(), userFriendSaveReqVO.getFriendUid());
        if (null != byUidAndFriendUid.getId()) {
            return ResultVOUtils.success();
        }

        UserFriend userFriend = new UserFriend();
        userFriend.setFriendUid(userFriendSaveReqVO.getUid());
        userFriend.setFriendUid(userFriendSaveReqVO.getFriendUid());
        userFriend.setRemark(userFriendSaveReqVO.getRemark());

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
    public BaseResVO delete(@Valid @RequestBody UserFriendSaveReqVO userFriendSaveReqVO,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        boolean b = userFriendService.deleteByUidAndFriendUid(userFriendSaveReqVO.getUid(), userFriendSaveReqVO.getFriendUid());

        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }

}
