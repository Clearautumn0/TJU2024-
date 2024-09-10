<template>
	<div class="wrapper">

		<!-- header部分 -->

		<header>
			<Backer></Backer>
			<p font-color="#fff">用户注册</p>
		</header>

		<!-- 表单部分 -->
		<ul class="form-box">
			<li>
				<div class="title">
					手机号码：
				</div>
				<div class="content">
					<input type="text" v-model="user.userId" placeholder="手机号码" @blur="validatetTel">
				</div>
			</li>
			<li>
				<div class="title">
					密码：
				</div>
				<div class="content">
					<input type="password" v-model="user.password" placeholder="密码" @blur="validatePw1">
				</div>
			</li>
			<li>
				<div class="title">
					确认密码：
				</div>
				<div class="content">
					<input type="password" v-model="confirmPassword" placeholder="确认密码" @blur="validatePw2">
				</div>
			</li>
			<li>
				<div class="title">
					用户名称：
				</div>
				<div class="content">
					<input type="text" v-model="user.userName" placeholder="用户名称" @blur="validateUs">
				</div>
			</li>
			<li>
				<div class="title">
					性别：
				</div>
				<div class="content" style="font-size: 3vw;">
					<input type="radio" v-model="user.userSex" value="1" style="width:6vw;height: 3.2vw;">男
					<input type="radio" v-model="user.userSex" value="0" style="width:6vw;height: 3.2vw;">女
				</div>
			</li>
		</ul>

		<div class="button-login">
			<button @click="register">注册</button>

		</div>
		<AlertPopup ref="alertPopup" :message="alertMessage" />
		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance
	} from 'vue';
	import Footer from '../components/Footer.vue';
	import Backer from '../components/backer.vue';
	import AlertPopup from '../components/AlertPopup.vue';
	import {
		useRouter
	} from 'vue-router';

	// 获取全局 axios 实例
	const instance = getCurrentInstance();
	const axios = instance?.appContext.config.globalProperties.$axios;

	const router = useRouter();
	const user = ref({
		userId: '',
		password: '',
		userName: '',
		userSex: 1
	});
	const confirmPassword = ref('');

	const alertMessage = ref('');

	// 显示弹窗的方法
	const showAlert = (message) => {
		alertMessage.value = message;
		const popup = instance?.refs.alertPopup;
		popup?.openPopup();
	};

	// 验证联系人姓名
	const validateUs = () => {
		if (user.value.userName === '') {
			showAlert('联系人姓名不能为空！');
			return false;
		}
		return true;
	};

	//验证密码首次
	const validatePw1 = () => {
		if (user.value.password === '') {
			showAlert('密码不能为空！');
			return false;
		}
		return true;
	}
	//验证密码二次
	const validatePw2 = () => {
		if (user.value.password !== confirmPassword.value) {
			showAlert('两次输入的密码不一致！');
			return false;
		}
		return true;
	}

	//验证手机号
	const validatetTel = () => {
		const phoneRegex = /^1[3-9]\d{9}$/;
		if (user.value.userId === '') {
			showAlert('手机号码不能为空！');
			return false;
		}
		// 验证手机号码是否符合规范
		if (!phoneRegex.test(user.value.userId)) {
			showAlert('请输入正确的手机号码！');
			return false;

		}
		axios.get(`users/${user.value.userId}`)
			.then(response => {
				if (response.data === 1) {
					user.value.userId = '';

					showAlert('此手机号码已存在！');
					return false;

				}
			})
			.catch(error => {
				console.error(error);
			});

		return true;
	};

	const register = () => {


		//点击“注册”按钮时的验证
		if (!validatePw1()) {
			return;
		}
		if (!validatePw2()) {
			return;
		}
		if (!validateUs()) {
			return;
		}
		if (!validatetTel()) {
			return;
		}

		// 注册请求
		axios.post('users', user.value)
			.then(response => {
				if (response.data > 0) {

					showAlert('注册成功！');
					router.go(-1);
				} else {
					showAlert('注册失败！');
				}
			})
			.catch(error => {
				console.error(error);
			});
	};

	// return {
	// 	user,
	// 	confirmPassword,
	// 	checkUserId,
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
		color: #666;
		background-color: #EEE;
		border-radius: 4px;

		border: none;
		outline: none;
		border: solid 1px #DDD;
	}
</style>