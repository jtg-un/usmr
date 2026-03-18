# 前端服务 Dockerfile
# 使用Nginx作为Web服务器

FROM nginx:1.25-alpine

# 维护者信息
LABEL maintainer="URMS System"
LABEL description="University Retired Staff Management System - Frontend"

# 设置时区
ENV TZ=Asia/Shanghai
RUN apk add --no-cache tzdata && \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 删除Nginx默认配置
RUN rm -rf /usr/share/nginx/html/*

# 复制前端静态文件
COPY . /usr/share/nginx/html/

# 复制Nginx配置文件
COPY nginx.conf /etc/nginx/conf.d/default.conf

# 暴露端口
EXPOSE 80

# 启动Nginx
CMD ["nginx", "-g", "daemon off;"]