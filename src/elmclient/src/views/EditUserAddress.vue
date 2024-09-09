<template>
	<div class="wrapper">

		<!-- header部分 -->
		<header>
			<Backer></Backer>
			<p>编辑送货地址</p>
		</header>

		<!-- 表单部分 -->
		<ul class="form-box">
			<li>
				<div class="title">
					联系人：
				</div>
				<div class="content">
					<input type="text" v-model="deliveryAddress.contactName" placeholder="联系人姓名"
						@blur="validateContactName">
				</div>
			</li>
			<li>
				<div class="title">
					性别：
				</div>
				<div class="content" style="font-size: 3vw;">
					<input type="radio" v-model="deliveryAddress.contactSex" value="1" style="width:6vw;height:3.2vw;"
						checked>男
					<input type="radio" v-model="deliveryAddress.contactSex" value="0" style="width:6vw;height:3.2vw;">女
				</div>
			</li>
			<li>
				<div class="title">
					电话：
				</div>
				<div class="content">
					<input type="tel" v-model="deliveryAddress.contactTel" placeholder="电话" @blur="validateContactTel">
				</div>
			</li>
			<li>
				<div class="title">
					收货地址：
				</div>
				<div class="content">
					<input type="text" v-model="deliveryAddress.address" placeholder="收货地址" @blur="validateAddress">
				</div>
			</li>
		</ul>

		<div class="button-add">
			<button @click="editUserAddress">更新</button>
		</div>
		<AlertPopup ref="alertPopup" :message="alertMessage" />
		<!-- 底部菜单部分 -->		
		<Footer></Footer>
	</div>
</template>

<script setup>
import { ref, onMounted, getCurrentInstance } from 'vue';
import Backer from '../components/backer.vue';
import Footer from '../components/Footer.vue';
import AlertPopup from '../components/AlertPopup.vue';
// import axios from 'axios';
import { useRouter, useRoute } from 'vue-router';

// 获取全局 axios 实例
const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;


const router = useRouter();

const businessId = ref(null);
const daId = ref(null);
const user = ref({});
const deliveryAddress = ref({
	contactName: '',
	contactTel: '',
	address: '',
});

const fetchAddress = async () => {
	try {
		const response = await axios.get(`delivery-addresses/${daId.value}`);
		deliveryAddress.value = response;
	} catch (error) {
		console.error(error);
	}
};

const alertMessage = ref('');

// 显示弹窗的方法
const showAlert = (message) => {
	alertMessage.value = message;
	const popup = instance?.refs.alertPopup;
	popup?.openPopup();
};

// 验证联系人姓名
const validateContactName = () => {
	if (deliveryAddress.contactName === '') {
		showAlert('联系人姓名不能为空！');
		return false;
	}
	return true;
};

// 验证电话
const validateContactTel = () => {
	const phoneRegex = /^1[3-9]\d{9}$/;
	// console.log('输入的电话:', deliveryAddress.value.contactTel); // 调试输出
	// console.log('正则表达式匹配:', phoneRegex.test(deliveryAddress.value.contactTel)); // 调试输出

	/************这里得用 .value ********** /
	/                                      /
	/                                      /
	/************************************ */

	if (deliveryAddress.value.contactTel === '') {
		showAlert('联系人电话不能为空！');
		return false;
	}
	if (!phoneRegex.test(deliveryAddress.value.contactTel)) {
		showAlert('联系人电话格式不正确');
		return false;
	}
	return true;
};

// 验证收货地址
const validateAddress = () => {
	if (deliveryAddress.address === '') {
		showAlert('联系人地址不能为空！');
		return false;
	}
	return true;
};

const editUserAddress = async () => {
	// if (!deliveryAddress.value.contactName) {
	// 	alert('联系人姓名不能为空！');
	// 	return;
	// }
	// if (!deliveryAddress.value.contactTel) {
	// 	alert('联系人电话不能为空！');
	// 	return;
	// }
	// if (!deliveryAddress.value.address) {
	// 	alert('联系人地址不能为空！');
	// 	return;
	// }

	// 最后进行所有验证
	console.log('hello');
	if (!validateContactName()) { return; }
	if (!validateContactTel()) { return; }
	console.log('hello');

	if (!validateAddress()) { return; }
	// console.log('hello');

	try {
		const response = await axios.put('delivery-addresses', deliveryAddress.value);
		if (response.data > 0) {
			// alert('更新地址成功！');
			showAlert('更新地址成功！');//不跳转
			// const userConfirmed = confirm('更新地址成功\n点击确定跳转至地址列表页面');
			// if (userConfirmed) {
			// 	router.push({
			// 		path: '/userAddress',
			// 		query: {
			// 			businessId: businessId.value,
			// 		},
			// 	});
			// }
			// else{

			// }


		} else {
			// alert('更新地址失败！');
			showAlert('更新地址失败！');
		}
	} catch (error) {
		console.error(error);
	}
};

onMounted(() => {
	const route = useRoute();
	const router = useRouter();
	businessId.value = route.query.businessId;
	daId.value = route.query.daId;
	user.value = JSON.parse(sessionStorage.getItem('user'));
	fetchAddress();
});

// return {
// 	businessId,
// 	daId,
// 	user,
// 	deliveryAddress,
// 	editUserAddress,
// };

</script>

<style scoped>
/*************** 总容器 ***************/
.wrapper {
	width: 100%;
	height: 100%;
}

/*************** header ***************/
.wrapper header {
	width: 100%;
	height: 12vw;
	background-color: #0097FF;
	display: flex;
	justify-content: space-around;
	align-items: center;
	color: #fff;
	font-size: 4.8vw;
	position: fixed;
	left: 0;
	top: 0;
	/*保证在最上层*/
	z-index: 1000;
}

/*************** （表单信息） ***************/
.wrapper .form-box {
	width: 100%;
	margin-top: 12vw;
}

.wrapper .form-box li {
	box-sizing: border-box;
	padding: 4vw 3vw 0vw 3vw;
	display: flex;
}

.wrapper .form-box li .title {
	flex: 0 0 18vw;
	font-size: 3vw;
	font-weight: 700;
	color: #666;
}

.wrapper .form-box li .content {
	flex: 1;

	display: flex;
	align-items: center;
}

.wrapper .form-box li .content input {
	border: none;
	outline: none;
	width: 100%;
	height: 4vw;
	font-size: 3vw;
}

.wrapper .button-add {
	box-sizing: border-box;
	padding: 4vw 3vw 0vw 3vw;
}

.wrapper .button-add button {
	width: 100%;
	height: 10vw;
	font-size: 3.8vw;
	font-weight: 700;

	border: none;
	outline: none;
	border-radius: 4px;
	color: #fff;
	background-color: #38CA73;
}
</style>
