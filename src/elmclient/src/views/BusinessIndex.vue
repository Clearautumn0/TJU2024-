<template>
	<div class="wrapper">

		<!-- header部分 -->
		<header>
			<Backer></Backer>
			<p>店铺管理</p>
		</header>

		<!-- 商家logo部分 -->
		<div class="business-logo">
			<img :src="business.businessImg">
		</div>

		<!-- 商家信息部分 -->
		<div class="business-info">
			<h1>{{ business.businessName }}</h1>
			<p>&#165;{{ business.starPrice }}起送 &#165;{{ business.deliveryPrice }}配送</p>
			<p>{{ business.businessExplain }}</p>
		</div>

		<!-- 店铺功能部分 -->
		<div class="managefood">
			<h4>&#124;商品管理</h4>
			<el-divider class="driver-line"/>
			<ul>
				<li @click="toAddFood">
					<div class="svg-container">
						<svg t="1726044451886" class="icon" viewBox="0 0 1024 1024" version="1.1"
							xmlns="http://www.w3.org/2000/svg" p-id="16582" id="mx_n_1726044451886" width="200"
							height="200">
							<path
								d="M297.173333 836.266667c39.210667 0 71.082667 32.554667 71.082667 72.533333 0 40.021333-31.872 72.533333-71.082667 72.533333-39.168 0-71.082667-32.512-71.082666-72.533333 0-39.978667 31.914667-72.533333 71.082666-72.533333zM79.445333 42.666667l4.437334 0.469333 97.664 17.28c15.786667 2.773333 27.733333 16.170667 29.013333 32.426667l45.226667 549.461333a49.493333 49.493333 0 0 0 49.28 46.208h320.853333a190.805333 190.805333 0 0 0-26.453333 71.850667h-294.4c-62.848 0-114.176-48.213333-119.466667-112.085334l-42.88-521.813333-70.826667-12.501333a35.882667 35.882667 0 0 1-28.714666-41.514667 35.328 35.328 0 0 1 31.872-29.653333l4.394666-0.085334z m803.712 184.32a97.28 97.28 0 0 1 74.24 34.56 101.546667 101.546667 0 0 1 22.912 79.914666l-44.586666 314.154667c-0.384 2.645333-0.853333 5.290667-1.365334 7.893333a192.469333 192.469333 0 0 0-64-48.298666l40.277334-284.032a28.458667 28.458667 0 0 0-6.485334-22.613334 27.306667 27.306667 0 0 0-20.992-9.770666H378.88a35.584 35.584 0 0 1-35.2-35.925334c0-19.797333 15.786667-35.925333 35.2-35.925333H883.2z m-141.226666 177.024c19.413333 0 35.2 16.085333 35.2 35.925333 0 19.797333-15.786667 35.925333-35.2 35.925333h-130.133334a35.541333 35.541333 0 0 1-35.242666-35.925333c0-19.84 15.744-35.925333 35.2-35.925333h130.133333z"
								p-id="16583" fill="#ffffff"></path>
							<path
								d="M682.624 761.301333m27.733333 0l157.866667 0q27.733333 0 27.733333 27.733334l0 0q0 27.733333-27.733333 27.733333l-157.866667 0q-27.733333 0-27.733333-27.733333l0 0q0-27.733333 27.733333-27.733334Z"
								p-id="16584" fill="#ffffff"></path>
							<path
								d="M817.664 683.050667m0 27.733333l0 157.866667q0 27.733333-27.733333 27.733333l0 0q-27.733333 0-27.733334-27.733333l0-157.866667q0-27.733333 27.733334-27.733333l0 0q27.733333 0 27.733333 27.733333Z"
								p-id="16585" fill="#ffffff"></path>
						</svg>
					</div>
					<p>上架商品</p>
				</li>
				<li @click="toOffFood">
					<div class="svg-container">
					<svg t="1726044940194" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="17678" id="mx_n_1726044940195" width="200" height="200"><path d="M885.75999999 704h-591.6444448l-91.02222186-500.62222187a35.49866667 35.49866667 0 0 0-6.82666667-15.24622293l-91.02222187-113.77777707a34.13333333 34.13333333 0 1 0-54.8408896 42.32533333l85.7884448 106.7235552L231.53777813 744.27733333a34.13333333 34.13333333 0 0 0 33.45066666 27.98933333h620.7715552a34.13333333 34.13333333 0 0 0 0-68.26666666z" fill="#ffffff" p-id="17679"></path><path d="M983.60888853 174.25066666a33.67822187 33.67822187 0 0 0-26.624-12.97066666H298.43911146a34.13333333 34.13333333 0 0 0 0 68.26666666h615.53777707l-78.73422187 343.15377814-458.9795552 31.1751104a34.13333333 34.13333333 0 0 0 2.2755552 68.26666666h2.50311147l484.01066666-32.768A34.36088853 34.36088853 0 0 0 895.99999999 612.75022186l94.208-409.6a33.67822187 33.67822187 0 0 0-6.59911146-28.8995552zM298.43911146 823.0115552a61.44 61.44 0 1 0 61.44 61.44 61.44 61.44 0 0 0-61.44-61.44z" fill="#ffffff" p-id="17680"></path><path d="M844.34488853 884.4515552m-61.44 0a61.44 61.44 0 1 0 122.88 0 61.44 61.44 0 1 0-122.88 0Z" fill="#ffffff" p-id="17681"></path><path d="M554.66666666 252.07466666a34.13333333 34.13333333 0 0 0-34.13333333 34.13333334v139.0364448l-38.00177814-38.00177814a34.13333333 34.13333333 0 0 0-48.24177706 48.24177814l96.256 96.4835552a36.40888853 36.40888853 0 0 0 11.15022186 7.28177813 31.17511147 31.17511147 0 0 0 12.288 2.5031104 32.31288853 32.31288853 0 0 0 12.5155552-2.5031104 33.90577813 33.90577813 0 0 0 11.15022294-7.28177813l97.62133333-96.4835552a34.36088853 34.36088853 0 0 0 0-48.24177814 33.90577813 33.90577813 0 0 0-48.24177813 0l-38.22933334 38.00177814v-139.0364448a34.13333333 34.13333333 0 0 0-34.13333333-34.13333334z" fill="#ffffff" p-id="17682"></path></svg>
					</div>
					<p>下架商品</p>
				</li>
				<li>
					<div class="svg-container">
					<svg t="1726045036066" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="20813" width="200" height="200"><path d="M342.755556 660.764444l-6.769778 46.876445 47.047111-6.656 469.276444-469.333333-40.220444-40.220445-469.333333 469.333333z m489.415111-529.635555l80.497777 80.440889a28.444444 28.444444 0 0 1 0 40.220444l-502.897777 502.840889-103.310223 14.620445a28.444444 28.444444 0 0 1-32.199111-32.199112l14.848-103.082666 502.897778-502.897778a28.444444 28.444444 0 0 1 40.163556 0z" fill="#ffffff" p-id="20814"></path><path d="M682.666667 267.776l97.109333 97.109333 40.220444-40.220444L722.887111 227.555556z" fill="#ffffff" p-id="20815"></path><path d="M841.955556 494.933333V853.333333a28.444444 28.444444 0 0 1-28.444445 28.444445H170.666667a28.444444 28.444444 0 0 1-28.444445-28.444445V210.488889a28.444444 28.444444 0 0 1 28.444445-28.444445h358.4a28.444444 28.444444 0 0 0 0-56.888888H170.666667a85.333333 85.333333 0 0 0-85.333334 85.333333V853.333333A85.333333 85.333333 0 0 0 170.666667 938.666667h642.844444a85.333333 85.333333 0 0 0 85.333333-85.333334V494.933333a28.444444 28.444444 0 1 0-56.888888 0z" fill="#ffffff" p-id="20816"></path></svg>
					</div>
					<p>修改商品</p>
				</li>
				<li>
					<div class="svg-container">
					<svg t="1726045016819" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="19740" width="200" height="200"><path d="M678.4 444.416C655.36 512.512 588.288 558.08 511.488 558.08s-143.872-45.568-166.912-113.664c-5.632-16.384 3.072-34.304 19.456-39.936 16.384-5.632 34.304 3.072 39.936 19.456 14.336 41.984 58.368 71.168 107.52 71.168s93.184-29.184 107.52-71.168c5.632-16.384 23.552-25.088 39.936-19.456 16.384 5.632 25.6 23.552 19.456 39.936z" p-id="19741" fill="#ffffff"></path><path d="M860.672 351.744c-7.168-54.272-53.248-94.72-108.544-94.72h-21.504c-9.728-107.52-104.448-192.512-219.648-192.512S301.568 149.504 291.84 257.024h-21.504c-54.784 0-101.376 40.96-108.032 94.72l-61.44 498.688c-3.584 27.648 5.12 55.296 24.064 76.288 18.432 20.992 45.568 33.28 74.24 33.28h331.264c17.408 0 31.232-13.824 31.232-31.232 0-17.408-13.824-31.232-31.232-31.232H199.168c-10.752 0-20.48-4.608-27.136-12.288s-9.728-16.896-8.192-27.136l61.952-498.688c2.56-22.528 22.528-39.424 45.568-39.424h481.792c23.04 0 43.008 16.896 45.568 39.424l21.504 173.056c2.048 16.896 17.408 29.184 34.304 27.136 17.408-2.048 29.696-17.408 27.648-34.816l-21.504-173.056zM355.328 257.024C363.52 194.56 412.672 144.384 476.16 130.56c2.048-0.512 4.096-1.024 6.656-1.024 2.048-0.512 4.096-0.512 6.656-1.024 7.168-1.024 14.848-1.536 22.016-1.536 80.384 0 146.432 56.832 156.16 129.536h-312.32z" p-id="19742" fill="#ffffff"></path><path d="M709.632 916.992h-11.776c-44.544 0-89.088-18.432-121.344-51.2-33.792-33.792-52.736-78.336-53.248-125.952-0.512-47.616 17.92-92.672 51.712-126.976l1.536-1.536c33.792-34.304 78.848-53.248 127.488-53.248 47.616 0 92.16 18.432 125.952 51.712l1.536 1.536c70.144 69.12 70.656 182.784 1.536 252.928l-1.536 1.536c-33.28 32.256-77.312 51.2-121.856 51.2z m-6.144-296.96c-23.04 0-56.832 5.632-84.48 33.792-47.616 47.616-47.616 121.856 0 169.472 23.04 23.04 53.248 35.84 84.48 35.84 31.744 0 61.952-12.8 84.48-35.84 47.616-47.616 47.616-121.856 0-169.472-27.648-28.16-61.44-33.792-84.48-33.792z" p-id="19743" fill="#ffffff"></path><path d="M780.288 859.136l42.496-42.496 92.16 92.16c11.776 11.776 11.776 30.72 0 42.496-11.776 11.776-30.72 11.776-42.496 0l-92.16-92.16z" p-id="19744" fill="#ffffff"></path></svg>
					</div>
					<p>查看商品</p>
				</li>
			</ul>
		</div>
		<div class="manageorder">
			<h4>&#124;订单管理</h4>
			<el-divider class="driver-line"/>
			<ul>
				<li>
					<div class="svg-container">
					<svg t="1726045208621" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="21859" width="200" height="200"><path d="M511.6928 961.422222h-390.144C62.134044 961.422222 22.755556 931.754667 22.755556 860.910933V101.142756C22.755556 51.057778 69.717333 5.688889 121.554489 5.688889h661.424355C831.744 5.688889 881.777778 51.057778 881.777778 101.142756v454.217955c0 11.690667-17.339733 23.557689-29.622045 23.557689s-29.622044-11.690667-29.622044-23.552V101.137067c0-17.629867-18.238578-35.424711-36.664889-35.424711H121.554489c-18.244267 0-36.482844 17.624178-36.482845 35.424711v765.701689c0 20.241067 18.961067 35.424711 36.482845 35.424711h384c12.276622 0 24.376889 18.147556 24.376889 29.838222 0 14.483911-6.144 29.314844-18.238578 29.314844z" fill="#ffffff" p-id="21860"></path><path d="M520.7552 694.044444h-280.120889c-12.1344 0-24.456533-17.066667-24.456533-29.155555s12.322133-29.155556 24.462222-29.155556h280.1152c12.316444 0 25.002667 12.8 24.462222 31.6416-0.182044 15.115378-12.322133 26.669511-24.462222 26.669511z m196.949333-209.055288h-477.070222c-12.1344 0-24.456533-17.066667-24.456533-29.155556s12.322133-29.155556 24.462222-29.155556h480.142222c12.322133 0 24.462222 17.066667 24.462222 29.155556-0.182044 12.088889-15.399822 29.155556-27.539911 29.155556z m0-210.500267h-477.070222c-12.1344 0-24.456533-17.066667-24.456533-29.155556C216.177778 233.4208 228.499911 216.177778 240.64 216.177778h480.142222c12.140089 0 24.462222 17.066667 24.462222 29.155555-0.182044 12.088889-12.322133 29.155556-27.539911 29.155556z" fill="#ffffff" p-id="21861"></path><path d="M947.598222 1012.662044l-118.698666-111.212088c-5.142756-4.818489-0.853333-19.968 5.888-27.221334 6.741333-7.264711 21.475556-12.623644 26.624-7.805155l118.692977 111.217777c5.216711 4.892444 3.458844 17.6128-7.281777 28.717512-8.493511 9.005511-20.081778 11.127467-25.224534 6.303288z" fill="#ffffff" p-id="21862"></path><path d="M739.555556 938.666667c-78.546489 0-142.222222-63.675733-142.222223-142.222223s63.675733-142.222222 142.222223-142.222222 142.222222 63.675733 142.222222 142.222222-63.675733 142.222222-142.222222 142.222223z m0-40.635734c56.103822 0 101.586489-45.482667 101.586488-101.586489 0-56.103822-45.482667-101.586489-101.586488-101.586488-56.103822 0-101.586489 45.482667-101.586489 101.586488 0 56.103822 45.482667 101.586489 101.586489 101.586489z" fill="#ffffff" p-id="21863"></path></svg>
					</div>
					<p>查看订单</p>
				</li>
				<li>
					<div class="svg-container">
					<svg t="1726045236219" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="22871" width="200" height="200"><path d="M870.741 361.685h72.534v601.771c0 32-26.752 57.557-59.734 57.557H132.267c-32.854 0-59.734-25.6-59.734-57.472V160.725c0-31.786 26.923-57.429 59.563-57.429h541.61v72.533h-528.64V948.48h725.675V361.685zM191.488 698.027h263.51v85.077h-263.51v-85.077z m0-178.39h441.941v85.078H191.488v-85.078z m550.187-95.786a41.899 41.899 0 0 1-72.534-41.686l179.456-331.69a41.899 41.899 0 0 1 72.32 42.112l-179.2 331.264z" fill="#ffffff" p-id="22872"></path></svg>
					</div>
					<p>修改订单</p>
				</li>
			</ul>
		</div>
		<div class="businessmessage">
			<h4>&#124;商家信息</h4>
			<el-divider class="driver-line"/>
			<ul>
				<li>
					<div class="svg-container">
					<svg t="1726045262814" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="23903" width="200" height="200"><path d="M576 928H192c-52.992 0-96-43.093333-96-96V192c0-52.992 43.093333-96 96-96h640c52.992 0 96 43.093333 96 96v384.064c0 17.706667-14.293333 32-32 32s-32-14.293333-32-32V192.021333c0-17.706667-14.4-32-32-32H192c-17.706667 0-32 14.378667-32 32v639.957334c0 17.706667 14.4 32 32 32h384c17.706667 0 32 14.314667 32 32 0 17.706667-14.293333 32.021333-32 32.021333zM128 693.312a32.064 32.064 0 0 1-22.613333-54.698667l159.402666-159.338666a95.786667 95.786667 0 0 1 110.72-17.984l173.589334 86.805333c12.309333 6.186667 27.093333 3.797333 36.8-5.994667l287.402666-287.445333a32.042667 32.042667 0 0 1 45.290667 45.312L631.210667 587.392a95.786667 95.786667 0 0 1-110.72 18.005333l-173.589334-86.826666a31.616 31.616 0 0 0-36.8 6.016l-159.509333 159.317333c-6.186667 6.314667-14.4 9.386667-22.592 9.386667z m320-277.376c-52.906667 0-96-43.093333-96-96s43.093333-96.021333 96-96.021333 96 43.093333 96 96-43.093333 96.021333-96 96.021333z m0-128c-17.6 0-32 14.378667-32 32 0 17.6 14.4 32 32 32s32-14.4 32-32c0-17.621333-14.4-32-32-32zM768 928c-17.706667 0-32-14.293333-32-32v-192.021333c0-17.706667 14.293333-32 32-32s32 14.293333 32 32v192c0 17.706667-14.293333 32.021333-32 32.021333z m128-128c-7.893333 0-15.701333-2.922667-21.909333-8.725333L768 691.669333l-106.090667 99.712a31.936 31.936 0 0 1-45.226666-1.408 31.957333 31.957333 0 0 1 1.408-45.205333l112.213333-105.386667A48.554667 48.554667 0 0 1 768 621.44c14.72 0 28.501333 6.613333 37.696 17.92l112.213333 105.386667A31.957333 31.957333 0 0 1 896 800z" fill="#ffffff" p-id="23904"></path></svg>
					</div>
					<p>上传图片</p>
				</li>
				<li>
					<div class="svg-container">
					<svg t="1726045292386" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="25061" id="mx_n_1726045292386" width="200" height="200"><path d="M314.296 569.362L263.603 758.94c-5.074 9.979 0 24.94 10.138 29.926 5.069 4.992 10.138 4.992 20.28 4.992h10.138l187.561-49.889h5.074l501.852-493.875c15.217-14.971 25.354-34.929 25.354-59.868 0-24.945-10.138-49.884-30.418-69.842l-45.629-44.902c-20.275-19.948-45.624-29.926-70.968-29.926-20.275 0-40.55 9.979-55.762 24.94L314.296 569.362c0-4.987 0-4.987 0 0m633.657-369.157l-50.688 49.889-81.111-79.821 50.698-49.889c10.132-9.979 25.339-4.987 30.413 0l45.629 44.897c5.059 4.992 10.138 9.979 10.138 19.958 0 4.987 0 9.979-5.079 14.966M405.545 574.359l359.921-354.196 81.106 79.816L486.651 654.18l-81.106-79.821zM339.64 719.025l25.349-94.781 70.968 69.842-96.317 24.939zM983.439 394.76c-20.28 0-35.487 14.966-35.487 34.924v458.962c0 24.945-20.275 44.897-45.624 44.897H111.529c-25.354 0-45.624-19.953-45.624-44.897V140.342c0-24.945 20.27-44.902 45.624-44.902h506.926c20.275 0 35.482-14.961 35.482-34.918s-15.206-34.918-35.482-34.918h-512C45.624 25.603 0 75.482 0 130.363v763.264c0 59.873 50.693 104.77 106.455 104.77h800.947c60.831 0 106.46-49.889 106.46-104.771V429.683c0-19.958-15.216-34.923-30.423-34.923" fill="#ffffff" p-id="25062"></path></svg>
					</div>
					<p>修改地址</p>
				</li>
				<li>
					<div class="svg-container">
					<svg t="1726045321192" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="26181" id="mx_n_1726045321192" width="200" height="200"><path d="M808.370944 0h-592.741888A192.840741 192.840741 0 0 0 23.034011 192.595045v638.80991a192.840741 192.840741 0 0 0 192.595045 192.595045h592.741888a192.810029 192.810029 0 0 0 192.595045-192.595045v-638.80991A192.810029 192.810029 0 0 0 808.370944 0z m-269.190811 85.532961v230.954352l-126.994181-51.012656-126.932758 51.012656v-230.954352z m386.203587 745.933418a117.104913 117.104913 0 0 1-117.012776 116.859216h-592.741888a117.166337 117.166337 0 0 1-117.012776-117.012776v-638.80991a116.951353 116.951353 0 0 1 112.80523-116.705656v271.002819a52.978226 52.978226 0 0 0 23.371844 43.979605 52.793954 52.793954 0 0 0 29.637094 9.213605 52.88609 52.88609 0 0 0 19.80925-3.869714l127.946254-51.227641 127.792694 51.381201a52.855378 52.855378 0 0 0 49.507768-5.251755 52.947514 52.947514 0 0 0 23.371843-43.948893V75.643693h195.512687a117.104913 117.104913 0 0 1 117.012776 117.012776z" p-id="26182" fill="#ffffff"></path><path d="M809.660848 596.120209H214.400576a37.468658 37.468658 0 0 0-37.468658 37.468658 37.49937 37.49937 0 0 0 37.468658 37.49937h595.260272a37.49937 37.49937 0 0 0 37.49937-37.49937 37.49937 37.49937 0 0 0-37.49937-37.468658z" p-id="26183" fill="#ffffff"></path><path d="M585.340292 776.338312H214.400576a37.468658 37.468658 0 0 0-37.468658 37.468658 37.49937 37.49937 0 0 0 37.468658 37.49937h370.939716a37.49937 37.49937 0 0 0 37.49937-37.49937 37.49937 37.49937 0 0 0-37.49937-37.468658z" p-id="26184" fill="#ffffff"></path></svg>
					</div>
					<p>商家介绍</p>
				</li>
				<li>
					<div class="svg-container">
					<svg t="1726045354762" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="28179" id="mx_n_1726045354763" width="200" height="200"><path d="M512 1008A496 496 0 1 1 1008 512a488.32 488.32 0 0 1-64 244.48A496.64 496.64 0 0 1 512 1008z m0-896a400 400 0 1 0 348.16 597.12A392.96 392.96 0 0 0 912 512 400 400 0 0 0 512 112z" fill="#ffffff" p-id="28180"></path><path d="M402.56 423.68c31.36-31.36 58.88-51.2 0-120.32s-89.6-16-120.32 16.64 0 166.4 128 296.32 261.12 163.2 296.32 128 83.2-67.84 14.08-123.52-88.96-28.16-120.32 3.2c-21.76 21.76-77.44-24.32-128-72.32S384 448 402.56 423.68z" fill="#ffffff" p-id="28181"></path></svg>
					</div>
					<p>联系方式</p>
				</li>
			</ul>
		</div>

	</div>
	<!-- 弹窗 -->
	<AlertPopup ref="alertPopup" :message="alertMessage" />
