<template>
	<div class="wrapper">

		<!-- header部分 -->
		<header>
			<Backer></Backer>
			<p>商家信息</p>
		</header>

		<!-- 商家logo部分 -->
		<div class="business-logo">
			<img :src="business.businessImg">
		</div>

		<!-- 商家信息部分 -->
		<div class="business-info">
			<h1>{{ business.businessName }}</h1>
			<p>&#165;{{ business.starPrice }}起送 &#165;{{ business.deliveryPrice }}配送</p>
			<p>{{ business.businessExplain }}</p>
		</div>

		<!-- 食品列表部分 -->
		<ul class="food">
			<li v-for="(item, index) in foodArr">
				<div class="food-left">
					<img :src="item.foodImg">
					<div class="food-left-info">
						<h3>{{ item.foodName }}</h3>
						<p>{{ item.foodExplain }}</p>
						<p>&#165;{{ item.foodPrice }}</p>
					</div>
				</div>
				<div class="food-right">
					<div>
						<i class="fa fa-minus-circle" @click="minus(index)" v-show="item.quantity != 0"></i>
					</div>
					<p><span v-show="item.quantity != 0">{{ item.quantity }}</span></p>
					<div>
						<i class="fa fa-plus-circle" @click="add(index)"></i>
					</div>
				</div>
			</li>
			<li class="empty-li">
				<div class="empty-message">已经到底了...</div>
			</li>
		</ul>

		<!-- 购物车部分 -->
		<div class="cart">
			<div class="cart-left">
				<div class="cart-left-icon" @click="toggleCart"
					:style="totalQuantity == 0 ? 'background-color:#505051;' : 'background-color:#3190E8;'">
					<i class="fa fa-shopping-cart"></i>
					<div class="cart-left-icon-quantity" v-show="totalQuantity != 0">{{ totalQuantity }}</div>
				</div>
				<div class="cart-left-info">
					<p>&#165;{{ totalPrice }}</p>
					<p>另需配送费{{ business.deliveryPrice }}元</p>
				</div>
			</div>
			<div class="cart-right">
				<!-- 不够起送费 -->
				<div class="cart-right-item" v-show="totalSettle < business.starPrice"
					style="background-color: #535356;cursor: default;">
					&#165;{{ business.starPrice }}起送
				</div>
				<!-- 达到起送费 -->
				<div class="cart-right-item" @click="toOrder" v-show="totalSettle >= business.starPrice">
					去结算
				</div>
			</div>

			<div v-if="isCartOpen" class="overlay" v-show="totalQuantity != 0"></div>
			<!--阴影背景  v-show="totalQuantity != 0" 表示有food的时候显示购物车-->

			<!-- 购物车详情部分 -->
			<div v-if="isCartOpen" class="cart-details" v-show="totalQuantity != 0">
				<!-- <transition name="slide-fade"> -->
				<div class="cart-top" v-show="totalQuantity != 0">
					<!---v-show="totalQuantity != 0" 表示有food的时候显示购物车上面的两个-->
					<div class="cart-topfirst">
						<p>已享优惠</p>
					</div>
					<div class="cart-topsecond">
						<p>已选商品</p>
						<hr />
					</div>
				</div>
				<ul>

					<li v-for="(item, index) in foodArr" v-show="item.quantity !=0">

						<div class="cart-leftbox">
							<div class="foodimg-box">
								<img :src="item.foodImg">
							</div>
							<div class="foodinfo-box"><span>{{ item.foodName }}</span>
								<p>&#165;{{ item.foodPrice }}</p>
							</div>
						</div>
						<div class="cart-rightbox">
							<i class="fa fa-minus-circle" @click="minus(index)" v-show="item.quantity != 0"></i>
							<span>{{ item.quantity }}</span>
							<i class="fa fa-plus-circle" @click="add(index)"></i>
						</div>
					</li>
				</ul>
				<!-- </transition> -->
			</div>
		</div>

		<!-- 验证码模态框 -->
		<div v-if="showCaptchaModal" class="captcha-modal">
			<div class="captcha-container">
				<h3>请输入验证码</h3>
				<div class="captcha-box">
					<input type="text" v-model="captchaInput" placeholder="请输入验证码" />
					<img :src="captchaUrl" alt="验证码" class="captcha-image" />
				</div>		
				<button @click="verifyCaptcha">提交</button>
				<button @click="closeCaptcha">取消</button>
			</div>
		</div>

	</div>
	<!-- 弹窗 -->
	<AlertPopup ref="alertPopup" :message="alertMessage" />
