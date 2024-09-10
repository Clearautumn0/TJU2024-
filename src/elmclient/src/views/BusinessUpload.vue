<template>
    <div class="wrapper">
        <header>
            <Backer></Backer>
            <h1>上架商品</h1>
        </header>
        <ul class="form-group-list">
            <li class="form-group">
                <p>食品名称：</p>
                <input type="text" id="foodName" v-model="foods.foodName" placeholder="食物名称：请输入" />
            </li>
            <li class="form-group">
                <p>食品介绍：</p>
                <input type="text" id="foodExplain" v-model="foods.foodExplain" placeholder="食品介绍：请输入" />
            </li>
            <li class="form-group">
                <p>食品价格：</p>
                <input type="text" id="foodPrice" v-model="foods.foodPrice" placeholder="食品价格：请输入" />
            </li>
            <li class="form-group">
                <p>上传图片：</p>
                <input type="file" @change="onImageChange" accept="image/*" />
                <div class="image-preview-box" v-if="imageUrl">
                    <img :src="imageUrl" alt="食品图片预览" class="image-preview" />
                </div>
            </li>
        </ul>
        <div class="save-button">
            <button @click="storemessage">保存信息</button>
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
    foodPrice: 1.0,
    businessId: 10001
});

const alertMessage = ref('');
const imageUrl = ref(null);  // 用于存储图片预览URL

// 图片上传处理函数
const onImageChange = (event) => {
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

// 保存信息并上传图片
const storemessage = async () => {
    console.log(foods.value);
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
.wrapper {
    width: 100%;
    height: 100%;
    background-color: #f8f9fa;
}

.wrapper header {
    width: 100%;
    height: 12vw;
    background-color: #0097FF;
    color: #fff;
    font-size: 3vw;

    position: fixed;
    left: 0;
    top: 0;
    z-index: 1000;

    display: flex;
    justify-content: center;
    align-items: center;
}


.form-group-list {
    list-style-type: none;
    padding: 3vw;
    margin: 0;
    width: 100%;
}

.form-group {
    width: 90vw;
    margin-bottom: 2rem;
}

.wrapper .form-group li {
    border: none;
    width: 90vw;
    height: 7vw;
    border-radius: 6vw;
    padding-left: 2vw;
}

/* input[type="text"] {
    width: 100vw;
    font-size: 1rem;
    padding: 0;
    margin: 1vw 1vw;
    margin-right: 1vw;
    border: 1px solid #ced4da;
    border-radius: 0.25rem;
} */

.wrapper .image-preview-box {
    margin-top: 1vw;
    width: 90vw;
    max-width: 500vw;
    height: auto;
    border: 1px solid gray;
    margin: 1vw 1vw;
    padding: 3vw;
    display: flex;
    justify-content: center;
    align-items: center;
}

.wrapper .image-preview {
    max-width: 100%;
    height: auto;
}

.wrapper .save-button {
    width: 90%;
    margin-left: 5%;
    margin-top: 3vw;
    display: flex;
    justify-content: center;
}

.wrapper .save-button button {
    width: 100%;
    padding: 1.5vw;
    font-size: 4.5vw;
    margin-top: 1vw;
    color: #fff;
    background-color: #28a745;
    border: none;
    border-radius: 0.25rem;
}
</style>
