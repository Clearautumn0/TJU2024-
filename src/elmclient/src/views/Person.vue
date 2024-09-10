<template>
	<!--  总容器 -->
	<div class="wrapper">
		<!--  header部分 -->
		<header>
			<Backer></Backer>
			<p class="nessage">个人资料</p>
		</header>

		<!--  基础信息部分 -->
		<div class="message-box">
			<div class="message">
				<p>基础信息</p>
			</div>
		</div>

		<!--  基础信息列表部分 -->
		<ul class="message-list">
			<li @click="toEditUserImg">
				<div class="left">
					<p>头像</p>
				</div>
				<div class="right">
					<div class="avatar-frame">
						<!-- 使用 v-if 检查 imageUrl 是否存在，如果不存在则显示默认图片 -->
						<img v-if="imageUrl" :src="imageUrl" alt="用户头像" class="avatar-img">
						<img v-else src="../assets/默认头像.png" alt="无法加载图片" class="avatar-img">
					</div>
					<svg t="1725796901210" class="icon" viewBox="0 0 1024 1024" version="1.1"
						xmlns="http://www.w3.org/2000/svg" p-id="4134" width="200" height="200">
						<path
							d="M353.865143 225.865143a42.642286 42.642286 0 0 1 60.342857 0l256 256a42.642286 42.642286 0 0 1 0 60.269714l-256 256a42.642286 42.642286 0 1 1-60.342857-60.342857L579.657143 512 353.865143 286.134857a42.642286 42.642286 0 0 1 0-60.269714z"
							fill="#8D8B8B" p-id="4135"></path>
					</svg>
				</div>
				<hr>
			</li>
			<li>
				<div class="left">
					<p>账号</p>
				</div>
				<div class="right">
					<p>{{ user.userId }}</p><!--新增-->
					<svg t="1725796901210" class="icon" viewBox="0 0 1024 1024" version="1.1"
						xmlns="http://www.w3.org/2000/svg" p-id="4134" width="200" height="200">
						<path
							d="M353.865143 225.865143a42.642286 42.642286 0 0 1 60.342857 0l256 256a42.642286 42.642286 0 0 1 0 60.269714l-256 256a42.642286 42.642286 0 1 1-60.342857-60.342857L579.657143 512 353.865143 286.134857a42.642286 42.642286 0 0 1 0-60.269714z"
							fill="#8D8B8B" p-id="4135"></path>
					</svg>
				</div>
				<hr>
			</li>
			<li @click="toEditUserName">
				<div class="left">
					<p>昵称</p>
				</div>
				<div class="right">
					<p>{{ user.userName }}</p> <!--新增-->
					<svg t="1725796901210" class="icon" viewBox="0 0 1024 1024" version="1.1"
						xmlns="http://www.w3.org/2000/svg" p-id="4134" width="200" height="200">
						<path
							d="M353.865143 225.865143a42.642286 42.642286 0 0 1 60.342857 0l256 256a42.642286 42.642286 0 0 1 0 60.269714l-256 256a42.642286 42.642286 0 1 1-60.342857-60.342857L579.657143 512 353.865143 286.134857a42.642286 42.642286 0 0 1 0-60.269714z"
							fill="#8D8B8B" p-id="4135"></path>
					</svg>
				</div>
				<hr>
			</li>
			<li>
				<div class="left">
					<p>个人简介</p>
				</div>
				<div class="right">
					<svg t="1725796901210" class="icon" viewBox="0 0 1024 1024" version="1.1"
						xmlns="http://www.w3.org/2000/svg" p-id="4134" width="200" height="200">
						<path
							d="M353.865143 225.865143a42.642286 42.642286 0 0 1 60.342857 0l256 256a42.642286 42.642286 0 0 1 0 60.269714l-256 256a42.642286 42.642286 0 1 1-60.342857-60.342857L579.657143 512 353.865143 286.134857a42.642286 42.642286 0 0 1 0-60.269714z"
							fill="#8D8B8B" p-id="4135"></path>
					</svg>
				</div>
				<hr>
			</li>
			<li @click="toUserAddress">
				<div class="left">
					<p>收货地址</p>
				</div>
				<div class="right">
					<svg t="1725796901210" class="icon" viewBox="0 0 1024 1024" version="1.1"
						xmlns="http://www.w3.org/2000/svg" p-id="4134" width="200" height="200">
						<path
							d="M353.865143 225.865143a42.642286 42.642286 0 0 1 60.342857 0l256 256a42.642286 42.642286 0 0 1 0 60.269714l-256 256a42.642286 42.642286 0 1 1-60.342857-60.342857L579.657143 512 353.865143 286.134857a42.642286 42.642286 0 0 1 0-60.269714z"
							fill="#8D8B8B" p-id="4135"></path>
					</svg>
				</div>
				<hr>
			</li>
		</ul>

		<!--  账号绑定部分 -->
		<!--
		<div class="Id-box">
			<div class="Id">
				<p>账号绑定</p>
			</div>
		</div>
        -->

		<!--  账号绑定信息部分 -->
		<!--
		<ul class="bind-list">
			<li>
				<div class="left">
					<img src="../assets/phone.png">手机
				</div>
				<div class="right">
					<svg t="1725796901210" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4134" width="200" height="200"><path d="M353.865143 225.865143a42.642286 42.642286 0 0 1 60.342857 0l256 256a42.642286 42.642286 0 0 1 0 60.269714l-256 256a42.642286 42.642286 0 1 1-60.342857-60.342857L579.657143 512 353.865143 286.134857a42.642286 42.642286 0 0 1 0-60.269714z" fill="#8D8B8B" p-id="4135"></path></svg>
				</div>
				<hr>
			</li>
			<li>
				<div class="left">
					<img src="../assets/taobao.png">淘宝
				</div>
				<div class="right">
					<svg t="1725796901210" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4134" width="200" height="200"><path d="M353.865143 225.865143a42.642286 42.642286 0 0 1 60.342857 0l256 256a42.642286 42.642286 0 0 1 0 60.269714l-256 256a42.642286 42.642286 0 1 1-60.342857-60.342857L579.657143 512 353.865143 286.134857a42.642286 42.642286 0 0 1 0-60.269714z" fill="#8D8B8B" p-id="4135"></path></svg>
				</div>
				<hr>
			</li>
			<li>
				<div class="left">
					<img src="../assets/zhifubao.png">支付宝
				</div>
				<div class="right">
					<svg t="1725796901210" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4134" width="200" height="200"><path d="M353.865143 225.865143a42.642286 42.642286 0 0 1 60.342857 0l256 256a42.642286 42.642286 0 0 1 0 60.269714l-256 256a42.642286 42.642286 0 1 1-60.342857-60.342857L579.657143 512 353.865143 286.134857a42.642286 42.642286 0 0 1 0-60.269714z" fill="#8D8B8B" p-id="4135"></path></svg>
				</div>
				<hr>
			</li>
			<li>
				<div class="left">
					<img src="../assets/weixin.png">微信
				</div>
				<div class="right">
					<svg t="1725796901210" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4134" width="200" height="200"><path d="M353.865143 225.865143a42.642286 42.642286 0 0 1 60.342857 0l256 256a42.642286 42.642286 0 0 1 0 60.269714l-256 256a42.642286 42.642286 0 1 1-60.342857-60.342857L579.657143 512 353.865143 286.134857a42.642286 42.642286 0 0 1 0-60.269714z" fill="#8D8B8B" p-id="4135"></path></svg>
				</div>
				<hr>
			</li>
			<li>
				<div class="left">
					<img src="../assets/QQ.png">QQ
				</div>
				<div class="right">
					<svg t="1725796901210" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4134" width="200" height="200"><path d="M353.865143 225.865143a42.642286 42.642286 0 0 1 60.342857 0l256 256a42.642286 42.642286 0 0 1 0 60.269714l-256 256a42.642286 42.642286 0 1 1-60.342857-60.342857L579.657143 512 353.865143 286.134857a42.642286 42.642286 0 0 1 0-60.269714z" fill="#8D8B8B" p-id="4135"></path></svg>
				</div>
				<hr>
			</li>
		</ul>
        -->
		
	</div>
