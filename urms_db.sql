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

 Date: 11/03/2026 17:47:17
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
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1, '春节联欢活动', '一年一度的春节联欢活动，欢迎各位退休教职工踊跃报名参加。', 2, '2026-03-11 16:29:08', '2026-04-10 16:29:08', 0);
INSERT INTO `activity` VALUES (2, '健康讲座', '邀请专家进行健康知识讲座，地点：老年活动中心。', 1, '2026-03-11 16:29:08', '2026-03-18 16:29:08', 0);
INSERT INTO `activity` VALUES (3, '春节联欢活动', '一年一度的春节联欢活动，欢迎各位退休教职工踊跃报名参加。', 2, '2026-03-11 17:46:53', '2026-04-10 17:46:53', 0);
INSERT INTO `activity` VALUES (4, '健康讲座', '邀请专家进行健康知识讲座，地点：老年活动中心。', 1, '2026-03-11 17:46:53', '2026-03-18 17:46:53', 0);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_registration
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of campus_notice
-- ----------------------------
INSERT INTO `campus_notice` VALUES (1, '系统上线公告', '欢迎使用大学退休教职工管理系统，本系统提供个人信息管理、校园公告查看、活动报名、工资查询、亲属管理等功能。', '2026-03-11 16:29:08', 1);
INSERT INTO `campus_notice` VALUES (2, '系统上线公告', '欢迎使用大学退休教职工管理系统，本系统提供个人信息管理、校园公告查看、活动报名、工资查询、亲属管理等功能。', '2026-03-11 17:46:53', 1);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_message
-- ----------------------------

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
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of retired_staff
-- ----------------------------
INSERT INTO `retired_staff` VALUES (2, '张三', NULL, '男', NULL, 65, '汉族', '本科', NULL, NULL, NULL, '教务处', '副教授');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary_record
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff_relative
-- ----------------------------
INSERT INTO `staff_relative` VALUES (1, 2, '配偶', '李子硕', '', 135.00, '98323186', '2695375620@qq.com', '华北水利水电大学', '');

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', 2, '2026-03-11 16:29:08');
INSERT INTO `sys_user` VALUES (2, 'user1', '123456', 1, '2026-03-11 16:29:08');

SET FOREIGN_KEY_CHECKS = 1;
