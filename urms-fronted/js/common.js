// API基础路径 - 后端服务地址
// IDEA启动Tomcat时context path通常是根路径，如果部署war包则可能是/urms
const API_BASE = 'http://localhost:8080';

// 创建axios实例
const api = axios.create({
    baseURL: API_BASE,
    timeout: 15000,
    headers: {
        'Content-Type': 'application/json'
    }
});

// 请求拦截器 - 添加token
api.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers['Authorization'] = 'Bearer ' + token;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

// 响应拦截器 - 处理错误
api.interceptors.response.use(
    response => {
        const res = response.data;
        if (res.code !== 200) {
            showMessage(res.message || '请求失败', 'error');
            if (res.code === 401) {
                localStorage.removeItem('token');
                localStorage.removeItem('userInfo');
                window.location.href = 'login.html';
            }
            return Promise.reject(new Error(res.message || 'Error'));
        }
        return res;
    },
    error => {
        showMessage(error.message || '网络错误', 'error');
        return Promise.reject(error);
    }
);

// GET请求
async function get(url) {
    return api.get(url);
}

// POST请求
async function post(url, data) {
    return api.post(url, data);
}

// DELETE请求
async function del(url) {
    return api.delete(url);
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
        window.location.href = 'login.html';
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
    // 跳转到登录页面
    window.location.href = window.location.origin + '/login.html';
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

// Vue混入 - 用于页面公共功能
const pageMixin = {
    data() {
        return {
            userInfo: null,
            loading: false
        };
    },
    created() {
        this.userInfo = getUserInfo();
    },
    methods: {
        showMessage,
        logout,
        formatDateTime,
        formatDate,
        formatMoney,
        getUrlParam,
        get,
        post,
        del
    }
};