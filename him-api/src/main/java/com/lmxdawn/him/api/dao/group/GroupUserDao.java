package com.lmxdawn.him.api.dao.group;

import com.lmxdawn.him.common.entity.group.GroupUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupUserDao {

    /**
     * 查询群用户
     * @return
     */
    GroupUser findByGroupIdAndUid(@Param("groupId") Long groupId,
                                  @Param("uid") Long uid);

    /**
     * 查询群的用户列表
     * @return
     */
    List<GroupUser> listByGroupId(@Param("groupId") Long groupId,
                                  @Param("offset") Integer offset,
                                  @Param("limit") Integer limit);

    /**
     * 查询用户的群列表
     * @return
     */
    List<GroupUser> listByUid(@Param("uid") Long uid,
                              @Param("offset") Integer offset,
                              @Param("limit") Integer limit);

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
     * 清空未读消息数量
     * @return
     */
    boolean clearUnMsgCountByGroupIdAndUid(GroupUser groupUser);

    /**
     * 根据群ID批量更新
     * @return
     */
    boolean updateGroupUserByGroupId(GroupUser groupUser);

    /**
     * 删除
     * @return
     */
    boolean deleteByGroupIdAndUid(@Param(value = "groupId") Long groupId, @Param(value = "uid") Long uid);

    /**
     * 删除
     * @return
     */
    boolean deleteByGroupId(@Param(value = "groupId") Long groupId);

}
