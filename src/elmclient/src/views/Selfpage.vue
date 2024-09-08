<template>
	<div class="wrapper">
		<header class="profile-header">
			<div class="header-left">
				<div class="avatar-frame">
					<img src="../assets/defaultphoto.png" alt="无法加载图片">
				</div>
				<h2 class="username">{{ user.userName }}</h2>
			</div>
			<div class="header-right">
				<img @click="toindividual_inf" src="../assets/setting.png">
			</div>
		</header>
		<ul class="money">
			<li>
				<img src="../assets/money.png">
				<p>我的资产</p>
			</li>
			<li>
				<img src="../assets/hongbao.png">
				<p>红包</p>
			</li>
			<li>
				<img src="../assets/kaquan.png">
				<p>券包</p>
			</li>
		</ul>
		<ul class="system-setting">
			<li>
				<img src="../assets/address.png">
				<p>我的地址</p>
			</li>
			<li>
				<img src="../assets/kefu.png">
				<p>我的客服</p>
			</li>
			<li>
				<img src="../assets/guanyu.png">
				<p>关于饿了么</p>
			</li>
			<li>
				<img src="../assets/protect.png">
				<p>用户隐私</p>
			</li>
			<li>
				<img src="../assets/rule.png">
				<p>规则中心</p>
			</li>
		</ul>

		<Footer></Footer>
	</div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref, onMounted, getCurrentInstance } from 'vue';
import Footer from '../components/Footer.vue';
import { getSessionStorage } from '../common.js';

// 获取全局 axios 实例
const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;


const router = useRouter(); // 使用 useRouter 获取路由实例
const user = getSessionStorage('user') || { userName: '未登录' }; // 获取用户数据

const toindividual_inf = () => {
	router.push({ path: '/person' });
};

// return {
// 	user,
// 	toindividual_inf
// };

</script>

<style scoped>
.wrapper {
	width: 100%;
	height: 100%;
	background-color: #f5f5f5;
	/* display: flex;
    flex-direction: column;
    padding-bottom: 14vw; */
	/* 为底部菜单留出空间 */
}

.wrapper header {
	width: 100%;
	height: 20vw;
	display: flex;
	align-items: center;
	justify-content: space-between;
}

.wrapper header .header-left {
	width: 80vw;
	height: 20vw;
	background-color: #f5f5f5;
	/* text-align: center; */
	display: flex;
	align-items: center;
	/* padding: 0 2vw; */
}

.avatar-frame {
	width: 12vw;
	/* 使用视口宽度的10%作为宽度 */
	height: 12vw;
	/* 使用视口宽度的10%作为高度 */
	border-radius: 50%;
	/* overflow: hidden; */
	/* border: 3px solid #ccc; */
	margin-right: 2vw;
	margin-left: 2vw;
}

.avatar-frame img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.username {
	font-size: 5vw;
	/* 使用视口宽度的2%作为字体大小 */
	color: #333;
	/* margin-top: 5vw; */
	/* 使用视口宽度的1%作为上边距 */
}

.wrapper header .header-right {
	width: 20vw;
	height: 20vw;
	display: flex;
	align-items: center;
}

.wrapper header .header-right img {
	width: 7vw;
	height: 7vw;
	font-weight: 600;
	margin-left: 10vw;
}

.profile-menu {
	list-style: none;
	padding: 0;
	margin: 0;
	flex: 1;
	/* 使菜单列表占据剩余的空间 */
}

.profile-menu-item {
	padding: 8vw;
	/* 使用视口宽度的2%作为内边距 */
	font-size: 3vw;
	/* 使用视口宽度的1.5%作为字体大小 */
	color: #555;
	border-bottom: 1px solid #e0e0e0;
	cursor: pointer;
	transition: background-color 0.3s;
}

.profile-menu-item:hover {
	background-color: #e9e9e9;
}

.wrapper .money {
	width: 95%;
	height: 30vw;

	display: flex;
	justify-content: space-around;
	align-items: center;
	background-color: #fff;
	border-radius: 5vw;
	margin-left: 2vw;
	margin-right: 2vw;
}

.wrapper .money li {
	width: 30vw;
	height: 20vw;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;

	/* 	user-select: none;
	cursor: pointer; */
}

.wrapper .money li img {
	width: 6vw;
	height: 6vw;
}

.wrapper .money li p {
	margin-top: 1vw;
	font-size: 3.2vw;
}

.wrapper .system-setting {
	width: 95%;
	height: 30vw;
	margin-top: 2vw;

	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
	align-items: center;
	background-color: #fff;
	border-radius: 5vw;
	margin-left: 2.5vw;
	margin-right: 2.5vw;
}

.wrapper .system-setting li {
	width: 22vw;
	height: 15vw;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;

	/* 	user-select: none;
	cursor: pointer; */
}

.wrapper .system-setting li img {
	width: 6vw;
	height: 6vw;
}

.wrapper .system-setting li p {
	margin-top: 1vw;
	font-size: 3.2vw;
}
</style>
