<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <Backer></Backer>
      <div class="setUserImg">
        <p>设置头像</p>
      </div>
      <div @click="submitImage" class="reposit">
        <p>保存</p>
      </div>
    </header>
    <!-- 隐藏的文件输入框 -->
    <input type="file" ref="fileInput" @change="handleFileChange" accept="image/*" style="display: none;" />
    <div class="button-box">
      <!-- 绑定点击事件的 el-button -->
      <el-button type="primary" @click="triggerFileInput">
        上传头像
      </el-button>
    </div>
    <div class="userimg-box">
      <div v-if="base64Image" class="image-preview">
        <img :src="base64Image" alt="头像预览" />
      </div>
    </div>
    <div class="bottom">
      <!-- 条件渲染，仅在选择文件后显示预览 -->
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, getCurrentInstance } from 'vue';
import { useRouter } from 'vue-router';
import Backer from '../components/backer.vue';
import { getSessionStorage, setLocalStorage } from '../common.js';

const instance = getCurrentInstance();
const axios = instance?.appContext.config.globalProperties.$axios;

const router = useRouter();
const user = ref({});
const base64Image = ref('');

// 引用文件输入框
const fileInput = ref(null);

// 触发隐藏的文件输入框点击事件
const triggerFileInput = () => {
  fileInput.value.click();
};

onMounted(() => {
  user.value = getSessionStorage('user') || {
    userName: '未登录',
    userId: ''
  };
});

const handleFileChange = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      base64Image.value = e.target.result;
      showAlert('上传头像成功');
			toggleinputfood();
    };
    reader.readAsDataURL(file);
  }
};

const submitImage = async () => {
  try {
    if (base64Image.value) {
      const response = await axios.put('users', {
        userId: user.value.userId,
        userImg: base64Image.value
      });
      if (response.data === 1) {
        setLocalStorage(`userImg${user.value.userId}`, base64Image.value);
      }
      router.go(-1);
    }
  } catch (error) {
    console.error(error);
  }
};
</script>

<style scoped>
/* 总容器 */
.wrapper {
  width: 100%;
  height: 100%;
}

/* header */
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

.wrapper header .setUserImg {
  font-weight: 600;
}

.wrapper header .reposit {
  margin-right: 3.5vw;
  font-weight: 525;
}

.wrapper .button-box {
  width: 100vw;
  height: 20vw;
  display: flex;
  align-items: center;
  justify-content: center;
}

.wrapper .button-box input {
  width: 60vw;
  height: 10vw;
}

.wrapper .button-box button {
  width: 60vw;
  height: 10vw;
  font-size: 4vw;
}

.wrapper .userimg-box {
  width: 100vw;
  height: 30vw;
  display: flex;
  align-items: center;
  justify-content: center;
}

.wrapper .userimg-box .image-preview {
  width: 30vw;
  height: 30vw;

}

.wrapper .userimg-box .image-preview img {
  width: 100%;
  height: 100%;
}

.wrapper .bottom {
  width: 100%;
  height: 10vw;
  background-color: #ebebeb;
  z-index: -1;
}
</style>
