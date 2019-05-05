package com.lmxdawn.him.api.service.group.impl;

import com.lmxdawn.him.api.BaseApplicationTest;
import com.lmxdawn.him.api.service.group.GroupService;
import com.lmxdawn.him.common.entity.group.Group;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GroupServiceImplTest extends BaseApplicationTest {

    @Resource
    private GroupService groupService;

    @Test
    public void findByGroupId() {

        Long groupId = 1L;
        Group group = groupService.findByGroupId(groupId);

        assertNotNull(group);
    }

    @Test
    public void listByGroupIdIn() {

        List<Long> groupIds = Arrays.asList(1L,2L);
        List<Group> groupList = groupService.listByGroupIdIn(groupIds);
        assertTrue(groupList.size() > 0);
    }

    @Test
    public void insertGroup() {

        Group group = new Group();
        group.setName("测试1");
        group.setMemberNum(1);

        boolean b = groupService.insertGroup(group);

        assertNotNull(group.getGroupId());

    }

    @Test
    public void updateGroup() {

        Group group = new Group();
        group.setGroupId(1L);
        group.setName("测试333");
        group.setMemberNum(1);

        boolean b = groupService.updateGroup(group);

        assertTrue(b);

    }

    @Test
    public void deleteByGroupId() {

        Long groupId = 1L;
        boolean b = groupService.deleteByGroupId(groupId);
        assertTrue(b);
    }
}
