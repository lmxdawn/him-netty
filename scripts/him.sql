/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : him

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 30/04/2019 17:25:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `group_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '群ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群昵称',
  `member_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '成员数量',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_msg
-- ----------------------------
DROP TABLE IF EXISTS `group_msg`;
CREATE TABLE `group_msg`  (
  `msg_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `group_id` bigint(20) UNSIGNED NOT NULL COMMENT '群ID',
  `sender_uid` bigint(20) UNSIGNED NOT NULL COMMENT '发送消息的用户ID',
  `msg_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '消息类型 ( 0 : 文本消息 )',
  `msg_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息内容',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`msg_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '群的消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_user
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user`  (
  `id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 AUTO_INCREMENT COMMENT '自增ID',
  `group_id` bigint(20) UNSIGNED NOT NULL COMMENT '群ID',
  `uid` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `remark` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群里的备注',
  `last_ack_msg_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后一次确认的消息ID',
  `offline_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '离线消息数量',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id_uid`(`group_id`, `uid`) USING BTREE,
  INDEX `idx_uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '群组表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of group_user
-- ----------------------------
INSERT INTO `group_user` VALUES (1, 1, 2, '3', 0, 0, '2019-04-30 16:52:59', '2019-04-30 16:53:01');
INSERT INTO `group_user` VALUES (2, 1, 1, '1', 0, 2, '2019-04-30 16:52:04', '2019-04-30 16:52:06');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_friend
-- ----------------------------
DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE `user_friend`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `uid` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
  `friend_uid` bigint(20) NOT NULL COMMENT '朋友的用户id',
  `remark` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NOT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_uid_friend_uid`(`uid`, `friend_uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户的朋友表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_offline_msg
-- ----------------------------
DROP TABLE IF EXISTS `user_offline_msg`;
CREATE TABLE `user_offline_msg`  (
  `msg_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `receiver_uid` bigint(20) UNSIGNED NOT NULL COMMENT '消息接收方',
  `sender_uid` bigint(20) UNSIGNED NOT NULL COMMENT '发送方用户ID',
  `msg_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '消息类型（0：普通文字消息，1：图片消息，2：文件消息，3：语音消息，4：视频消息）',
  `msg_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容',
  `create_time` datetime(0) NOT NULL COMMENT '消息创建时间',
  PRIMARY KEY (`msg_id`) USING BTREE,
  INDEX `idx_receiver_uid`(`receiver_uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户的离线消息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