</template>

<script setup>
import {
	useRouter
} from 'vue-router';
import {
	ref,
	onMounted,
	getCurrentInstance
} from 'vue';
import Backer from '../components/backer.vue';
import {
	getSessionStorage,
	getLocalStorage
} from '../common.js';

// 获取全局 axios 实例
const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;

const user = ref({});
const deliveryaddress = ref({});
const imageUrl = ref('');

const router = useRouter();

// 使用 onMounted 生命周期钩子来代替 created
onMounted(() => {
	user.value = getSessionStorage('user') || { userName: '未登录', userId: '', usserImg: '' };
	imageUrl.value = getLocalStorage(`userImg${user.value.userId}`);
	deliveryaddress.value = getLocalStorage(user.value.userId);
	// console.log(deliveryaddress.value);
});

const toUserAddress = () => {
	if (user.value.userName === '未登录') {
		router.push({ path: '/login' });
	}
	else {
		router.push({ path: '/userAddress' });
	}
}

const toEditUserImg = () => {
	if (user.value.userName === '未登录') {
		router.push({ path: '/login' });
	}
	else {
		router.push({ path: '/editUserImg' });
	}
	
}

const toEditUserName = () => {
	if (user.value.userName === '未登录') {
		router.push({ path: '/login' });
	}
	else {
		router.push({ path: '/editUserName' });
	}
	
}

