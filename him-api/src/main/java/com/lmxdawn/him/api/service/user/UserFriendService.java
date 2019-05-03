package com.lmxdawn.him.api.service.user;

import com.lmxdawn.him.api.vo.req.UserFriendListReqVO;
import com.lmxdawn.him.api.vo.res.UserFriendListInfoResVO;
import com.lmxdawn.him.common.entity.user.UserFriend;

import java.util.List;

public interface UserFriendService {

    List<UserFriendListInfoResVO> listByUid(UserFriendListReqVO userFriendListRequest);

    UserFriend findByUidAndFriendUid(Long uid, Long friendUid);

    boolean insertUserFriend(UserFriend userFriend);

    boolean updateUserFriend(UserFriend userFriend);

    boolean deleteByUidAndFriendUid(Long uid, Long friendUid);

}
