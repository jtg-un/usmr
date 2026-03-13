/*
 Navicat Premium Dump SQL

 Source Server         : spbt
 Source Server Type    : MySQL
 Source Server Version : 80027 (8.0.27)
 Source Host           : localhost:3306
 Source Schema         : urms_db

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 13/03/2026 17:20:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `type` tinyint NULL DEFAULT 1,
  `start_time` datetime NULL DEFAULT NULL,
  `end_time` datetime NULL DEFAULT NULL,
  `vote_count` int NULL DEFAULT 0,
  `vote_limit` int NULL DEFAULT 10,
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1, '春节联欢活动', '一年一度的春节联欢活动，欢迎各位退休教职工踊跃报名参加。', 2, '2026-03-11 16:29:08', '2026-04-10 16:29:08', 21, 1);
INSERT INTO `activity` VALUES (2, '健康讲座', '邀请专家进行健康知识讲座，地点：老年活动中心。', 1, '2026-03-11 16:29:08', '2026-03-18 16:29:08', 29, 1);
INSERT INTO `activity` VALUES (3, '春节联欢活动', '一年一度的春节联欢活动，欢迎各位退休教职工踊跃报名参加。', 2, '2026-03-11 17:46:53', '2026-04-10 17:46:53', 36, 1);
INSERT INTO `activity` VALUES (4, '健康讲座', '邀请专家进行健康知识讲座，地点：老年活动中心。', 1, '2026-03-11 17:46:53', '2026-03-18 17:46:53', 10, 1);
INSERT INTO `activity` VALUES (5, '111', '111111111111', 2, '2026-03-27 18:00:00', '2026-03-30 18:00:00', 70, 1);
INSERT INTO `activity` VALUES (6, '222', '333', 1, '2026-03-06 15:38:00', '2026-03-23 15:38:00', 30, 1);
INSERT INTO `activity` VALUES (7, '444', '4444', 1, '2026-03-13 19:49:00', '2026-03-27 15:49:00', 13, 1);
INSERT INTO `activity` VALUES (8, '555', '5555', 1, '2026-03-20 15:49:00', '2026-03-29 15:49:00', 0, 10);
INSERT INTO `activity` VALUES (9, '多人活动6', '123', 2, '2026-03-20 16:59:00', '2026-03-25 16:59:00', 1, 10);
INSERT INTO `activity` VALUES (10, '多人活动7', '444', 2, '2026-04-04 17:06:00', '2026-03-29 21:06:00', 2, 10);

-- ----------------------------
-- Table structure for activity_registration
-- ----------------------------
DROP TABLE IF EXISTS `activity_registration`;
CREATE TABLE `activity_registration`  (
  `reg_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `staff_id` int NOT NULL,
  `reg_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `group_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`reg_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_registration
-- ----------------------------
INSERT INTO `activity_registration` VALUES (1, 3, 2, '2026-03-11 17:51:51', '');
INSERT INTO `activity_registration` VALUES (2, 2, 2, '2026-03-11 18:01:09', '');
INSERT INTO `activity_registration` VALUES (3, 4, 2, '2026-03-11 18:01:22', '');
INSERT INTO `activity_registration` VALUES (4, 5, 2, '2026-03-11 18:01:25', '');
INSERT INTO `activity_registration` VALUES (5, 1, 2, '2026-03-11 18:01:29', '');
INSERT INTO `activity_registration` VALUES (6, 5, 4, '2026-03-13 00:27:04', '123');
INSERT INTO `activity_registration` VALUES (7, 3, 4, '2026-03-13 00:41:09', '12345组');
INSERT INTO `activity_registration` VALUES (8, 7, 2, '2026-03-13 15:51:18', '123');
INSERT INTO `activity_registration` VALUES (9, 6, 4, '2026-03-13 16:46:25', '');
INSERT INTO `activity_registration` VALUES (10, 9, 2, '2026-03-13 17:03:32', '新组名');
INSERT INTO `activity_registration` VALUES (11, 10, 4, '2026-03-13 17:06:36', '组名1');
INSERT INTO `activity_registration` VALUES (12, 10, 2, '2026-03-13 17:06:51', '组名1');

-- ----------------------------
-- Table structure for campus_notice
-- ----------------------------
DROP TABLE IF EXISTS `campus_notice`;
CREATE TABLE `campus_notice`  (
  `notice_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `publish_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `admin_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of campus_notice
-- ----------------------------
INSERT INTO `campus_notice` VALUES (1, '系统上线公告', '欢迎使用大学退休教职工管理系统，本系统提供个人信息管理、校园公告查看、活动报名、工资查询、亲属管理等功能。', '2026-03-11 16:29:08', 1);
INSERT INTO `campus_notice` VALUES (2, '系统上线公告', '欢迎使用大学退休教职工管理系统，本系统提供个人信息管理、校园公告查看、活动报名、工资查询、亲属管理等功能。', '2026-03-11 17:46:53', 1);
INSERT INTO `campus_notice` VALUES (3, '123', '123213213', '2026-03-11 18:00:24', 1);

-- ----------------------------
-- Table structure for comment_message
-- ----------------------------
DROP TABLE IF EXISTS `comment_message`;
CREATE TABLE `comment_message`  (
  `cm_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `target_id` int NULL DEFAULT NULL,
  `target_type` tinyint NULL DEFAULT 1,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_message
-- ----------------------------
INSERT INTO `comment_message` VALUES (1, 2, 3, 2, '123', '2026-03-11 17:51:58');
INSERT INTO `comment_message` VALUES (2, 2, 4, 2, '123', '2026-03-11 17:52:19');
INSERT INTO `comment_message` VALUES (3, 2, 0, 3, '1', '2026-03-11 17:55:10');
INSERT INTO `comment_message` VALUES (4, 2, 2, 1, '123', '2026-03-11 17:57:07');
INSERT INTO `comment_message` VALUES (5, 2, 2, 1, '444', '2026-03-11 17:57:09');
INSERT INTO `comment_message` VALUES (6, 2, 5, 2, '111', '2026-03-11 18:01:39');
INSERT INTO `comment_message` VALUES (7, 4, 3, 2, '456', '2026-03-13 00:27:17');
INSERT INTO `comment_message` VALUES (8, 2, 0, 3, '留言', '2026-03-13 00:38:28');

-- ----------------------------
-- Table structure for retired_staff
-- ----------------------------
DROP TABLE IF EXISTS `retired_staff`;
CREATE TABLE `retired_staff`  (
  `staff_id` int NOT NULL,
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `nation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `education` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `native_place` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `work_start_date` date NULL DEFAULT NULL,
  `retire_date` date NULL DEFAULT NULL,
  `former_dept` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `job_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of retired_staff
-- ----------------------------
INSERT INTO `retired_staff` VALUES (2, '张三', '/urms/upload/daa7df73-8564-4429-9108-51b4b71d630b.png', '男', '012345678910234523', 65, '汉族', '本科', '北京', '2026-02-26', '2026-04-04', '教务处', '副教授', '13298324256');
INSERT INTO `retired_staff` VALUES (4, '李四', '/urms/upload/f09f4cb1-78c2-419a-8826-40ad2fe9958d.png', '女', '1234567890123456', 55, '汉', '博士', '日本', '2004-07-13', '2026-03-03', '教务处', '教授', NULL);

-- ----------------------------
-- Table structure for salary_appeal
-- ----------------------------
DROP TABLE IF EXISTS `salary_appeal`;
CREATE TABLE `salary_appeal`  (
  `appeal_id` int NOT NULL AUTO_INCREMENT,
  `salary_id` int NOT NULL,
  `staff_id` int NOT NULL,
  `reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reply` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `status` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`appeal_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary_appeal
-- ----------------------------

-- ----------------------------
-- Table structure for salary_record
-- ----------------------------
DROP TABLE IF EXISTS `salary_record`;
CREATE TABLE `salary_record`  (
  `salary_id` int NOT NULL AUTO_INCREMENT,
  `staff_id` int NOT NULL,
  `year_month` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `base_salary` decimal(10, 2) NULL DEFAULT 0.00,
  `subsidy` decimal(10, 2) NULL DEFAULT 0.00,
  `condolence_fee` decimal(10, 2) NULL DEFAULT 0.00,
  `change_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `status` tinyint NULL DEFAULT 0,
  PRIMARY KEY (`salary_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary_record
-- ----------------------------
INSERT INTO `salary_record` VALUES (1, 4, '2026-07', 1.00, 0.00, 0.00, '1', 0);

-- ----------------------------
-- Table structure for staff_relative
-- ----------------------------
DROP TABLE IF EXISTS `staff_relative`;
CREATE TABLE `staff_relative`  (
  `relative_id` int NOT NULL AUTO_INCREMENT,
  `staff_id` int NOT NULL,
  `relation_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `living_area` decimal(6, 2) NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `workplace` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `qq_wechat` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`relative_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff_relative
-- ----------------------------
INSERT INTO `staff_relative` VALUES (2, 2, '子女', 'un jtg', '', 135.00, '123456', '2695375620@qq.com', '华北水利水电大学', '123456');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` tinyint NOT NULL DEFAULT 1,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_user`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', 2, '2026-03-11 16:29:08');
INSERT INTO `sys_user` VALUES (2, 'user1', '123456', 1, '2026-03-11 16:29:08');
INSERT INTO `sys_user` VALUES (4, 'user2', '123456', 1, '2026-03-13 00:10:52');

-- ----------------------------
-- Table structure for vote_record
-- ----------------------------
DROP TABLE IF EXISTS `vote_record`;
CREATE TABLE `vote_record`  (
  `vote_id` int NOT NULL AUTO_INCREMENT,
  `activity_id` int NOT NULL,
  `staff_id` int NOT NULL,
  `vote_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vote_id`) USING BTREE,
  INDEX `idx_staff_activity`(`staff_id` ASC, `activity_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vote_record
-- ----------------------------
INSERT INTO `vote_record` VALUES (1, 5, 2, '2026-03-13 16:56:52');
INSERT INTO `vote_record` VALUES (2, 6, 2, '2026-03-13 16:57:03');
INSERT INTO `vote_record` VALUES (3, 7, 2, '2026-03-13 16:57:07');
INSERT INTO `vote_record` VALUES (4, 2, 2, '2026-03-13 16:58:44');
INSERT INTO `vote_record` VALUES (5, 9, 2, '2026-03-13 17:03:33');
INSERT INTO `vote_record` VALUES (6, 10, 4, '2026-03-13 17:06:30');
INSERT INTO `vote_record` VALUES (7, 10, 2, '2026-03-13 17:06:46');

SET FOREIGN_KEY_CHECKS = 1;
