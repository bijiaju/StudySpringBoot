/*
package com.bee.springboot.util.encrypt.des;


import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.Security;

*/
/**
 * SHA
 *//*

public class BeeSHA {

    public static final String src = "D撒旦法sddes";

    public static void main(String[] args) {
        jdkSHA1();
        bcSHA1();
        bcSHA224();
        ccSHA1();
        ccSHA2();
    }


    public static void jdkSHA1() {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(src.getBytes());
            System.out.println("Jdk sha-1: " + Hex.encodeHexString(md.digest()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    */
/**
     * 通过引入BC的maven实现的
     *//*

    public static void bcSHA1() {
        try {
            Digest digest = new SHA1Digest();
            digest.update(src.getBytes(),0,src.getBytes().length);
            byte[] md4Bytes = new byte[digest.getDigestSize()];
            digest.doFinal(md4Bytes,0);
            System.out.println("BC SHA1: " + org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bcSHA224() {

        try {
            Digest digest = new SHA224Digest();
            digest.update(src.getBytes(),0,src.getBytes().length);
            byte[] sha224Bytes = new byte[digest.getDigestSize()];
            digest.doFinal(sha224Bytes,0);
            System.out.println("BC sha224: " + org.bouncycastle.util.encoders.Hex.toHexString(sha224Bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void bcSHA224_2() {
        //使用 provider的方式实现，和之前一样

    }

    public static void ccSHA1() {
        System.out.println("CC ccSHA1: " + DigestUtils.sha1Hex(src.getBytes()));
    }

    public static void ccSHA2() {
        System.out.println("CC ccSHA2: " + DigestUtils.sha1Hex(src));
    }

}*/
