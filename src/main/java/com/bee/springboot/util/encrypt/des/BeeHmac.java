/*
package com.bee.springboot.util.encrypt.des;


        import org.apache.commons.codec.binary.Hex;
        import org.apache.commons.codec.digest.DigestUtils;
        import org.bouncycastle.crypto.Digest;
        import org.bouncycastle.crypto.digests.MD5Digest;
        import org.bouncycastle.crypto.digests.SHA1Digest;
        import org.bouncycastle.crypto.digests.SHA224Digest;
        import org.bouncycastle.crypto.macs.HMac;
        import org.bouncycastle.crypto.params.KeyParameter;

        import javax.crypto.KeyGenerator;
        import javax.crypto.Mac;
        import javax.crypto.SecretKey;
        import javax.crypto.spec.SecretKeySpec;
        import java.security.MessageDigest;

*/
/**
 * mac
 *//*

public class BeeHmac {

    public static final String src = "D撒旦法sddes";

    public static void main(String[] args) {
        jdkHmacMD5();
        bcHmacMD5();
    }


    public static void jdkHmacMD5() {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");//初始化KeyGenerator
            SecretKey secretKey = keyGenerator.generateKey();//产生密钥
            //byte[] key = secretKey.getEncoded();//获得密钥

            byte[] key = new byte[]{'a','a','a','a','a','a','a','a','a','a'};//为了和下面生成的一致

            SecretKeySpec restoreSecretKey = new SecretKeySpec(key, "HmacMD5");//还原密钥
            Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());//实例化mac
            mac.init(restoreSecretKey);//初始化mac
            byte[] hmacMD5Bytes = mac.doFinal(src.getBytes());//执行摘要
            System.out.println("jdk hmacMD5: "+Hex.encodeHexString(hmacMD5Bytes));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    */
/**
     * 通过引入BC的maven实现的
     *//*

    public static void bcHmacMD5() {
        try {
            HMac hmac = new HMac(new MD5Digest());
            hmac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("aaaaaaaaaa")));
            hmac.update(src.getBytes(),0,src.getBytes().length);

            byte[] bcHmacMD5Bytes = new byte[hmac.getMacSize()];//执行摘要
            hmac.doFinal(bcHmacMD5Bytes,0);
            System.out.println("BC bcHmacMD5B: " + org.bouncycastle.util.encoders.Hex.toHexString(bcHmacMD5Bytes));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}*/
