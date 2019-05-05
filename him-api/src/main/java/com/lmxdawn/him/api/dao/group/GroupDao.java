package com.lmxdawn.him.api.dao.group;

import com.lmxdawn.him.common.entity.group.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupDao {

    /**
     * 查询列表
     * @return
     */
    Group findByGroupId(Long groupId);

    /**
     * 根据多个id查询
     * @return
     */
     List<Group> listByGroupIdIn(List<Long> groupIds);

    /**
     * 插入
     * @return
     */
    boolean insertGroup(Group group);

    /**
     * 更新
     * @return
     */
    boolean updateGroup(Group group);

    /**
     * 递增用户数量
     * @return
     */
    boolean incMemberNumByGroupId(@Param("groupId") Long groupId,
                                  @Param("memberNum") Integer memberNum);

    /**
     * 递减用户数量
     * @return
     */
    boolean decMemberNumByGroupId(@Param("groupId") Long groupId);

    /**
     * 删除
     * @return
     */
    boolean deleteByGroupId(Long groupId);

}
