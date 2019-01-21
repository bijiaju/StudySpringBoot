package com.bee.springboot.util.encrypt.des;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * 学习Des时创建的文件
 */
public class BeeDes {

    public static final String src = "测试des";

    public static void main(String[] args){
        jdkDes();
    }

    /**
     * jdk的Des加密方法
     */
    public static  void jdkDes(){
        try{
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);//根据Des的属性，自己生成的是56，也可以是64
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //Key转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertSecretKey = factory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());//等待价密的文字
            System.out.println("jdk加密： "+result);
            System.out.println("jdk加密： "+ Hex.encodeHexString(result));//十六进制编码修改

            // 解密： 上面用到的是  一套东西，  只是在解密的模式上不同
            cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
            result = cipher.doFinal(result);//等待解密的文字
            System.out.println("jdk解密： "+new String(result));
        }catch (Exception e){
            e.printStackTrace();
        }

    }


  /*  *//**
     * bc的Des加密方法
     *//*
    public static  void bcDes(){

        try{
            Security.addProvider(new BouncyCastleProvider());//报错是因为jdk版本问题
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");
            keyGenerator.getProvider();
            keyGenerator.init(56);//根据Des的属性，自己生成的是56，也可以是64
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //Key转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertSecretKey = factory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());//等待价密的文字
            System.out.println("jdk加密： "+result);
            System.out.println("jdk加密： "+ Hex.encodeHexString(result));//十六进制编码修改

            // 解密： 上面用到的是  一套东西，  只是在解密的模式上不同
            cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
            result = cipher.doFinal(result);//等待解密的文字
            System.out.println("jdk解密： "+new String(result));
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/
}
