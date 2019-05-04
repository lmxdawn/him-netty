package com.lmxdawn.him.api.dao.user;

import com.lmxdawn.him.common.entity.user.UserProfile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserProfileDao {
    
    /**
     * 根据Uid查询
     *
     * @param uid
     * @return
     */
    UserProfile findByUid(Long uid);
    
    /**
     * 根据Uid查询
     *
     * @param uids
     * @return
     */
    List<UserProfile> listByUidIn(List<Long> uids);
    
    /**
     * 插入
     *
     * @param userProfile
     * @return
     */
    boolean insertUserProfile(UserProfile userProfile);
    
    /**
     * 批量插入
     *
     * @param userProfiles
     * @return
     */
    boolean insertUserProfileAll(List<UserProfile> userProfiles);
    
    /**
     * 更新
     *
     * @return
     */
    boolean updateUserProfile(UserProfile userProfile);
    
}
