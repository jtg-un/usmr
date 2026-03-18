# 高校退休教职工管理系统 (URMS)

University Retired Staff Management System

## 项目概述

本项目是一个面向高校退休教职工的综合管理服务平台，提供个人信息管理、校园公告、活动报名、工资查询、亲属管理、站内留言等功能。系统采用前后端分离架构，支持Docker容器化一键部署。

## 技术栈

| 层次 | 技术 |
|------|------|
| 后端框架 | Spring MVC 5.3.x + Spring 5.3.x + MyBatis 3.5.x |
| 数据库 | MySQL 8.0 |
| 运行环境 | JDK 17 + Tomcat 9.0 |
| 前端技术 | HTML5 + CSS3 + Vue.js 2.x + Axios |
| 容器化 | Docker + Docker Compose + Nginx |
| 认证方式 | JWT (JSON Web Token) |

---

## 项目结构

```
usmr/
├── urms_backed/                    # 后端项目
│   ├── src/main/java/com/urms/
│   │   ├── config/                 # 配置类
│   │   ├── controller/             # 控制器层
│   │   ├── dao/                    # 数据访问层
│   │   ├── entity/                 # 实体类
│   │   ├── filter/                 # 过滤器
│   │   ├── interceptor/            # 拦截器
│   │   ├── service/                # 服务层接口
│   │   │   └── impl/               # 服务层实现
│   │   └── util/                   # 工具类
│   ├── src/main/resources/
│   │   ├── mapper/                 # MyBatis映射文件
│   │   ├── applicationContext.xml  # Spring配置
│   │   ├── spring-mvc.xml          # Spring MVC配置
│   │   └── db.properties           # 数据库配置
│   └── pom.xml                     # Maven依赖配置
│
├── urms-fronted/                   # 前端项目
│   ├── pages/
│   │   ├── admin/                  # 管理员页面
│   │   └── user/                   # 退休人员页面
│   ├── js/                         # JavaScript文件
│   ├── css/                        # 样式文件
│   ├── images/                     # 图片资源
│   ├── nginx.conf                  # Nginx配置
│   └── Dockerfile                  # 前端Docker配置
│
├── docker-compose.yml              # Docker编排配置
├── deploy.bat                      # Windows一键部署脚本
├── deploy.sh                       # Linux/macOS一键部署脚本
└── urms_db.sql                     # 数据库初始化脚本
```

---

## 系统架构

```
┌─────────────────────────────────────────────────────────────────┐
│                         用户浏览器                               │
└─────────────────────────────────┬───────────────────────────────┘
                                  │ HTTP :80
                                  ▼
┌─────────────────────────────────────────────────────────────────┐
│                    Nginx (urms-frontend)                        │
│  ┌─────────────────────┐  ┌─────────────────────────────────┐  │
│  │   静态资源服务       │  │     API反向代理 /api/ → :8080   │  │
│  └─────────────────────┘  └─────────────────────────────────┘  │
└─────────────────────────────────┬───────────────────────────────┘
                                  │ HTTP :8080
                                  ▼
┌─────────────────────────────────────────────────────────────────┐
│                    Tomcat (urms-backend)                        │
│  ┌────────────────────────────────────────────────────────────┐ │
│  │                    Spring MVC                               │ │
│  │  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │ │
│  │  │  Controller  │→│   Service    │→│     DAO      │     │ │
│  │  └──────────────┘  └──────────────┘  └──────────────┘     │ │
│  │         ↓                                    ↓              │ │
│  │  ┌──────────────┐                    ┌──────────────┐      │ │
│  │  │ Interceptor  │                    │   MyBatis    │      │ │
│  │  │   (JWT验证)  │                    │   Mapper     │      │ │
│  │  └──────────────┘                    └──────────────┘      │ │
│  └────────────────────────────────────────────────────────────┘ │
└─────────────────────────────────┬───────────────────────────────┘
                                  │ JDBC :3306
                                  ▼
┌─────────────────────────────────────────────────────────────────┐
│                    MySQL 8.0 (urms-mysql)                       │
│                         urms_db                                 │
└─────────────────────────────────────────────────────────────────┘
```

---

## 功能模块

### 用户角色

| 角色 | 角色ID | 权限说明 |
|------|--------|----------|
| 管理员 | 2 | 发布公告、管理活动、工资管理、人员管理、留言管理 |
| 退休人员 | 1 | 个人信息管理、查看公告、活动报名、工资查询、亲属管理、站内留言 |

### 功能列表

