package com.bee.springboot.util.encrypt.des;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * 学习Aes时创建的文件
 */
public class BeeAes {

    public static final String src = "测试des";

    public static void main(String[] args){
        jdkAes();
    }

    /**
     * jdk的Des加密方法
     */
    public static  void jdkAes(){
        try{
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);//根据Des的属性，自己生成的是56，也可以是64
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //Key转换
            Key key = new SecretKeySpec(bytesKey, "AES");

            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//加密方式， 工作模式  ，填充方式
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] result = cipher.doFinal(src.getBytes());//等待价密的文字
            System.out.println("jdk aes加密： "+result);
            System.out.println("jdk aes加密： "+ Hex.encodeHexString(result));//十六进制编码修改

            // 解密： 上面用到的是  一套东西，  只是在解密的模式上不同
            cipher.init(Cipher.DECRYPT_MODE,key);
            result = cipher.doFinal(result);//等待解密的文字
            System.out.println("jdk aes解密： "+new String(result));
        }catch (Exception e){
            e.printStackTrace();
        }

    }


  /*  *//**
     * bc的Aes加密方法
     *//*
    public static  void bcDes(){
         //todo
    }*/
}
