<template>
	<!-- 总容器 -->
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<Backer></Backer>
			<p class="title">上架商品</p>
		</header>

		<div class="input-box">
			<ul class="register-message">
				<li>
					<div class="input-container">
						<span class="input-label">食品名称</span>
						<input type="text" class="input-field" placeholder="请输入" v-model="foods.foodName"
							@blur="validateName" />
					</div>
				</li>
				<li>
					<div class="input-container">
						<span class="input-label">食品介绍</span>
						<input type="text" class="input-field" placeholder="请输入" v-model="foods.foodExplain"
							@blur="validateExplain" />
					</div>
				</li>
				<li>
					<div class="input-container">
						<span class="input-label">食品价格</span>
						<input type="text" class="input-field" placeholder="请输入" v-model="foods.foodPrice"
							@blur="validatePrice" />
					</div>
				</li>
			</ul>

			<div class="register-button">
				<button @click="storemessage">保存信息</button>
			</div>
		</div>

		<!-- 修改后的图片预览框，点击上传图片 -->
		<!-- <div class="inputbox2" @click="triggerFileInput">
            <div class="image-preview-box">
                <img :src="imageUrl || 'default-image-url.png'" alt="点击添加食品图片" class="image-preview" />
                <input type="file" @change="handleFileChange" accept="image/*" style="display: none;"
                    ref="imageInput" />
            </div>
        </div> -->
		<!-- 定义文件上传的 URL 地址，文件将会上传到这个地址。当前这个 URL 是一个模拟接口地址（可以替换为你自己的服务端接口） -->
		<!-- <div class="photo-box">
			<el-upload v-model:file-list="fileList" class="upload-demo"
				action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15" :on-preview="handlePreview"
				:on-remove="handleRemove" list-type="picture">
				<el-button type="primary">上传食品照片</el-button>
				<template #tip>
					<div class="el-upload__tip">
						jpg/png files with a size less than 500kb
					</div>
				</template>
</el-upload>
</div> -->
		<!-- 隐藏的文件输入框 -->
		<input type="file" ref="fileInput" @change="handleFileChange" accept="image/*" style="display: none;" />
		<div class="button-box">
			<!-- 绑定点击事件的 el-button -->
			<el-button type="primary" @click="triggerFileInput">
				上传食品图片
			</el-button>
		</div>
		<div class="userimg-box">
			<div v-if="base64Image" class="image-preview">
				<img :src="base64Image" alt="头像预览" />
			</div>
		</div>
	</div>
	<AlertPopup ref="alertPopup" :message="alertMessage" />
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
import AlertPopup from '../components/AlertPopup.vue';
import Backer from '../components/backer.vue';
import {
	getSessionStorage,
} from '../common.js';

const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;

const router = useRouter();
const route = useRoute();
const foods = ref({
	foodName: '',
	foodExplain: '',
	foodImg: '',
	foodPrice: '',
	businessId: 0
});

const alertMessage = ref('');
// const imageUrl = ref(''); // 用于存储图片预览URL
const base64Image = ref('');
const fileInput = ref(null);
const user = ref({});

// // 定义文件列表的响应式状态
// const fileList = ref([]);

// // 文件移除时的处理函数
// const handleRemove = (uploadFile, uploadFiles) => {
// 	console.log(uploadFile, uploadFiles);
// };

// // 文件预览时的处理函数
// const handlePreview = (file) => {
// 	console.log(file);
// };

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
			foods.value.foodImg = base64Image.value;
		};
		reader.readAsDataURL(file);
	}
};

// 显示弹窗的方法
const showAlert = (message) => {
	alertMessage.value = message;
	const popup = instance?.refs.alertPopup;
	popup?.openPopup();
};

const validateName = () => {
	if (foods.value.foodName == '') {
		showAlert('请输入食品名称！');
		return false;
	}
	return true;
};

const validateExplain = () => {
	if (foods.value.foodExplain == '') {
		showAlert('请输入食品介绍！');
		return false;
	}
	return true;
}

const validatePrice = () => {
	if (foods.value.foodPrice == '') {
		showAlert('请输入食品价格！');
		return false;
	}
	return true;
}

// 保存信息并上传图片
const storemessage = async () => {
	// console.log(foods.value.foodName);
	if (!validateName() || !validateExplain() || !validatePrice()) {
		return;
	}
	try {
		const response = await axios.post('foods', {
			foodName: foods.value.foodName,
			foodExplain: foods.value.foodExplain,
			foodPrice: foods.value.foodPrice,
			foodImg: foods.value.foodImg,
			businessId: foods.value.businessId,
		});
		if (response.data > 0) {
			showAlert('添加食品信息成功！');
		} else {
			showAlert('添加食品信息失败！');
		}
		router.back();
	} catch (error) {
		console.warn(error);
		showAlert('添加食品信息失败！');
	}
};

// 根据userId查询businessId
const getBusinessId = async () => {
	try {
		user.value = getSessionStorage('user') || {
			userName: '未登录',
			userId: ''
		};
		const response = await axios.get(`users/businessId/${user.value.userId}`);
		foods.value.businessId = response.data;
		// console.log(response);
	} catch (error) {
		console.error(error);
	}
}

onMounted(() => {
	getBusinessId();
});
</script>

<style scoped>
/* 总容器 */
.wrapper {
	width: 100%;
	height: 100%;
	background-color: #eaeaea;
}

/* header */
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

.wrapper .input-box {
	width: 95vw;
	height: 55vw;
	margin: 2.5vw;
	background-color: #ffffff;
	border-radius: 3vw;
}

.wrapper .input-box li {
	width: 95vw;
	height: 10vw;
	display: flex;
	margin: 2vw;
	align-items: center;
	justify-content: center;
}

.wrapper .input-box li .input-container {
	display: flex;
	align-items: center;
	margin-top: 2vw;
}

.input-label {
	flex: 1;
	color: #000000;
	pointer-events: none;
	font-size: 4vw;
	cursor: pointer;
	user-select: none;
}

.input-field {
	border: none;
	outline: none;
	flex: 3;
	padding-left: 10vw;
	padding-right: 2vw;
	height: 10vw;
	width: 60vw;
	font-size: 3.5vw;
}

.wrapper .input-box .register-button {
	width: 90vw;
	height: 10vw;
	padding: 2.5vw;
	display: flex;
	justify-content: center;
	align-items: center;
}

.wrapper .input-box .register-button button {
	width: 80%;
	height: 10vw;
	border: none;
	outline: none;
	border-radius: 8vw;
	background-color: #1677ff;
	color: #fff;
	font-size: 4.5vw;
	font-weight: 300;
}

.wrapper .inputbox2 {
	width: 95vw;
	height: 50vw;
	margin: 2.5vw;
	background-color: #ffffff;
	border-radius: 3vw;
	display: flex;
	justify-content: center;
	align-items: center;
	overflow: hidden;
}

.wrapper .image-preview-box {
	width: 86vw;
	height: 40vw;
	border: 1px solid gray;
	padding: 3vw;
	display: flex;
	justify-content: center;
	align-items: center;
	overflow: hidden;
}

.wrapper .image-preview {
	max-width: 100%;
	height: auto;
	object-fit: cover;
}

.wrapper .photo-box {
	width: 95vw;
	margin: 2.5vw;
	height: 40vw;
	display: flex;
}

.wrapper .button-box button {
	width: 40vw;
	height: 10vw;
	margin-left: 2vw;
}
</style>