#### 退休人员功能

| 模块 | 功能描述 |
|------|----------|
| 个人信息管理 | 登记/修改姓名、照片、性别、身份证、年龄、民族、学历、籍贯、工作/退休时间、原单位、职称职务等 |
| 校园公告 | 查看公告列表、公告详情、发表/删除评论 |
| 活动管理 | 查看活动、投票、报名（单人/多人组队）、发表/删除评论 |
| 工资管理 | 查看工资记录、提交工资申诉 |
| 亲属管理 | 管理配偶及子女的联系信息 |
| 站内留言 | 向管理员提交留言 |

#### 管理员功能

| 模块 | 功能描述 |
|------|----------|
| 公告管理 | 发布/删除校园公告 |
| 活动管理 | 创建/删除活动、查看报名统计 |
| 工资管理 | 录入/修改工资记录、处理申诉 |
| 人员管理 | 查看/搜索退休人员信息、亲属备案 |
| 留言管理 | 查看用户留言 |

---

## 后端分层详解

### 1. Controller层（控制器层）

负责接收HTTP请求，调用Service层处理业务逻辑，返回统一格式的响应结果。

| 控制器 | 路径前缀 | 功能说明 |
|--------|----------|----------|
| `AuthController` | `/api/auth` | 用户登录认证、获取用户信息、修改密码 |
| `CampusNoticeController` | `/api/notice` | 校园公告CRUD、评论管理 |
| `ActivityController` | `/api/activity` | 活动管理、投票、报名、评论 |
| `SalaryController` | `/api/salary` | 工资记录管理、申诉处理 |
| `StaffRelativeController` | `/api/relative` | 亲属信息管理 |
| `RetiredStaffController` | `/api/staff` | 退休人员信息管理 |
| `MessageController` | `/api/message` | 站内留言管理 |

### 2. Service层（服务层）

业务逻辑处理层，负责事务管理和业务规则校验。

| 服务接口 | 实现类 | 功能说明 |
|----------|--------|----------|
| `SysUserService` | `SysUserServiceImpl` | 用户登录验证、密码修改 |
| `RetiredStaffService` | `RetiredStaffServiceImpl` | 退休人员信息管理 |
| `CampusNoticeService` | `CampusNoticeServiceImpl` | 公告管理 |
| `ActivityService` | `ActivityServiceImpl` | 活动管理、投票、报名 |
| `SalaryRecordService` | `SalaryRecordServiceImpl` | 工资记录管理 |
| `SalaryAppealService` | `SalaryAppealServiceImpl` | 工资申诉处理 |
| `StaffRelativeService` | `StaffRelativeServiceImpl` | 亲属信息管理 |
| `CommentMessageService` | `CommentMessageServiceImpl` | 评论/留言管理 |

### 3. DAO层（数据访问层）

数据持久化操作，使用MyBatis进行数据库交互。

| DAO接口 | 对应表 | 功能说明 |
|---------|--------|----------|
| `SysUserDao` | `sys_user` | 用户账号管理 |
| `RetiredStaffDao` | `retired_staff` | 退休人员信息 |
| `CampusNoticeDao` | `campus_notice` | 校园公告 |
| `ActivityDao` | `activity` | 活动信息 |
| `ActivityRegistrationDao` | `activity_registration` | 活动报名记录 |
| `VoteRecordDao` | `vote_record` | 投票记录 |
| `SalaryRecordDao` | `salary_record` | 工资记录 |
| `SalaryAppealDao` | `salary_appeal` | 工资申诉 |
| `StaffRelativeDao` | `staff_relative` | 亲属信息 |
| `CommentMessageDao` | `comment_message` | 评论/留言 |

### 4. Entity层（实体层）

数据库表对应的Java实体类。

| 实体类 | 对应表 | 主要字段 |
|--------|--------|----------|
| `SysUser` | `sys_user` | userId, username, password, role |
| `RetiredStaff` | `retired_staff` | staffId, realName, gender, idCard, age, nation, education... |
| `CampusNotice` | `campus_notice` | noticeId, title, content, publishTime, adminId |
| `Activity` | `activity` | activityId, title, description, type, startTime, endTime, voteCount |
| `ActivityRegistration` | `activity_registration` | regId, activityId, staffId, regTime, groupName |
| `VoteRecord` | `vote_record` | voteId, activityId, staffId, voteTime |
| `SalaryRecord` | `salary_record` | recordId, staffId, year, month, baseSalary, subsidy... |
| `SalaryAppeal` | `salary_appeal` | appealId, recordId, staffId, content, status, reply |
| `StaffRelative` | `staff_relative` | relativeId, staffId, relationType, name, phone, address... |
| `CommentMessage` | `comment_message` | cmId, userId, targetId, targetType, content, createTime |

