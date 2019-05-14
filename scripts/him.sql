/*
 Navicat Premium Data Transfer

 Source Server         : php
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost
 Source Database       : him

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 05/15/2019 07:54:42 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `group`
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `group_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '群ID',
  `uid` bigint(20) NOT NULL COMMENT '创建者用户ID',
  `name` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '群昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '群头像',
  `member_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '成员数量',
  `remark` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `group_msg`
-- ----------------------------
DROP TABLE IF EXISTS `group_msg`;
CREATE TABLE `group_msg` (
  `msg_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `group_id` bigint(20) unsigned NOT NULL COMMENT '群ID',
  `sender_uid` bigint(20) unsigned NOT NULL COMMENT '发送消息的用户ID',
  `msg_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息类型（0：普通文字消息，1：图片消息，2：文件消息，3：语音消息，4：视频消息）',
  `msg_content` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '消息内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`msg_id`) USING BTREE,
  KEY `idx_group_id_create_time` (`group_id`,`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='群的消息表';

-- ----------------------------
--  Table structure for `group_user`
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `group_id` bigint(20) unsigned NOT NULL COMMENT '群ID',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `remark` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '群里的备注',
  `last_ack_msg_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后一次确认的消息ID',
  `last_msg_content` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后一次的消息内容',
  `last_msg_time` datetime DEFAULT NULL COMMENT '最后一次的消息时间',
  `un_msg_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '未读消息数量',
  `rank` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '等级（0：普通成员，1：管理员，2：群主）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_group_id_uid` (`group_id`,`uid`) USING BTREE,
  KEY `idx_uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='群组表';

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `remark` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '个性签名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
--  Table structure for `user_friend`
-- ----------------------------
DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE `user_friend` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `friend_uid` bigint(20) NOT NULL COMMENT '朋友的用户id',
  `remark` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
  `un_msg_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '未读消息数量',
  `last_msg_content` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '最后一次接收的消息内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_uid_friend_uid` (`uid`,`friend_uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户的朋友表';

-- ----------------------------
--  Table structure for `user_friend_ask`
-- ----------------------------
DROP TABLE IF EXISTS `user_friend_ask`;
CREATE TABLE `user_friend_ask` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `friend_uid` bigint(20) unsigned NOT NULL COMMENT '发送消息的用户ID',
  `remark` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注信息',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态（0：未确认过，1：已确认，2: 拒绝）',
  `create_time` datetime NOT NULL,
  `modified_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='好友请求表';

-- ----------------------------
--  Table structure for `user_friend_msg`
-- ----------------------------
DROP TABLE IF EXISTS `user_friend_msg`;
CREATE TABLE `user_friend_msg` (
  `msg_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `to_uid` bigint(20) unsigned NOT NULL COMMENT '和上面的uid做查询用',
  `sender_uid` bigint(20) unsigned NOT NULL COMMENT '发送方用户ID',
  `msg_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息类型（0：普通文字消息，1：图片消息，2：文件消息，3：语音消息，4：视频消息）',
  `msg_content` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '消息内容',
  `create_time` datetime NOT NULL COMMENT '消息创建时间',
  PRIMARY KEY (`msg_id`) USING BTREE,
  KEY `idx_uid_to_uid` (`uid`,`to_uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户的好友消息表';

-- ----------------------------
--  Table structure for `user_profile`
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户的消息配置表',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `friend_ask_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '好友请求的数量',
  `friend_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '好友数量',
  `create_time` datetime NOT NULL,
  `modified_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户的额外信息表';

-- ----------------------------
--  Table structure for `user_qq`
-- ----------------------------
DROP TABLE IF EXISTS `user_qq`;
CREATE TABLE `user_qq` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) unsigned DEFAULT NULL COMMENT '用户uid（关联 user 表）',
  `openid` varchar(40) DEFAULT NULL COMMENT '用户openID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_user_id` (`uid`) USING BTREE,
  UNIQUE KEY `uniq_openid` (`openid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='QQ用户授权表';

SET FOREIGN_KEY_CHECKS = 1;
