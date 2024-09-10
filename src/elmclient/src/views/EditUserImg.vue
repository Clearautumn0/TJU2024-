<template>
	<!--  总容器 -->
	<div class="wrapper">
		<!--  header部分 -->
		<header>
			<Backer></Backer>
			<div class="setUserImg">
				<p>设置头像</p>
			</div>
			<div @click="submitImage" class="reposit">
				<p>保存</p>
			</div>
		</header>
		<div class="color-box"></div>
		<div class="name-box">
			<input type="file" @change="handleFileChange" accept="image/*">
		</div>
		<div class="bottom">
			<!-- 条件渲染，仅在选择文件后显示预览 -->
			<div v-if="base64Image" class="image-preview">
				<img :src="base64Image" alt="头像预览" />
			</div>
		</div>
	</div>
</template>

<script setup>
import {
	useRouter
} from 'vue-router';
import Backer from '../components/backer.vue';
import {
	ref,
	onMounted,
	getCurrentInstance
} from 'vue';
import {
	getSessionStorage,
	setLocalStorage
} from '../common.js';

const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;

const router = useRouter();
const user = ref({});
const base64Image = ref('');

onMounted(() => {
	user.value = getSessionStorage('user') || { userName: '未登录', userId: '' };
});

const handleFileChange = (event) => {
	const file = event.target.files[0];
	if (file) {
		const reader = new FileReader();
		reader.onload = (e) => {
			base64Image.value = e.target.result;
		};
		reader.readAsDataURL(file);
	}
};

const submitImage = async () => {
	try {
		if (base64Image.value) {
			const response = await axios.put('users', {
				userId: user.value.userId,
				userImg: base64Image.value
			});
			if (response.data === 1) {
				setLocalStorage(`userImg${user.value.userId}`, base64Image.value);
			}
			router.go(-1);
		}
	} catch (error) {
		console.error(error);
	}
}

</script>

<style scoped>
/********************* 总容器  *********************/
.wrapper {
	width: 100%;
	height: 100%;
}

/********************* header  *********************/
.wrapper header {
	width: 100%;
	height: 12vw;
	background-color: #ffffff;
	color: #e2e2e2;
	font-size: 4.5vw;

	display: flex;
	align-items: center;
	justify-content: space-between;
}

.wrapper header p {
	color: #000000;
}

.wrapper header .back-button {
	width: 5vw;
	height: 5vw;
}

.wrapper header .setUserImg {
	font-weight: 600;
}

.wrapper header .reposit {
	margin-right: 3.5vw;
	font-weight: 525;
}

.wrapper .color-box {
	width: 100%;
	height: 3vw;
	background-color: #ebebeb;
}

.wrapper .name-box {
	width: 100%;
	height: 12vw;
	color: #ffffff;

}

.wrapper .name-box input {
	border: none;
	outline: none;
	width: 100%;
	height: 11vw;
	font-size: 4vw;
	margin-left: 2vw;
}

.wrapper .bottom {
	width: 100%;
	height: 100%;
	background-color: #ebebeb;
	z-index: -1;
}
</style>