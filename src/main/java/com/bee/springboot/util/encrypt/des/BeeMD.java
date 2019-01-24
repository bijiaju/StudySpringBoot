package com.bee.springboot.util.encrypt.des;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.Security;

/**
 * MD5
 * PS: jdk只提供了md5、2没有提供md4；使用cc实现md5
 * PS: 通过cc可以简化一些操作，cc提供md4的实现，
 */
public class BeeMD {

    public static final String src = "D撒旦法sddes";

    public static void main(String[] args) {
        jdkMD5();
        jdkMD2();
        bcMD4();
        bcMD5();
        ccMD5();
    }


    public static void jdkMD5() {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md.digest(src.getBytes());
            System.out.println("Jdk md5: " + Hex.encodeHexString(md5Bytes));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void jdkMD2() {

        try {
            MessageDigest md = MessageDigest.getInstance("MD2");
            byte[] md2Bytes = md.digest(src.getBytes());
            System.out.println("Jdk md2: " + Hex.encodeHexString(md2Bytes));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过引入BC的maven实现的
     */
    public static void bcMD4() {

        try {
            Security.addProvider(new BouncyCastleProvider());// 后面也能实现，只是这种方式是通过添加provider实现的
            MessageDigest md = MessageDigest.getInstance("MD4");
            byte[] md4Bytes = md.digest(src.getBytes());
            System.out.println("BC md4: " +  Hex.encodeHexString(md4Bytes));

           /* Digest digest = new MD4Digest();
            digest.update(src.getBytes(),0,src.getBytes().length);
            byte[] md4Bytes = new byte[digest.getDigestSize()];
            digest.doFinal(md4Bytes,0);
            System.out.println("BC md4: " + org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void bcMD5() {

        try {
            Digest digest = new MD5Digest();
            digest.update(src.getBytes(),0,src.getBytes().length);
            byte[] md5Bytes = new byte[digest.getDigestSize()];
            digest.doFinal(md5Bytes,0);
            System.out.println("BC md5: " + org.bouncycastle.util.encoders.Hex.toHexString(md5Bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void ccMD5() {
        System.out.println("CC md5: " + DigestUtils.md5Hex(src.getBytes()));
    }

    public static void ccMD2() {
        System.out.println("CC md2: " + DigestUtils.md2Hex(src.getBytes()));
    }

}