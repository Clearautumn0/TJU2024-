<template>
    <div class="wrapper">
        <header>
            <div class="user">
                <img src="../assets/logo.png">
                <p>用户名</p>
            </div>
        </header>
        <ul class="useraction">
            <li @click="userself_inf">
                <p>用户基本信息</p>
            </li>
            <li>
                <p>会员</p>
            </li>
        </ul>
    </div>
</template>

<script scope>
export default {
    data() {
        return {

        }
    },
    methods: {
        userself_inf() {
            if (1 == 2) {

            } else {
                this.$router.push({ path: '/login' });
            }
        }
    }
}
</script>

<style scoped>
.wrapper {
    width: 100%;
    height: 100%;
}


.wrapper header {
    width: 100%;
    height: 15vw;
    background-color: #0097FF;
    display: flex;
    justify-content: center;
    align-items: center;
}
.wrapper header
</style>