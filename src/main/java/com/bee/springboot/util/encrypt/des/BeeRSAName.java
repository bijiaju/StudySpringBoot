package com.bee.springboot.util.encrypt.des;

import org.apache.commons.codec.binary.Hex;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密 RSA 签名算法
 */
public class BeeRSAName {

    public static final String src = "D撒旦法sddes";

    public static void main(String[] args) {
        jdkRSA();
    }


    public static void jdkRSA() {

        try{
            //1.初始化密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            //System.out.println("rsaPublicKey: "+ Base64.encodeBase64String(rsaPublicKey.getEncoded()));
            //System.out.println("rsaPrivateKey: "+ Base64.encodeBase64String(rsaPrivateKey.getEncoded()));

            //2.私钥加密，公钥解密-----加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(privateKey);
            signature.update(src.getBytes());
            byte[] result = signature.sign();
            System.out.println("私钥加密，公钥解密-----加密： "+ Hex.encodeHexString(result));

            //签名验证
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());//  主要x509标准进行编码
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(publicKey);
            signature.update(src.getBytes());
            boolean bool = signature.verify(result);
            System.out.println("jdk rsa verify :　"+bool);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
