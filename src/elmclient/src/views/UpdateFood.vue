<template>
    <div class="wrapper">

        <!-- header部分 -->
        <header>
            <Backer></Backer>
            <p>食品管理</p>
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
		
		<!-- <div class="add-button">
			<el-button @click="toBusinessUpload" type="primary" plain>上架商品</el-button>
		</div> -->

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
                    <p><span><u @click="updatefoods(item.foodId, item.foodExplain, item.foodPrice, item.foodName)">修改商品</u></span></p>
                </div>
            </li>
            <li class="empty-li">
                <div class="empty-message">已经到底了...</div>
            </li>
        </ul>

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
    getSessionStorage
} from '../common';

// 获取全局 axios 实例
const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;

// 获取路由实例和路由参数
const route = useRoute();
const router = useRouter();

// 定义响应式数据
const businessId = ref(null);
const business = ref({});
const foodArr = ref([]);
const del = ref({});
const user = ref({});

const toBusinessUpload = () =>{
	router.push({
		path: 'businessUpload'
	})
}

const initialize = async () => {
    try {
        // 根据userId查询businessId
        user.value = getSessionStorage('user');
        businessId.value = await axios.get(`users/businessId/${user.value.userId}`);

        // console.log(businessId.value)
        // 根据 businessId 查询商家信息
        const businessResponse = await axios.get(`businesses/${businessId.value.data}`);
        business.value = businessResponse;

        // 根据 businessId 查询食品信息
        const foodResponse = await axios.get(`foods/business/${businessId.value.data}`);
        foodArr.value = foodResponse;
        foodArr.value.forEach(food => {
            food.quantity = 0;
        });

    } catch (error) {
        console.error('Error initializing:', error);
    }
};
// 在组件创建时调用
onMounted(() => {
    initialize();
});

const alertMessage = ref('');

// 显示弹窗的方法
const showAlert = (message) => {
    alertMessage.value = message;
    const popup = instance?.refs.alertPopup;
    popup?.openPopup();
};

//下架商品
const updatefoods = async (foodId, foodExplain, foodPrice, foodName) => {
    router.push({ path: '/updateFoodInfo', query: { foodId, foodExplain, foodPrice, foodName } });
};

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

.wrapper .captcha-modal .captcha-container .captcha-box {
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

.wrapper .captcha-modal .captcha-container input {
    border: 1px solid #cdcdcd;
    /* 细线边框，颜色为黑色 */
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
	position: relative;
}

/****************** header部分 ******************/
.wrapper header {
    width: 100%;
    height: 12vw;
    background-color: #f43256;
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

.wrapper .add-button{
	position: absolute;
	right: 2vw;
	width: 20vw;
	height: 7vw;
	color: #0097FF;
	display: flex;
	align-items: center;
	justify-content: center;
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
</style>