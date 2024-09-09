<template>
    <div v-if="isVisible" class="popup-container">
        <div class="popup">
            <!-- 关闭按钮图片放在弹窗右上角 -->
            <img @click="closePopup" src="../assets/叉号.png" alt="关闭" class="close-button" />
            <p class="mes">{{ message }}</p>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

// 接收父组件传递的 `message` 属性
const props = defineProps({
    message: {
        type: String,
        required: true
    }
});

// 控制弹窗显示与隐藏的状态
const isVisible = ref(false);

// 打开弹窗方法
const openPopup = () => {
    isVisible.value = true;
};

// 关闭弹窗方法
const closePopup = () => {
    isVisible.value = false;
};

// 公开 `openPopup` 方法供父组件调用
defineExpose({
    openPopup
});
</script>

<style scoped>
.popup-container {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1001;
}

.popup {
    background-color: white;
    padding: 10vw;
    border-radius: 2vw;
    text-align: center;
    position: relative;
    /* 为了让关闭按钮绝对定位 */
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
}

.close-button {
    position: absolute;
    top: 2vw;
    /* 调整关闭按钮的垂直位置 */
    right: 2vw;
    /* 调整关闭按钮的水平位置 */
    width: 3vw;
    /* 控制关闭按钮的大小 */
    cursor: pointer;
}

.mes{
    font-size: 3vw;
}
</style>
