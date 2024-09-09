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
			<li v-for="item in orders.list">
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
				<img src="../assets/alipay.png">
				<i class="fa fa-check-circle"></i>
			</li>
			<li>
				<img src="../assets/wechat.png">
			</li>
		</ul>
		<div class="payment-button">
			<button @click="toPayok">确认支付</button>
			<u class="quxiaodingdan" @click="deleteOrder">取消订单</u>
		</div>

		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script setup>
import {
	ref,
	reactive,
	onBeforeMount,
	onMounted,
	onBeforeUnmount,
	getCurrentInstance
} from 'vue';
import {
	useRouter,
	useRoute
} from 'vue-router';
import Backer from '../components/backer.vue';
import Footer from '../components/Footer.vue';

// 获取全局 axios 实例
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

const toPayok = async () => {
	try {
		const response = await axios.patch(`orders/${route.query.orderId}`);
		if (response.data > 0) {//这里标记已支付后直接进入支付成功页面
			router.push({ path: '/payok' });
		}
	} catch (error) {
		console.error(error);
	}

};

const deleteOrder = async () => {
	try {
		const response = await axios.delete(`orders/${route.query.orderId}`);
		if (response.data > 0) {//删成功后就直接到OrderList页面
			// alert('ok');
			router.push({ path: '/orderList' });
		}
	} catch (error) {
		console.error(error);
	}
};

onBeforeMount(() => {
	fetchOrders();
});

onMounted(() => {
	// 这里的代码是实现：一旦路由到在线支付组件，就不能回到订单确认组件。
	// 先将当前url添加到history对象中
	history.pushState(null, null, document.URL);
	// popstate事件能够监听history对象的变化
	window.onpopstate = () => {
		router.push({
			path: '/index'
		});
	};
});

onBeforeUnmount(() => {
	window.onpopstate = null;
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
	border-radius: 4px;
	background-color: #38CA73;
	color: #fff;
	margin-bottom: 2vw;
	/* 按钮与"取消订单"之间的间距 */
}

.quxiaodingdan {
	align-self: flex-end;
	/* 右对齐 */
	color: #0097FF;
	/* 根据需要调整颜色 */
	cursor: pointer;

}
</style>