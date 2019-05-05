package com.lmxdawn.him.api.service.group;

import com.lmxdawn.him.common.entity.group.GroupUser;

import java.util.List;

public interface GroupUserService {

    /**
     * 查询群用户信息
     * @return
     */
    GroupUser findByGroupIdAndUid(Long groupId, Long uid);

    /**
     * 查询群的用户列表
     * @return
     */
    List<GroupUser> listByGroupId(Long groupId, Integer page, Integer limit);

    /**
     * 查询用户的群列表
     * @return
     */
    List<GroupUser> listByUid(Long uid, Integer page, Integer limit);

    /**
     * 插入
     * @return
     */
    boolean insertGroupUser(GroupUser groupUser);

    /**
     * 更新
     * @return
     */
    boolean updateGroupUserByGroupIdAndUid(GroupUser groupUser);
    
    /**
     * 清空未读消息
     * @return
     */
    boolean clearUnMsgCountByGroupIdAndUid(GroupUser groupUser);

    /**
     * 根据群ID更新
     * @return
     */
    boolean updateGroupUserByGroupId(GroupUser groupUser);

    /**
     * 删除
     * @return
     */
    boolean deleteByGroupIdAndUid(Long groupId, Long uid);

    /**
     * 删除
     * @return
     */
    boolean deleteByGroupId(Long groupId);

}