</script>

<style scoped>
/********************* 总容器  *********************/
.wrapper {
	width: 100%;
	height: 100%;
}

/********************* header  *********************/
.wrapper header {
	width: 100%;
	height: 12vw;
	background-color: #ffffff;
	font-weight: 800;
	color: #e2e2e2;
	font-size: 4.5vw;

	display: flex;
	align-items: center;
	justify-content: center;
}

.wrapper header p {
	color: #000000;
	font-weight: 600;
}

/********************* message  *********************/
.wrapper .message-box {
	width: 100%;
	height: 11vw;
	background-color: #ebebeb;

	display: flex;
	align-items: flex-start;
}

.wrapper .message-box .message {
	font-size: 3.5vw;
	margin-left: 2vw;
	margin-top: 4vw;
	color: #5e5e5e;
}

/********************* message列表 *********************/
.wrapper .message-list {
	width: 100%;
	height: 60vw;

	display: flex;
	flex-wrap: wrap;
	justify-content: flex-start;
}



.wrapper .message-list li {
	width: 100vw;
	height: 12vw;

	display: flex;
	align-items: center;
	justify-content: space-between;
	position: relative;
}

.wrapper .message-list li .left {
	width: 20vw;
	height: 4vw;

	display: flex;
	align-items: center;
	justify-content: flex-start;
}

.wrapper .message-list li .left p {
	font-size: 4vw;
	font-weight: 525;
	margin-left: 2vw;
	/* 	margin-top: 3.8vw; */
}

.wrapper .message-list li .right {
	display: flex;
	align-items: center;
	margin-right: 4vw;
}

.wrapper .message-list li .right svg {
	width: 5vw;
	height: 5vw;
	display: flex;
	align-items: center;
	color: #888;

	cursor: pointer;
}

.wrapper .message-list li .right p {
	font-size: 4vw;
	color: #888;
}

/*头像显示*/
.avatar-frame {
	width: 10vw;
	/* 使用视口宽度的10%作为宽度 */
	height: 10vw;
	/* 使用视口宽度的10%作为高度 */
	border-radius: 50%;
	/* overflow: hidden; */
	border: 2px solid #ccc;
	margin-right: 0vw;
	/*调整右对齐 */
	/* margin-left: 2vw; */
	/* overflow: hidden; */
}

.avatar-frame img {
	width: 100%;
	height: 100%;
	object-fit: cover;
	border-radius: 50%;
}

/* *****分割线样式******* */


.wrapper .message-list li hr {
	width: calc(100% - 4vw);
	/* 减去左右的边距 */
	position: absolute;
	bottom: 0;
	left: 2vw;
	/* 确保 <hr> 与左侧对齐 */
	background-color: #e2e2e2;
	border: none;
	height: 0.05vw;
	margin: 0;
}

/********************* 账号绑定  *********************/
.wrapper .Id-box {
	width: 100%;
	height: 11vw;
	background-color: #ebebeb;

	display: flex;
	align-items: flex-start;
}

.wrapper .Id-box .Id {
	font-size: 3.5vw;
	margin-left: 2vw;
	margin-top: 4vw;
	color: #5e5e5e;
}

/********************* 绑定列表 *********************/
.wrapper .bind-list {
	width: 100%;
	height: 60vw;

	display: flex;
	flex-wrap: wrap;
	justify-content: flex-start;
}

.wrapper .bind-list li {
	width: 100vw;
	height: 12vw;

	position: relative;

	display: flex;
	align-items: center;
	justify-content: space-between;
}

.wrapper .bind-list li .left {
	font-size: 4vw;
	font-weight: 500;

	display: flex;
	align-items: center;
	margin-left: 2.0vw;
}

.wrapper .bind-list li .left img {
	width: 5vw;
	height: 5vw;

	display: flex;
	align-items: center;
	margin-right: 3vw;
}

.wrapper .bind-list li .right svg {
	width: 5vw;
	height: 5vw;
	display: flex;
	align-items: center;
	margin-right: 4vw;
	color: #888;

	cursor: pointer;
}

.wrapper .bind-list li hr {
	width: 98vw;
	margin-left: 2vw;

	position: absolute;
	bottom: 0;

	background-color: #e2e2e2;
	border: none;
	/* 移除默认的边框 */
	height: 0.05vw;
	/* 设置分割线的高度 */
	margin-bottom: 0;
}

.wrapper .bottom {
	width: 100%;
	height: 100%;
	background-color: #ebebeb;
}
</style>