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
			<button>确认支付</button>
		</div>
		<div class="cancel-button">
			<!-- <svg t="1725880463932" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2281" width="200" height="200"><path d="M960.253652 703.916984c8.637728 8.636704 8.637728 22.64065 0.001023 31.277354l-281.871739 281.871739c-8.636704 8.636704-22.64065 8.636704-31.278377-0.001023l0 0c-8.636704-8.635681-8.636704-22.64065 0-31.276331l281.871739-281.871739C937.614026 695.279257 951.616948 695.279257 960.253652 703.916984L960.253652 703.916984z" fill="#272536" p-id="2282"></path><path d="M960.254675 1017.065054c-8.636704 8.637728-22.64065 8.637728-31.277354 0.002047l-281.871739-281.871739c-8.636704-8.636704-8.636704-22.641673 0.001023-31.278377l0 0c8.635681-8.636704 22.64065-8.636704 31.276331 0l281.871739 281.871739C968.892403 994.425428 968.892403 1008.429373 960.254675 1017.065054L960.254675 1017.065054z" fill="#272536" p-id="2283"></path><path d="M732.397131 248.980631c0 12.216229-9.901511 22.11774-22.115694 22.11774l-398.626883 0c-12.215206 0-22.116717-9.902534-22.116717-22.11774l0 0c0-12.214183 9.902534-22.115694 22.116717-22.115694l398.626883 0C722.49562 226.863914 732.397131 236.766448 732.397131 248.980631L732.397131 248.980631z" fill="#272536" p-id="2284"></path><path d="M732.397131 492.752634c0 12.216229-9.901511 22.11774-22.115694 22.11774l-398.626883 0c-12.215206 0-22.116717-9.902534-22.116717-22.11774l0 0c0-12.214183 9.902534-22.115694 22.116717-22.115694l398.626883 0C722.49562 470.63694 732.397131 480.539474 732.397131 492.752634L732.397131 492.752634z" fill="#272536" p-id="2285"></path><path d="M522.024819 721.127971c0 12.216229-9.901511 22.11774-22.115694 22.11774l-188.254571 0c-12.215206 0-22.116717-9.902534-22.116717-22.11774l0 0c0-12.214183 9.902534-22.115694 22.116717-22.115694l188.254571 0C512.124332 699.012278 522.024819 708.914812 522.024819 721.127971L522.024819 721.127971z" fill="#272536" p-id="2286"></path><path d="M836.267826 0.455371l-648.536676 0c-71.938426 0-130.464377 58.525951-130.464377 130.464377l0 762.109338c0 71.938426 58.525951 130.464377 130.464377 130.464377l355.275539 0c0.473791 0.029676 0.949628 0.050142 1.430582 0.050142 12.446473 0 22.537296-10.090822 22.537296-22.537296 0-12.008498-9.392928-21.819958-21.230534-22.496363-0.00614-0.014326-0.01228-0.027629-0.017396-0.040932l-357.995487 0c-47.110971 0-85.438904-38.327934-85.438904-85.438904l0-762.109338c0-47.110971 38.327934-85.438904 85.438904-85.438904l648.536676 0c47.110971 0 85.438904 38.327934 85.438904 85.438904l0 467.611024c-0.029676 0.471744-0.049119 0.946558-0.049119 1.425466 0 12.446473 10.089799 22.537296 22.537296 22.537296 12.446473 0 22.537296-10.090822 22.537296-22.537296l0-469.036489C966.732204 58.982346 908.206252 0.455371 836.267826 0.455371z" p-id="2287"></path></svg> -->
			<button>取消订单</button>
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
	}

	.wrapper .payment-button button {
		width: 100%;
		height: 10vw;
		border: none;
		/*去掉外轮廓线*/
		outline: none;
		border-radius: 4px;
		background-color: #38CA73;
		color: #fff;
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

