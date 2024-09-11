<template>
	<!--  总容器 -->
	<div class="wrapper">
		<!--  header部分 -->
		<header>
			<Backer></Backer>
			<p class="title">门店信息录入</p>
		</header>

		<div class="business-register-img">
			<img src="../assets/registerbusiness.png">
		</div>

		<div class="input-box">
			<ul class="register-message">
				<li>
					<div class="input-container">
						<span class="input-label">门店名称</span>
						<input type="text" class="input-field" placeholder="需与门牌照名称一致" v-model="storeName">
					</div>
					<hr>
				</li>
				<li>
					<div class="input-container">
						<span class="input-label">门店地址</span>
						<input type="text" class="input-field" placeholder="请填写门店实际位置" v-model="storeAddress">
					</div>
					<hr>
				</li>
				<!-- <li>
					<div class="input-container">
						<span class="input-label">商家分类</span>
						<input type="text" class="input-field" placeholder="请输入分类" v-model="storeCategory">
					</div>
				</li> -->
				<li>
					<div class="input-container">
						<span class="input-label">商家分类</span>
						<!-- 使用 v-model 绑定选中的值到 storeCategory 变量 -->
						<select class="input-field2" v-model="storeCategoryInt">
							<!-- 循环创建十个选项 -->
							<option v-for="(category, index) in categories" :key="category" :value="index + 1">
								{{ category }}
							</option>
						</select>
					</div>
					<hr>
				</li>
				<!-- <li>
					<div class="input-container">
						<span class="input-label">手机号码</span>
						<input type="text" class="input-field" placeholder="请输入" v-model="phoneNumber">
					</div>
				</li>
				<li>
					<div class="input-container">
						<span class="input-label">设置密码</span>
						<input type="text" class="input-field" placeholder="请输入">		
						<input type="text" class="input-field" placeholder="请输入" v-model="password">
					</div>
				</li> -->
			</ul>

			<div class="register-button">
				<!-- <button>立即入驻</button> -->
				 <!-- <el-button type="primary" round>立即入驻</el-button> -->
				<button @click="registerBusiness">立即入驻</button>
			</div>
		</div>
	</div>

</template>

<script setup>
import { ref, getCurrentInstance, computed } from 'vue';
import Backer from '../components/backer.vue';
import { getSessionStorage, getLocalStorage, setSessionStorage } from '../common.js';
import { useRouter } from 'vue-router';

// 获取全局 axios 实例
const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;


const router = useRouter();


const storeName = ref('');
const storeAddress = ref('');
const storeCategoryInt = ref(null);
const user = ref({});
// const phoneNumber = ref('');
// const password = ref('');
const categories = ref([
	'美食', '早餐', '跑腿代购', '汉堡披萨', '甜品饮品',
	'速食简餐', '地方小吃', '米粉面馆', '包子粥铺', '炸鸡炸串'
]);

const storeCategory = computed({
	get: () => categories.value[storeCategoryInt.value - 1],
	set: (category) => {
		const index = categories.value.indexOf(category);
		if (index !== -1) {
			storeCategoryInt.value = index + 1;
		}
	}
});

// 定义点击函数
const registerBusiness = async () => {
	try {
		user.value = getSessionStorage('user');
		const response = await axios.post(`businesses/${user.value.userId}`, {
			businessName: storeName.value,
			businessAddress: storeAddress.value,
			orderTypeId: storeCategoryInt.value
		});
		// console.log(response);
		if(response.data>=2){
			user.value.authorization = 2;
			setSessionStorage('user', user.value);
		}

		router.back();
		// router.push({ path: '/person' });


	} catch (error) {
		console.error('Failed to register business:', error);
	}
};


</script>

<style scoped>
/********************* 总容器  *********************/
.wrapper {
	width: 100%;
	height: 100%;
	background-color: #eaeaea;
}

/********************* header  *********************/
.wrapper header {
	width: 100%;
	height: 12vw;
	background-color: #ffffff;
	font-weight: 800;
	color: #e2e2e2;
	font-size: 4.5vw;

	display: flex;
	align-items: center;
	justify-content: center;
}

.wrapper header p {
	color: #000000;
	font-weight: 600;
}

.wrapper .business-register-img img {
	width: 100vw;
	height: 40vw;
}

.wrapper .input-box {
	width: 95vw;
	height: 65vw;
	margin: 2.5vw;
	background-color: #ffffff;
	border-radius: 3vw;

}

.wrapper .input-box li {
	width: 95vw;
	height: 15vw;
	display: flex;
	align-items: center;
	justify-content: center;
	position: relative;
}

.wrapper .input-box li .input-container {
	width: 90vw;
	height: 15vw;
	display: flex;
	align-items: center;
	padding: 2vw;
}

.input-label {
	flex: 1;
	color: #000000;
	pointer-events: none;
	font-size: 4vw;
	/* 让文字不会阻挡输入 */
}

.input-field {
	border: none;
	outline: none;
	flex: 3;
	padding-left: 5vw;
	/* 留出空间放置标签 */
	padding-right: 1vw;
	height: 10vw;
	width: 60vw;
	font-size: 3.5vw;
}

.input-field2 {
	border: none;
	outline: none;
	flex: 3;
	padding-left: 3.8vw;
	/* 留出空间放置标签 */
	padding-right: 1vw;
	height: 10vw;
	width: 60vw;
	font-size: 3.5vw;
}

.wrapper .input-box .register-button {
	width: 90vw;
	height: 12vw;
	padding: 2.5vw;
	display: flex;
	justify-content: center;
	align-items: center;
}

.wrapper .input-box .register-button button {
	width: 80%;
	height: 12vw;
	border: none;
	/*去掉外轮廓线*/
	outline: none;
	border-radius: 8vw;
	background-color: #1677ff;
	color: #fff;
	font-size: 4.5vw;
	font-weight: 300;
}

/* *****分割线样式******* */


.wrapper .input-box ul li hr {
	width: calc(100% - 4vw);
	/* 减去左右的边距 */
	position: absolute;
	bottom: 0;
	left: 2vw;
	/* 确保 <hr> 与左侧对齐 */
	background-color: #e7e7e7;
	border: none;
	height: 0.05vw;
	margin: 0;
}
</style>