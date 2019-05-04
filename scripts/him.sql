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

 Date: 05/04/2019 13:40:34 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `group`
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `group_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '群ID',
  `name` varchar(255) DEFAULT NULL COMMENT '群昵称',
  `member_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '成员数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `group_msg`
-- ----------------------------
DROP TABLE IF EXISTS `group_msg`;
CREATE TABLE `group_msg` (
  `msg_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `group_id` bigint(20) unsigned NOT NULL COMMENT '群ID',
  `sender_uid` bigint(20) unsigned NOT NULL COMMENT '发送消息的用户ID',
  `msg_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息类型 ( 0 : 文本消息 )',
  `msg_content` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '消息内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`msg_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='群的消息表';

-- ----------------------------
--  Table structure for `group_user`
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `group_id` bigint(20) unsigned NOT NULL COMMENT '群ID',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `remark` varchar(30) DEFAULT NULL COMMENT '群里的备注',
  `last_ack_msg_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后一次确认的消息ID',
  `offline_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '离线消息数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_group_id_uid` (`group_id`,`uid`) USING BTREE,
  KEY `idx_uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='群组表';

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', '14e1b600b1fd579f47433b88e8d85291', '是是是', null, '2019-05-04 03:39:36', '2019-05-04 03:39:38'), ('2', '14e1b600b1fd579f47433b88e8d85291', '随时随地', null, '2019-05-04 03:39:48', '2019-05-04 03:39:50');
COMMIT;

-- ----------------------------
--  Table structure for `user_friend`
-- ----------------------------
DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE `user_friend` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `friend_uid` bigint(20) NOT NULL COMMENT '朋友的用户id',
  `remark` varchar(30) DEFAULT NULL COMMENT '备注',
  `un_msg_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '未读消息数量',
  `last_msg_content` varchar(255) DEFAULT NULL COMMENT '最后一次接收的消息内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_uid_friend_uid` (`uid`,`friend_uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户的朋友表';

-- ----------------------------
--  Records of `user_friend`
-- ----------------------------
BEGIN;
INSERT INTO `user_friend` VALUES ('20', '2', '1', '', '0', 'hi额hi是浪费减肥', '2019-05-04 12:53:46', '2019-05-04 13:38:20'), ('21', '1', '2', '', '2', '[图片消息]', '2019-05-04 12:53:46', '2019-05-04 13:38:17');
COMMIT;

-- ----------------------------
--  Table structure for `user_friend_ask`
-- ----------------------------
DROP TABLE IF EXISTS `user_friend_ask`;
CREATE TABLE `user_friend_ask` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `friend_uid` bigint(20) unsigned NOT NULL COMMENT '发送消息的用户ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态（0：未确认过，1：已确认，2: 拒绝）',
  `create_time` datetime NOT NULL,
  `modified_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='好友请求表';

-- ----------------------------
--  Records of `user_friend_ask`
-- ----------------------------
BEGIN;
INSERT INTO `user_friend_ask` VALUES ('1', '1', '2', '大胆的', '0', '2019-05-03 22:44:26', '2019-05-03 22:44:28'), ('2', '1', '3', 'fff', '0', '2019-05-03 22:48:47', '2019-05-03 22:48:49'), ('3', '1', '4', 'ddd', '0', '2019-05-03 23:02:23', '2019-05-03 23:02:25'), ('4', '1', '5', 'sss', '0', '2019-05-03 23:02:35', '2019-05-03 23:02:37'), ('5', '1', '6', 'sssss', '0', '2019-05-03 23:03:52', '2019-05-03 23:03:55'), ('6', '1', '7', 'ssssf', '0', '2019-05-03 23:04:06', '2019-05-03 23:04:08'), ('7', '2', '1', 'dddd', '0', '2019-05-03 23:04:57', '2019-05-03 23:04:59'), ('8', '2', '3', 'fff', '0', '2019-05-03 23:05:13', '2019-05-03 23:05:14'), ('9', '2', '4', 'rrrr', '0', '2019-05-03 23:05:35', '2019-05-03 23:05:37'), ('10', '2', '5', 'ssss', '0', '2019-05-03 23:05:45', '2019-05-03 23:05:49'), ('11', '3', '1', 'ssss', '0', '2019-05-03 23:06:09', '2019-05-03 23:06:11'), ('12', '3', '2', 'ssss', '0', '2019-05-03 23:06:20', '2019-05-03 23:06:22'), ('13', '2', '1', 'hi额hi是浪费减肥', '0', '2019-05-04 04:22:44', '2019-05-04 04:22:44'), ('14', '2', '1', 'hi额hi是浪费减肥', '0', '2019-05-04 04:25:40', '2019-05-04 04:25:40'), ('15', '2', '1', 'hi额hi是浪费减肥', '0', '2019-05-04 04:26:04', '2019-05-04 04:26:04'), ('16', '2', '1', 'hi额hi是浪费减肥', '0', '2019-05-04 04:27:20', '2019-05-04 04:27:20'), ('17', '2', '1', 'hi额hi是浪费减肥', '1', '2019-05-04 04:30:21', '2019-05-04 12:53:46');
COMMIT;

-- ----------------------------
--  Table structure for `user_friend_msg`
-- ----------------------------
DROP TABLE IF EXISTS `user_friend_msg`;
CREATE TABLE `user_friend_msg` (
  `msg_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `receiver_uid` bigint(20) unsigned NOT NULL COMMENT '消息接收方',
  `sender_uid` bigint(20) unsigned NOT NULL COMMENT '发送方用户ID',
  `msg_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息类型（0：普通文字消息，1：图片消息，2：文件消息，3：语音消息，4：视频消息）',
  `msg_content` varchar(255) NOT NULL COMMENT '消息内容',
  `create_time` datetime NOT NULL COMMENT '消息创建时间',
  PRIMARY KEY (`msg_id`) USING BTREE,
  KEY `idx_receiver_uid_sender_uid` (`receiver_uid`,`sender_uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户的好友消息表';

-- ----------------------------
--  Records of `user_friend_msg`
-- ----------------------------
BEGIN;
INSERT INTO `user_friend_msg` VALUES ('13', '1', '2', '1', '2fff', '2019-05-04 00:07:56'), ('14', '1', '2', '1', 'sssss', '2019-05-04 00:08:10'), ('15', '1', '2', '1', 'ssssss', '2019-05-04 00:08:20'), ('16', '2', '1', '1', 'sss', '2019-05-04 00:09:52'), ('17', '2', '1', '1', 'aaa', '2019-05-04 00:10:05'), ('18', '3', '1', '1', 'sssss', '2019-05-04 00:10:14'), ('19', '2', '1', '1', 'hi额hi是浪费减肥', '2019-05-04 12:53:46'), ('20', '1', '2', '1', 'sssssff', '2019-05-04 13:08:21'), ('21', '1', '2', '1', 'sssssff', '2019-05-04 13:11:27'), ('22', '1', '2', '1', 'sssssff', '2019-05-04 13:28:19'), ('23', '1', '2', '1', 'sssssff', '2019-05-04 13:38:17');
COMMIT;

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户的额外信息表';

-- ----------------------------
--  Records of `user_profile`
-- ----------------------------
BEGIN;
INSERT INTO `user_profile` VALUES ('1', '2', '0', '1', '2019-05-04 04:27:20', '2019-05-04 12:53:46'), ('3', '1', '0', '1', '2019-05-04 11:51:30', '2019-05-04 12:53:46');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
