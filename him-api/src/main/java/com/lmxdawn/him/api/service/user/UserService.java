package com.lmxdawn.him.api.service.user;

import com.lmxdawn.him.api.vo.res.UserInfoListResVO;
import com.lmxdawn.him.common.entity.user.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User findByUid(Long uid);

    User findPwdByUid(Long uid);
    
    Map<Long, UserInfoListResVO> listByUidIn(List<Long> uids);

    boolean insertUser(User user);

    boolean updateUser(User user);

    boolean deleteByUid(Long uid);

}
