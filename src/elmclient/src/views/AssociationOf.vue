<template>
    <div class="wrapper">
        <header>
            <Backer></Backer>
            <p>关于饿了吧</p>
        </header>
    </div>

</template>

<script setup>
import Backer from '../components/backer.vue';


</script>

<style scoped>
/****************** 总容器 ******************/
.wrapper {
    width: 100%;
    height: 100%;
}

/****************** header部分 ******************/
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
</style>