package com.lmxdawn.him.api.controller.group;

import com.lmxdawn.him.api.dto.UserLoginDTO;
import com.lmxdawn.him.api.service.group.GroupService;
import com.lmxdawn.him.api.service.group.GroupUserService;
import com.lmxdawn.him.api.utils.UserLoginUtils;
import com.lmxdawn.him.api.vo.req.GroupSaveReqVO;
import com.lmxdawn.him.common.entity.group.Group;
import com.lmxdawn.him.common.entity.group.GroupUser;
import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.common.utils.ResultVOUtils;
import com.lmxdawn.him.common.vo.res.BaseResVO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 群相关
 */
@RequestMapping("/group")
@RestController
public class GroupIndexController {

    @Resource
    private GroupService groupService;

    @Resource
    private GroupUserService groupUserService;


    /**
     * 添加
     *
     * @return
     */
    @PostMapping("/create")
    public BaseResVO create(@Valid @RequestBody GroupSaveReqVO groupSaveReqVO,
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

        Group group = new Group();
        group.setUid(uid);
        group.setName(groupSaveReqVO.getName());
        group.setAvatar(groupSaveReqVO.getAvatar());
        group.setMemberNum(1);
        group.setRemark(groupSaveReqVO.getRemark());
        boolean b = groupService.insertGroup(group);

        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        // 加入群列表
        GroupUser groupUser = new GroupUser();
        groupUser.setGroupId(group.getGroupId());
        groupUser.setUid(uid);
        groupUser.setRank(2); // 群主
        boolean b1 = groupUserService.insertGroupUser(groupUser);

        return ResultVOUtils.success(group);
    }

    /**
     * 更新
     *
     * @return
     */
    @PostMapping("/update")
    public BaseResVO update(@Valid @RequestBody GroupSaveReqVO groupSaveReqVO,
                            BindingResult bindingResult,
                            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, bindingResult.getFieldError().getDefaultMessage());
        }

        if (groupSaveReqVO.getGroupId() == null
                || groupSaveReqVO.getGroupId() <= 0) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误~");
        }

        // 验证登录
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null || userLoginDTO.getUid() == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }

        Long uid = userLoginDTO.getUid();

        Group group1 = groupService.findByGroupId(groupSaveReqVO.getGroupId());
        // 判断是否是群主
        if (group1 == null || !uid.equals(group1.getUid())) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "没有该群信息~");
        }

        Group group = new Group();
        boolean isUp = false;
        if (!groupSaveReqVO.getName().equals(group1.getName())) {
            group.setName(groupSaveReqVO.getName());
            isUp = true;
        }
        if (!groupSaveReqVO.getAvatar().equals(group1.getAvatar())) {
            group.setAvatar(groupSaveReqVO.getAvatar());
            isUp = true;
        }
        if (!groupSaveReqVO.getRemark().equals(group1.getRemark())) {
            group.setRemark(groupSaveReqVO.getRemark());
            isUp = true;
        }
        boolean b = true;
        if (isUp) {
            b = groupService.insertGroup(group);
        }
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }

    /**
     * 删除 （解散群）
     *
     * @return
     */
    @PostMapping("/delete")
    public BaseResVO delete(@RequestParam(value = "groupId") Long groupId,
                            HttpServletRequest request) {

        if (groupId == null
                || groupId <= 0) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "参数错误~");
        }

        // 验证登录
        UserLoginDTO userLoginDTO = UserLoginUtils.check(request);
        if (userLoginDTO == null || userLoginDTO.getUid() == null) {
            return ResultVOUtils.error(ResultEnum.LOGIN_VERIFY_FALL);
        }

        Long uid = userLoginDTO.getUid();

        Group group1 = groupService.findByGroupId(groupId);
        // 判断是否是群主
        if (group1 == null || !uid.equals(group1.getUid())) {
            return ResultVOUtils.error(ResultEnum.PARAM_VERIFY_FALL, "没有该群信息~");
        }

        // 删除群成员信息
        boolean b = groupUserService.deleteByGroupId(groupId);
        if (!b) {
            return ResultVOUtils.error(ResultEnum.NOT_NETWORK);
        }

        return ResultVOUtils.success();
    }

}
