package com.lmxdawn.him.api.dao.user;

import com.lmxdawn.him.api.req.UserFriendListRequest;
import com.lmxdawn.him.common.entity.user.UserFriend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserFriendDao {

    /**
     * 查询朋友列表
     * @param userFriendListRequest
     * @return
     */
    List<UserFriend> listByUid(UserFriendListRequest userFriendListRequest);

    /**
     * 查询两个用户是否是朋友关系
     * @return
     */
    UserFriend findByUidAndFriendUid(@Param("uid") Long uid, @Param("friendUid") Long friendUid);

    /**
     * 插入
     * @param userFriend
     * @return
     */
    boolean insertUserFriend(UserFriend userFriend);

    /**
     * 更新
     * @param userFriend
     * @return
     */
    boolean updateUserFriend(UserFriend userFriend);

    /**
     * 删除
     * @return
     */
    boolean deleteByUidAndFriendUid(@Param("uid") Long uid, @Param("friendUid") Long friendUid);

}
