<template>
	<div class="wrapper">

		<!-- header部分 -->
		<header>
			<p>我的订单</p>
		</header>

		<!-- 订单列表部分 -->
		<h3>未支付订单信息：</h3>
		<ul class="order">
			<li v-for="item in unpaidOrders">
				<div class="order-info">
					<p>
						{{ item.business.businessName }}
						<i class="fa fa-caret-down" @click="detailetShow(item)"></i>
					</p>
					<div class="order-info-right">
						<p>&#165;{{ item.orderTotal }}</p>
						<div class="order-info-right-icon" @click="goToPayment(item.orderId)">去支付</div>
					</div>
				</div>
				<ul class="order-detailet" v-show="item.isShowDetailet">
					<li v-for="odItem in item.list">
						<p>{{ odItem.food.foodName }} x {{ odItem.quantity }}</p>
						<p>&#165;{{ parseFloat((odItem.food.foodPrice * odItem.quantity).toFixed(2)) }}</p>
					</li>
					<li>
						<p>配送费</p>
						<p>&#165;{{ item.business.deliveryPrice }}</p>
					</li>
				</ul>
			</li>
			<li class="empty-li">
				<div class="empty-message">已经到底了...</div>
			</li>
		</ul>

		<h3>已支付订单信息：</h3>
		<ul class="order">
			<li v-for="item in paidOrders">
				<div class="order-info">
					<p>
						{{ item.business.businessName }}
						<i class="fa fa-caret-down" @click="detailetShow(item)"></i>
					</p>
					<div class="order-info-right">
						<p>&#165;{{ item.orderTotal }}</p>
					</div>
				</div>
				<ul class="order-detailet" v-show="item.isShowDetailet">
					<li v-for="odItem in item.list">
						<p>{{ odItem.food.foodName }} x {{ odItem.quantity }}</p>
						<p>&#165;{{ parseFloat((odItem.food.foodPrice * odItem.quantity).toFixed(2)) }}</p>
					</li>
					<li>
						<p>配送费</p>
						<p>&#165;{{ item.business.deliveryPrice }}</p>
					</li>
				</ul>
			</li>

			<li class="empty-li">
				<div class="empty-message">已经到底了...</div>
			</li>
		</ul> -->

		<!-- 底部菜单部分 -->
		<Footer></Footer>

	</div>
</template>
<script setup>
	import {
		ref,
		reactive,
		onMounted,
		getCurrentInstance,
		computed
	} from 'vue';
	import {
		useRouter
	} from 'vue-router'; // 导入 useRouter
	import Footer from '../components/Footer.vue';

	// 获取全局 axios 实例
	const instance = getCurrentInstance();
	const axios = instance?.appContext.config.globalProperties.$axios;

	const orderArr = ref([]);
	const user = reactive({});
	const router = useRouter(); // 使用 useRouter 获取路由实例

	// 获取用户信息和订单数据
	const getUserOrders = () => {
		const storedUser = JSON.parse(sessionStorage.getItem('user')); // Vue 3 中 sessionStorage 的直接访问方式
		Object.assign(user, storedUser);

		axios.get(`orders/user/${user.userId}`)
			.then(response => {
				let result = response;
				result.forEach(orders => {
					orders.isShowDetailet = false;
				});
				orderArr.value = result;
			})
			.catch(error => {
				console.error(error);
			});
	};

	// 跳转到支付页面
	const goToPayment = (orderId) => {
		router.push({
			path: '/payment',
			query: {
				orderId: orderId
			}
		});
	};

	// 切换订单详情的显示状态
	const detailetShow = (orders) => {
		orders.isShowDetailet = !orders.isShowDetailet;
	};
	// 过滤已支付订单
	const paidOrders = computed(() => {
		return orderArr.value.filter(order => order.orderState === 1);
	});

	// 过滤未支付订单
	const unpaidOrders = computed(() => {
		return orderArr.value.filter(order => order.orderState === 0);
	});


	onMounted(() => {
		getUserOrders();
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

	/****************** 历史订单列表部分 ******************/
	.wrapper h3 {
		margin-top: 12vw;
		box-sizing: border-box;
		padding: 4vw;
		font-size: 4vw;
		font-weight: 300;
		color: #999;
	}

	.wrapper .order {
		width: 100%;
	}

	.wrapper .order li {
		width: 100%;
	}

	.wrapper .order li .order-info {
		box-sizing: border-box;
		padding: 2vw 4vw;
		font-size: 4vw;
		color: #666;

		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .order li .order-info .order-info-right {
		display: flex;
	}

	.wrapper .order li .order-info .order-info-right .order-info-right-icon {
		background-color: #f90;
		color: #fff;
		border-radius: 3px;
		margin-left: 2vw;
		user-select: none;
		cursor: pointer;
	}

	.wrapper .order li .order-detailet {
		width: 100%;
	}

	.wrapper .order li .order-detailet li {
		width: 100%;
		box-sizing: border-box;
		padding: 1vw 4vw;
		color: #666;
		font-size: 3vw;

		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.empty-li {
		width: 200vw;
		/* 设置矩形的宽度 */
		height: 50vw;
		/* 设置矩形的高度 */
		/* border: 2px solid #000; */
		/* 添加一个2像素的黑色边框 */
		background-color: transparent;
		/* 背景色为透明 */
		list-style: none;
		/* 移除li的默认列表样式 */
		/* margin: 10px; */
		/* 添加一些外边距让矩形之间有间隔 */
	}

	.empty-message {
		position: absolute;
		top: -25;
		/* 将文字放置在顶部 */
		left: 50%;
		/* 从左边开始居中 */
		transform: translateX(-50%);
		/* 修正left 50%带来的偏移，使其完全居中 */
		font-size: 4vw;
		/* 设置字体大小 */
		color: #000;
		/* 设置文字颜色 */
	}
</style>