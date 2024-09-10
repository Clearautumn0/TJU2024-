<template>
    <div class="wrapper">
        <header>
            <!--<Backer></Backer>-->
            <p class="ziti">支付详情</p>
        </header>
        <div class="center-content">
            <p class="success-message">订单支付成功</p>
            <div class="image-container">
                <img src="../assets/支付成功.png" alt="支付成功">
            </div>
            <!-- Add buttons below the image -->
            <div class="button-container">
                <button class="btn confirm" @click="toOrderList">查看订单</button>
                <button class="btn back" @click="toIndex">返回首页</button>
            </div>
        </div>
    </div>
</template>

<script setup>
// import Backer from '../components/backer.vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const toOrderList = () => {
    router.push({path: '/orderList'});
}

const toIndex = () => {
    router.push({path: '/index'});
}

</script>

<style scoped>
.wrapper {
    width: 100%;
    height: 100%;
}

/* Header styling */
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

/* Centering content on the page */
.center-content {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    text-align: center;
}

/* Styling for the success message */
.success-message {
    font-size: 7vw;
    padding-top: 20vw;
    padding-bottom: 10vw;
    color: #0097FF;
}

/* Image container styling */
.image-container img {
    width: 95vw;
    height: auto;
    padding-bottom: 8vw;
}

/* Button container styling */
.button-container {
    display: flex;
    justify-content: center;
    gap: 5vw;
    /* Space between buttons */
    margin-top: 10vw;
}

/* General button styling */
.btn {
    width: 35vw;
    padding: 3vw;
    font-size: 4vw;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

/* Specific button styling */
.btn.confirm {
    background-color: #38CA73;
    color: white;
}

.btn.back {
    background-color: #0097FF;
    color: white;
}
</style>
