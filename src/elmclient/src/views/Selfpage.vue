<template>
    <div class="wrapper">
            <header class="profile-header">
                <div class="avatar-frame">
                    <img src="../assets/logo.png" alt="无法加载图片">
                </div>
                <h2 class="username">用户名</h2>
            </header>
            <ul class="profile-menu">
                <li @click="toindividual_inf" class="profile-menu-item">个人信息</li>
                <li class="profile-menu-item">会员</li>
                <li class="profile-menu-item">设置</li>
                <li @click="backfun" class="profile-menu-item">返回主界面</li>
            </ul>

        <Footer></Footer>
    </div>



</template>

<script>
import Footer from '../components/Footer.vue';
export default {
    methods: {
        backfun() {
            this.$router.push({ path: '/index' });
        },
        toindividual_inf() {
            this.$router.push({});
        }
    },
    components: {
        Footer
    }
}
</script>

<style>
.wrapper {
    width: 100%;
    height: 100%;
    /* display: flex;
    flex-direction: column;
    padding-bottom: 14vw; */
    /* 为底部菜单留出空间 */
}

.wrapper header {
    width: 100%;
    height: 20vw;
    background-color: #0097FF;
    /* text-align: center; */
    display: flex;
    align-items: center;
    /* padding: 0 2vw; */
}

.avatar-frame {
    width: 12vw;
    /* 使用视口宽度的10%作为宽度 */
    height: 12vw;
    /* 使用视口宽度的10%作为高度 */
    border-radius: 50%;
    /* overflow: hidden; */
    border: 3px solid #ccc;
    margin-right: 5vw;
    margin-left: 2vw;
}

.avatar-frame img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.username {
    font-size: 5vw;
    /* 使用视口宽度的2%作为字体大小 */
    color: #333;
    margin-top: 5vw;
    /* 使用视口宽度的1%作为上边距 */
}

.profile-menu {
    list-style: none;
    padding: 0;
    margin: 0;
    flex: 1;
    /* 使菜单列表占据剩余的空间 */
}

.profile-menu-item {
    padding: 8vw;
    /* 使用视口宽度的2%作为内边距 */
    font-size: 3vw;
    /* 使用视口宽度的1.5%作为字体大小 */
    color: #555;
    border-bottom: 1px solid #e0e0e0;
    cursor: pointer;
    transition: background-color 0.3s;
}

.profile-menu-item:hover {
    background-color: #e9e9e9;
}
</style>