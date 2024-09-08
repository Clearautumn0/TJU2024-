<template>
	<div id="app">
		<router-view />
	</div>
</template>

<!-- 这里是共通样式，适用于所有组件，所以不要加scoped -->
<style>
	html,
	body,
	div,
	span,
	h1,
	h2,
	h3,
	h4,
	h5,
	h6,
	ul,
	ol,
	li,
	p {
		margin: 0;
		padding: 0;
	}

	html,
	body,
	#app {
		width: 100%;
		height: 100%;
		font-family: "微软雅黑";
	}

	ul,
	ol {
		list-style: none;
	}

	a {
		text-decoration: none;
	}
	/**
	 * 定位小图标
	 * 放置在容器中调整大小
	 */
	.icon-location{
		position:relative;
		width: 100%;
		height: 100%;		/*正方形*/
		border-radius: 50% 50% 50% 0;   	/*切三个圆角*/
		background: #fff;
		transform: rotate(-45deg);		/*旋转45°*/
	}
	.icon-location:after {
		content: '';
		width: 40%;
		height: 40%;
		margin: 30% 0 0 29%;
		background-color: #0097FF;
		position: absolute;
		border-radius: 50%;
	}
</style>
