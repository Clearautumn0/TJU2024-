<template>

  <!-- 返回按钮 -->
	  <div @click="goBack" class="back-button">
		  <svg t="1725798864765" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5185" width="200" height="200"><path d="M330.2 539c0.5 0.7 1.1 1.4 1.7 2.1l296 324.2c16.1 17.7 43.4 18.9 61.1 2.8 17.7-16.1 18.9-43.4 2.8-61.1L422.2 511.6 692 217c16.1-17.6 14.9-44.9-2.7-61.1-17.6-16.1-44.9-14.9-61.1 2.7L331.8 482.4c-14.8 16.1-15.1 40.3-1.6 56.6z" p-id="5186"></path></svg>
	  </div>
  
</template>

<script>
import { useRouter } from 'vue-router';

export default {
  name: 'Backer',
  setup() {
    const router = useRouter();

    function goBack() {
      router.go(-1);  // 返回上一页
    }

    return {
      goBack
    };
  }
}
</script>

<style scoped>
/* 返回按钮样式 */
/* .wrapper .back-button{
	position: absolute;
	margin-left: 2vw;
	width: 10vw;
	height: 10vw;
	display: flex;
	justify-content: center;
	align-items: center;
} */
.wrapper .back-button svg{
	position: absolute;
	left: 2vw;
	top: 3vw;
	width: 6vw;
	height: 6vw;
	cursor: pointer;
	font-size: 3.5vw;
	color: #000;
}
</style>
