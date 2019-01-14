package com.lmxdawn.him.api.service.user;

import com.lmxdawn.him.common.entity.user.User;

public interface UserService {

    User findByUid(Long uid);

    boolean insertUser(User user);

    boolean updateUser(User user);

    boolean deleteByUid(Long uid);

}
