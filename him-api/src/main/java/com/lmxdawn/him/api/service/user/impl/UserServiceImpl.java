package com.lmxdawn.him.api.service.user.impl;

import com.lmxdawn.him.api.dao.user.UserDao;
import com.lmxdawn.him.api.service.user.UserService;
import com.lmxdawn.him.api.vo.res.UserInfoListResVO;
import com.lmxdawn.him.common.entity.user.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    
    @Resource
    private UserDao userDao;
    
    @Override
    public User findByUid(Long uid) {
        return userDao.findByUid(uid);
    }
    
    @Override
    public User findPwdByUid(Long uid) {
        return userDao.findPwdByUid(uid);
    }
    
    @Override
    public Map<Long, UserInfoListResVO> listByUidIn(List<Long> uids) {
        Map<Long, UserInfoListResVO> userInfoListResVOMap = new HashMap<>();
        if (uids.size() == 0) {
            return userInfoListResVOMap;
        }
        List<User> users = userDao.listByUidIn(uids);
        users.forEach(v -> {
            UserInfoListResVO userInfoListResVO = new UserInfoListResVO();
            BeanUtils.copyProperties(v, userInfoListResVO);
            userInfoListResVOMap.put(v.getUid(), userInfoListResVO);
        });
        return userInfoListResVOMap;
    }
    
    @Override
    public boolean insertUser(User user) {
        user.setCreateTime(new Date());
        user.setModifiedTime(new Date());
        return userDao.insertUser(user);
    }
    
    @Override
    public boolean updateUser(User user) {
        user.setModifiedTime(new Date());
        return userDao.updateUser(user);
    }
    
    @Override
    public boolean deleteByUid(Long uid) {
        return userDao.deleteByUid(uid);
    }
}
