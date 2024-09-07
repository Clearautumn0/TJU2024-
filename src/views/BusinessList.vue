<template>
	<div class="wrapper">

		<!-- header部分 -->
		<header>
			<!-- 返回按钮 -->
			<!-- <img @click="goBack" src="../assets/backer.png" alt="返回" class="back-button"> -->
			<Backer></Backer>
			<p>商家列表</p>
		</header>

		<!-- 商家列表部分 -->
		<ul class="business">
			<li v-for="item in businessArr" @click="toBusinessInfo(item.businessId)">
				<div class="business-img">
					<img :src="item.businessImg">
					<div class="business-img-quantity" v-show="item.quantity > 0">{{ item.quantity }}</div>
				</div>
				<div class="business-info">
					<h3>{{ item.businessName }}</h3>
					<p>&#165;{{ item.starPrice }}起送 | &#165;{{ item.deliveryPrice }}配送</p>
					<p>{{ item.businessExplain }}</p>
				</div>
			</li>
			<li class="empty-li">

				<div class="empty-message">已经到底了...</div>

			</li>
		</ul>

		<!-- 底部菜单部分 -->
		<Footer></Footer>

	</div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import axios from 'axios'; // 确保你在项目中安装并引入了 axios
import Footer from '../components/Footer.vue';
import Backer from '../components/backer.vue';

// 获取路由参数
const route = useRoute();
const router = useRouter();

// 声明响应式状态
const orderTypeId = ref(route.query.orderTypeId);
const businessArr = reactive([]);
const user = ref({});
const businessId = ref(route.query.businessId); // 初始化 businessId

// 获取用户信息
const getSessionStorage = key => {
  return JSON.parse(window.sessionStorage.getItem(key));
};

const listCart = async () => {
  try {
    const response = await axios.get('carts/user', {
      params: {
        businessId: businessId.value, // 需要确保你定义了 businessId 变量
        userId: user.value.userId
      }
    });
    const cartArr = response.data;

    // 遍历所有食品列表
    for (let businessItem of businessArr) {
      businessItem.quantity = 0;
      for (let cartItem of cartArr) {
        if (cartItem.businessId == businessItem.businessId) {
          businessItem.quantity += cartItem.quantity;
        }
      }
    }
    businessArr.sort(); // 确保你定义了 sort 方法
  } catch (error) {
    console.error(error);
  }
};

const fetchBusinessData = async () => {
  try {
    const response = await axios.get(`businesses/orderType/${orderTypeId.value}`);
    businessArr.splice(0, businessArr.length, ...response.data); // 使用 splice 进行响应式更新
    if (user.value != null) {
      listCart();
    }
  } catch (error) {
    console.error(error);
  }
};

// 路由导航
const toBusinessInfo = (businessId) => {
  router.push({ path: '/businessInfo', query: { businessId } });
};

const goBack = () => {
  router.go(-1);
};

// 在组件挂载时调用
onMounted(() => {
  user.value = getSessionStorage('user');
  fetchBusinessData();
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

/* 返回按钮样式 */
.wrapper header .back-button {
	position: absolute;
	left: 2vw;
	top: 2vw;
	width: 8vw;
	height: 8vw;
	cursor: pointer;
}

/****************** 商家列表部分 ******************/
.wrapper .business {
	width: 100%;
	margin-top: 12vw;
	margin-bottom: 14vw;
}

.wrapper .business li {
	width: 100%;
	box-sizing: border-box;
	padding: 2.5vw;
	border-bottom: solid 1px #DDD;
	user-select: none;
	cursor: pointer;

	display: flex;
	align-items: center;
}

.wrapper .business li .business-img {
	/*这里设置为相当定位，成为business-img-quantity元素的父元素*/
	position: relative;
}

.wrapper .business li .business-img img {
	width: 20vw;
	height: 20vw;
}

.wrapper .business li .business-img .business-img-quantity {
	width: 5vw;
	height: 5vw;
	background-color: red;
	color: #fff;
	font-size: 3.6vw;
	border-radius: 2.5vw;

	display: flex;
	justify-content: center;
	align-items: center;

	/*设置成绝对定位，不占文档流空间*/
	position: absolute;
	right: -1.5vw;
	top: -1.5vw;
}

.wrapper .business li .business-info {
	margin-left: 3vw;
}

.wrapper .business li .business-info h3 {
	font-size: 3.8vw;
	color: #555;
}

.wrapper .business li .business-info p {
	font-size: 3vw;
	color: #888;
	margin-top: 2vw;
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
</style>
