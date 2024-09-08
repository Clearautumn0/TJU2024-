import { createApp } from 'vue';
import App from './App.vue';
import router from './router';

import 'font-awesome/css/font-awesome.min.css';
import axios from 'axios';
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

const app = createApp(App);

// 设置 axios 的基础 URL 部分
axios.defaults.baseURL = 'http://localhost:8080/elm/';

// 将 axios 挂载到 Vue 实例上，使用时就可以 this.$axios 这样使用了
app.config.globalProperties.$axios = axios;
app.config.globalProperties.$qs = qs;

app.config.globalProperties.$getCurDate = getCurDate;
app.config.globalProperties.$setSessionStorage = setSessionStorage;
app.config.globalProperties.$getSessionStorage = getSessionStorage;
app.config.globalProperties.$removeSessionStorage = removeSessionStorage;
app.config.globalProperties.$setLocalStorage = setLocalStorage;
app.config.globalProperties.$getLocalStorage = getLocalStorage;
app.config.globalProperties.$removeLocalStorage = removeLocalStorage;

app.use(router);

// 设置路由导航守卫
router.beforeEach((to, from, next) => {
  let user = sessionStorage.getItem('user');
  // 除了登录、注册、首页、商家列表、商家信息之外，都需要判断是否登录
  if (!(to.path === '/' || to.path === '/index' || to.path === '/businessList' || to.path === '/businessInfo' || to.path === '/login' || to.path === '/register' || to.path === '/person')) {
    if (user === null) {
      next('/login');
      return; // 确保不要执行下一步
    }
  }
  next();
});

app.mount('#app');