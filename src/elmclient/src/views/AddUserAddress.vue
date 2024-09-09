<template>
	<div class="wrapper">

		<!-- header部分 -->
		<header>
			<Backer></Backer>
			<p>新增送货地址</p>
		</header>

		<!-- 表单部分 -->
		<ul class="form-box">
			<li>
				<div class="title">
					联系人：
				</div>
				<div class="content">
					<input type="text" v-model="deliveryAddress.contactName" placeholder="联系人姓名">
				</div>
			</li>
			<li>
				<div class="title">
					性别：
				</div>
				<div class="content" style="font-size: 3vw;">
					<input type="radio" v-model="deliveryAddress.contactSex" value="1" style="width:6vw;height:3.2vw;">男
					<input type="radio" v-model="deliveryAddress.contactSex" value="0" style="width:6vw;height:3.2vw;">女
				</div>
			</li>
			<li>
				<div class="title">
					电话：
				</div>
				<div class="content">
					<input type="tel" v-model="deliveryAddress.contactTel" placeholder="电话">
				</div>
			</li>
			<li>
				<div class="title">
					收货地址：
				</div>
				<div class="content">
					<input type="text" v-model="deliveryAddress.address" placeholder="收货地址">
				</div>
			</li>
		</ul>

		<div class="button-add">
			<button @click="addUserAddress">保存</button>
		</div>

		<!-- 底部菜单部分 -->
		<Footer></Footer>

	</div>
</template>

<script setup>
import { ref, reactive, onMounted, getCurrentInstance } from 'vue';
import { useRouter, useRoute } from 'vue-router';
// import axios from 'axios';
import Footer from '../components/Footer.vue';
import Backer from '../components/backer.vue';
import Address_manage_backer from '../components/address_manage_backer.vue';
import { getSessionStorage } from '../common.js'; // Adjust if necessary

// 获取全局 axios 实例
const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;

const router = useRouter();
const route = useRoute();

const businessId = ref(route.query.businessId);
const user = ref({});
const deliveryAddress = reactive({
	contactName: '',
	contactSex: 1,
	contactTel: '',
	address: ''
});

onMounted(() => {
	user.value = getSessionStorage('user');
});

const addUserAddress = async () => {

	const phoneRegex = /^1[3-9]\d{9}$/; // Validate phone number format
	
	if (deliveryAddress.contactName === '') {
		alert('联系人姓名不能为空！');
		return;
	}
	if (deliveryAddress.contactTel === '') {
		alert('联系人电话不能为空！');
		return;
	}
	if (!phoneRegex.test(deliveryAddress.contactTel)) {
		alert('联系人电话格式不正确');
		return;
	}
	if (deliveryAddress.address === '') {
		alert('联系人地址不能为空！');
		return;
	}

	deliveryAddress.userId = user.value.userId;

	try {
		const response = await axios.post('delivery-addresses', deliveryAddress);
		if (response.data > 0) {
			alert('新增地址成功！');
			// Navigate to another page or handle success
			router.push({ path: '/userAddress', query: { businessId: businessId.value } });
		} else {
			alert('新增地址失败！');
		}
	} catch (error) {
		console.error('Error adding address:', error);
	}
};

// return {
// 	deliveryAddress,
// 	addUserAddress
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
