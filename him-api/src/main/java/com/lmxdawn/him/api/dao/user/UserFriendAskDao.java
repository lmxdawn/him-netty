package com.lmxdawn.him.api.dao.user;

import com.lmxdawn.him.api.vo.req.UserFriendAskListReqVO;
import com.lmxdawn.him.common.entity.user.UserFriendAsk;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserFriendAskDao {

    /**
     * 查询列表
     * @param userFriendAskListReqVO
     * @return
     */
    List<UserFriendAsk> listByUid(UserFriendAskListReqVO userFriendAskListReqVO);


    /**
     * 插入
     * @param userFriendAsk
     * @return
     */
    boolean insertUserFriendAsk(UserFriendAsk userFriendAsk);

    /**
     * 更新
     * @param userFriendAsk
     * @return
     */
    boolean updateUserFriendAsk(UserFriendAsk userFriendAsk);

    /**
     * 删除
     * @return
     */
    boolean deleteById(@Param("id") Long id);

}
