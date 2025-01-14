import { createApp } from 'vue';

import App from './App.vue';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import router from './router';
import axiosInstance from './utils/interceptor.js'

import 'font-awesome/css/font-awesome.min.css';
// import axios from 'axios'	//已在utils/interceptor.js导入;
import qs from 'qs';
import {
	getCurDate,
	setSessionStorage,
	getSessionStorage,
	removeSessionStorage,
	setLocalStorage,
	getLocalStorage,
	removeLocalStorage
} from './common.js';


// // 启用兼容模式
// configureCompat({
// 	MODE: 2, // 兼容模式：2 代表全面兼容 Vue 2 语法
// });

const app = createApp(App);

// 设置 axios 的基础 URL

app.config.globalProperties.$axios = axiosInstance;
app.config.globalProperties.$qs = qs;
app.config.globalProperties.$getCurDate = getCurDate;
app.config.globalProperties.$setSessionStorage = setSessionStorage;
app.config.globalProperties.$getSessionStorage = getSessionStorage;
app.config.globalProperties.$removeSessionStorage = removeSessionStorage;
app.config.globalProperties.$setLocalStorage = setLocalStorage;
app.config.globalProperties.$getLocalStorage = getLocalStorage;
app.config.globalProperties.$removeLocalStorage = removeLocalStorage;

// 路由导航守卫
router.beforeEach((to, from, next) => {
	const user = sessionStorage.getItem('user');
	if (
		!(
			to.path === '/' ||
			to.path === '/index' ||
			to.path === '/businessList' ||
			to.path === '/businessInfo' ||
			to.path === '/login' ||
			to.path === '/register' ||
			to.path === '/selfpage' ||
			to.path === '/person' ||
			to.path === '/orderList'||
			to.path === '/associationOf'
		)
	) {
		if (user === null) {
			router.push('/login');
			location.reload();
		}
	}
	next();
});

// 创建 Vue 实例并挂载
app.use(router);
app.use(ElementPlus);
app.mount('#app');
