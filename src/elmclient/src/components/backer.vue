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
  methods: {
    goBack() {
      this.$router.go(-1);  // 返回上一页
    }
  }
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
