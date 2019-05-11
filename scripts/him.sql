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

 Date: 05/10/2019 02:58:49 AM
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
--  Records of `group`
-- ----------------------------
BEGIN;
INSERT INTO `group` VALUES ('12', '2', '测试', null, '1', '', '2019-05-06 19:05:29', '2019-05-06 19:05:29');
COMMIT;

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
--  Records of `group_msg`
-- ----------------------------
BEGIN;
INSERT INTO `group_msg` VALUES ('7', '12', '2', '0', '1111', '2019-05-06 19:05:39', '2019-05-06 19:05:39');
COMMIT;

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
--  Records of `group_user`
-- ----------------------------
BEGIN;
INSERT INTO `group_user` VALUES ('15', '12', '2', null, '0', '1111', '2019-05-06 19:05:39', '0', '2', '2019-05-06 19:05:29', '2019-05-07 09:12:51');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', '14e1b600b1fd579f47433b88e8d85291', '是是是', 'http://thirdwx.qlogo.cn/mmopen/vi_32/onZtiaPzj0hhsBjlKiaE2Dib7dahCLqZXAqTBy6y6mXpicCu2X5arzDvSh0eDfyibR0CW06cNudiaxyiaEWic6MEm1Veow/132', '很好喝', '2019-05-04 03:39:36', '2019-05-04 03:39:38'), ('2', '14e1b600b1fd579f47433b88e8d85291', '随时随地', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er68jLFmuyksnx80vqaKx1V2On1KAX69IiahGyvYKXhwU3qcQHud44xjlvcQa7WEv9SPnGs2YibPQkQ/132', '女女女女', '2019-05-04 03:39:48', '2019-05-04 03:39:50'), ('3', '14e1b600b1fd579f47433b88e8d85291', 'lmxdawn', 'http://thirdwx.qlogo.cn/mmopen/Kf2cpiarhCfcNn2dNkvhwwUibvoKzrWQqFSpbVB4nB5gTtd5KGyhFUF5PWLq1S8SIFCNStJI6cs2dpS90yq2AUuwbFrticBNQibp/132', '大胆的', '2019-05-04 23:31:54', '2019-05-04 23:31:57');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户的朋友表';

-- ----------------------------
--  Records of `user_friend`
-- ----------------------------
BEGIN;
INSERT INTO `user_friend` VALUES ('40', '2', '1', '', '2', '66666', '2019-05-06 19:43:42', '2019-05-09 18:02:07'), ('41', '1', '2', '', '0', '66666', '2019-05-06 19:43:42', '2019-05-09 18:02:07');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='好友请求表';

-- ----------------------------
--  Records of `user_friend_ask`
-- ----------------------------
BEGIN;
INSERT INTO `user_friend_ask` VALUES ('22', '2', '1', '', '1', '2019-05-06 19:27:37', '2019-05-06 19:27:50'), ('23', '2', '1', '', '1', '2019-05-06 19:27:52', '2019-05-06 19:28:10'), ('24', '2', '1', '', '1', '2019-05-06 19:39:51', '2019-05-06 19:43:42'), ('25', '2', '1', '', '1', '2019-05-06 19:40:42', '2019-05-06 19:40:58'), ('26', '2', '1', '', '1', '2019-05-06 19:43:30', '2019-05-06 19:47:53');
COMMIT;