</template>

<script setup>
import {
	ref,
	onMounted,
	computed,
	getCurrentInstance
} from 'vue'
import {
	useRoute,
	useRouter
} from 'vue-router'
// import axios from 'axios'
import Backer from '../components/backer.vue'
import AlertPopup from '../components/AlertPopup.vue';
import {
	getSessionStorage,
	getLocalStorage
} from '../common';

// 获取全局 axios 实例
const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;

const showCaptchaModal = ref(false);
const captchaInput = ref('');
const captchaUrl = ref('');
const pendingIndex = ref(null);
const isCaptchaVerified = ref(false); // 添加一个状态来跟踪验证码是否验证通过


// 获取路由实例和路由参数
const route = useRoute();
const router = useRouter();

// 定义响应式数据
const businessId = ref(route.query.businessId);
const business = ref({});
const foodArr = ref([]);
const user = ref({});
const isCartOpen = ref(false);

const businessImage = ref('');

const initialize = async () => {
	try {
		user.value = getSessionStorage('user');

		// 根据 businessId 查询商家信息
		const businessResponse = await axios.get(`businesses/${businessId.value}`);
		business.value = businessResponse;

		// 根据 businessId 查询食品信息
		const foodResponse = await axios.get(`foods/business/${businessId.value}`);
		foodArr.value = foodResponse;
		foodArr.value.forEach(food => {
			food.quantity = 0;
		});

		// 如果已登录，那么需要去查询购物车中是否已经选购了某个食品
		if (user.value) {
			await listCart();
		}
	} catch (error) {
		console.error('Error initializing:', error);
	}
};
// 在组件创建时调用
onMounted(() => {
	initialize();
	businessImage.value = getLocalStorage(`businessImg${business.value.businessId}`);
	if (user.value !== null) {
		listCart(0);
	}
});

// 定义方法
async function listCart() {
	try {
		const response = await axios.get('carts/user', {
			params: {
				businessId: businessId.value,
				userId: user.value.userId
			}
		});
		const cartArr = response;
		foodArr.value.forEach(foodItem => {
			foodItem.quantity = 0;
			cartArr.forEach(cartItem => {
				if (cartItem.foodId === foodItem.foodId) {
					foodItem.quantity = cartItem.quantity;
				}
			});
		});
		foodArr.value.sort();
	} catch (error) {
		console.error('Error fetching cart:', error);
	}
}
const alertMessage = ref('');

// 显示弹窗的方法
const showAlert = (message) => {
	alertMessage.value = message;
	const popup = instance?.refs.alertPopup;
	popup?.openPopup();
};

