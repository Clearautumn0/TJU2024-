<template>
	<div class="wrapper">

		<!-- header部分 -->
		<header>
			<p>用户登陆</p>
		</header>

		<!-- 表单部分 -->
		<ul class="form-box">
			<li>
				<div class="title">
					手机号码：
				</div>
				<div class="content">
					<input type="text" v-model="userId" placeholder="手机号码" @blur="validateTel">
				</div>
			</li>
			<li>
				<div class="title">
					密码：
				</div>
				<div class="content">
					<input type="password" v-model="password" placeholder="密码" @blur="validatePw">
				</div>
			</li>
		</ul>

		<div class="button-login">
			<button @click="login">登陆</button>
		</div>
		<div class="button-register">
			<button @click="register">去注册</button>
		</div>

		<AlertPopup ref="alertPopup" :message="alertMessage" />

		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script setup>
import { ref, getCurrentInstance } from 'vue';

import Footer from '../components/Footer.vue';
import AlertPopup from '../components/AlertPopup.vue';

import JSEncrypt from 'jsencrypt';
import { useRouter } from 'vue-router';
import { setSessionStorage } from '../common.js'; // 确保你有一个setSessionStorage方法

// 获取全局 axios 实例
const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;

const router = useRouter();
const userId = ref('');
const password = ref('');

const alertMessage = ref('');

// 显示弹窗的方法
const showAlert = (message) => {
	alertMessage.value = message;
	const popup = instance?.refs.alertPopup;
	popup?.openPopup();
};

//联系人电话
const validateTel = () => {
	if (userId.value === '') {
		showAlert('手机号码不能为空！');
		return false;
	}
	return true;

}
//联系人密码
const validatePw = () => {
	if (password.value === '') {
		showAlert('密码不能为空！');
		return false;
	}
	return true;
}

const login = async () => {
	if (!validateTel()) {
		return;
	}
	if (!validatePw()) {
		return;
	}

	// 获取公钥并加密密码
	try {
		const keyResponse = await axios.get('/public-key');
		const publicKey = keyResponse.data;

		// 使用公钥加密密码
		const encryptor = new JSEncrypt();
		encryptor.setPublicKey(publicKey);
		const encryptedPassword = encryptor.encrypt(password.value);
		if (!encryptedPassword) {
			showAlert('密码加密失败，请稍后再试！');
			return;
		}

		// 发送加密后的密码进行登录
		const loginResponse = await axios.post('users/login', {
			userId: userId.value,
			password: encryptedPassword
		});
		const user = loginResponse;
		if (user == null || user == '') {
			showAlert('用户名或密码不正确！');
		} else {
			// sessionStorage有容量限制，为了防止数据溢出，所以不将userImg数据放入session中
			user.userImg = '';
			setSessionStorage('user', user);
			sessionStorage.setItem('token', user.token);
			router.go(-1);
		}
	} catch (error) {
		console.error('登录失败：', error);
	}
};

const register = () => {
	router.push({
		path: 'register'
	});
};

// return {
// 	userId,
// 	password,
// 	login,
// 	register
// };


</script>

<style scoped>
/****************** 总容器 ******************/
.wrapper {
	width: 100%;
	height: 100%;
}

/****************** header部分 ******************/
.wrapper header {
	width: 100%;
	height: 12vw;
	background-color: #0097FF;
	color: #fff;
	font-size: 4.8vw;

	position: fixed;
	left: 0;
	top: 0;
	z-index: 1000;

	display: flex;
	justify-content: center;
	align-items: center;
}

/****************** 表单部分 ******************/
.wrapper .form-box {
	width: 100%;
	margin-top: 12vw;
}

.wrapper .form-box li {
	box-sizing: border-box;
	padding: 4vw 3vw 0 3vw;
	display: flex;
	align-items: center;
}

.wrapper .form-box li .title {
	flex: 0 0 18vw;
	font-size: 3vw;
	font-weight: 700;
	color: #666;
}

.wrapper .form-box li .content {
	flex: 1;
}

.wrapper .form-box li .content input {
	border: none;
	outline: none;
	width: 100%;
	height: 4vw;
	font-size: 3vw;
}

.wrapper .button-login {
	width: 100%;
	box-sizing: border-box;
	padding: 4vw 3vw 0 3vw;
}

.wrapper .button-login button {
	width: 100%;
	height: 10vw;
	font-size: 3.8vw;
	font-weight: 700;
	color: #fff;
	background-color: #38CA73;
	border-radius: 4px;

	border: none;
	outline: none;
}

.wrapper .button-register {
	width: 100%;
	box-sizing: border-box;
	padding: 4vw 3vw 0 3vw;
}

.wrapper .button-register button {
	width: 100%;
	height: 10vw;
	font-size: 3.8vw;
	font-weight: 700;
	/*与上面登陆按钮不同的只有颜色、背景色、边框不同*/
	color: #666;
	background-color: #EEE;
	border: solid 1px #DDD;
	border-radius: 4px;

	border: none;
	outline: none;
}

/****************** 底部菜单部分 ******************/
.wrapper .footer {
	width: 100%;
	height: 14vw;
	border-top: solid 1px #DDD;
	background-color: #fff;

	position: fixed;
	left: 0;
	bottom: 0;

	display: flex;
	justify-content: space-around;
	align-items: center;
}

.wrapper .footer li {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;

	color: #999;
	user-select: none;
	cursor: pointer;
}

.wrapper .footer li p {
	font-size: 2.8vw;
}

.wrapper .footer li i {
	font-size: 5vw;
}
</style>