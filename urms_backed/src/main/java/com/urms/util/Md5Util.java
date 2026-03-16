package com.urms.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 */
public class Md5Util {

    /**
     * 对字符串进行MD5加密，返回32位小写的十六进制字符串
     * @param input 要加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String input) {
        if (input == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext.toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密失败", e);
        }
    }

    /**
     * 验证密码是否正确
     * 由于前端已经对密码进行了MD5加密传输，这里直接比较两个MD5字符串
     * @param inputPassword 前端传输过来的已加密密码
     * @param dbPassword 数据库中存储的密码
     * @return 是否匹配
     */
    public static boolean verify(String inputPassword, String dbPassword) {
        if (inputPassword == null || dbPassword == null) {
            return false;
        }
        // 前端传来的已经是MD5加密后的密码，直接比较
        return inputPassword.equalsIgnoreCase(dbPassword);
    }
}