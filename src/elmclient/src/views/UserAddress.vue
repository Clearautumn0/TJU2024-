<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<Backer></Backer>
			<p>地址管理</p>
		</header>

		<!-- 地址列表部分 -->
		<ul class="addresslist">
			<li v-for="item in deliveryAddressArr" :key="item.daId">
				<div class="addresslist-left" @click="setDeliveryAddress(item)">
					<h3>{{ item.contactName }}{{ sexFilter(item.contactSex) }} {{ item.contactTel }}</h3>
					<p>{{ item.address }}</p>
				</div>
				<div class="addresslist-right">
					<i class="fa fa-edit" @click="editUserAddress(item.daId)"></i>
					<i class="fa fa-remove" @click="removeUserAddress(item.daId)"></i>
				</div>
			</li>
			<li class="empty-li">
				<div class="addbtn" @click="toAddUserAddress">
					<i class="fa fa-plus-circle"></i>
					<p>新增收货地址</p>
				</div>
			</li>
			<!-- <li class="empty-li">

			</li> -->
		</ul>
		<!-- 新增地址部分 -->
		<!-- <div class="addbtn" @click="toAddUserAddress">
			<i class="fa fa-plus-circle"></i>
			<p>新增收货地址</p>
		</div> -->

		<AlertPopup ref="alertPopup" :message="alertMessage" />

		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script setup>
import { ref, onMounted, computed, getCurrentInstance } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import Backer from '../components/backer.vue';
import Footer from '../components/Footer.vue';

import AlertPopup from '../components/AlertPopup.vue';

import { getSessionStorage, setLocalStorage, removeLocalStorage, getLocalStorage } from '../common.js';

// 获取全局 axios 实例
const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;


const router = useRouter();
const route = useRoute();
const businessId = ref(route.query.businessId);
const user = ref({});
const deliveryAddressArr = ref([]);


const alertMessage = ref('');

// 显示弹窗的方法
const showAlert = (message) => {
	alertMessage.value = message;
	const popup = instance?.refs.alertPopup;
	popup?.openPopup();
};


onMounted(() => {
	user.value = getSessionStorage('user');
	listDeliveryAddressByUserId();
});

const listDeliveryAddressByUserId = () => {
	axios.get(`delivery-addresses/user/${user.value.userId}`)
		.then(response => {
			deliveryAddressArr.value = response;
		})
		.catch(error => {
			console.error(error);
		});
};

const setDeliveryAddress = (deliveryAddress) => {
	setLocalStorage(user.value.userId, deliveryAddress);
};

const toAddUserAddress = () => {
	router.push({ path: '/addUserAddress', query: { businessId: businessId.value } });
};

const editUserAddress = (daId) => {
	router.push({ path: '/editUserAddress', query: { businessId: businessId.value, daId } });
};

const removeUserAddress = (daId) => {

	// if (!confirm('确认要删除此送货地址吗？')) return;
	showAlert('删除地址成功');//直接删除，没有再次确定选项


	axios.delete(`delivery-addresses/${daId}`)
		.then(response => {
			if (response.data > 0) {
				let deliveryAddress = getLocalStorage(user.value.userId);
				if (deliveryAddress && deliveryAddress.daId === daId) {
					removeLocalStorage(user.value.userId);
				}
				listDeliveryAddressByUserId();
			} else {

				showAlert('删除地址失败！');

			}
		})
		.catch(error => {
			console.error(error);
		});
};

// 性别过滤器方法
const sexFilter = (value) => {
	return value === 1 ? '先生' : '女士';
};

// return {
// 	businessId,
// 	user,
// 	deliveryAddressArr,
// 	listDeliveryAddressByUserId,
// 	setDeliveryAddress,
// 	toAddUserAddress,
// 	editUserAddress,
// 	removeUserAddress,
// 	sexFilter
// };

</script>

<style scoped>
/*************** 总容器 ***************/
.wrapper {
	width: 100%;
	height: 100%;
	background-color: #fff;
}

/*************** header ***************/
.wrapper header {
	width: 100%;
	height: 12vw;
	background-color: #0097FF;
	color: #fff;
	font-size: 4.8vw;

	position: fixed;
	left: 0;
	top: 0;
	/*保证在最上层*/
	z-index: 1000;

	display: flex;
	justify-content: center;
	align-items: center;
}

/*************** addresslist ***************/
.wrapper .addresslist {
	width: 100%;
	margin-top: 12vw;
	background-color: #fff;
}

.wrapper .addresslist li {
	width: 100%;
	box-sizing: border-box;
	border-bottom: solid 1px #DDD;
	padding: 3vw;

	display: flex;
}

.wrapper .addresslist li .addresslist-left {
	flex: 5;
	/*左边这块区域是可以点击的*/
	user-select: none;
	cursor: pointer;
}

.wrapper .addresslist li .addresslist-left h3 {
	font-size: 4.6vw;
	font-weight: 300;
	color: #666;
}

.wrapper .addresslist li .addresslist-left p {
	font-size: 4vw;
	color: #666;
}

.wrapper .addresslist li .addresslist-right {
	flex: 1;
	font-size: 5.6vw;
	color: #999;
	cursor: pointer;

	display: flex;
	justify-content: space-around;
	align-items: center;
}

/*************** 新增地址部分 ***************/
.wrapper .addbtn {
	width: 100%;
	height: 14vw;
	border-top: solid 1px #fff;
	border-bottom: solid 1px #fff;
	background-color: #fff;
	margin-top: -3vw;

	display: flex;
	justify-content: center;
	align-items: center;

	font-size: 4.5vw;
	color: #0097FF;
	user-select: none;
	cursor: pointer;
}

.wrapper .addbtn p {
	margin-left: 2vw;
}

.empty-li {
	width: 200vw;
	/* 设置矩形的宽度 */
	height: 30vw;
	/* 设置矩形的高度 */
	/* border: 2px solid #000; */
	/* 添加一个2像素的黑色边框 */

	background-color: transparent;
	/* 背景色为透明 */
	list-style: none;
	margin-bottom: 0px;
	/* padding-bottom-color: #fff; */
}
</style>
