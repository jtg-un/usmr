# URMS Docker 部署指南

本文档说明如何使用Docker容器化部署高校退休教职工管理系统。

## 前置条件

**唯一要求：安装 Docker**

- Windows: [Docker Desktop](https://docs.docker.com/desktop/install/windows-install/)
- macOS: [Docker Desktop](https://docs.docker.com/desktop/install/mac-install/)
- Linux: [Docker Engine](https://docs.docker.com/engine/install/)

> 不需要安装 JDK、Maven、MySQL、Tomcat、Nginx 等任何其他软件！
> 所有构建和运行都在Docker容器内完成。

## 一键部署（推荐）

### Windows 用户

双击运行 `deploy.bat` 或在命令行执行：

```cmd
deploy.bat
```

### Linux / macOS 用户

```bash
# 添加执行权限
chmod +x deploy.sh

# 运行部署脚本
./deploy.sh
```

部署完成后，浏览器会自动打开系统首页。

## 访问系统

| 地址 | 说明 |
|------|------|
| http://localhost | 系统首页 |
| http://localhost:8080 | 后端API |

### 测试账号

- 管理员: **admin / admin**
- 退休人员: **user1 / 123456**

## 项目结构

```
usmr/
├── docker-compose.yml      # Docker编排配置
├── deploy.bat              # Windows一键部署脚本
├── deploy.sh               # Linux/macOS一键部署脚本
├── urms_db.sql             # 数据库初始化脚本
├── urms_backed/            # 后端项目
│   ├── Dockerfile          # 多阶段构建：Maven编译 + Tomcat运行
│   └── src/                # 源代码
├── urms-fronted/           # 前端项目
│   ├── Dockerfile          # Nginx静态服务
│   ├── nginx.conf          # Nginx配置（含反向代理）
│   └── ...                 # 静态文件
└── DOCKER_DEPLOY.md        # 本部署文档
```

## 架构说明

```
              ┌─────────────────────────────────────┐
              │           用户浏览器                │
              └─────────────┬───────────────────────┘
                            │ :80
              ┌─────────────▼───────────────────────┐
              │      urms-frontend (Nginx)          │
              │   静态资源 + 反向代理 /api/          │
              └─────────────┬───────────────────────┘
                            │ :8080
              ┌─────────────▼───────────────────────┐
              │      urms-backend (Tomcat)          │
              │      Spring MVC + MyBatis           │
              └─────────────┬───────────────────────┘
                            │ :3306
              ┌─────────────▼───────────────────────┐
              │        urms-mysql (MySQL 8.0)       │
              │           urms_db 数据库            │
              └─────────────────────────────────────┘
```

## 服务说明

### MySQL 服务 (urms-mysql)
- 端口: 3306
- 数据库名: urms_db
- 用户名: root
- 密码: 123456
- 数据持久化: mysql_data 数据卷

### 后端服务 (urms-backend)
- 端口: 8080
- 运行在 Tomcat 9.0 + JDK 17 环境
- 上传文件存储: uploads_data 数据卷

### 前端服务 (urms-frontend)
- 端口: 80
- 运行在 Nginx 环境
- 通过反向代理访问后端API

## 常用命令

### 启动服务
```bash
docker-compose up -d
```

### 停止服务
```bash
docker-compose down
```

### 重启服务
```bash
docker-compose restart
```

### 查看日志
```bash
# 所有服务日志
docker-compose logs -f

# 特定服务日志
docker-compose logs -f backend
docker-compose logs -f frontend
docker-compose logs -f mysql
```

### 重新构建镜像
```bash
# 重新构建所有镜像
docker-compose build --no-cache

# 重新构建特定服务
docker-compose build --no-cache backend
docker-compose build --no-cache frontend
```

### 进入容器
```bash
# 进入后端容器
docker exec -it urms-backend bash

# 进入MySQL容器
docker exec -it urms-mysql bash

# 进入前端容器
docker exec -it urms-frontend sh
```

### 数据库操作
```bash
# 连接MySQL
docker exec -it urms-mysql mysql -uroot -p123456 urms_db

# 导出数据库
docker exec urms-mysql mysqldump -uroot -p123456 urms_db > backup.sql

# 导入数据库
docker exec -i urms-mysql mysql -uroot -p123456 urms_db < backup.sql
```

## 数据持久化

Docker Compose 配置了两个数据卷：
- `mysql_data`: MySQL数据文件
- `uploads_data`: 上传文件存储

数据卷位置：
```bash
# 查看数据卷
docker volume ls

# 查看数据卷详情
docker volume inspect usmr_mysql_data
```

## 配置修改

### 修改数据库密码

1. 修改 `docker-compose.yml` 中的 `MYSQL_ROOT_PASSWORD`
2. 修改 `urms_backed/src/main/resources/db.properties` 中的 `jdbc.password`
3. 重新构建并启动服务

### 修改端口

编辑 `docker-compose.yml` 中的 `ports` 配置：
```yaml
services:
  frontend:
    ports:
      - "80:80"  # 修改为 "自定义端口:80"
```

## 生产环境建议

1. **修改默认密码**: 更改MySQL root密码和管理员账号密码
2. **配置HTTPS**: 在Nginx中配置SSL证书
3. **限制资源**: 添加容器资源限制
   ```yaml
   services:
     backend:
       deploy:
         resources:
           limits:
             cpus: '2'
             memory: 2G
   ```
4. **备份数据**: 定期备份MySQL数据卷
5. **日志管理**: 配置日志轮转或使用ELK收集日志

## 故障排查

### 后端无法连接MySQL
```bash
# 检查MySQL是否就绪
docker-compose logs mysql | grep "ready for connections"

# 检查网络连接
docker exec urms-backend ping mysql
```

### 前端无法访问后端API
```bash
# 检查后端服务状态
docker-compose logs backend

# 测试API连接
curl http://localhost/api/auth/login
```

### 文件上传失败
```bash
# 检查上传目录权限
docker exec urms-backend ls -la /usr/local/tomcat/uploads
```

## 清理

### 停止并删除所有容器、网络、数据卷
```bash
docker-compose down -v
```

### 删除所有镜像
```bash
docker-compose down --rmi all
```

### 完全清理（慎用）
```bash
docker-compose down -v --rmi all
docker system prune -f
```

---

如有问题，请检查日志或联系系统管理员。