---

## 数据库设计

### E-R关系图

```
┌──────────────┐       ┌──────────────────┐
│   sys_user   │ 1:1   │  retired_staff   │
│──────────────│───────│──────────────────│
│ user_id (PK) │       │ staff_id (PK,FK) │
│ username     │       │ real_name        │
│ password     │       │ photo            │
│ role         │       │ gender           │
└──────────────┘       │ ...              │
                       └────────┬─────────┘
                                │
              ┌─────────────────┼─────────────────┐
              │ 1:N             │ 1:N             │ 1:N
              ▼                 ▼                 ▼
┌──────────────────┐  ┌──────────────────┐  ┌──────────────────┐
│ staff_relative   │  │ salary_record    │  │ activity_reg...  │
│──────────────────│  │──────────────────│  │──────────────────│
│ relative_id (PK) │  │ record_id (PK)   │  │ reg_id (PK)      │
│ staff_id (FK)    │  │ staff_id (FK)    │  │ staff_id (FK)    │
│ relation_type    │  │ year, month      │  │ activity_id (FK) │
│ name, phone...   │  │ base_salary...   │  │ group_name       │
└──────────────────┘  └──────────────────┘  └──────────────────┘

┌──────────────────┐       ┌──────────────────┐
│ campus_notice    │       │     activity     │
│──────────────────│       │──────────────────│
│ notice_id (PK)   │       │ activity_id (PK) │
│ title, content   │       │ title, type      │
│ publish_time     │       │ start/end_time   │
│ admin_id (FK)    │       │ vote_count       │
└────────┬─────────┘       └────────┬─────────┘
         │                          │
         │ 1:N                      │ 1:N
         ▼                          ▼
┌──────────────────────────────────────────────┐
│              comment_message                  │
│───────────────────────────────────────────────│
│ cm_id (PK)                                    │
│ user_id (FK), target_id, target_type          │
│ content, create_time                          │
│ target_type: 1=公告评论, 2=活动评论, 3=站内留言 │
└──────────────────────────────────────────────┘
```

### 核心表结构

#### sys_user（用户表）
```sql
CREATE TABLE `sys_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '工号',
  `password` varchar(64) NOT NULL COMMENT '密码(MD5加密)',
  `role` tinyint DEFAULT 1 COMMENT '角色(1:退休人员 2:管理员)',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
);
```

#### retired_staff（退休人员信息表）
```sql
CREATE TABLE `retired_staff` (
  `staff_id` int NOT NULL COMMENT '关联sys_user.user_id',
  `real_name` varchar(50) NOT NULL COMMENT '真实姓名',
  `photo` varchar(255) COMMENT '电子照片路径',
  `gender` varchar(2) COMMENT '性别',
  `id_card` varchar(18) COMMENT '身份证号',
  `age` int COMMENT '年龄',
  `nation` varchar(20) COMMENT '民族',
  `education` varchar(20) COMMENT '学历',
  `native_place` varchar(100) COMMENT '籍贯',
  `work_start_date` date COMMENT '参加工作时间',
  `retire_date` date COMMENT '退休时间',
  `former_dept` varchar(100) COMMENT '退休前所在单位',
  `job_title` varchar(50) COMMENT '职称/职务',
  `phone` varchar(11) COMMENT '手机号码',
  PRIMARY KEY (`staff_id`)
);
```

---

## 核心算法与工具类

### 1. JwtUtil（JWT工具类）

负责JWT Token的生成、解析和验证。

```java
public class JwtUtil {
    // 使用HMAC-SHA256算法
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // Token有效期：7天
    private static final long EXPIRATION = 7 * 24 * 60 * 60 * 1000L;

    // 生成Token，包含用户ID、用户名、角色信息
    public static String generateToken(Integer userId, String username, Integer role);

    // 解析Token获取Claims
    public static Claims parseToken(String token);

    // 验证Token是否有效
    public static boolean validateToken(String token);

