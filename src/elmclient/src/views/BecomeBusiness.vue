<template>
	<!--  总容器 -->
	<div class="wrapper">
		<!--  header部分 -->
		<header>
			<Backer></Backer>
			<p class="title">门店信息录入</p>
		</header>

		<div class="business-register-img">
			<img src="../assets/registerbusiness.jpg">
		</div>

		<div class="input-box">
			<ul class="register-message">
				<li>
					<div class="input-container">
						<span class="input-label">门店名称</span>
						<input type="text" class="input-field" placeholder="需与门牌照名称一致" v-model="storeName">
					</div>
				</li>
				<li>
					<div class="input-container">
						<span class="input-label">门店地址</span>
						<input type="text" class="input-field" placeholder="请填写门店实际位置" v-model="storeAddress">
					</div>
				</li>
				<li>
					<div class="input-container">
						<span class="input-label">商家分类</span>
						<input type="text" class="input-field" placeholder="请输入分类" v-model="storeCategory">
					</div>
				</li>
				<li>
					<div class="input-container">
						<span class="input-label">手机号码</span>
						<input type="text" class="input-field" placeholder="请输入" v-model="phoneNumber">
					</div>
				</li>
				<li>
					<div class="input-container">
						<span class="input-label">设置密码</span>
						<input type="text" class="input-field" placeholder="请输入" v-model="password">
					</div>
				</li>
			</ul>

			<div class="register-button">
				<button @click="register">立即入驻</button>
			</div>
		</div>
	</div>

</template>

<script setup>
import { ref, getCurrentInstance } from 'vue';
import Backer from '../components/backer.vue';

const storeName = ref('');
const storeAddress = ref('');
const storeCategory = ref('');
const phoneNumber = ref('');
const password = ref('');

// 获取全局 axios 实例
const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;

// 定义点击函数
function register() {
	// 这里处理注册逻辑
	console.log('注册信息:', {
		storeName: storeName.value,
		storeAddress: storeAddress.value,
		storeCategory: storeCategory.value,
		phoneNumber: phoneNumber.value,
		password: password.value
	});
}

</script>

<style scoped>
/********************* 总容器  *********************/
.wrapper {
	width: 100%;
	height: 100%;
	background-color: #eaeaea;
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

.wrapper .business-register-img img {
	width: 100vw;
	height: 40vw;
}

.wrapper .input-box {
	width: 95vw;
	height: 80vw;
	margin: 2.5vw;
	background-color: #ffffff;
	border-radius: 3vw;

}

.wrapper .input-box li {
	width: 95vw;
	height: 10vw;
	display: flex;
	margin: 2vw;
	align-items: center;
	justify-content: center;
}

.wrapper .input-box li .input-container {
	display: flex;
	align-items: center;
	margin-top: 2vw;
}

.input-label {
	flex: 1;
	color: #000000;
	pointer-events: none;
	font-size: 4vw;
	/* 让文字不会阻挡输入 */
}

.input-field {
	border: none;
	outline: none;
	flex: 3;
	padding-left: 10vw;
	/* 留出空间放置标签 */
	padding-right: 2vw;
	height: 10vw;
	width: 60vw;
	font-size: 3.5vw;
}

.wrapper .input-box .register-button {
	width: 90vw;
	height: 10vw;
	padding: 2.5vw;
	display: flex;
	justify-content: center;
	align-items: center;
}

.wrapper .input-box .register-button button {
	width: 80%;
	height: 10vw;
	border: none;
	/*去掉外轮廓线*/
	outline: none;
	border-radius: 8vw;
	background-color: #1677ff;
	color: #fff;
	font-size: 4.5vw;
	font-weight: 300;
}
</style>