<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<Backer></Backer>
			<p>确认订单</p>
		</header>

		<!-- 订单信息部分 -->
		<div class="order-info">
			<h5>订单配送至：</h5>
			<div class="order-info-address" @click="toUserAddress">
				<p>{{ deliveryaddress?.address || '请选择送货地址' }}</p>
				<i class="fa fa-angle-right"></i>
			</div>
			<p>{{ deliveryaddress?.contactName || '无联系人' }}{{ sexFilter(deliveryaddress?.contactSex) }} {{ user.userId
				}}</p>
		</div>

		<h3>{{ business.businessName }}</h3>

		<!-- 订单明细部分 -->
		<ul class="order-detailed">
			<li v-for="item in cartArr" :key="item.food.foodId">
				<div class="order-detailed-left">
					<img :src="item.food.foodImg" />
					<p>{{ item.food.foodName }} x {{ item.quantity }}</p>
				</div>
				<p>&#165;{{ parseFloat((item.food.foodPrice * item.quantity).toFixed(2)) }}</p>
			</li>
		</ul>
		<div class="order-deliveryfee">
			<p>配送费</p>
			<p>&#165;{{ business.deliveryPrice || 0 }}</p>
		</div>

		<!-- 合计部分 -->
		<div class="total">
			<div class="total-left">
				&#165;{{ parseFloat(totalPrice.toFixed(2)) }}
			</div>
			<div class="total-right" @click="toPayment">
				去支付
			</div>
		</div>
	</div>
	<!-- 弹窗 -->
	<AlertPopup ref="alertPopup" :message="alertMessage" />

</template>

<script setup>
import { ref, reactive, computed, onMounted, getCurrentInstance } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import Backer from '../components/backer.vue';

import AlertPopup from '../components/AlertPopup.vue';

import { getSessionStorage, getLocalStorage } from '../common.js';

// 获取全局 axios 实例
const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;


const router = useRouter();
const route = useRoute();

const businessId = ref(route.query.businessId);
const business = reactive({});
const user = ref({});
const cartArr = ref([]);
const deliveryaddress = ref({});

const totalPrice = computed(() => {
	let total = 0;
	cartArr.value.forEach(item => {
		console.log(item.food);
		if(item.food!=null){
			total += item.food.foodPrice * item.quantity;
		}

	});
	total += business.deliveryPrice || 0; // 防止 deliveryPrice 未定义时报错
	return parseFloat(total.toFixed(2));
});


const alertMessage = ref('');

// 显示弹窗的方法
const showAlert = (message) => {
	alertMessage.value = message;
	const popup = instance?.refs.alertPopup;
	popup?.openPopup();
};


const fetchBusinessData = async () => {
	try {
		const businessResponse = await axios.get(`businesses/${businessId.value}`);
		business.value = businessResponse;
	} catch (error) {
		console.error('Failed to fetch business data:', error);
	}
};

const fetchCartData = async () => {
	try {
		const cartResponse = await axios.get('carts/user', {
			params: {
				businessId: businessId.value,
				userId: user.value.userId
			}
		});
		cartArr.value = cartResponse;
		// console.log(cartArr.value);
	} catch (error) {
		console.error('Failed to fetch cart data:', error);
	}
};

const sexFilter = (value) => {
	return value === 1 ? '先生' : '女士';
};

onMounted(() => {
	user.value = getSessionStorage('user');
	deliveryaddress.value = getLocalStorage(user.value.userId);

	fetchBusinessData();
	fetchCartData();
});

const toUserAddress = () => {
	// if()
	router.push({ path: '/userAddress', query: { businessId: businessId.value } });
};

const toPayment = async () => {
	if (!deliveryaddress.value) {
		showAlert('请选择送货地址！');
		return;
	}
	try {
		const response = await axios.post('orders', {
			userId: user.value.userId,
			businessId: businessId.value,
			daId: deliveryaddress.value.daId,
			orderTotal: totalPrice.value
		});
		const orderId = response.data;
		if (orderId > 0) {
			router.push({ path: '/payment', query: { orderId } });
		} else {
			showAlert('创建订单失败！');
		}
	} catch (error) {
		console.error('Failed to create order:', error);
	}
};

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

/****************** 订单信息部分 ******************/
.wrapper .order-info {
	/*注意这里，不设置高，靠内容撑开。因为地址有可能折行*/
	width: 100%;
	margin-top: 12vw;
	background-color: #0097EF;
	box-sizing: border-box;
	padding: 2vw;
	color: #fff;
}

.wrapper .order-info h5 {
	font-size: 3vw;
	font-weight: 300;
}

.wrapper .order-info .order-info-address {
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;

	font-weight: 700;
	user-select: none;
	cursor: pointer;
	margin: 1vw 0;
}

.wrapper .order-info .order-info-address p {
	width: 90%;
	font-size: 5vw;
}

.wrapper .order-info .order-info-address i {
	font-size: 6vw;
}

.wrapper .order-info p {
	font-size: 3vw;
}

.wrapper h3 {
	box-sizing: border-box;
	padding: 3vw;
	font-size: 4vw;
	color: #666;
	border-bottom: solid 1px #DDD;
}

/****************** 订单明细部分 ******************/
.wrapper .order-detailed {
	width: 100%;
}

.wrapper .order-detailed li {
	width: 100%;
	height: 16vw;
	box-sizing: border-box;
	padding: 3vw;
	color: #666;

	display: flex;
	justify-content: space-between;
	align-items: center;
}

.wrapper .order-detailed li .order-detailed-left {
	display: flex;
	align-items: center;
}

.wrapper .order-detailed li .order-detailed-left img {
	width: 10vw;
	height: 10vw;
}

.wrapper .order-detailed li .order-detailed-left p {
	font-size: 3.5vw;
	margin-left: 3vw;
}

.wrapper .order-detailed li p {
	font-size: 3.5vw;
}

.wrapper .order-deliveryfee {
	width: 100%;
	height: 16vw;
	box-sizing: border-box;
	padding: 3vw;
	color: #666;
	display: flex;
	justify-content: space-between;
	align-items: center;
	font-size: 3.5vw;
}

/****************** 订单合计部分 ******************/
.wrapper .total {
	width: 100%;
	height: 14vw;

	position: fixed;
	left: 0;
	bottom: 0;

	display: flex;
}

.wrapper .total .total-left {
	flex: 2;
	background-color: #505051;
	color: #fff;
	font-size: 4.5vw;
	font-weight: 700;
	user-select: none;

	display: flex;
	justify-content: center;
	align-items: center;
}

.wrapper .total .total-right {
	flex: 1;
	background-color: #38CA73;
	color: #fff;
	font-size: 4.5vw;
	font-weight: 700;
	user-select: none;
	cursor: pointer;

	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
