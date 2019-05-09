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

 Date: 09/05/2019 20:14:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `group_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '群ID',
  `uid` bigint(20) NOT NULL COMMENT '创建者用户ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '群昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群头像',
  `member_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '成员数量',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES (12, 2, '测试', NULL, 1, '', '2019-05-06 19:05:29', '2019-05-06 19:05:29');

-- ----------------------------
-- Table structure for group_msg
-- ----------------------------
DROP TABLE IF EXISTS `group_msg`;
CREATE TABLE `group_msg`  (
  `msg_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `group_id` bigint(20) UNSIGNED NOT NULL COMMENT '群ID',
  `sender_uid` bigint(20) UNSIGNED NOT NULL COMMENT '发送消息的用户ID',
  `msg_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '消息类型（0：普通文字消息，1：图片消息，2：文件消息，3：语音消息，4：视频消息）',
  `msg_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息内容',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`msg_id`) USING BTREE,
  INDEX `idx_group_id_create_time`(`group_id`, `create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '群的消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_msg
-- ----------------------------
INSERT INTO `group_msg` VALUES (7, 12, 2, 0, '1111', '2019-05-06 19:05:39', '2019-05-06 19:05:39');

-- ----------------------------
-- Table structure for group_user
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `group_id` bigint(20) UNSIGNED NOT NULL COMMENT '群ID',
  `uid` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `remark` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '群里的备注',
  `last_ack_msg_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后一次确认的消息ID',
  `last_msg_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后一次的消息内容',
  `last_msg_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次的消息时间',
  `un_msg_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '未读消息数量',
  `rank` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '等级（0：普通成员，1：管理员，2：群主）',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id_uid`(`group_id`, `uid`) USING BTREE,
  INDEX `idx_uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '群组表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of group_user
-- ----------------------------
INSERT INTO `group_user` VALUES (15, 12, 2, NULL, 0, '1111', '2019-05-06 19:05:39', 0, 2, '2019-05-06 19:05:29', '2019-05-07 09:12:51');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '14e1b600b1fd579f47433b88e8d85291', '是是是', 'http://thirdwx.qlogo.cn/mmopen/vi_32/onZtiaPzj0hhsBjlKiaE2Dib7dahCLqZXAqTBy6y6mXpicCu2X5arzDvSh0eDfyibR0CW06cNudiaxyiaEWic6MEm1Veow/132', '很好喝', '2019-05-04 03:39:36', '2019-05-04 03:39:38');
INSERT INTO `user` VALUES (2, '14e1b600b1fd579f47433b88e8d85291', '随时随地', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83er68jLFmuyksnx80vqaKx1V2On1KAX69IiahGyvYKXhwU3qcQHud44xjlvcQa7WEv9SPnGs2YibPQkQ/132', '女女女女', '2019-05-04 03:39:48', '2019-05-04 03:39:50');
INSERT INTO `user` VALUES (3, '14e1b600b1fd579f47433b88e8d85291', 'lmxdawn', 'http://thirdwx.qlogo.cn/mmopen/Kf2cpiarhCfcNn2dNkvhwwUibvoKzrWQqFSpbVB4nB5gTtd5KGyhFUF5PWLq1S8SIFCNStJI6cs2dpS90yq2AUuwbFrticBNQibp/132', '大胆的', '2019-05-04 23:31:54', '2019-05-04 23:31:57');

-- ----------------------------
-- Table structure for user_friend
-- ----------------------------
DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE `user_friend`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `uid` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
  `friend_uid` bigint(20) NOT NULL COMMENT '朋友的用户id',
  `remark` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `un_msg_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '未读消息数量',
  `last_msg_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后一次接收的消息内容',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NOT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_uid_friend_uid`(`uid`, `friend_uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 134 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户的朋友表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_friend
-- ----------------------------
INSERT INTO `user_friend` VALUES (40, 2, 1, '', 2, '66666', '2019-05-06 19:43:42', '2019-05-09 18:02:07');
INSERT INTO `user_friend` VALUES (41, 1, 2, '', 0, '66666', '2019-05-06 19:43:42', '2019-05-09 18:02:07');

-- ----------------------------
-- Table structure for user_friend_ask
-- ----------------------------
DROP TABLE IF EXISTS `user_friend_ask`;
CREATE TABLE `user_friend_ask`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `friend_uid` bigint(20) UNSIGNED NOT NULL COMMENT '发送消息的用户ID',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `status` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态（0：未确认过，1：已确认，2: 拒绝）',
  `create_time` datetime(0) NOT NULL,
  `modified_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '好友请求表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_friend_ask
-- ----------------------------
INSERT INTO `user_friend_ask` VALUES (22, 2, 1, '', 1, '2019-05-06 19:27:37', '2019-05-06 19:27:50');
INSERT INTO `user_friend_ask` VALUES (23, 2, 1, '', 1, '2019-05-06 19:27:52', '2019-05-06 19:28:10');
INSERT INTO `user_friend_ask` VALUES (24, 2, 1, '', 1, '2019-05-06 19:39:51', '2019-05-06 19:43:42');
INSERT INTO `user_friend_ask` VALUES (25, 2, 1, '', 1, '2019-05-06 19:40:42', '2019-05-06 19:40:58');
INSERT INTO `user_friend_ask` VALUES (26, 2, 1, '', 1, '2019-05-06 19:43:30', '2019-05-06 19:47:53');

-- ----------------------------
-- Table structure for user_friend_msg
-- ----------------------------
DROP TABLE IF EXISTS `user_friend_msg`;
CREATE TABLE `user_friend_msg`  (
  `msg_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `uid` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `sender_uid` bigint(20) UNSIGNED NOT NULL COMMENT '发送方用户ID',
  `msg_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '消息类型（0：普通文字消息，1：图片消息，2：文件消息，3：语音消息，4：视频消息）',
  `msg_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息内容',
  `create_time` datetime(0) NOT NULL COMMENT '消息创建时间',
  PRIMARY KEY (`msg_id`) USING BTREE,
  INDEX `idx_uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户的好友消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_friend_msg
-- ----------------------------
INSERT INTO `user_friend_msg` VALUES (49, 1, 1, 1, '成为好友，现在开始聊吧~', '2019-05-06 19:43:42');
INSERT INTO `user_friend_msg` VALUES (50, 1, 1, 0, '1111', '2019-05-09 10:38:47');
INSERT INTO `user_friend_msg` VALUES (51, 1, 1, 0, '11222', '2019-05-09 10:39:03');
INSERT INTO `user_friend_msg` VALUES (52, 1, 1, 0, '2222', '2019-05-09 10:52:11');
INSERT INTO `user_friend_msg` VALUES (53, 1, 1, 0, '是是是', '2019-05-09 10:52:22');
INSERT INTO `user_friend_msg` VALUES (54, 1, 1, 0, '反反复复', '2019-05-09 13:19:02');
INSERT INTO `user_friend_msg` VALUES (55, 1, 1, 0, '通天塔', '2019-05-09 13:22:21');
INSERT INTO `user_friend_msg` VALUES (56, 1, 1, 0, '发部部v', '2019-05-09 13:22:50');
INSERT INTO `user_friend_msg` VALUES (57, 1, 1, 0, '发发发', '2019-05-09 13:25:02');
INSERT INTO `user_friend_msg` VALUES (58, 1, 1, 0, '发发发', '2019-05-09 13:26:34');
INSERT INTO `user_friend_msg` VALUES (59, 1, 1, 0, '发发发', '2019-05-09 13:34:52');
INSERT INTO `user_friend_msg` VALUES (60, 1, 1, 0, 'xxx', '2019-05-09 13:34:59');
INSERT INTO `user_friend_msg` VALUES (61, 1, 1, 0, '6666', '2019-05-09 14:06:12');
INSERT INTO `user_friend_msg` VALUES (62, 1, 1, 0, '111', '2019-05-09 14:16:37');
INSERT INTO `user_friend_msg` VALUES (63, 1, 1, 0, '111', '2019-05-09 14:16:43');
INSERT INTO `user_friend_msg` VALUES (64, 1, 1, 0, '2222', '2019-05-09 14:22:27');
INSERT INTO `user_friend_msg` VALUES (65, 1, 1, 0, '2222', '2019-05-09 14:22:31');
INSERT INTO `user_friend_msg` VALUES (66, 1, 1, 0, '吧吧吧v', '2019-05-09 14:22:40');
INSERT INTO `user_friend_msg` VALUES (67, 1, 1, 0, 'xxx', '2019-05-09 14:22:46');
INSERT INTO `user_friend_msg` VALUES (68, 1, 1, 0, 'vvvvvvvvvvv', '2019-05-09 14:23:10');
INSERT INTO `user_friend_msg` VALUES (69, 1, 1, 0, '6666666', '2019-05-09 14:23:18');
INSERT INTO `user_friend_msg` VALUES (70, 1, 2, 0, '444444', '2019-05-09 14:30:35');
INSERT INTO `user_friend_msg` VALUES (71, 1, 2, 0, 'vvvvv', '2019-05-09 14:30:44');
INSERT INTO `user_friend_msg` VALUES (72, 1, 2, 0, 'vvvv', '2019-05-09 14:31:50');
INSERT INTO `user_friend_msg` VALUES (73, 1, 2, 0, 'ccccc', '2019-05-09 14:32:11');
INSERT INTO `user_friend_msg` VALUES (74, 1, 1, 0, 'ggggg', '2019-05-09 14:32:32');
INSERT INTO `user_friend_msg` VALUES (75, 1, 1, 0, 'cccc', '2019-05-09 14:37:24');
INSERT INTO `user_friend_msg` VALUES (76, 1, 1, 0, '反反复复', '2019-05-09 14:47:33');
INSERT INTO `user_friend_msg` VALUES (77, 1, 1, 0, '嘻嘻嘻嘻嘻', '2019-05-09 14:47:42');
INSERT INTO `user_friend_msg` VALUES (78, 1, 1, 0, 'xxxx', '2019-05-09 14:47:53');
INSERT INTO `user_friend_msg` VALUES (79, 1, 1, 0, '反反复复6666', '2019-05-09 14:48:00');
INSERT INTO `user_friend_msg` VALUES (80, 1, 1, 0, '啛啛喳喳', '2019-05-09 14:48:21');
INSERT INTO `user_friend_msg` VALUES (81, 1, 1, 0, '777777', '2019-05-09 14:48:28');
INSERT INTO `user_friend_msg` VALUES (82, 1, 1, 0, '8888888', '2019-05-09 14:53:11');
INSERT INTO `user_friend_msg` VALUES (83, 1, 1, 0, '吧吧吧吧吧v', '2019-05-09 14:53:46');
INSERT INTO `user_friend_msg` VALUES (84, 1, 1, 0, '66666666', '2019-05-09 14:53:59');
INSERT INTO `user_friend_msg` VALUES (85, 1, 1, 0, '啛啛喳喳错', '2019-05-09 14:54:06');
INSERT INTO `user_friend_msg` VALUES (86, 1, 1, 0, '吧吧吧吧吧吧v', '2019-05-09 14:54:19');
INSERT INTO `user_friend_msg` VALUES (87, 1, 1, 0, '66666666', '2019-05-09 14:54:28');
INSERT INTO `user_friend_msg` VALUES (88, 1, 1, 0, 'vvvv5555', '2019-05-09 14:54:45');
INSERT INTO `user_friend_msg` VALUES (89, 1, 1, 0, 'xxxxxx', '2019-05-09 14:54:55');
INSERT INTO `user_friend_msg` VALUES (90, 1, 1, 0, '试试', '2019-05-09 17:54:47');
INSERT INTO `user_friend_msg` VALUES (91, 1, 1, 0, '66666', '2019-05-09 17:54:58');
INSERT INTO `user_friend_msg` VALUES (92, 1, 2, 0, '吧吧吧v', '2019-05-09 17:59:04');
INSERT INTO `user_friend_msg` VALUES (93, 1, 1, 0, 'vvv', '2019-05-09 18:01:52');
INSERT INTO `user_friend_msg` VALUES (94, 1, 1, 0, '66666', '2019-05-09 18:02:07');

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户的消息配置表',
  `uid` bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
  `friend_ask_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '好友请求的数量',
  `friend_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '好友数量',
  `create_time` datetime(0) NOT NULL,
  `modified_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_uid`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户的额外信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_profile
-- ----------------------------
INSERT INTO `user_profile` VALUES (11, 2, 0, 2, '2019-05-06 18:57:20', '2019-05-06 19:43:42');
INSERT INTO `user_profile` VALUES (12, 1, 0, 2, '2019-05-06 18:58:06', '2019-05-06 19:43:42');

SET FOREIGN_KEY_CHECKS = 1;
