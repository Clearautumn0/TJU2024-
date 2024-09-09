package com.neusoft.elmboot.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.IOException;
import java.util.Random;

import net.coobird.thumbnailator.Thumbnails;

public class CaptchaUtil {
    // 生成随机的验证码文本
    public static String generateCaptchaText(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < length; i++) {
            captcha.append(chars.charAt(random.nextInt(chars.length())));
        }
        return captcha.toString();
    }
    // 生成验证码图片
    public static BufferedImage createCaptchaImage(String captchaText) throws IOException {
        int width = 150;
        int height = 50;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();

        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 设置字体
        g.setFont(new Font("Arial", Font.BOLD, 40));

        // 画验证码
        g.setColor(Color.BLACK);
        g.drawString(captchaText, 10, 35);

        // 添加干扰线条
        addNoiseLines(g, width, height);

        // 添加噪点
        addNoiseDots(bufferedImage);

        // 释放 Graphics2D 资源
        g.dispose();

        // 应用模糊处理
        return applyBlur(bufferedImage);
    }

    // 添加干扰线条
    private static void addNoiseLines(Graphics2D g, int width, int height) {
        Random rand = new Random();
        g.setColor(Color.GRAY);
        for (int i = 0; i < 5; i++) { // 生成5条干扰线条
            int x1 = rand.nextInt(width);
            int y1 = rand.nextInt(height);
            int x2 = rand.nextInt(width);
            int y2 = rand.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    // 添加噪点
    private static void addNoiseDots(BufferedImage bufferedImage) {
        Random rand = new Random();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        for (int i = 0; i < 100; i++) { // 生成100个随机噪点
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            int rgb = rand.nextBoolean() ? Color.BLACK.getRGB() : Color.GRAY.getRGB(); // 随机点的颜色
            bufferedImage.setRGB(x, y, rgb);
        }
    }

    // 模糊处理方法
    public static BufferedImage applyBlur(BufferedImage bufferedImage) {
        // 模糊处理方法可以和之前的一样
        float[] blurKernel = {
                1f / 9f, 1f / 9f, 1f / 9f,
                1f / 9f, 1f / 9f, 1f / 9f,
                1f / 9f, 1f / 9f, 1f / 9f
        };
        Kernel kernel = new Kernel(3, 3, blurKernel);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        return op.filter(bufferedImage, null);
    }
}
