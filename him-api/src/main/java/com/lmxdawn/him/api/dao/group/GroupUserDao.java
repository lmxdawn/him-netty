package com.lmxdawn.him.api.dao.group;

import com.lmxdawn.him.api.vo.req.GroupUserListReqVO;
import com.lmxdawn.him.api.vo.req.UserGroupListReqVO;
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
    List<GroupUser> listByGroupId(GroupUserListReqVO groupUserListReqVO);

    /**
     * 查询用户的群列表
     * @return
     */
    List<GroupUser> listByUid(UserGroupListReqVO userGroupListReqVO);

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