-- ----------------------------
--  Table structure for `user_friend_msg`
-- ----------------------------
DROP TABLE IF EXISTS `user_friend_msg`;
CREATE TABLE `user_friend_msg` (
  `msg_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `sender_uid` bigint(20) unsigned NOT NULL COMMENT '发送方用户ID',
  `msg_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息类型（0：普通文字消息，1：图片消息，2：文件消息，3：语音消息，4：视频消息）',
  `msg_content` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '消息内容',
  `create_time` datetime NOT NULL COMMENT '消息创建时间',
  PRIMARY KEY (`msg_id`) USING BTREE,
  KEY `idx_uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户的好友消息表';

-- ----------------------------
--  Records of `user_friend_msg`
-- ----------------------------
BEGIN;
INSERT INTO `user_friend_msg` VALUES ('49', '1', '1', '1', '成为好友，现在开始聊吧~', '2019-05-06 19:43:42'), ('50', '1', '1', '0', '1111', '2019-05-09 10:38:47'), ('51', '1', '1', '0', '11222', '2019-05-09 10:39:03'), ('52', '1', '1', '0', '2222', '2019-05-09 10:52:11'), ('53', '1', '1', '0', '是是是', '2019-05-09 10:52:22'), ('54', '1', '1', '0', '反反复复', '2019-05-09 13:19:02'), ('55', '1', '1', '0', '通天塔', '2019-05-09 13:22:21'), ('56', '1', '1', '0', '发部部v', '2019-05-09 13:22:50'), ('57', '1', '1', '0', '发发发', '2019-05-09 13:25:02'), ('58', '1', '1', '0', '发发发', '2019-05-09 13:26:34'), ('59', '1', '1', '0', '发发发', '2019-05-09 13:34:52'), ('60', '1', '1', '0', 'xxx', '2019-05-09 13:34:59'), ('61', '1', '1', '0', '6666', '2019-05-09 14:06:12'), ('62', '1', '1', '0', '111', '2019-05-09 14:16:37'), ('63', '1', '1', '0', '111', '2019-05-09 14:16:43'), ('64', '1', '1', '0', '2222', '2019-05-09 14:22:27'), ('65', '1', '1', '0', '2222', '2019-05-09 14:22:31'), ('66', '1', '1', '0', '吧吧吧v', '2019-05-09 14:22:40'), ('67', '1', '1', '0', 'xxx', '2019-05-09 14:22:46'), ('68', '1', '1', '0', 'vvvvvvvvvvv', '2019-05-09 14:23:10'), ('69', '1', '1', '0', '6666666', '2019-05-09 14:23:18'), ('70', '1', '2', '0', '444444', '2019-05-09 14:30:35'), ('71', '1', '2', '0', 'vvvvv', '2019-05-09 14:30:44'), ('72', '1', '2', '0', 'vvvv', '2019-05-09 14:31:50'), ('73', '1', '2', '0', 'ccccc', '2019-05-09 14:32:11'), ('74', '1', '1', '0', 'ggggg', '2019-05-09 14:32:32'), ('75', '1', '1', '0', 'cccc', '2019-05-09 14:37:24'), ('76', '1', '1', '0', '反反复复', '2019-05-09 14:47:33'), ('77', '1', '1', '0', '嘻嘻嘻嘻嘻', '2019-05-09 14:47:42'), ('78', '1', '1', '0', 'xxxx', '2019-05-09 14:47:53'), ('79', '1', '1', '0', '反反复复6666', '2019-05-09 14:48:00'), ('80', '1', '1', '0', '啛啛喳喳', '2019-05-09 14:48:21'), ('81', '1', '1', '0', '777777', '2019-05-09 14:48:28'), ('82', '1', '1', '0', '8888888', '2019-05-09 14:53:11'), ('83', '1', '1', '0', '吧吧吧吧吧v', '2019-05-09 14:53:46'), ('84', '1', '1', '0', '66666666', '2019-05-09 14:53:59'), ('85', '1', '1', '0', '啛啛喳喳错', '2019-05-09 14:54:06'), ('86', '1', '1', '0', '吧吧吧吧吧吧v', '2019-05-09 14:54:19'), ('87', '1', '1', '0', '66666666', '2019-05-09 14:54:28'), ('88', '1', '1', '0', 'vvvv5555', '2019-05-09 14:54:45'), ('89', '1', '1', '0', 'xxxxxx', '2019-05-09 14:54:55'), ('90', '1', '1', '0', '试试', '2019-05-09 17:54:47'), ('91', '1', '1', '0', '66666', '2019-05-09 17:54:58'), ('92', '1', '2', '0', '吧吧吧v', '2019-05-09 17:59:04'), ('93', '1', '1', '0', 'vvv', '2019-05-09 18:01:52'), ('94', '1', '1', '0', '66666', '2019-05-09 18:02:07');
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
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户的额外信息表';

-- ----------------------------
--  Records of `user_profile`
-- ----------------------------
BEGIN;
INSERT INTO `user_profile` VALUES ('11', '2', '0', '2', '2019-05-06 18:57:20', '2019-05-06 19:43:42'), ('12', '1', '0', '2', '2019-05-06 18:58:06', '2019-05-06 19:43:42');
COMMIT;

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