    // 从Token中提取用户信息
    public static Integer getUserId(String token);
    public static String getUsername(String token);
    public static Integer getRole(String token);
}
```

### 2. Md5Util（MD5加密工具）

密码加密存储，使用MD5哈希算法。

```java
public class Md5Util {
    /**
     * 对字符串进行MD5加密
     * @param input 原始字符串
     * @return 32位小写十六进制字符串
     */
    public static String encrypt(String input) {
        // 使用MessageDigest进行MD5哈希
        // 返回32位小写十六进制字符串
    }
}
```

### 3. Result（统一响应结果）

所有API返回统一格式的JSON响应。

```java
public class Result<T> {
    private Integer code;      // 状态码：200成功，其他失败
    private String message;    // 提示信息
    private T data;            // 返回数据

    // 常用静态方法
    public static <T> Result<T> success();
    public static <T> Result<T> success(T data);
    public static <T> Result<T> error(String message);
}
```

**响应示例：**
```json
{
  "code": 200,
  "message": "操作成功",
  "data": { ... }
}
```

### 4. LoginInterceptor（登录拦截器）

基于JWT的身份验证拦截器。

```java
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                            HttpServletResponse response,
                            Object handler) {
        // 1. 放行OPTIONS预检请求
        // 2. 从请求头获取Authorization Token
        // 3. 验证Token有效性
        // 4. 将用户信息存入request属性
        // 5. 验证失败返回401错误
    }
}
```

**公开接口（无需登录）：**
- `/auth/login` - 登录接口
- `/api/notice/list` - 公告列表（首页展示）
- `/api/activity/list` - 活动列表（首页展示）
- 静态资源路径

---

## 前端架构

### 页面结构

```
urms-fronted/
├── index.html              # 欢迎首页（公开访问）
├── login.html              # 登录页面
└── pages/
    ├── admin/              # 管理员页面
    │   ├── admin-index.html      # 管理员首页
    │   ├── admin-notice.html     # 公告管理
    │   ├── admin-activity.html   # 活动管理
    │   ├── admin-salary.html     # 工资管理
    │   ├── admin-staff.html      # 人员管理
    │   └── admin-message.html    # 留言管理
    └── user/               # 退休人员页面
        ├── user-index.html       # 用户首页
        ├── user-profile.html     # 个人信息
        ├── user-notice.html      # 公告查看
        ├── user-activity.html    # 活动报名
        ├── user-salary.html      # 工资查询
        ├── user-relative.html    # 亲属管理
        └── user-message.html     # 站内留言
```

### 公共JS模块

`js/common.js` 提供公共功能：

```javascript
// API基础路径配置（自动适配Docker/本地环境）
const API_BASE = window.location.hostname === 'localhost' && window.location.port === ''
    ? '/api'           // Docker环境，通过Nginx反向代理
    : 'http://localhost:8080';  // 本地开发环境

// Axios实例配置
const api = axios.create({
    baseURL: API_BASE,
    timeout: 15000,
    headers: { 'Content-Type': 'application/json' }
});

// 请求拦截器：自动添加JWT Token
api.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers['Authorization'] = 'Bearer ' + token;
    }
    return config;
});

// 响应拦截器：统一处理错误
api.interceptors.response.use(
    response => { /* 处理成功响应 */ },
    error => { /* 处理错误，401跳转登录 */ }
);

// 公共方法
function showMessage(text, type);    // 显示消息提示
function showConfirm(title, msg, cb); // 显示确认弹窗
function checkLogin();                // 检查登录状态
function getUserInfo();               // 获取用户信息
function logout();                    // 退出登录
```

---

## Docker容器化部署

### 架构图

```
┌───────────────────────────────────────────────────────────────┐
│                     Docker Network: urms-network              │
│                                                               │
│  ┌─────────────────┐  ┌─────────────────┐  ┌───────────────┐ │
│  │  urms-frontend  │  │  urms-backend   │  │  urms-mysql   │ │
│  │  (Nginx)        │  │  (Tomcat 9)     │  │  (MySQL 8.0)  │ │
│  │  Port: 80       │  │  Port: 8080     │  │  Port: 3307   │ │
│  │                 │  │                 │  │               │ │
│  │  静态资源服务   │  │  Spring MVC     │  │  urms_db      │ │
│  │  API反向代理    │  │  MyBatis        │  │               │ │
│  └────────┬────────┘  └────────┬────────┘  └───────────────┘ │
│           │                    │                             │
│           └────────────────────┘                             │
└───────────────────────────────────────────────────────────────┘
```

### 部署命令

```bash
# Windows用户：双击 deploy.bat
# 或命令行执行：
docker-compose up -d --build

# 查看服务状态
docker-compose ps

