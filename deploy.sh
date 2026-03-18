#!/bin/bash

# URMS 一键部署脚本
# 适用于Linux/macOS系统

set -e

echo "=========================================="
echo "   高校退休教职工管理系统 - Docker部署"
echo "=========================================="

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 检查Docker是否安装
check_docker() {
    if ! command -v docker &> /dev/null; then
        echo -e "${RED}错误: 未检测到Docker，请先安装Docker${NC}"
        echo "安装文档: https://docs.docker.com/get-docker/"
        exit 1
    fi

    if ! command -v docker-compose &> /dev/null && ! docker compose version &> /dev/null; then
        echo -e "${RED}错误: 未检测到Docker Compose，请先安装${NC}"
        echo "安装文档: https://docs.docker.com/compose/install/"
        exit 1
    fi

    echo -e "${GREEN}✓ Docker环境检测通过${NC}"
}

# 检查端口占用
check_ports() {
    echo -e "\n${YELLOW}检查端口占用...${NC}"

    for port in 80 3306 8080; do
        if lsof -Pi :$port -sTCP:LISTEN -t >/dev/null 2>&1 || netstat -tuln 2>/dev/null | grep -q ":$port "; then
            echo -e "${RED}警告: 端口 $port 已被占用${NC}"
            read -p "是否继续部署? (y/n): " continue_deploy
            if [[ ! "$continue_deploy" =~ ^[Yy]$ ]]; then
                exit 1
            fi
        else
            echo -e "${GREEN}✓ 端口 $port 可用${NC}"
        fi
    done
}

# 构建并启动服务
deploy() {
    echo -e "\n${YELLOW}开始构建并启动服务...${NC}"
    echo "首次部署需要下载镜像和构建，请耐心等待..."

    # 使用docker-compose构建并启动
    if docker-compose version &> /dev/null; then
        docker-compose up -d --build
    else
        docker compose up -d --build
    fi

    echo -e "${GREEN}✓ 服务启动完成${NC}"
}

# 等待服务就绪
wait_for_services() {
    echo -e "\n${YELLOW}等待服务启动...${NC}"

    # 等待MySQL就绪
    echo "等待MySQL启动..."
    sleep 10

    for i in {1..30}; do
        if docker exec urms-mysql mysqladmin ping -h localhost -uroot -p123456 --silent 2>/dev/null; then
            echo -e "${GREEN}✓ MySQL已就绪${NC}"
            break
        fi
        echo -n "."
        sleep 2
    done

    # 等待后端就绪
    echo -e "\n等待后端服务启动..."
    for i in {1..60}; do
        if curl -s http://localhost:8080/ > /dev/null 2>&1; then
            echo -e "${GREEN}✓ 后端服务已就绪${NC}"
            break
        fi
        echo -n "."
        sleep 2
    done

    # 等待前端就绪
    echo -e "\n等待前端服务..."
    for i in {1..30}; do
        if curl -s http://localhost/ > /dev/null 2>&1; then
            echo -e "${GREEN}✓ 前端服务已就绪${NC}"
            break
        fi
        echo -n "."
        sleep 1
    done
}

# 显示部署结果
show_result() {
    echo -e "\n=========================================="
    echo -e "${GREEN}   部署完成！${NC}"
    echo -e "=========================================="
    echo ""
    echo "访问地址:"
    echo -e "  系统首页:  ${GREEN}http://localhost${NC}"
    echo -e "  后端API:   ${GREEN}http://localhost:8080${NC}"
    echo ""
    echo "测试账号:"
    echo "  管理员:   admin / admin"
    echo "  退休人员: user1 / 123456"
    echo ""
    echo "常用命令:"
    echo "  查看状态: docker-compose ps"
    echo "  查看日志: docker-compose logs -f"
    echo "  停止服务: docker-compose down"
    echo "  重启服务: docker-compose restart"
    echo ""
}

# 主流程
main() {
    check_docker
    check_ports
    deploy
    wait_for_services
    show_result
}

main