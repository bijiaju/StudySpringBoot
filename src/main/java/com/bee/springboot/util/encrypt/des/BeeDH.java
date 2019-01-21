package com.bee.springboot.util.encrypt.des;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

/**
 * 非对称加密
 */
public class BeeDH {

    public static final String src = "测试des";

    public static void main(String[] args) {
        jdkDH();
    }


    public static void jdkDH() {

        try{
            //1.初始化发送方密钥
            KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            senderKeyPairGenerator.initialize(512);
            KeyPair sendKeyPair = senderKeyPairGenerator.generateKeyPair();
            byte[] senderPublicKeyEnc = sendKeyPair.getPublic().getEncoded();//发送方公钥,发送给接收方 （网络、文件 ...）

            //2.初始化接收方的密钥
            KeyFactory receiverKeyFactory = KeyFactory.getInstance("DH");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnc);//  主要x509标准进行编码
            PublicKey receiverPublicKey = receiverKeyFactory.generatePublic(x509EncodedKeySpec);
            DHParameterSpec dhParameterSpec = ((DHPublicKey) receiverPublicKey).getParams();
            KeyPairGenerator receiverKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            receiverKeyPairGenerator.initialize(dhParameterSpec);
            KeyPair receiverKeyPair = receiverKeyPairGenerator.generateKeyPair();
            PrivateKey receiverPrivateKey = receiverKeyPair.getPrivate();
            byte[] receiverPublicKeyEnc = receiverKeyPair.getPublic().getEncoded();

            //3.密钥构建
            KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance("DH");
            receiverKeyAgreement.init(receiverPrivateKey);
            receiverKeyAgreement.doPhase(receiverPublicKey,true);
            SecretKey receiverDesKey = receiverKeyAgreement.generateSecret("DES");

            KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
            x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
            PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);
            KeyAgreement senderKeyAgreement = KeyAgreement.getInstance("DH");
            senderKeyAgreement.init(sendKeyPair.getPrivate());
            senderKeyAgreement.doPhase(senderPublicKey,true);
            SecretKey senderDesKey = senderKeyAgreement.generateSecret("DES");
            if(Objects.equals(receiverDesKey,senderDesKey)){
                System.out.println("双方密钥相同");
            }

            //加密
            Cipher cipher = Cipher.getInstance("DES");//加密方式， 工作模式  ，填充方式
            cipher.init(Cipher.ENCRYPT_MODE,senderDesKey);
            byte[] result = cipher.doFinal(src.getBytes());//等待价密的文字
            System.out.println("jdk DH加密： "+result);
            System.out.println("jdk DH加密： "+ Hex.encodeHexString(result));//十六进制编码修改

            // 解密： 上面用到的是  一套东西，  只是在解密的模式上不同
            cipher.init(Cipher.DECRYPT_MODE,receiverDesKey);
            result = cipher.doFinal(result);//等待解密的文字
            System.out.println("jdk DH解密： "+new String(result));


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
