# 后端服务 Dockerfile (多阶段构建)
# 阶段1: 使用Maven构建war包
# 阶段2: 使用Tomcat运行应用

# ==================== 构建阶段 ====================
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /build

# 复制pom.xml先下载依赖（利用Docker缓存）
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 复制源码并构建
COPY src ./src
RUN mvn clean package -DskipTests -B

# ==================== 运行阶段 ====================
FROM tomcat:9.0.98-jdk17-temurin-jammy

# 维护者信息
LABEL maintainer="URMS System"
LABEL description="University Retired Staff Management System - Backend"

# 设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 删除Tomcat默认的应用
RUN rm -rf /usr/local/tomcat/webapps/*

# 创建上传文件目录
RUN mkdir -p /usr/local/tomcat/uploads

# 从构建阶段复制war包
COPY --from=builder /build/target/urms.war /usr/local/tomcat/webapps/ROOT.war

# 暴露端口
EXPOSE 8080

# 启动Tomcat
CMD ["catalina.sh", "run"]