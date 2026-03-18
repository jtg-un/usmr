@echo off
chcp 65001 >nul
REM URMS 一键部署脚本
REM 适用于Windows系统

echo ==========================================
echo    高校退休教职工管理系统 - Docker部署
echo ==========================================
echo.

REM 检查Docker是否安装
docker --version >nul 2>&1
if errorlevel 1 (
    echo [错误] 未检测到Docker，请先安装Docker Desktop
    echo 安装文档: https://docs.docker.com/desktop/install/windows-install/
    pause
    exit /b 1
)
echo [√] Docker环境检测通过

REM 检查Docker Compose
docker-compose version >nul 2>&1
if errorlevel 1 (
    docker compose version >nul 2>&1
    if errorlevel 1 (
        echo [错误] 未检测到Docker Compose
        pause
        exit /b 1
    )
)
echo [√] Docker Compose检测通过

echo.
echo [提示] 开始构建并启动服务...
echo        首次部署需要下载镜像和构建，请耐心等待...
echo.

REM 构建并启动服务
docker-compose up -d --build
if errorlevel 1 (
    echo [错误] 部署失败，请检查错误信息
    pause
    exit /b 1
)

echo.
echo [提示] 等待服务启动...
timeout /t 15 /nobreak >nul

echo.
echo ==========================================
echo    部署完成！
echo ==========================================
echo.
echo 访问地址:
echo   系统首页:  http://localhost
echo   后端API:   http://localhost:8080
echo.
echo 测试账号:
echo   管理员:   admin / admin
echo   退休人员: user1 / 123456
echo.
echo 常用命令:
echo   查看状态: docker-compose ps
echo   查看日志: docker-compose logs -f
echo   停止服务: docker-compose down
echo   重启服务: docker-compose restart
echo.

REM 尝试打开浏览器
echo 正在打开浏览器...
timeout /t 3 /nobreak >nul
start http://localhost

pause