const add = async (index) => {
	try {
		if (!user.value) {
			router.push('/login');
			return;
		}
		if (foodArr.value[index].quantity == 0) {
			saveCart(index);

		} else if (foodArr.value[index].quantity >= 50 && !isCaptchaVerified.value) {
			// 显示验证码模态框
			showCaptchaModal.value = true;
			pendingIndex.value = index;

			// 获取验证码图片的URL
			captchaUrl.value = 'http://localhost:8080/elm/captcha';
		} else {
			updateCart(index, 1);
		}
	} catch {
		console.error(error);
	}


};
// 验证验证码
const verifyCaptcha = async () => {
	if (!captchaInput.value) {
		showAlert('请输入验证码');
		return;
	}
	// alert(captchaInput.value);
	try {
		// alert(captchaInput.value);
		const response = await axios.post('/captcha', {
			captcha: captchaInput.value
		}, {
			withCredentials: true
		});


		if (response && response.success) { // 确保 response.data 不为 undefined
			isCaptchaVerified.value = true; // 如果验证码验证通过，设置 isCaptchaVerified 为 true
			updateCart(pendingIndex.value, 1);
			closeCaptcha();
		} else {
			showAlert('验证码错误，请重试！');
		}
	} catch (error) {
		console.error('Error verifying captcha:', error);
		showAlert('验证码验证失败，请稍后再试。');


	}
};
// 关闭验证码模态框
const closeCaptcha = () => {
	showCaptchaModal.value = false;
	captchaInput.value = ''; // 清空验证码输入框
	pendingIndex.value = null;
};
const minus = (index) => {
	if (!user.value) {
		router.push('/login');
		return;
	}

	if (foodArr.value[index].quantity > 1) {
		updateCart(index, -1);
	} else {
		removeCart(index);
	}
}

const saveCart = async (index) => {
	try {
		const response = await axios.post('carts', {
			businessId: businessId.value,
			userId: user.value.userId,
			foodId: foodArr.value[index].foodId
		});
		if (response.data == 1) {
			foodArr.value[index].quantity = 1;
			foodArr.value.sort();
		} else {
			showAlert('向购物车中添加食品失败！');
		}
	} catch (error) {
		console.error(error);

	}
}
const toggleCart = () => {
	if (totalQuantity.value !== 0) {
		isCartOpen.value = !isCartOpen.value;
	} else {
		showAlert('当前购物车为空');

	}
}
const updateCart = async (index, num) => {
	try {
		const response = await axios.put('carts', {
			businessId: businessId.value,
			userId: user.value.userId,
			foodId: foodArr.value[index].foodId,
			quantity: foodArr.value[index].quantity + num
		});
		if (response.data === 1) {
			
			foodArr.value[index].quantity += num;
			foodArr.value.sort();
		} else {
			showAlert('updateCart: 向购物车中更新食品失败！');
		}
	} catch (error) {
		console.error(error);

	}
}

const removeCart = async (index) => {
	try {
		const response = await axios.delete('carts', {
			data: {
				businessId: businessId.value,
				userId: user.value.userId,
				foodId: foodArr.value[index].foodId
			}
		});
		if (response.data == 1) {
			foodArr.value[index].quantity = 0;
			foodArr.value.sort();
		} else {
			showAlert('从购物车中删除食品失败！');
		}
	} catch (error) {
		console.error(error);
	}
}

const toOrder = () => {
	router.push({
		path: '/orders',
		query: {
			businessId: business.value.businessId
		}
	})
}



// 计算属性
const totalPrice = computed(() => {
	return parseFloat(foodArr.value.reduce((total, item) => total + item.foodPrice * item.quantity, 0)
		.toFixed(2))
})

const totalQuantity = computed(() => {
	return foodArr.value.reduce((quantity, item) => quantity + item.quantity, 0)
})

const totalSettle = computed(() => {
	return totalPrice.value + business.value.deliveryPrice
})
</script>

