<template>
    <!--  总容器 -->
    <div class="wrapper">
        <!--  header部分 -->
        <header>
            <Backer></Backer>
            <p class="nessage">个人资料</p>
        </header>

        <!--  基础信息部分 -->
        <div class="message-box">
            <div class="message">
                <p>基础信息</p>
            </div>
        </div>

        <!--  基础信息列表部分 -->
        <ul class="message-list">
            <li>
                <div class="left">
                    <p>头像</p>
                </div>
                <div class="right">
                    <div class="avatar-frame">
                        <img src="../assets/默认头像.png" alt="无法加载图片" class="avatar-img">
                    </div>
                    <!-- &gt; -->
                </div>
                <hr>
            </li>
            <li>
                <div class="left">
                    <p>账号</p>
                </div>
                <div class="right">
                    {{ user.userId }} <!--新增-->
                    <!--&gt;-->
                </div>
                <hr>
            </li>
            <li>
                <div class="left">
                    <p>昵称</p>
                </div>
                <div class="right">
                    {{ user.userName }} <!--新增-->
                    <!--&gt;-->
                </div>
                <hr>
            </li>
            <!-- <li>
                <div class="left">
                    <p>个人简介</p>
                </div>
                <div class="right">
                    &gt;
                </div>
                <hr>
            </li> -->
            <li @click="toUserAddress">
                <div class="left">
                    <p>收货地址编辑</p>
                </div>
                <div class="right">
                    {{ deliveryaddersss.address }}
                    &gt;
                </div>
                <hr>
            </li>
        </ul>

        <!--  账号绑定部分 -->
        <div class="Id-box">
            <div class="Id">
                <p>账号绑定</p>
            </div>
        </div>

        <!--  账号绑定信息部分 -->
        <ul class="bind-list">
            <li>
                <div class="left">
                    <img src="../assets/phone.png">手机
                </div>
                <div class="right">
                    &gt;
                </div>
                <hr>
            </li>
            <li>
                <div class="left">
                    <img src="../assets/taobao.png">淘宝
                </div>
                <div class="right">
                    &gt;
                </div>
                <hr>
            </li>
            <li>
                <div class="left">
                    <img src="../assets/zhifubao.png">支付宝
                </div>
                <div class="right">
                    &gt;
                </div>
                <hr>
            </li>
            <li>
                <div class="left">
                    <img src="../assets/weixin.png">微信
                </div>
                <div class="right">
                    &gt;
                </div>
                <hr>
            </li>
            <li>
                <div class="left">
                    <img src="../assets/QQ.png">QQ
                </div>
                <div class="right">
                    &gt;
                </div>
                <hr>
            </li>
        </ul>
        <!--  底部 -->
        <div class="bottom">

        </div>

    </div>
</template>

<script>
import Backer from '../components/backer.vue';
export default {
    data() {
        return {
            user: {},
            deliveryaddersss: {}
        }
    },
    created() {
        this.user = this.$getSessionStorage('user');
        this.deliveryaddress = this.$getLocalStorage(this.user.userId);
    },
    methods: {
        toUserAddress() {
            this.$router.push({ path: '/userAddress' });
        }
    },
    components: {
        Backer
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
    font-weight: 800;
    color: #e2e2e2;
    font-size: 4.5vw;

    display: flex;
    align-items: center;
    justify-content: center;
}

.wrapper header p{
	color: #000000;
	font-weight: 600;
}

/********************* message  *********************/
.wrapper .message-box {
    width: 100%;
    height: 11vw;
    background-color: #ebebeb;

    display: flex;
    align-items: flex-start;
}

.wrapper .message-box .message {
    font-size: 3.5vw;
    margin-left: 2vw;
    margin-top: 4vw;
    color: #5e5e5e;
}

/********************* message列表 *********************/
.wrapper .message-list {
    width: 100%;
    height: 60vw;

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
    width: 20vw;
    height: 4vw;

    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.wrapper .message-list li .left p {
    font-size: 4vw;
    font-weight: 525;
    margin-left: 2vw;
    /* 	margin-top: 3.8vw; */
}

.wrapper .message-list li .right {
    font-size: 6vw;
    display: flex;
    align-items: center;
    margin-right: 4vw;
    color: #888;

    cursor: pointer;
}

/*头像显示*/
.avatar-frame {
    width: 10vw;
    /* 使用视口宽度的10%作为宽度 */
    height: 10vw;
    /* 使用视口宽度的10%作为高度 */
    border-radius: 50%;
    /* overflow: hidden; */
    border: 2px solid #ccc;
    margin-right: 0vw; /*调整右对齐 */
    /* margin-left: 2vw; */
    /* overflow: hidden; */
}

.avatar-frame img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 50%;
}

/* ************ */


.wrapper .message-list li hr {
    width: 98vw;
    margin-left: 2vw;

    position: absolute;
    bottom: 0;

    background-color: #e2e2e2;
    border: none;
    /* 移除默认的边框 */
    height: 0.05vw;
    /* 设置分割线的高度 */
    margin-bottom: 0;
}

/********************* 账号绑定  *********************/
.wrapper .Id-box {
    width: 100%;
    height: 11vw;
    background-color: #ebebeb;

    display: flex;
    align-items: flex-start;
}

.wrapper .Id-box .Id {
    font-size: 3.5vw;
    margin-left: 2vw;
    margin-top: 4vw;
    color: #5e5e5e;
}

/********************* 绑定列表 *********************/
.wrapper .bind-list {
    width: 100%;
    height: 60vw;

    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
}

.wrapper .bind-list li {
    width: 100vw;
    height: 12vw;

    position: relative;

    display: flex;
    align-items: center;
    justify-content: space-between;
}

.wrapper .bind-list li .left {
    font-size: 4vw;
    font-weight: 500;

    display: flex;
    align-items: center;
    margin-left: 2.0vw;
}

.wrapper .bind-list li .left img {
    width: 5vw;
    height: 5vw;

    display: flex;
    align-items: center;
    margin-right: 3vw;
}

.wrapper .bind-list li .right {
    font-size: 6vw;
    display: flex;
    align-items: center;
    margin-right: 4vw;
    color: #888;

    cursor: pointer;
}

.wrapper .bind-list li hr {
    width: 98vw;
    margin-left: 2vw;

    position: absolute;
    bottom: 0;

    background-color: #e2e2e2;
    border: none;
    /* 移除默认的边框 */
    height: 0.05vw;
    /* 设置分割线的高度 */
    margin-bottom: 0;
}

.wrapper .bottom {
    width: 100%;
    height: 100vw;
    background-color: #ebebeb;
}
</style>