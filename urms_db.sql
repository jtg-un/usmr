/*
 Navicat Premium Dump SQL

 Source Server         : bysx
 Source Server Type    : MySQL
 Source Server Version : 80027 (8.0.27)
 Source Host           : localhost:3306
 Source Schema         : urms_db

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 16/03/2026 12:18:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `activity_id` int NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '活动描述',
  `type` tinyint NULL DEFAULT 1 COMMENT '活动类型(1:普通活动 2:多人活动)',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `vote_count` int NULL DEFAULT 0 COMMENT '投票数',
  `vote_limit` int NULL DEFAULT 10 COMMENT '投票上限',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动图片路径',
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1, '春节联欢活动', '一年一度的春节联欢活动，欢迎各位退休教职工踊跃报名参加。', 2, '2026-03-11 16:29:08', '2026-04-10 16:29:08', 21, 1, NULL);
INSERT INTO `activity` VALUES (2, '健康讲座', '邀请专家进行健康知识讲座，地点：老年活动中心。', 1, '2026-03-11 16:29:08', '2026-03-18 16:29:08', 29, 1, NULL);
INSERT INTO `activity` VALUES (3, '春节联欢活动', '一年一度的春节联欢活动，欢迎各位退休教职工踊跃报名参加。', 2, '2026-03-11 17:46:53', '2026-04-10 17:46:53', 36, 1, NULL);
INSERT INTO `activity` VALUES (4, '健康讲座', '邀请专家进行健康知识讲座，地点：老年活动中心。', 1, '2026-03-11 17:46:53', '2026-03-18 17:46:53', 11, 1, NULL);
INSERT INTO `activity` VALUES (5, '111', '111111111111', 2, '2026-03-27 18:00:00', '2026-03-30 18:00:00', 70, 1, NULL);
INSERT INTO `activity` VALUES (6, '222', '333', 1, '2026-03-06 15:38:00', '2026-03-23 15:38:00', 30, 1, NULL);
INSERT INTO `activity` VALUES (7, '444', '4444', 1, '2026-03-13 19:49:00', '2026-03-27 15:49:00', 13, 1, NULL);
INSERT INTO `activity` VALUES (8, '555', '5555', 1, '2026-03-20 15:49:00', '2026-03-29 15:49:00', 1, 1, NULL);
INSERT INTO `activity` VALUES (9, '多人活动6', '123', 2, '2026-03-20 16:59:00', '2026-03-25 16:59:00', 1, 1, NULL);
INSERT INTO `activity` VALUES (10, '多人活动7', '444', 2, '2026-04-04 17:06:00', '2026-03-29 21:06:00', 3, 1, 'upload/activities/666011d7-7488-4ef7-9cd5-457fa5ea3bc8.jpg');

-- ----------------------------
-- Table structure for activity_registration
-- ----------------------------
DROP TABLE IF EXISTS `activity_registration`;
CREATE TABLE `activity_registration`  (
  `reg_id` int NOT NULL AUTO_INCREMENT COMMENT '报名ID',
  `activity_id` int NOT NULL COMMENT '活动ID',
  `staff_id` int NOT NULL COMMENT '退休人员ID',
  `reg_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '报名时间',
  `group_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组名(多人活动时使用)',
  PRIMARY KEY (`reg_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `activity_registration` VALUES (13, 10, 5, '2026-03-13 21:48:41', '组名1');
INSERT INTO `activity_registration` VALUES (14, 8, 5, '2026-03-13 21:48:50', '');
INSERT INTO `activity_registration` VALUES (15, 4, 5, '2026-03-13 23:01:13', '');
INSERT INTO `activity_registration` VALUES (16, 5, 5, '2026-03-13 23:01:23', '123');

-- ----------------------------
-- Table structure for campus_notice
-- ----------------------------
DROP TABLE IF EXISTS `campus_notice`;
CREATE TABLE `campus_notice`  (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告内容',
  `publish_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `admin_id` int NULL DEFAULT NULL COMMENT '发布管理员ID',
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
  `cm_id` int NOT NULL AUTO_INCREMENT COMMENT '评论/留言ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `target_id` int NULL DEFAULT NULL COMMENT '目标ID(公告ID/活动ID/0表示站内留言)',
  `target_type` tinyint NULL DEFAULT 1 COMMENT '目标类型(1:公告 2:活动 3:站内留言)',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论/留言内容',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`cm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_message
-- ----------------------------
INSERT INTO `comment_message` VALUES (1, 2, 3, 2, '123', '2026-03-11 17:51:58');
INSERT INTO `comment_message` VALUES (2, 2, 4, 2, '123', '2026-03-11 17:52:19');
INSERT INTO `comment_message` VALUES (4, 2, 2, 1, '123', '2026-03-11 17:57:07');
INSERT INTO `comment_message` VALUES (5, 2, 2, 1, '444', '2026-03-11 17:57:09');
INSERT INTO `comment_message` VALUES (6, 2, 5, 2, '111', '2026-03-11 18:01:39');
INSERT INTO `comment_message` VALUES (7, 4, 3, 2, '456', '2026-03-13 00:27:17');
INSERT INTO `comment_message` VALUES (9, 2, 10, 2, '123', '2026-03-13 21:43:59');
INSERT INTO `comment_message` VALUES (10, 2, 0, 3, '123', '2026-03-13 21:44:20');
INSERT INTO `comment_message` VALUES (11, 5, 0, 3, '123', '2026-03-13 21:48:16');
INSERT INTO `comment_message` VALUES (12, 2, 3, 1, '1', '2026-03-13 23:00:08');
INSERT INTO `comment_message` VALUES (13, 5, 4, 2, '666', '2026-03-13 23:01:32');

-- ----------------------------
-- Table structure for retired_staff
-- ----------------------------
DROP TABLE IF EXISTS `retired_staff`;
CREATE TABLE `retired_staff`  (
  `staff_id` int NOT NULL COMMENT '退休人员ID(关联sys_user.user_id)',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '真实姓名',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子照片路径',
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `nation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '民族',
  `education` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学历',
  `native_place` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '籍贯',
  `work_start_date` date NULL DEFAULT NULL COMMENT '参加工作时间',
  `retire_date` date NULL DEFAULT NULL COMMENT '退休时间',
  `former_dept` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '退休前所在单位',
  `job_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职称/职务',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of retired_staff
-- ----------------------------
INSERT INTO `retired_staff` VALUES (2, '张三', '/uploads/74fdf66a-495a-4655-a4ff-5e4502b01a33.png', '男', '012345678910234523', 65, '汉族', '本科', '北京', '2026-02-26', '2026-04-04', '教务处', '副教授', '13298324256');
INSERT INTO `retired_staff` VALUES (4, '李四', '/upload/3a3a2e4b-1a2f-4695-a268-d6ee4221d3ed.png', '女', '432123202123299983', 55, '汉', '博士', '日本', '2004-07-13', '2026-03-03', '教务处', '教授', '13321231231');
INSERT INTO `retired_staff` VALUES (5, '王五', '/uploads/5d44a7a3-93c2-454d-b0e5-bfd7d4d98dab.png', '女', '413232197012308876', 89, '汉', '硕士', '123', '2026-02-27', '2026-03-06', '123', '123', '13212312345');

-- ----------------------------
-- Table structure for salary_appeal
-- ----------------------------
DROP TABLE IF EXISTS `salary_appeal`;
CREATE TABLE `salary_appeal`  (
  `appeal_id` int NOT NULL AUTO_INCREMENT COMMENT '申诉ID',
  `salary_id` int NOT NULL COMMENT '工资记录ID',
  `staff_id` int NOT NULL COMMENT '申诉人ID',
  `reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申诉原因',
  `reply` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '管理员回复',
  `status` tinyint NULL DEFAULT 0 COMMENT '处理状态(0:待处理 1:已处理)',
  PRIMARY KEY (`appeal_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary_appeal
-- ----------------------------
INSERT INTO `salary_appeal` VALUES (5, 1, 4, '原因123', '不告诉你', 1);
INSERT INTO `salary_appeal` VALUES (7, 2, 2, '1', '无需修改，是正常的', 1);

-- ----------------------------
-- Table structure for salary_record
-- ----------------------------
DROP TABLE IF EXISTS `salary_record`;
CREATE TABLE `salary_record`  (
  `salary_id` int NOT NULL AUTO_INCREMENT COMMENT '工资记录ID',
  `staff_id` int NOT NULL COMMENT '退休人员ID',
  `year_month` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '年月(格式:YYYY-MM)',
  `base_salary` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '基本工资',
  `subsidy` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '补贴',
  `condolence_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '慰问金',
  `change_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '变动原因说明',
  `status` tinyint NULL DEFAULT 0 COMMENT '状态(0:正常 1:有申诉)',
  PRIMARY KEY (`salary_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary_record
-- ----------------------------
INSERT INTO `salary_record` VALUES (1, 4, '2026-07', 1.00, 0.00, 0.00, '1', 0);
INSERT INTO `salary_record` VALUES (2, 2, '2026-01', 1.00, 0.00, 0.00, '', 0);

-- ----------------------------
-- Table structure for staff_relative
-- ----------------------------
DROP TABLE IF EXISTS `staff_relative`;
CREATE TABLE `staff_relative`  (
  `relative_id` int NOT NULL AUTO_INCREMENT COMMENT '亲属ID',
  `staff_id` int NOT NULL COMMENT '退休人员ID',
  `relation_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '关系类型(配偶/子女等)',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '亲属姓名',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `living_area` decimal(6, 2) NULL DEFAULT NULL COMMENT '居住面积(平方米)',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `workplace` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工作单位',
  `qq_wechat` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'QQ号/微信号',
  PRIMARY KEY (`relative_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff_relative
-- ----------------------------
INSERT INTO `staff_relative` VALUES (2, 2, '子女', 'un jtg', '123123', 135.00, '13321234123', '1234@qq.com', '华北水利水电大学', '123456');
INSERT INTO `staff_relative` VALUES (3, 5, '配偶', '14', '123', 123.00, '13231231231', '123123', '123123', '123123');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `role` tinyint NOT NULL DEFAULT 1 COMMENT '角色(1:退休人员 2:管理员)',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_user`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 2, '2026-03-11 16:29:08');
INSERT INTO `sys_user` VALUES (2, 'user1', 'e10adc3949ba59abbe56e057f20f883e', 1, '2026-03-11 16:29:08');
INSERT INTO `sys_user` VALUES (4, 'user2', 'e10adc3949ba59abbe56e057f20f883e', 1, '2026-03-13 00:10:52');
INSERT INTO `sys_user` VALUES (5, 'user3', 'e10adc3949ba59abbe56e057f20f883e', 1, '2026-03-13 21:45:57');

-- ----------------------------
-- Table structure for vote_record
-- ----------------------------
DROP TABLE IF EXISTS `vote_record`;
CREATE TABLE `vote_record`  (
  `vote_id` int NOT NULL AUTO_INCREMENT COMMENT '投票ID',
  `activity_id` int NOT NULL COMMENT '活动ID',
  `staff_id` int NOT NULL COMMENT '投票人ID',
  `vote_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '投票时间',
  PRIMARY KEY (`vote_id`) USING BTREE,
  INDEX `idx_staff_activity`(`staff_id` ASC, `activity_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `vote_record` VALUES (8, 10, 5, '2026-03-13 21:48:36');
INSERT INTO `vote_record` VALUES (9, 8, 5, '2026-03-13 21:48:50');
INSERT INTO `vote_record` VALUES (10, 4, 5, '2026-03-13 23:01:28');

SET FOREIGN_KEY_CHECKS = 1;
