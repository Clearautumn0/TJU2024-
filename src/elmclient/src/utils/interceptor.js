import axios from 'axios';
import router from '../router';

// 创建 Axios 实例
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/elm/',
    timeout: 1000,
});

// 请求拦截器
axiosInstance.interceptors.request.use(
    config => {
        // 在请求发送前做些什么，比如添加 token
        const token = sessionStorage.getItem('token');
        if (token) {
            // 移除 token 字符串中的双引号
            config.headers['token'] = token.replace(/"/g, '');
        }
        // 可以添加其他需要的请求头或数据处理
        return config;
    },
    error => {
        // 对请求错误做些什么
        console.error('请求错误:', error);
        return Promise.reject(error);
    }
);

axiosInstance.defaults.retryCount = 0;
axiosInstance.defaults.maxRetry = 3; // 设置最大重试次数

// 响应拦截器
axiosInstance.interceptors.response.use(
    response => {
        // 对响应数据做点什么
        // 例如，你可以在这里根据状态码统一处理错误
        if (response.status === 200) {
            return response.data;
        } else {
            // 处理非 200 的响应状态码
            console.error('响应错误:', response.status);
            return Promise.reject(new Error('响应状态码非 200'));
        }
    },
    async error => {
        // 对响应错误做点什么
        console.error('响应错误:', error);

        if (!error.response) {
            // 网络错误，可能是网络不稳定导致的
            axiosInstance.defaults.retryCount += 1;
            if (axiosInstance.defaults.retryCount <= axiosInstance.defaults.maxRetry) {
                try {
                    // 重试逻辑
                    const retryResponse = await axiosInstance(error.config);
                    return retryResponse;
                } catch (retryError) {
                    // 重试失败，跳转回主页
                    alert('重连失败');
                    console.error('重试失败，跳转回主页');
                    router.push('/');
                }
            } else {
                // 重试次数超过最大限制，不再重试
                alert('网络连接超时');
                console.error('重试次数超过最大限制3，跳转回主页');
                router.push('/');
            }
        } else {
            // 如果是服务器返回的错误，可以根据状态码做不同的处理
            if (error.response.status === 401) {
                // 例如，401 未授权，可能需要重新登录

                console.error('可能需要重新登录');

                router.push('/login');
            }
            // 抛出错误，可以在调用请求时通过 .catch() 捕获
            return Promise.reject(error);
        }
    }
);

// 导出 Axios 实例
export default axiosInstance;