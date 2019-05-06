package com.lmxdawn.him.api.service.user;

import com.lmxdawn.him.common.entity.user.UserFriendAsk;

import java.util.List;

public interface UserFriendAskService {
    
    List<UserFriendAsk> listByUid(Long uid, Integer page, Integer limit);
    
    UserFriendAsk findById(Long id);
    
    boolean insertUserFriendAsk(UserFriendAsk userFriendAsk);
    
    boolean updateUserFriendAsk(UserFriendAsk userFriendAsk);
    
    boolean deleteById(Long id);
    
}