</template>

<script setup>
	import {
		ref,
		onMounted,
		computed,
		getCurrentInstance
	} from 'vue'
	import {
		useRoute,
		useRouter
	} from 'vue-router'
	// import axios from 'axios'
	import Backer from '../components/backer.vue'
	import AlertPopup from '../components/AlertPopup.vue';
	import {
		getSessionStorage
	} from '../common';

	// 获取全局 axios 实例
	const instance = getCurrentInstance();
	const axios = instance?.appContext.config.globalProperties.$axios;

	// 获取路由实例和路由参数
	const route = useRoute();
	const router = useRouter();

	// 定义响应式数据
	const businessId = ref(null);
	const business = ref({});
	const foodArr = ref([]);
	const del = ref({});
	const user = ref({});
	
	const toAddFood = () => {
		router.push({
			path: '/addFood'
		})
	}
	
	const toOffFood = () => {
		router.push({
			path: '/offFood'
		})
	}
	
	const toBusinessUpload = () => {
		router.push({
			path: '/businessUpload'
		})
	}

	const initialize = async () => {
		try {
			// 根据userId查询businessId
			user.value = getSessionStorage('user');
			businessId.value = await axios.get(`users/businessId/${user.value.userId}`);

			// console.log(businessId.value)
			// 根据 businessId 查询商家信息
			const businessResponse = await axios.get(`businesses/${businessId.value.data}`);
			business.value = businessResponse;

			// 根据 businessId 查询食品信息
			const foodResponse = await axios.get(`foods/business/${businessId.value.data}`);
			foodArr.value = foodResponse;
			foodArr.value.forEach(food => {
				food.quantity = 0;
			});

		} catch (error) {
			console.error('Error initializing:', error);
		}
	};
	// 在组件创建时调用
	onMounted(() => {
		initialize();
	});

	const alertMessage = ref('');

	// 显示弹窗的方法
	const showAlert = (message) => {
		alertMessage.value = message;
		const popup = instance?.refs.alertPopup;
		popup?.openPopup();
	};

	//下架商品
	const deletefoods = async (foodId) => {
		try {
			// console.log(businessId.value);
			// console.log(foodId);
			const response = await axios.delete('foods', {
				params: {
					businessId: businessId.value.data,
					foodId: foodId
				}
			});
			del.value = response;
			console.log(del.value);
			if (del.value.data > 0) {
				showAlert('下架商品成功！');
			} else {
				showAlert('下架商品失败！');
			}
			router.go(0);
		} catch (error) {
			console.warn(error);
		}
	};
