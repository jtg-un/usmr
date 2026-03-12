// API基础路径
const API_BASE = '/urms';

// 通用请求方法
async function request(url, method = 'GET', data = null) {
    const options = {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        }
    };

    // 添加token
    const token = localStorage.getItem('token');
    if (token) {
        options.headers['Authorization'] = 'Bearer ' + token;
    }

    if (data && (method === 'POST' || method === 'PUT')) {
        options.body = JSON.stringify(data);
    }

    try {
        const response = await fetch(API_BASE + url, options);
        const result = await response.json();
        return result;
    } catch (error) {
        console.error('请求错误:', error);
        return { code: 500, message: '网络错误' };
    }
}

// GET请求
async function get(url) {
    return request(url, 'GET');
}

// POST请求
async function post(url, data) {
    return request(url, 'POST', data);
}

// DELETE请求
async function del(url) {
    return request(url, 'DELETE');
}

// 显示消息提示
function showMessage(text, type = 'success') {
    const msg = document.createElement('div');
    msg.className = 'message ' + type;
    msg.textContent = text;
    document.body.appendChild(msg);

    setTimeout(() => {
        msg.remove();
    }, 3000);
}

// 检查登录状态
function checkLogin() {
    const token = localStorage.getItem('token');
    if (!token) {
        window.location.href = '/urms/login.html';
        return false;
    }
    return true;
}

// 获取用户信息
function getUserInfo() {
    const userInfo = localStorage.getItem('userInfo');
    return userInfo ? JSON.parse(userInfo) : null;
}

// 退出登录
function logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('userInfo');
    window.location.href = '/urms/login.html';
}

// 格式化日期时间
function formatDateTime(dateTimeStr) {
    if (!dateTimeStr) return '-';
    const date = new Date(dateTimeStr);
    return date.toLocaleString('zh-CN');
}

// 格式化日期
function formatDate(dateStr) {
    if (!dateStr) return '-';
    const date = new Date(dateStr);
    return date.toLocaleDateString('zh-CN');
}

// 格式化金额
function formatMoney(amount) {
    if (amount === null || amount === undefined) return '0.00';
    return parseFloat(amount).toFixed(2);
}

// 获取URL参数
function getUrlParam(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}

// 验证是否为管理员
function isAdmin() {
    const userInfo = getUserInfo();
    return userInfo && userInfo.role === 2;
}

// 验证是否为退休人员
function isUser() {
    const userInfo = getUserInfo();
    return userInfo && userInfo.role === 1;
}

// 渲染导航栏
function renderNavbar(activeMenu) {
    const userInfo = getUserInfo();
    const navLinks = document.querySelector('.nav-links');
    const userDiv = document.querySelector('.user-info');

    if (isAdmin()) {
        navLinks.innerHTML = `
            <a href="admin-index.html" class="${activeMenu === 'index' ? 'active' : ''}">首页</a>
            <a href="admin-notice.html" class="${activeMenu === 'notice' ? 'active' : ''}">公告管理</a>
            <a href="admin-activity.html" class="${activeMenu === 'activity' ? 'active' : ''}">活动管理</a>
            <a href="admin-salary.html" class="${activeMenu === 'salary' ? 'active' : ''}">工资管理</a>
            <a href="admin-staff.html" class="${activeMenu === 'staff' ? 'active' : ''}">人员管理</a>
            <a href="admin-message.html" class="${activeMenu === 'message' ? 'active' : ''}">留言管理</a>
        `;
    } else {
        navLinks.innerHTML = `
            <a href="user-index.html" class="${activeMenu === 'index' ? 'active' : ''}">首页</a>
            <a href="user-profile.html" class="${activeMenu === 'profile' ? 'active' : ''}">个人信息</a>
            <a href="user-notice.html" class="${activeMenu === 'notice' ? 'active' : ''}">校园公告</a>
            <a href="user-activity.html" class="${activeMenu === 'activity' ? 'active' : ''}">活动报名</a>
            <a href="user-salary.html" class="${activeMenu === 'salary' ? 'active' : ''}">工资查询</a>
            <a href="user-relative.html" class="${activeMenu === 'relative' ? 'active' : ''}">亲属管理</a>
            <a href="user-message.html" class="${activeMenu === 'message' ? 'active' : ''}">站内留言</a>
        `;
    }

    if (userDiv && userInfo) {
        userDiv.innerHTML = `
            <span>欢迎, ${userInfo.realName || userInfo.username}</span>
            <span class="logout" onclick="logout()">退出</span>
        `;
    }
}