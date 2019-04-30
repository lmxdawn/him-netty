package com.lmxdawn.him.api.service.user;

import com.lmxdawn.him.api.req.UserFriendListRequest;
import com.lmxdawn.him.api.res.UserFriendListInfoResponse;
import com.lmxdawn.him.common.entity.user.UserFriend;

import java.util.List;

public interface UserFriendService {

    List<UserFriendListInfoResponse> listByUid(UserFriendListRequest userFriendListRequest);

    UserFriend findByUidAndFriendUid(Long uid, Long friendUid);

    boolean insertUserFriend(UserFriend userFriend);

    boolean updateUserFriend(UserFriend userFriend);

    boolean deleteByUidAndFriendUid(Long uid, Long friendUid);

}
