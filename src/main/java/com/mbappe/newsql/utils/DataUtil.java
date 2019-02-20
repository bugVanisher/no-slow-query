package com.mbappe.newsql.utils;

import java.math.BigInteger;
import java.security.*;

/**
 * @author: gannicus at 2018/7/25
 */
public class DataUtil {

    public static final String EMPTY_STRING = "";

    public static String getMd5(String str){
        try {
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
