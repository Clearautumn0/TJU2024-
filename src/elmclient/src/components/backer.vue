<template>

  <!-- 返回按钮 -->
  <!-- <img @click="goBack" src="../assets/backer.png" alt="返回" class="back-button"> -->
  <div @click="goBack" class="back-button">
	  &lt;
  </div>
</template>

<script>
export default {
  name: 'Backer',
  // data(){
  //   return{
  //     isselfpage: false
  //   }
  // },
  methods: {
    goBack() {
      this.$router.go(-1);  // 返回上一页
    }
  }
  // , beforeRouteEnter(to, from, next) {
  //   // from 是上一页面的路由对象
  //   console.log('上一页面的路径:', from.fullPath); // 获取上一页面路径
  //   if(from.fullPath == '/selfpage'){
  //     this.isselfpage = true;
  //   }
  //   else {
  //     this.isselfpage = false;
  //   }
  //   next();
  // }
}
</script>

<style scoped>
/* 返回按钮样式 */
.wrapper .back-button {
	position: absolute;
	left: 2vw;
	cursor: pointer;
	color: black;
	font-size: 6vw;
}
</style>