</script>

<style scoped>
	.wrapper .captcha-modal {
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		background-color: rgba(0, 0, 0, 0.5);
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .captcha-modal .captcha-container {
		background-color: white;
		padding: 2vw;
		border-radius: 5vw;
		box-shadow: 0 1vw 5vw rgba(0, 0, 0, 0.2);
		display: flex;
		justify-content: center;
		align-items: center;
		flex-wrap: wrap;
	}

	.wrapper .captcha-modal .captcha-container .captcha-box {
		width: 100%;
		height: 30vw;
		display: flex;
		align-items: center;
		margin-top: 5vw;
		margin: 2vw;
	}

	.wrapper .captcha-modal .captcha-container .captcha-image {
		width: 40vw;
		height: 20vw;
		margin-left: 2vw;
	}

	.wrapper .captcha-modal .captcha-container input {
		border: 1px solid #cdcdcd;
		/* 细线边框，颜色为黑色 */
		outline: none;
		width: 50%;
		height: 12vw;
		font-size: 4vw;
		padding-left: 2vw;
	}

	.wrapper .captcha-modal .captcha-container button {
		border: none;
		outline: none;
		border-radius: 5vw;
		width: 35vw;
		height: 10vw;
		margin: 3vw;
		background-color: #328fce;
		color: #ffffff;
		font-size: 4vw;
		font-weight: 500;
	}

	/****************** 总容器 ******************/
	.wrapper {
		width: 100%;
		height: 100%;
		position: relative;
	}

	/****************** header部分 ******************/
	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: #f43256;
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

	/****************** 商家logo部分 ******************/
	.wrapper .business-logo {
		width: 100%;
		height: 35vw;
		/*使用上外边距避开header部分*/
		margin-top: 12vw;

		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper .business-logo img {
		width: 40vw;
		height: 30vw;
		border-radius: 5px;
	}

	/****************** 商家信息部分 ******************/
	.wrapper .business-info {
		width: 100%;
		height: 20vw;

		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.wrapper .business-info h1 {
		font-size: 5vw;
	}

	.wrapper .business-info p {
		font-size: 3vw;
		color: #666;
		margin-top: 1vw;
	}

	.wrapper .managefood {
		width: 100vw;
		height: 40vw;
	}

	.wrapper .managefood h4 {
		color: #f43256;
		font-size: 5.5vw;
		margin-left: 2vw;
		margin-bottom: 2vw;
	}
	
	.driver-line {
		margin: 0;
		padding: 0;
	}

	.wrapper .managefood ul {
		width: 100vw;
		height: 35vw;
		display: flex;
		align-items: center;
		flex-wrap: wrap;
	}

	.wrapper .managefood ul li {
		width: 25vw;
		height: 25vw;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-wrap: wrap;
	}

	.wrapper .managefood ul li .svg-container {
		width: 15vw;
		height: 15vw;
		background-color: #ff6241;
		/* 背景色 */
		border-radius: 50%;
		/* 圆形边框 */
		overflow: hidden;
		position: relative;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	
	.wrapper .managefood ul li .svg-container svg{
		width: 10vw;
		height: 10vw;
	}
/* 	.wrapper .managefood ul li .svg-container p{
		
	} */

	.wrapper .manageorder {
		width: 100vw;
		height: 40vw;
	}

	.wrapper .manageorder h4 {
		color: #f43256;
		font-size: 5.5vw;
		margin-left: 2vw;
		margin-bottom: 2vw;
	}

	.wrapper .manageorder ul {
		width: 100vw;
		height: 35vw;
		display: flex;
		align-items: center;
		flex-wrap: wrap;
	}

	.wrapper .manageorder ul li {
		width: 25vw;
		height: 25vw;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-wrap: wrap;
	}

	.wrapper .manageorder ul li .svg-container {
		width: 15vw;
		height: 15vw;
		background-color: #ff6241;
		/* 背景色 */
		border-radius: 50%;
		/* 圆形边框 */
		overflow: hidden;
		position: relative;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	
	.wrapper .manageorder ul li .svg-container svg{
		width: 10vw;
		height: 10vw;
	}

	.wrapper .businessmessage {
		width: 100vw;
		height: 40vw;
	}

	.wrapper .businessmessage h4 {
		color: #f43256;
		font-size: 5.5vw;
		margin-left: 2vw;
		margin-bottom: 2vw;
	}

	.wrapper .businessmessage ul {
		width: 100vw;
		height: 35vw;
		display: flex;
		align-items: center;
		flex-wrap: wrap;
	}

	.wrapper .businessmessage ul li {
		width: 25vw;
		height: 25vw;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-wrap: wrap;
	}

	.wrapper .businessmessage ul li .svg-container {
		width: 15vw;
		height: 15vw;
		background-color: #ff6241;
		/* 背景色 */
		border-radius: 50%;
		/* 圆形边框 */
		overflow: hidden;
		position: relative;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	
	.wrapper .businessmessage ul li .svg-container svg{
		width: 10vw;
		height: 10vw;
	}
</style>