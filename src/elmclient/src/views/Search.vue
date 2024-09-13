<template>
	<!--  总容器 -->
	<div class="wrapper">
		<!--  header部分 -->
		<header>
			<Backer></Backer>
			<div class="search-box">
				<input type="text" id="SearchContent" name="SearchContent" placeholder="最近搜索" v-model="searchQuery">
				<p @click="toSearchBussiness">搜索</p>
			</div>
		</header>
		<div class="content">
			<div v-if="!showResults">
				<div class="search-history">
					<h4>历史搜索</h4>
				</div>
				<div class="search-example">
					<h4>搜索发现</h4>
				</div>
			</div>
			<div v-else class="search-result">
				<h4>搜索结果</h4>
				<ul class="business">
					<li v-for="item in searchResults" @click="toBusinessInfo(item.businessId)">
						<div class="business-img">
							<img :src="item.businessImg">
							<div class="business-img-quantity" v-show="item.quantity > 0">{{ item.quantity }}</div>
						</div>
						<div class="business-info">
							<h3>{{ item.businessName }}</h3>
							<p>&#165;{{ item.starPrice }}起送 | &#165;{{ item.deliveryPrice }}配送</p>
							<p>{{ item.businessExplain }}</p>
						</div>
					</li>
					<!-- <li class="empty-li">
						<div class="empty-message">已经到底了...</div>
					</li> -->
				</ul>
			</div>
		</div>
	</div>
</template>

<script setup>
	import {
		useRouter,
		useRoute
	} from 'vue-router';
	import Backer from '../components/backer.vue';
	import {
		onMounted,
		onBeforeUnmount,
		getCurrentInstance,
		nextTick,
		ref,
		reactive
	} from 'vue';

	// 获取全局 axios 实例
	const instance = getCurrentInstance();
	const axios = instance?.appContext.config.globalProperties.$axios;

	//获取路由参数
	const router = useRouter();

	const businessArr = reactive([]);
	const user = ref({});
	const searchQuery = ref(''); // 用于存储搜索输入框的内容
	const showResults = ref(false); // 用于控制是否显示搜索结果
	const orderTypeId = ref();
	const searchResults = reactive([]); //用于存储搜索结果
	
	// 获取用户信息
	const getSessionStorage = key => {
		return JSON.parse(window.sessionStorage.getItem(key));
	};

	const toSearchBussiness = async () => {
		// 清空搜索结果
		 searchResults.length = 0;  // 清空 reactive 数组
		
		if (searchQuery.value.trim() === '') {
			return; // 如果输入为空，则不进行搜索
		}
		
		// console.log(searchQuery);
		const regex = new RegExp(searchQuery.value, 'i'); // 'i' 忽略大小写
		for (let i = 1; i <= 10; i++) {
			const orderTypeId = {
				value: i
			}; // 示例：创建一个对象，包含 value 属性
			const response = await axios.get(`businesses/orderType/${orderTypeId.value}`);
			// console.log(response);
			businessArr.splice(0, businessArr.length, ...response); // 使用 splice 进行响应式更新
			// console.log(businessArr);
			const searchResponse = businessArr.filter(business => regex.test(business.businessName));
			// console.log(searchResponse);
			searchResults.push(...searchResponse);
		}
		showResults.value = true; // 显示搜索结果
		// console.log(searchResults);
	};
	
	// 路由导航
	const toBusinessInfo = (businessId) => {
		router.push({ path: '/businessInfo', query: { businessId } });
	};
	
	// 在组件挂载时调用
	onMounted(() => {
		user.value = getSessionStorage('user');
	});
</script>

<style scoped>
	.wrapper {
		width: 100%;
		height: 100%;
		background-color: #ebebeb;
		z-index: -1;
	}

	.wrapper header {
		width: 100%;
		height: 12vw;
		display: flex;
		align-items: center;
	}

	/* 返回按钮样式 */
	.wrapper header .back-button {
		/* margin-left: 2vw; */
		width: 10vw;
		height: 10vw;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper header .search-box {
		width: 85vw;
		height: 8vw;
		border-radius: 6vw;
		background-color: #fff;
		display: flex;
		align-items: center;
		justify-content: space-between;
	}

	.wrapper header .search-box input {
		border: none;
		outline: none;
		width: 90vw;
		height: 7vw;
		font-size: 3vw;
		border-radius: 6vw;
		/* margin-top: 2vw; */
		padding-left: 2vw;
	}

	.wrapper header .search-box p {
		width: 10vw;
		font-size: 3.5vw;
		color: #00a7fa;
		margin-right: 2vw;
		font-weight: 500;
	}

	.wrapper .content {
		width: 100%;
		height: 100%;
		margin-top: 2vw;
		background-color: #fff;
		border-radius: 4vw;

	}

	.wrapper .content .search-history {
		width: 20vw;
		height: 10vw;
		display: flex;
		align-items: center;
		margin-left: 3vw;
		padding-top: 0vw;
	}

	.wrapper .content .search-history h4 {
		font-weight: 500;
		font-size: 5vw;
	}

	.wrapper .content .search-example {
		width: 20vw;
		height: 10vw;
		display: flex;
		align-items: center;
		margin-left: 3vw;
		padding-top: 7vw;
	}

	.wrapper .content .search-example h4 {
		font-weight: 500;
		font-size: 5vw;
	}
	
/* 	.wrapper .content .search-result {
		width: 20vw;
		height: 10vw;
		display: flex;
		align-items: center;
		margin-left: 3vw;
		padding-top: 0vw;
	} */
	
	.wrapper .content .search-result h4{
		font-weight: 500;
		padding: 2vw;
	}
	
	/****************** 商家列表部分 ******************/
	.wrapper .content .search-result .business {
		width: 100%;
		margin-top: 3vw;
		margin-bottom: 14vw;
	}
	
	.wrapper .content .search-result .business li {
		width: 100%;
		box-sizing: border-box;
		padding: 2.5vw;
		border-bottom: solid 1px #DDD;
		user-select: none;
		cursor: pointer;
	
		display: flex;
		align-items: center;
	}
	
	.wrapper .content .search-result .business li .business-img {
		/*这里设置为相当定位，成为business-img-quantity元素的父元素*/
		position: relative;
	}
	
	.wrapper .content .search-result .business li .business-img img {
		width: 20vw;
		height: 20vw;
	}
	
	.wrapper .content .search-result .business li .business-img .business-img-quantity {
		width: 5vw;
		height: 5vw;
		background-color: red;
		color: #fff;
		font-size: 3.6vw;
		border-radius: 2.5vw;
	
		display: flex;
		justify-content: center;
		align-items: center;
	
		/*设置成绝对定位，不占文档流空间*/
		position: absolute;
		right: -1.5vw;
		top: -1.5vw;
	}
	
	.wrapper .content .search-result .business li .business-info {
		margin-left: 3vw;
	}
	
	.wrapper .content .search-result .business li .business-info h3 {
		font-size: 3.8vw;
		color: #555;
	}
	
	.wrapper .content .search-result .business li .business-info p {
		font-size: 3vw;
		color: #888;
		margin-top: 2vw;
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
		/* 移除li的默认列表样式 */
		/* margin: 10px; */
		/* 添加一些外边距让矩形之间有间隔 */
	}
	
</style>