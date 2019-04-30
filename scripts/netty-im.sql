/*
Navicat MySQL Data Transfer

Source Server         : php
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : netty-im

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-01-17 19:04:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `group_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '群ID',
  `name` varchar(255) DEFAULT NULL COMMENT '群昵称',
  `member_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '成员数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for group_msg
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='群的消息表';

-- ----------------------------
-- Table structure for group_user
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '自增ID',
  `group_id` bigint(20) unsigned NOT NULL COMMENT '群ID',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `remark` varchar(30) DEFAULT NULL COMMENT '群里的备注',
  `last_ack_msg_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '最后一次确认的消息ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_group_id_uid` (`group_id`,`uid`),
  KEY `idx_uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='群组表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(20) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for user_friend
-- ----------------------------
DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE `user_friend` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `friend_uid` bigint(20) NOT NULL COMMENT '朋友的用户id',
  `remark` varchar(30) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime NOT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_uid_friend_uid` (`uid`,`friend_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户的朋友表';

-- ----------------------------
-- Table structure for user_offline_msg
-- ----------------------------
DROP TABLE IF EXISTS `user_offline_msg`;
CREATE TABLE `user_offline_msg` (
  `msg_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `receiver_uid` bigint(20) unsigned NOT NULL COMMENT '消息接收方',
  `sender_uid` bigint(20) unsigned NOT NULL COMMENT '发送方用户ID',
  `msg_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息类型 ( 0: 文本消息 )',
  `msg_content` varchar(255) NOT NULL COMMENT '消息内容',
  `create_time` datetime NOT NULL COMMENT '消息创建时间',
  PRIMARY KEY (`msg_id`),
  KEY `idx_receiver_uid` (`receiver_uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户的离线消息表';
