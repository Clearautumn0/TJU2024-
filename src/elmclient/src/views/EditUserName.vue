<template>
	<!--  总容器 -->
	<div class="wrapper">
		<!--  header部分 -->
		<header>
			<Backer></Backer>
			<div class="setUserName">
				<p>设置昵称</p>
			</div>
			<div @click="toPerson" class="reposit">
				<p>保存</p>
			</div>
		</header>
		<div class="color-box"></div>
		<div class="name-box">
			<input type="text" id="userName" name="userName" placeholder="请输入昵称">
		</div>
		<div class="bottom">
			
		</div>
	</div>
</template>

<script setup>
	import {
		useRouter
	} from 'vue-router';
	import Backer from '../components/backer.vue';

	const router = useRouter();

	const toPerson= () => {
		router.push({
			path: '/editUserName'
		});
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
		color: #e2e2e2;
		font-size: 4.5vw;

		display: flex;
		align-items: center;
		justify-content: space-between;
	}

	.wrapper header p {
		color: #000000;
	}
	
	.wrapper header .back-button{
		width:5vw;
		height: 5vw;
	}

	.wrapper header .setUserName {
		font-weight: 600;
	}

	.wrapper header .reposit {
		margin-right: 3.5vw;
		font-weight: 525;
	}
	
	.wrapper .color-box{
		width: 100%;
		height: 3vw;
		background-color: #ebebeb;
	}
	
	.wrapper .name-box{
		width: 100%;
		height: 12vw;
		color: #ffffff;
		
	}
	
	.wrapper .name-box input{
		border: none;
		outline: none;
		width: 100%;
		height: 11vw;
		font-size: 4vw;
		margin-left: 2vw;
	}

	.wrapper .bottom {
		width: 100%;
		height: 100%;
		background-color: #ebebeb;
		z-index: -1;
	}
</style>