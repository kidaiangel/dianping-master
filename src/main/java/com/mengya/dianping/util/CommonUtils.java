package com.mengya.dianping.util;

import com.mengya.dianping.constant.UserConstant;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @ClassName CommonUtils
 * @Description 通用工具类
 * @Author xiongwei.wu
 * @Date 2021/4/20 10:12
 **/
public class CommonUtils {
    /**
     * 处理错误返回结果集信息，加逗号隔开
     *
     * @param bindingResult 错误返回结果集
     * @return String
     */
    public static String processErrorString(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            stringBuilder.append(fieldError.getDefaultMessage()).append(",");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    /**
     * sun BASE64Encoder MD5方式加密
     *
     * @param str 加密字符串
     * @return 密文
     * @throws NoSuchAlgorithmException Base64加密算法异常信息
     */
    public static String encoder(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(UserConstant.MESSAGE_DIGEST_MD5);
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(messageDigest.digest(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * BASE64Decoder 解密
     *
     * @param str 密文
     * @return 字符串
     */
    public static String decoder(String str) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return new String(decoder.decodeBuffer(str), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try {
            String decoder = CommonUtils.decoder("4QrcOUm6Wau+VuBX8g+IPg==");
            System.out.println(decoder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
