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
						<select class="input-field" v-model="storeCategoryInt">
							<!-- 循环创建十个选项 -->
							<option v-for="(category, index) in categories" :key="category" :value="index + 1">
								{{ category }}
							</option>
						</select>
					</div>
					<hr>
				</li>
				
				<li>
					<div @click="toggleinputbusiness" class="input-container">
						<span class="input-label">商家图片</span>
						<button>请上传图片</button>
						<!-- <input type="text" class="input-field" placeholder="请填写门店实际位置" v-model="storeAddress"> -->
					</div>
					<hr>
				</li>
			</ul>
			
			<div class=""></div>

			<div class="register-button">
				<!-- <button>立即入驻</button> -->
				 <!-- <el-button type="primary" round>立即入驻</el-button> -->
				<button @click="registerBusiness">立即入驻</button>
			</div>
			
			<div class="bottom"></div>
			
			<div v-if="isAvatarOpen" class="overlay"></div>
			<!--阴影背景  v-show="totalQuantity != 0" 表示有food的时候显示购物车-->
			
			<div v-if="isAvatarOpen" class="Avatar-update">
				<div class="button-box">
					<ul>
						<li>
							<p>拍一张</p>
							<el-divider class="custom-divider" />
						</li>
						<li>
							<p @click="triggerFileInput">上传商家头像</p>
							<el-divider class="custom-divider" />
							<input ref="fileInput" type="file" accept="image/*" style="display: none;"
								@change="handleFileChange" />
						</li>
						<div class="driver-box"></div>
						<li class="cancel-box" @click="toggleinputbusiness">
							<p>取消</p>
							<el-divider class="custom-divider" />
						</li>
					</ul>
					<!-- 绑定点击事件的 el-button -->
					<!-- <el-button type="primary" @click="triggerFileInput">
						上传头像
					</el-button> -->
				</div>
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
const isAvatarOpen = ref(false);

const storeName = ref('');
const storeAddress = ref('');
const storeCategoryInt = ref(null);
const store = ref('');
const user = ref({});

const businesses = ref({
	businessName: '',
	businessAddress: '',
	orderTypeId: 0,
	businessImg: '',
});

const base64Image = ref('');
const fileInput = ref(null);
// const phoneNumber = ref('');
// const password = ref('');
const categories = ref([
	'美食', '早餐', '跑腿代购', '汉堡披萨', '甜品饮品',
	'速食简餐', '地方小吃', '米粉面馆', '包子粥铺', '炸鸡炸串'
]);

// 触发隐藏的文件输入框点击事件
const triggerFileInput = () => {
	fileInput.value.click();
};

// 处理文件选择
const handleFileChange = (event) => {
	const file = event.target.files[0];
	if (file) {
		const reader = new FileReader();
		reader.onload = (e) => {
			base64Image.value = e.target.result;
			businesses.value.businessImg = base64Image.value;
		};
		reader.readAsDataURL(file);
	}
};

const toggleinputbusiness = () => {
	isAvatarOpen.value = !isAvatarOpen.value;
}

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
			orderTypeId: storeCategoryInt.value,
			businessImg: businesses.value.businessImg,
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
	height: 80vw;
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


.wrapper .input-box li .input-container button{
	border: none;
	outline: none;
	color: #757575;
	background-color: #fff;
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
	padding-left: 10vw;
	/* 留出空间放置标签 */
	padding-right: 2vw;
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

/****************** 阴影背景 ******************/
.wrapper .overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;

	background-color: rgba(0, 0, 0, 0.5);
	z-index: 1000;
}

.wrapper .button-box {
	position: fixed;
	bottom: 0;
	left: 0;
	/* 固定在视口底部 */
	width: 100vw;
	height: 40vw;
	border-radius: 5vw 5vw 0 0;
	display: flex;
	align-items: center;
	justify-content: center;
	background-color: #ffffff;
	z-index: 1100;
}

.wrapper .button-box li {
	position: relative;
	width: 100vw;
	height: 13vw;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-wrap: wrap;
}

.wrapper .button-box ul .driver-box {
	width: 100vw;
	height: 1.5vw;
	background-color: #eaeaea;
}

.wrapper .button-box li .custom-divider {
	margin: 0vw;
	padding: 0;
	position: absolute;
	bottom: 0;
}

.wrapper .button-box li p {
	font-size: 4.5vw;
	font-weight: 500;
}

.wrapper .button-box .cancel-box p {
	color: #b1b1b1;
}

</style>