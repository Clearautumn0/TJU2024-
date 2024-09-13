<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<Backer></Backer>
			<p>在线支付</p>
		</header>

		<!-- 订单信息部分 -->
		<h3>订单信息：</h3>
		<div class="order-info">
			<p>
				{{ orders.business.businessName }}
				<i class="fa fa-caret-down" @click="detailetShow"></i>
			</p>
			<p>&#165;{{ orders.orderTotal }}</p>
		</div>

		<!-- 订单明细部分 -->
		<ul class="order-detailet" v-show="isShowDetailet">
			<li v-for="item in orders.list" :key="item.food.id">
				<p>{{ item.food.foodName }} x {{ item.quantity }}</p>
				<p>&#165;{{ (parseFloat(item.food.foodPrice * item.quantity).toFixed(2)) }}</p>
			</li>
			<li>
				<p>配送费</p>
				<p>&#165;{{ orders.business.deliveryPrice }}</p>
			</li>
		</ul>

		<!-- 支付方式部分 -->
		<ul class="payment-type">
			<li>
				<img src="../assets/alipay.png" />
				<i class="fa fa-check-circle"></i>
			</li>
			<li>
				<img src="../assets/wechat.png" />
			</li>
		</ul>
		<div class="payment-button">
			<button @click="payOrder" :disabled="timeUp">确认支付</button>
			<p class="daojishi">剩余支付时间: {{ minutes }}分{{ seconds }}秒</p><!-- 倒计时部分 -->
			<u class="quxiaodingdan" @click="cancelOrder">取消订单</u>
		</div>

		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script setup>
import { ref, reactive, onBeforeMount, onMounted, onBeforeUnmount, getCurrentInstance } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import Backer from '../components/backer.vue';
import Footer from '../components/Footer.vue';

const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;

const route = useRoute();
const router = useRouter();

const orders = reactive({
	business: {},
	orderTotal: 0,
	list: []
});
const isShowDetailet = ref(false);
const minutes = ref(0);
const seconds = ref(10);
const timeUp = ref(false);
let countdownInterval = null;

const detailetShow = () => {
	isShowDetailet.value = !isShowDetailet.value;
};

const fetchOrders = async () => {
	try {
		const response = await axios.get(`orders/${route.query.orderId}`);
		Object.assign(orders, response);
	} catch (error) {
		console.error(error);
	}
};

const payOrder = async () => {
	if (timeUp.value) return;
	try {
		await axios.patch(`orders/${route.query.orderId}`);
		toPayok();
	} catch (error) {
		console.error(error);
	}
};

const cancelOrder = async () => {
	try {
		await axios.delete(`orders/${route.query.orderId}`);
		router.push({ path: '/orderList' });
	} catch (error) {
		console.error(error);
	}
};

const deleteOrder = async () => {
	try {
		await axios.delete(`orders/${route.query.orderId}`);
		router.push({ path: '/orderList' });
	} catch (error) {
		console.error(error);
	}
};

const toPayok = () => {
	router.push({
		path: '/payok'
	});
};

const startCountdown = () => {
	countdownInterval = setInterval(() => {
		if (seconds.value === 0) {
			if (minutes.value > 0) {
				minutes.value--;
				seconds.value = 59;
			} else {
				clearInterval(countdownInterval);
				timeUp.value = true;
				// 计时结束后删除订单
				deleteOrder();
			}
		} else {
			seconds.value--;
		}
	}, 1000);
};

onMounted(() => {
	fetchOrders();
	startCountdown();
	history.pushState(null, null, document.URL);
	window.onpopstate = () => {
		router.push({ path: '/index' });
	};
});

onBeforeUnmount(() => {
	window.onpopstate = null;
	clearInterval(countdownInterval);
});
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
.wrapper h3 {
	margin-top: 12vw;
	box-sizing: border-box;
	padding: 4vw 4vw 0;

	font-size: 4vw;
	font-weight: 300;
	color: #999;
}

.wrapper .order-info {
	box-sizing: border-box;
	padding: 4vw;
	font-size: 4vw;
	color: #666;

	display: flex;
	justify-content: space-between;
	align-items: center;
}

.wrapper .order-info p:last-child {
	color: orangered;
}

/****************** 订单明细部分 ******************/
.wrapper .order-detailet {
	width: 100%;
}

.wrapper .order-detailet li {
	width: 100%;
	box-sizing: border-box;
	padding: 1vw 4vw;

	display: flex;
	justify-content: space-between;
	align-items: center;
}

.wrapper .order-detailet li p {
	font-size: 3vw;
	color: #666;
}

/****************** 支付方式部分 ******************/
.wrapper .payment-type {
	width: 100%;
}

.wrapper .payment-type li {
	width: 100%;
	box-sizing: border-box;
	padding: 4vw;

	display: flex;
	justify-content: space-between;
	align-items: center;
}

.wrapper .payment-type li img {
	width: 33vw;
	height: 8.9vw;
}

.wrapper .payment-type li .fa-check-circle {
	font-size: 5vw;
	color: #38CA73;
}

.wrapper .payment-button {
	width: 100%;
	box-sizing: border-box;
	padding: 4vw;
	display: flex;
	flex-direction: column;
	/* 垂直方向排列 */
	align-items: stretch;
	/* 拉伸按钮宽度以适应父容器 */
}

.wrapper .payment-button button {
	width: 100%;
	/* 按钮占满整个宽度 */
	height: 10vw;
	border: none;
	outline: none;
	font-size: 4vw;
	border-radius: 4px;
	background-color: #38CA73;
	color: #fff;
	margin-bottom: 2vw;
	/* 按钮与"取消订单"之间的间距 */
}

.quxiaodingdan {
	font-size: 4vw;
	align-self: flex-end;
	/* 右对齐 */
	color: #0097FF;
	/* 根据需要调整颜色 */
	cursor: pointer;

}

.daojishi {
	font-size: 5vw;
	align-self: flex-start;
	/* 右对齐 */
	color: black;
	/* 根据需要调整颜色 */
	cursor: pointer;
}


.wrapper .cancel-button {
	width: 20vw;
	box-sizing: border-box;
	padding: 2vw;
	margin-left: auto;
	margin-right: 6vw;
}

.wrapper .cancel-button button {
	width: 20vw;
	height: 10vw;
	border: none;
	/*去掉外轮廓线*/
	outline: none;
	margin-right: 3vw;
	border-radius: 4px;
	background-color: #00aaff;
	color: #fff;
}


/* 	.wrapper .cancel-button{
		width: 12vw;
		height: 12vw;
		margin-left: auto;
		margin-right: 3vw;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
	} */

/* 	.wrapper .cancel-button svg{
		width: 5vw;
		height: 5vw;
	} */

/* .wrapper .cancel-button p{
		margin-top: 2vw;
		font-size: 2.5vw;
		display: flex;
		align-items: center;
		justify-content: center;
	} */
</style>
