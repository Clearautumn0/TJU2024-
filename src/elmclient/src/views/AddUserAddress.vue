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
					<input type="text" v-model="deliveryAddress.contactName" placeholder="联系人姓名"
						@blur="validateContactName">
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
			<button @click="addUserAddress">保存</button>
		</div>

		<!-- 弹窗提示 -->
		<AlertPopup ref="alertPopup" :message="alertMessage" />

		<!-- 底部菜单部分 -->
		<Footer></Footer>

	</div>
</template>

<script setup>
import { ref, reactive, onMounted, getCurrentInstance } from 'vue';
import { useRouter, useRoute } from 'vue-router';

import Footer from '../components/Footer.vue';
import Backer from '../components/backer.vue';
import AlertPopup from '../components/AlertPopup.vue';
import { getSessionStorage } from '../common.js';


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
	if (deliveryAddress.contactTel === '') {
		showAlert('联系人电话不能为空！');
		return false;
	} else if (!phoneRegex.test(deliveryAddress.contactTel)) {
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

// 保存地址时的验证和提交
const addUserAddress = async () => {
	// 最后进行所有验证
	// console.log('hello');
	if (!validateContactName()) { return; }
	if (!validateContactTel()) { return; }
	if (!validateAddress()) { return; }
	// console.log('hello');

	if (
		deliveryAddress.contactName !== '' &&
		deliveryAddress.contactTel !== '' &&
		/^1[3-9]\d{9}$/.test(deliveryAddress.contactTel) &&
		deliveryAddress.address !== ''
	) {
		deliveryAddress.userId = user.value.userId;

		try {
			const response = await axios.post('delivery-addresses', deliveryAddress);
			if (response.data > 0) {
				showAlert('新增地址成功！');
				// alert('ok');
				// router.push({ path: '/userAddress', query: { businessId: businessId.value } });
				router.go(-1);
			} else {
				showAlert('新增地址失败！');
			}
		} catch (error) {
			console.error('Error adding address:', error);
		}
	}
};

// 初始化用户数据
onMounted(() => {
	user.value = getSessionStorage('user');
});

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
	justify-content: center;
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
