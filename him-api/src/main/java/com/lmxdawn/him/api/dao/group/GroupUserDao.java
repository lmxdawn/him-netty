package com.lmxdawn.him.api.dao.group;

import com.lmxdawn.him.api.req.GroupUserListRequest;
import com.lmxdawn.him.api.req.UserGroupListRequest;
import com.lmxdawn.him.common.entity.group.GroupUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupUserDao {

    /**
     * 查询群的用户列表
     * @return
     */
    List<GroupUser> listByGroupId(GroupUserListRequest groupUserListRequest);

    /**
     * 查询用户的群列表
     * @return
     */
    List<GroupUser> listByUid(UserGroupListRequest userGroupListRequest);

    /**
     * 插入
     * @return
     */
    boolean insertGroupUser(GroupUser groupUser);

    /**
     * 更新
     * @return
     */
    boolean updateGroupUser(GroupUser groupUser);

    /**
     * 删除
     * @return
     */
    boolean deleteByGroupIdAndUid(@Param(value = "groupId") Long groupId, @Param(value = "uid") Long uid);

}