<style scoped>
.wrapper .captcha-modal {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: rgba(0, 0, 0, 0.5);
	display: flex;
	justify-content: center;
	align-items: center;
}

	.wrapper .captcha-modal .captcha-container {
		background-color: white;
		padding: 2vw;
		border-radius: 5vw;
		box-shadow: 0 1vw 5vw rgba(0, 0, 0, 0.2);
		display: flex;
		justify-content: center;
		align-items: center;
		flex-wrap: wrap;
	}
	
	.wrapper .captcha-modal .captcha-container .captcha-box{
		width: 100%;
		height: 30vw;
		display: flex;
		align-items: center;
		margin-top: 5vw;
		margin: 2vw;
	}

	.wrapper .captcha-modal .captcha-container .captcha-image {
		width: 40vw;
		height: 20vw;
		margin-left: 2vw;
	}
	
	.wrapper .captcha-modal .captcha-container input{
		border: 1px solid #cdcdcd; /* 细线边框，颜色为黑色 */
		outline: none;
		width: 50%;
		height: 12vw;
		font-size: 4vw;
		padding-left: 2vw;
	}

	.wrapper .captcha-modal .captcha-container button {
		border: none;
		outline: none;
		border-radius: 5vw;
		width: 35vw;
		height: 10vw;
		margin: 3vw;
		background-color: #328fce;
		color: #ffffff;
		font-size: 4vw;
		font-weight: 500;
	}

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

/****************** 商家logo部分 ******************/
.wrapper .business-logo {
	width: 100%;
	height: 35vw;
	/*使用上外边距避开header部分*/
	margin-top: 12vw;

	display: flex;
	justify-content: center;
	align-items: center;
}

.wrapper .business-logo img {
	width: 40vw;
	height: 30vw;
	border-radius: 5px;
}

