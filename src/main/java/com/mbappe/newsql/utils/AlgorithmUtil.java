package com.mbappe.newsql.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: gannicus at 2018/7/25
 */
@Component
public class AlgorithmUtil {

    @Value("${mbappe.security.secretkey}")
    private String salt;

    public String getMd5(String str) {
        try {
            str = str + ":" + salt;
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException ignored) {
        }
        return null;
    }

}
