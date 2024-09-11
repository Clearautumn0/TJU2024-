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
                        <input type="text" class="input-field" placeholder="请输入" v-model="foods.foodName" @blur="validateName" />
                    </div>
                </li>
                <li>
                    <div class="input-container">
                        <span class="input-label">食品介绍</span>
                        <input type="text" class="input-field" placeholder="请输入" v-model="foods.foodExplain" @blur="validateExplain" />
                    </div>
                </li>
                <li>
                    <div class="input-container">
                        <span class="input-label">食品价格</span>
                        <input type="text" class="input-field" placeholder="请输入" v-model="foods.foodPrice" @blur="validatePrice" />
                    </div>
                </li>
            </ul>

            <div class="register-button">
                <button @click="storemessage">保存信息</button>
            </div>
        </div>

        <!-- 修改后的图片预览框，点击上传图片 -->
        <div class="inputbox2" @click="triggerFileInput">
            <div class="image-preview-box">
                <img :src="imageUrl || 'default-image-url.png'" alt="点击添加食品图片" class="image-preview" />
                <input type="file" @change="handleFileChange" accept="image/*" style="display: none;"
                    ref="imageInput" />
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
import { useRouter, useRoute } from 'vue-router';
import AlertPopup from '../components/AlertPopup.vue';
import Backer from '../components/backer.vue';

const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;

const router = useRouter();
const route = useRoute();
const foods = ref({
    foodName: '',
    foodExplain: '',
    foodImg: '',
    foodPrice: '',//还得处理
    businessId: route.query.businessId
});

const alertMessage = ref('');
const imageUrl = ref(null);  // 用于存储图片预览URL

// 触发文件输入的点击事件
const triggerFileInput = () => {
    const inputElement = instance?.refs.imageInput;
    inputElement?.click();
};

// 处理文件选择
const handleFileChange = (event) => {
    const file = event.target.files[0];
    if (file) {
        foods.value.foodImg = file;
        imageUrl.value = URL.createObjectURL(file);  // 生成本地图片预览URL
    }
};

// 显示弹窗的方法
const showAlert = (message) => {
    alertMessage.value = message;
    const popup = instance?.refs.alertPopup;
    popup?.openPopup();
};

const validateName = () => {
    if(foods.value.foodName == ''){
        showAlert('请输入食品名称！');
        return false;
    }
    return true;
};

const validateExplain = () => {
    if(foods.value.foodExplain == ''){
        showAlert('请输入食品介绍！');
        return false;
    }
    return true;
}

const validatePrice = () => {
    if(foods.value.foodPrice == ''){
        showAlert('请输入食品价格！');
        return false;
    }
    return true;
}

// 保存信息并上传图片
const storemessage = async () => {
    console.log(foods.value);
    if(!validateName() || !validateExplain() || !validatePrice()){
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
    } catch (error) {
        console.warn(error);
        showAlert('添加食品信息失败！');
    }
};
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
    height: 60vw;
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
</style>