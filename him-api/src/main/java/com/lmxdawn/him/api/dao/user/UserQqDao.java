package com.lmxdawn.him.api.dao.user;

import com.lmxdawn.him.common.entity.user.UserQq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserQqDao {

    /**
     * 根据Uid查询
     * @param openId
     * @return
     */
    UserQq findByOpenId(@Param("openId") String openId);

    /**
     * 插入
     * @param userQq
     * @return
     */
    boolean insertUserQq(UserQq userQq);

    /**
     * 删除
     * @param uid
     * @return
     */
    boolean deleteByUid(@Param("uid") Long uid);

}
