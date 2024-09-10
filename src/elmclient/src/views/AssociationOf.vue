<template>
    <div class="wrapper">
        <header>
            <Backer></Backer>
            <p class="ziti">关于饿了吧</p>
        </header>
        <div class="center-content">
            <div class="image-container">
                <img src="../assets/饿了吧.png" alt="支付成功">
            </div>
            <p class="versionMessage">v2.0</p>
        </div>
        <!--  基础信息列表部分 -->
        <ul class="message-list">
            <li>
                <div class="left">
                    <p>平台服务协议</p>
                </div>
                <div class="right">
                    <p></p>
                    <svg t="1725796901210" class="icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="4134" width="200" height="200">
                        <path
                            d="M353.865143 225.865143a42.642286 42.642286 0 0 1 60.342857 0l256 256a42.642286 42.642286 0 0 1 0 60.269714l-256 256a42.642286 42.642286 0 1 1-60.342857-60.342857L579.657143 512 353.865143 286.134857a42.642286 42.642286 0 0 1 0-60.269714z"
                            fill="#8D8B8B" p-id="4135"></path>
                    </svg>
                </div>
                <hr>
            </li>
            <li>
                <div class="left">
                    <p>资质证照</p>
                </div>
                <div class="right">
                    <p></p><!--新增-->
                    <svg t="1725796901210" class="icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="4134" width="200" height="200">
                        <path
                            d="M353.865143 225.865143a42.642286 42.642286 0 0 1 60.342857 0l256 256a42.642286 42.642286 0 0 1 0 60.269714l-256 256a42.642286 42.642286 0 1 1-60.342857-60.342857L579.657143 512 353.865143 286.134857a42.642286 42.642286 0 0 1 0-60.269714z"
                            fill="#8D8B8B" p-id="4135"></path>
                    </svg>
                </div>
                <hr>
            </li>
            <li>
                <div class="left">
                    <p>检查最新版本</p>
                </div>
                <div class="right">
                    <p>当前为最新版本</p> <!--新增-->
                    <svg t="1725796901210" class="icon" viewBox="0 0 1024 1024" version="1.1"
                        xmlns="http://www.w3.org/2000/svg" p-id="4134" width="200" height="200">
                        <path
                            d="M353.865143 225.865143a42.642286 42.642286 0 0 1 60.342857 0l256 256a42.642286 42.642286 0 0 1 0 60.269714l-256 256a42.642286 42.642286 0 1 1-60.342857-60.342857L579.657143 512 353.865143 286.134857a42.642286 42.642286 0 0 1 0-60.269714z"
                            fill="#8D8B8B" p-id="4135"></path>
                    </svg>
                </div>
                <hr>
            </li>
        </ul>
        <div class="bottom_ziti">
            <p>tju 22届软件工程五班 第一小组</p>
        </div>
    </div>
</template>

<script setup>
import Backer from '../components/backer.vue';
import { useRouter } from 'vue-router';

const router = useRouter();


</script>

<style scoped>
.wrapper {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
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
    height: 35vh;
    text-align: center;
}


.versionMessage {
    font-size: 5vw;
    padding-top: 1vw;
    /* padding-bottom: 10vw; */
    color: grey;
}

/* Image container styling */
.image-container img {
    width: 30vw;
    height: auto;
    padding-top: 20vw;
}

.wrapper .message-list {
    width: 100%;
    height: 50vw;

    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
}



.wrapper .message-list li {
    width: 100vw;
    height: 12vw;

    display: flex;
    align-items: center;
    justify-content: space-between;
    position: relative;
}

.wrapper .message-list li .left {
    width: 28vw;
    height: 4vw;

    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.wrapper .message-list li .left p {
    font-size: 3.5vw;
    font-weight: 500;
    margin-left: 2vw;
    /* 	margin-top: 3.8vw; */
}

.wrapper .message-list li .right {
    display: flex;
    align-items: center;
    margin-right: 4vw;
}

.wrapper .message-list li .right svg {
    width: 5vw;
    height: 5vw;
    display: flex;
    align-items: center;
    color: #888;

    cursor: pointer;
}

.wrapper .message-list li .right p {
    font-size: 3vw;
    color: #888;
}

.wrapper .message-list li hr {
    width: calc(100% - 4vw);
    /* 减去左右的边距 */
    position: absolute;
    bottom: 0;
    left: 2vw;
    /* 确保 <hr> 与左侧对齐 */
    background-color: #e2e2e2;
    border: none;
    height: 0.05vw;
    margin: 0;
}

.wrapper .bottom_ziti {
    font-size: 3vw;
    margin-top: auto;
    text-align: center;
    padding-bottom: 2vw;
}
</style>