/****************** 商家信息部分 ******************/
.wrapper .business-info {
	width: 100%;
	height: 20vw;

	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.wrapper .business-info h1 {
	font-size: 5vw;
}

.wrapper .business-info p {
	font-size: 3vw;
	color: #666;
	margin-top: 1vw;
}

/****************** 食品列表部分 ******************/
.wrapper .food {
	width: 100%;
	/*使用下外边距避开footer部分*/
	margin-bottom: 14vw;
}

.wrapper .food li {
	width: 100%;
	box-sizing: border-box;
	padding: 2.5vw;
	user-select: none;

	display: flex;
	justify-content: space-between;
	align-items: center;
}

.wrapper .food li .food-left {
	display: flex;
	align-items: center;
}

.wrapper .food li .food-left img {
	width: 20vw;
	height: 20vw;
}

.wrapper .food li .food-left .food-left-info {
	margin-left: 3vw;
}

.wrapper .food li .food-left .food-left-info h3 {
	font-size: 3.8vw;
	color: #555;
}

.wrapper .food li .food-left .food-left-info p {
	font-size: 3vw;
	color: #888;
	margin-top: 2vw;
}

.wrapper .food li .food-right {
	width: 16vw;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.wrapper .food li .food-right .fa-minus-circle {
	font-size: 5.5vw;
	color: #999;
	cursor: pointer;
}

.wrapper .food li .food-right p {
	font-size: 3.6vw;
	color: #333;
}

.wrapper .food li .food-right .fa-plus-circle {
	font-size: 5.5vw;
	color: #0097EF;
	cursor: pointer;
}

/****************** 购物车部分 ******************/
.wrapper .cart {
	width: 100%;
	height: 14vw;

	position: fixed;
	left: 0;
	bottom: 0;

	display: flex;
	z-index: 1100;
}

.wrapper .cart .cart-left {
	flex: 2;
	background-color: #505051;
	display: flex;

	position: relative;
	z-index: 1100;
}

.wrapper .cart .cart-left .cart-left-icon {
	width: 16vw;
	height: 16vw;
	box-sizing: border-box;
	border: solid 1.6vw #444;
	border-radius: 8vw;
	background-color: #3190E8;
	font-size: 7vw;
	color: #fff;

	display: flex;
	justify-content: center;
	align-items: center;

	margin-top: -4vw;
	margin-left: 3vw;

	position: relative;
	z-index: 1100;
}

.wrapper .cart .cart-left .cart-left-icon-quantity {
	width: 5vw;
	height: 5vw;
	border-radius: 2.5vw;
	background-color: red;
	color: #fff;
	font-size: 3.6vw;

	display: flex;
	justify-content: center;
	align-items: center;

	position: absolute;
	right: -1.5vw;
	top: -1.5vw;
}

.wrapper .cart .cart-left .cart-left-info p:first-child {
	font-size: 4.5vw;
	color: #fff;
	margin-top: 1vw;
}

.wrapper .cart .cart-left .cart-left-info p:last-child {
	font-size: 2.8vw;
	color: #AAA;
}

.wrapper .cart .cart-right {
	flex: 1;
	position: relative;
	z-index: 1100;
}

/*达到起送费时的样式*/
.wrapper .cart .cart-right .cart-right-item {
	width: 100%;
	height: 100%;
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

.empty-li {
	width: 200vw;
	/* 设置矩形的宽度 */
	height: 30vw;
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

/*不够起送费时的样式（只有背景色和鼠标样式的区别）*/
/*
	.wrapper .cart .cart-right .cart-right-item{
		width: 100%;
		height: 100%;
		background-color: #535356;
		color: #fff;
		font-size: 4.5vw;
		font-weight: 700;
		user-select: none;
		
		display: flex;
		justify-content: center;
		align-items: center;
	}
	*/
   /****************** 阴影背景 ******************/
.cart .overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;

	background-color: rgba(0, 0, 0, 0.5);
	z-index: 1000;
}


/****************** 购物车详情部分 ******************/

.wrapper .cart-details {
	width: 100vw;
	max-height: 80vw;
	background-color: #fff;
	position: fixed;
	z-index: 1000;
	bottom: 14vw;
	/* left: 0; */
	/* box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.1); */
	overflow-y: auto;
}

.wrapper .cart-details .cart-top {
	width: 100vw;
	height: 20vw;
	background-color: #fff;
}

.wrapper .cart-details .cart-topfirst {
	width: 100vw;
	height: 10vw;
	background-color: #fdf6e1;
	display: flex;
	align-items: center;
	border-top-left-radius: 3vw;
	border-top-right-radius: 3vw;
}

.wrapper .cart-details .cart-topfirst p {
	font-size: 3.5vw;
	font-weight: 550;
	margin-left: 2vw;
}

.wrapper .cart-details .cart-topsecond {
	width: 100vw;
	height: 10vw;
	background-color: #fff;
	display: flex;
	align-items: center;
	position: relative;
}

.wrapper .cart-details .cart-topsecond p {
	font-size: 3.5vw;
	font-weight: 550;
	margin-left: 2vw;
}

.wrapper .cart-details .cart-topsecond hr {
	width: 96vw;
	margin-left: 2vw;
	margin-right: 2vw;

	position: absolute;
	bottom: 0;
	/* 将分割线对齐到父元素的底部 */

	background-color: #e2e2e2;
	border: none;
	/* 移除默认的边框 */
	height: 0.02vw;
	/* 设置分割线的高度 */
	margin-bottom: 0;
}

.wrapper .cart-details li {
	width: 100%;
	box-sizing: border-box;
	padding: 2.5vw;
	user-select: none;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.wrapper .cart-details li .cart-leftbox {
	display: flex;
	align-items: center;
}

.wrapper .cart-details li .cart-leftbox .foodimg-box img {
	width: 15vw;
	height: 15vw;
	border-radius: 2vw;
}

.wrapper .cart-details li .cart-leftbox .foodinfo-box {
	margin-left: 2vw;
}

.wrapper .cart-details li .cart-leftbox .foodinfo-box p {
	font-size: 3.5vw;
	color: #e84d00;
	margin-top: 4vw;
}

.wrapper .cart-details li .cart-leftbox .foodinfo-box span {
	font-size: 3.5vw;
}

.wrapper .cart-details li .cart-rightbox .fa-minus-circle,
li .fa-plus-circle {
	font-size: 5vw;
	color: #0097EF;
	cursor: pointer;
	margin: 0 2vw;
}

.wrapper .cart-details li .cart-rightbox {
	font-size: 4vw;
}
	
</style>