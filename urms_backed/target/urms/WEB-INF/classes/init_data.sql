# 数据库初始化脚本
# 用于创建初始用户数据

-- 插入管理员账户 (用户名: admin, 密码: admin)
INSERT INTO sys_user (username, password, role, create_time) VALUES ('admin', 'admin', 2, NOW());

-- 插入测试退休人员账户 (用户名: user1, 密码: 123456)
INSERT INTO sys_user (username, password, role, create_time) VALUES ('user1', '123456', 1, NOW());

-- 为user1创建退休人员信息
INSERT INTO retired_staff (staff_id, real_name, gender, age, nation, education, former_dept, job_title)
VALUES (2, '张三', '男', 65, '汉族', '本科', '教务处', '副教授');

-- 插入测试公告
INSERT INTO campus_notice (title, content, publish_time, admin_id) VALUES
('系统上线公告', '欢迎使用大学退休教职工管理系统，本系统提供个人信息管理、校园公告查看、活动报名、工资查询、亲属管理等功能。', NOW(), 1);

-- 插入测试活动
INSERT INTO activity (title, description, type, start_time, end_time, vote_count) VALUES
('春节联欢活动', '一年一度的春节联欢活动，欢迎各位退休教职工踊跃报名参加。', 2, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), 0),
('健康讲座', '邀请专家进行健康知识讲座，地点：老年活动中心。', 1, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 0);

-- 插入测试工资记录
INSERT INTO salary_record (staff_id, year_month, base_salary, subsidy, condolence_fee, change_reason, status) VALUES
(2, '2026-02', 8000.00, 500.00, 200.00, '正常发放', 0);