# 查看日志
docker-compose logs -f
```

### 服务配置

| 服务 | 镜像 | 端口 | 数据卷 |
|------|------|------|--------|
| urms-mysql | mysql:8.0 | 3307:3306 | mysql_data |
| urms-backend | tomcat:9.0-jdk17 | 8080:8080 | uploads_data |
| urms-frontend | nginx:1.25-alpine | 80:80 | - |

---

## 快速开始

### 方式一：Docker一键部署（推荐）

**前提条件：** 仅需安装Docker Desktop

```bash
# 1. 克隆或下载项目
git clone <repository-url>
cd usmr

# 2. 运行一键部署脚本
deploy.bat        # Windows
./deploy.sh       # Linux/macOS

# 3. 访问系统
# 前端：http://localhost
# 后端：http://localhost:8080
```

### 方式二：本地开发环境

**前提条件：** JDK 17、Maven 3.x、MySQL 8.0、Tomcat 9.0

```bash
# 1. 创建数据库
mysql -u root -p
CREATE DATABASE urms_db;
USE urms_db;
SOURCE urms_db.sql;

# 2. 修改数据库配置
# 编辑 urms_backed/src/main/resources/db.properties

# 3. 构建后端
cd urms_backed
mvn clean package

# 4. 部署war包到Tomcat
# 将 target/urms.war 复制到 Tomcat webapps 目录

# 5. 启动前端
# 将 urms-fronted 目录部署到Web服务器
# 或直接用IDEA/VSCode的Live Server插件
```

### 测试账号

| 角色 | 用户名 | 密码 |
|------|--------|------|
| 管理员 | admin | admin |
| 退休人员 | user1 | 123456 |

---

## API接口文档

### 认证接口

| 方法 | 路径 | 说明 | 是否公开 |
|------|------|------|----------|
| POST | `/api/auth/login` | 用户登录 | ✓ |
| GET | `/api/auth/info` | 获取当前用户信息 | ✗ |
| POST | `/api/auth/change-password` | 修改密码 | ✗ |

### 公告接口

| 方法 | 路径 | 说明 | 角色 |
|------|------|------|------|
| GET | `/api/notice/list` | 获取公告列表 | 公开 |
| GET | `/api/notice/{id}` | 获取公告详情 | 登录用户 |
| POST | `/api/notice/create` | 发布公告 | 管理员 |
| DELETE | `/api/notice/{id}` | 删除公告 | 管理员 |

### 活动接口

| 方法 | 路径 | 说明 | 角色 |
|------|------|------|------|
| GET | `/api/activity/list` | 获取活动列表 | 公开 |
| GET | `/api/activity/{id}` | 获取活动详情 | 登录用户 |
| POST | `/api/activity/{id}/vote` | 投票 | 退休人员 |
| POST | `/api/activity/{id}/register` | 报名 | 退休人员 |
| POST | `/api/activity/create` | 创建活动 | 管理员 |
| DELETE | `/api/activity/{id}` | 删除活动 | 管理员 |

### 工资接口

| 方法 | 路径 | 说明 | 角色 |
|------|------|------|------|
| GET | `/api/salary/list` | 获取工资列表 | 登录用户 |
| POST | `/api/salary/appeal` | 提交申诉 | 退休人员 |
| POST | `/api/salary/create` | 录入工资 | 管理员 |
| PUT | `/api/salary/appeal/{id}` | 处理申诉 | 管理员 |

---

## 项目亮点

1. **前后端分离架构**：前端Vue.js + 后端Spring MVC，职责清晰，易于维护
2. **JWT无状态认证**：Token-based认证，支持分布式部署
3. **Docker容器化**：一键部署，环境一致性保证
4. **多阶段构建**：后端Dockerfile使用Maven镜像编译，无需本地安装构建工具
5. **Nginx反向代理**：解决跨域问题，支持静态资源缓存
6. **角色权限控制**：基于拦截器的权限验证，管理员/用户功能分离
7. **统一响应格式**：Result封装，前后端交互规范
8. **首页数据动态展示**：公告和活动实时从数据库获取，提升用户体验

---

## 开发环境

| 工具 | 版本 |
|------|------|
| JDK | 17 |
| Maven | 3.9.x |
| MySQL | 8.0 |
| Tomcat | 9.0 |
| Spring | 5.3.30 |
| MyBatis | 3.5.13 |
| Jackson | 2.15.x |
| JJWT | 0.11.5 |

---

## 许可证

本项目仅供学习和研究使用。

---

## 作者

高校退休教职工管理系统开发团队

最后更新：2026年3月