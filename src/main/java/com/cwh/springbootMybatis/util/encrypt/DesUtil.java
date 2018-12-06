package com.cwh.springbootMybatis.util.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.Charset;
import java.security.Key;

/**
 * DES加密介绍
 * DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
 * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
 * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现
 */
public class DesUtil {
    //static Key key;
    private static final String  SKEY    = "9Ta4OaHS";
    private static final Charset CHARSET = Charset.forName("utf-8");

    public static void main(String[] args) {
       // System.out.println(SKEY);

        // 待加密内容
        String str = "测试内容，今天周四";
        String encryptResult = encrypt(str);
        System.out.println(encryptResult);
        // 直接将如上内容解密
        String decryResult = "";
        try {
            decryResult = decrypt(encryptResult);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(decryResult);

    }

   /* static Key key;

    public static Key getKey() {
        return key;
    }*/

    /* Key key;

    public  DesUtil(String str) {
        setKey(str);// 生成密匙
    }

    public  DesUtil() {
        setKey("9Ta4OaHZdpA");
    }*/


    /**
     * 加密
     * @param srcStr
     * @return
     */
    public static String encrypt(String srcStr) {
        byte[] src = srcStr.getBytes(CHARSET);
        byte[] buf = encrypt(src, SKEY);
        return parseByte2HexStr(buf);
    }

    /**
     * 解密
     * @param hexStr
     * @return
     * @throws Exception
     */
    public static String decrypt(String hexStr) throws Exception {
        byte[] src = parseHexStr2Byte(hexStr);
        byte[] buf = decrypt(src, SKEY);
        return new String(buf, CHARSET);
    }
    /**
     * 加密
     * @param srcStr
     * @param charset
     * @param sKey
     * @return
     */
    public  static String encrypt(String srcStr, Charset charset, String sKey) {
        byte[] src = srcStr.getBytes(charset);
        byte[] buf = encrypt(src, sKey);
        return parseByte2HexStr(buf);
    }

    /**
     * 根据参数生成KEY
     */
    public static String setKey(String strKey) {
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            return keyFactory.generateSecret(new DESKeySpec(strKey.getBytes("UTF8"))).toString();
        } catch (Exception e) {
            throw new RuntimeException(//NOSONAR
                    "Error initializing SqlMap class. Cause: " + e);
        }
    }

    /**
     * 解密
     *
     * @param hexStr
     * @param sKey
     * @return
     * @throws Exception
     */
    public static String decrypt(String hexStr, Charset charset, String sKey) throws Exception {
        byte[] src = parseHexStr2Byte(hexStr);
        byte[] buf = decrypt(src, sKey);
        return new String(buf, charset);
    }

    /**
     * 加密
     * @param data
     * @param sKey
     * @return
     */
    public static byte[] encrypt(byte[] data, String sKey) {
        try {
            byte[] key = sKey.getBytes();
            // 初始化向量
            IvParameterSpec iv = new IvParameterSpec(key);
            DESKeySpec desKey = new DESKeySpec(key);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成securekey
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(data);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param src
     * @param sKey
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String sKey) throws Exception {
        byte[] key = sKey.getBytes();
        // 初始化向量
        IvParameterSpec iv = new IvParameterSpec(key);
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key);
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


}
