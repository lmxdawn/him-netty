package com.lmxdawn.him.api.service.user;

import com.lmxdawn.him.api.vo.req.UserFriendAskListReqVO;
import com.lmxdawn.him.api.vo.res.UserFriendAskListResVO;
import com.lmxdawn.him.common.entity.user.UserFriendAsk;

import java.util.List;

public interface UserFriendAskService {

    List<UserFriendAskListResVO> listByUid(UserFriendAskListReqVO userFriendAskListReqVO);

    boolean insertUserFriendAsk(UserFriendAsk userFriendAsk);

    boolean updateUserFriendAsk(UserFriendAsk userFriendAsk);

    boolean deleteById(Long id);

}
