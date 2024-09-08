<template>

    <!-- 返回按钮 -->
    <img @click="goBack" src="../assets/backer.png" alt="返回" class="back-button">
    
  </template>
  
  <script>
  export default {
    name: 'Backer',
    methods: {
      goBack() {
        this.$router.push({path: '/orders'}) ;  // 返回确认订单页面
      }
    }
  }
  </script>
  
  <style scoped>
  /* 返回按钮样式 */
  .wrapper .back-button {
      position: absolute;
      left: 2vw;
      top: 2vw;
      width: 8vw;
      height: 8vw;
      cursor: pointer;
  }
  </style>
  