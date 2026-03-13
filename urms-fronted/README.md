# 大学退休教职工管理系统 - 前后端分离版

## 项目结构

```
urms/
├── urms_backed/          # 后端项目 (SSM)
│   └── src/main/java/...
├── urms-fronted/         # 前端项目 (Vue2 CDN + HTML)
│   ├── index.html
│   ├── login.html
│   ├── css/
│   ├── js/
│   │   ├── vue.min.js    # Vue2库
│   │   ├── axios.min.js  # Axios库
│   │   └── common.js     # 公共工具函数
│   └── pages/
│       ├── user/         # 用户端页面 (9个)
│       └── admin/        # 管理员页面 (6个)
└── urms_db.sql           # 数据库脚本
```

## 启动方式

### 1. 启动后端

```bash
cd urms_backed
mvn clean package
# 将war包部署到Tomcat，或使用IDE运行
```

后端默认运行在 `http://localhost:8080/urms`

### 2. 启动前端

使用任意静态文件服务器：

```bash
# 方式1: 使用Python
cd urms-fronted
python -m http.server 8081

# 方式2: 使用Node.js http-server
npx http-server -p 8081

# 方式3: 使用VS Code Live Server插件
# 右键login.html -> Open with Live Server
```

前端运行在 `http://localhost:8081`

### 3. 访问系统

打开浏览器访问: `http://localhost:8081/login.html`

## 测试账号

- 管理员: admin / admin
- 退休人员: user1 / 123456

## 技术栈

- **后端**: SpringMVC + Spring + MyBatis + MySQL 8.0 + JDK 17
- **前端**: HTML + Vue2 (CDN) + Axios

## API配置

前端API基础路径配置在 `js/common.js` 中：

```javascript
const API_BASE = 'http://localhost:8080/urms';
```

生产环境请修改为实际后端地址。