<template>
	<ul class="footer">
		<li @click="toIndex">
			<i class="fa fa-home"></i>
			<p>首页</p>
		</li>
		<li>
			<i @click="test" class="fa fa-compass"></i>
			<p>发现</p>
		</li>
		<li @click="toOrderList">
			<i class="fa fa-file-text-o"></i>
			<p>订单</p>
		</li>
		<li @click="toselfpage">
			<i class="fa fa-user-o"></i>
			<p>我的</p>
		</li>
	</ul>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { getSessionStorage } from '../common.js';

const router = useRouter();

const toIndex = () => {
	router.push({ path: '/index' });
};

const toOrderList = () => {
	if (getSessionStorage('user') == null || getSessionStorage('user').userId == '') {
		router.push({path:'/login'});
	}
	else {
		router.push({ path: '/orderList' });
	}
};

const toselfpage = () => {
	router.push({ path: '/selfpage' });
};

const test = () => {
	router.push({ path: '/' });
};
</script>

<style>
.wrapper .footer {
	width: 100%;
	height: 14vw;
	border-top: solid 1px #DDD;
	background-color: #fff;

	position: fixed;
	left: 0;
	bottom: 0;

	display: flex;
	justify-content: space-around;
	align-items: center;

	/* z-index: 1000; */
	/* 确保在其他内容之上 */
}

.wrapper .footer li {
	/*li本身的尺寸完全由内容撑起*/
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;

	color: #999;
	user-select: none;
	cursor: pointer;
}

.wrapper .footer li p {
	font-size: 2.8vw;
}

.wrapper .footer li i {
	font-size: 5vw;
}
</style>
