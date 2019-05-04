package com.lmxdawn.him.api.service.user;

import com.lmxdawn.him.common.entity.user.UserProfile;

import java.util.List;

public interface UserProfileService {

    UserProfile findByUid(Long uid);
    
    List<UserProfile> listByUidIn(List<Long> uids);

    boolean incFriendAskCount(Long uid, Integer count);
    
    boolean incFriendCountAll(List<UserProfile> userProfiles);

    boolean clearFriendAskCount(Long uid);

}
