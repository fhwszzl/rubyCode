package com.zzl.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * .
 * 
 * @author zhengmo
 *
 */
public class MD5 {
    /**
     * 数字.
     */
    protected static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * B2B密码key.
     */
    private static final String B2BKEY = "b2BPWd!#";

    /**
     * B2B密码IVkey.
     */
    private static final String B2BIVKEY = "B2BpWdiv";
    
    /**
     * B2B密码IVkey.
     */
    private static final String ENCRYPTCOPHER  = "DES/CBC/PKCS5Padding";

    /**
     * 解密.
     * 
     * @param decryptString
     *            解密字符串
     * @param key
     *            key
     * @param kiv
     *            kiv
     * @return 解密后字符串
     */
    public static String decrypt(String decryptString, String key, String kiv) {
        try {
            decryptString = decryptString.replace('@', '=');
            byte[] rgbkey = key.getBytes("UTF-8");
            byte[] rgbIV = kiv.getBytes("UTF-8");
            byte[] inputByteArray = Base64.decodeBase64(decryptString);
            DESKeySpec desKeySpec = new DESKeySpec(rgbkey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec ivp = new IvParameterSpec(rgbIV);
            Cipher encryptCipher = Cipher.getInstance(ENCRYPTCOPHER);
            encryptCipher.init(2, secretKey, ivp);
            byte[] decryptbyte = encryptCipher.doFinal(inputByteArray);
            return new String(decryptbyte);
        } catch (Exception ex) {
            return decryptString;
        }
    }

    /**
     * 解密.
     * 
     * @param decryptString
     *            解密字符串
     * @return 解密后字符串
     */
    public static String decryptB2BPWD(String decryptString) {
        try {
            if (StringUtils.isBlank(decryptString)) {
                return decryptString;
            }
            if (!decryptString.contains("云")) { // B2B兼容老密码,如果不带云,则不用解密
                return decryptString;
            } else {
                decryptString = decryptString.replace("云", ""); // 带云,则去掉云来解密
            }
            return decrypt(decryptString, B2BKEY, B2BIVKEY);
        } catch (Exception ex) {
            return decryptString;
        }
    }

    /**
     * 加密.
     * 
     * @param encryptStr
     *            加密字符串
     * @param key
     *            key
     * @param kiv
     *            kiv
     * @return 加密后字符串
     */
    public static String encrypt(String encryptStr, String key, String kiv) {
        try {
            byte[] rgbkey = key.getBytes("UTF-8");
            byte[] rgbIV = kiv.getBytes("UTF-8");
            byte[] inputByteArray = encryptStr.getBytes("UTF-8");
            DESKeySpec desKeySpec = new DESKeySpec(rgbkey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec ivp = new IvParameterSpec(rgbIV);
            Cipher encryptCipher = Cipher.getInstance(ENCRYPTCOPHER);
            encryptCipher.init(1, secretKey, ivp);
            byte[] encrybyte = encryptCipher.doFinal(inputByteArray);
            String sfsadfString = Base64.encodeBase64String(encrybyte);
            return sfsadfString;
        } catch (Exception ex) {
            return encryptStr;
        }
    }

    /**
     * 获取文件MD5值.
     * 
     * @param file
     *            文件
     * @return .
     * @throws Exception
     *             异常
     */
    public static String getFileMD5String(File file) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            FileChannel ch = in.getChannel();
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            return bufferToHex(md5.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取文件MD5值.
     * 
     * @param filePath
     *            文件
     * @return .
     * @throws Exception
     *             异常
     */
    public static String getFileMD5String(String filePath) throws Exception {
        File file = null;
        FileInputStream in = null;
        try {
            file = new File(filePath);
            in = new FileInputStream(file);
            FileChannel ch = in.getChannel();
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            return bufferToHex(md5.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 字节加密.
     * 
     * @param bytes
     *            .
     * @return .
     * @throws Exception .
     */
    public static String getMD5String(byte[] bytes) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(bytes);
        return bufferToHex(md5.digest());
    }

    /**
     * 字符串MD5 .
     * 
     * @param s
     *            字符串.
     * @return MD5加密
     * @throws Exception .
     */
    public static String getMD5String(String s) throws Exception {
        return getMD5String(s.getBytes());
    }

    /**
     * 方法.
     * 
     * @param args
     *            .
     */
    public static void main(String[] args) {
    }

    // public static void main(String[] args) {
    // String s = MD5.md5("123456");
    // for (int i = 1; i < 666; i++) {
    // s = MD5.md5(s.toUpperCase());
    // }
    // System.out.println(s);
    // }
    /**
     * md5加密.
     * 
     * @param plainText
     *            .
     * @return String .
     */
    public static String md5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 
     * 加密.
     * 
     * @param value
     *            。.
     * @return string
     */
    public static String secret(String value) {

        if (null == value || "".equals(value)) {
            return value;
        }

        String first = MD5.md5(value);

        if (null == first || "".equals(first)) {
            return value;
        }
        for (int v = 1; v < 666; v++) {
            if (null == first) {
                return value;
            }
            first = MD5.md5(first.toUpperCase());
        }
        return first;
    }

    /**
     * 16进制.
     * 
     * @param bt
     *            .
     * @param stringbuffer
     *            .
     */
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    /**
     * 字节转16进制.
     * 
     * @param bytes
     *            .
     * @return .
     */
    private static String bufferToHex(byte[] bytes) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    /**
     * 16进制转换.
     * 
     * @param bytes
     *            .
     * @param m
     *            .
     * @param n
     *            .
     * @return .
     */
    private static String bufferToHex(byte[] bytes, int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }
}
