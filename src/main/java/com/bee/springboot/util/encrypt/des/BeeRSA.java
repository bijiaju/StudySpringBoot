package com.bee.springboot.util.encrypt.des;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

/**
 * 非对称加密 RSA
 */
public class BeeRSA {

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
            System.out.println("rsaPublicKey: "+ Base64.encodeBase64String(rsaPublicKey.getEncoded()));
            System.out.println("rsaPrivateKey: "+ Base64.encodeBase64String(rsaPrivateKey.getEncoded()));

            //2.私钥加密，公钥解密-----加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");//加密方式， 工作模式  ，填充方式
            cipher.init(Cipher.ENCRYPT_MODE,privateKey);
            byte[] result = cipher.doFinal(src.getBytes());//等待价密的文字
            System.out.println("私钥加密，公钥解密-----加密： "+ Base64.encodeBase64String(result));//十六进制编码修改

            //3.私钥加密，公钥解密-----解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());//  主要x509标准进行编码
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");//加密方式， 工作模式  ，填充方式
            cipher.init(Cipher.DECRYPT_MODE,publicKey);
            result = cipher.doFinal(result);//等待价密的文字
            System.out.println("私钥加密，公钥解密-----解密： "+new String(result));
            /*********************************分割*********************************************************/
            //4.公钥加密，私钥解密---加密
            x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());//  主要x509标准进行编码
            keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");//加密方式， 工作模式  ，填充方式
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            result = cipher.doFinal(src.getBytes());//等待价密的文字
            System.out.println("公钥加密，私钥解密---加密： "+Base64.encodeBase64String(result));//十六进制编码修改

            //5.公钥加密，私钥解密---解密
            pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
             keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");//加密方式， 工作模式  ，填充方式
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            result = cipher.doFinal(result);//等待价密的文字
            System.out.println("公钥加密，私钥解密---加密： "+new String(result));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
