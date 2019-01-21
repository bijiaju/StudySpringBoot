package com.bee.springboot.util.encrypt.des;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * 学习3Des时创建的文件，主要是因为  DES不安全才出现的
 */
public class Bee3Des {

    public static final String src = "测试des";

    public static void main(String[] args){
        jdk3Des();
    }

    /**
     * jdk的Des加密方法
     */
    public static  void jdk3Des(){
        try{
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            keyGenerator.init(168);//根据Des的属性，自己生成的是56，也可以是64
           // keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            //Key转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);//这也有修改！！！！！！
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            Key convertSecretKey = factory.generateSecret(desKeySpec);

            //加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,convertSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());//等待价密的文字
            System.out.println("jdk3加密： "+result);
            System.out.println("jdk加密： "+ Hex.encodeHexString(result));//十六进制编码修改

            // 解密： 上面用到的是  一套东西，  只是在解密的模式上不同
            cipher.init(Cipher.DECRYPT_MODE,convertSecretKey);
            result = cipher.doFinal(result);//等待解密的文字
            System.out.println("jdk3解密： "+new String(result));
        }catch (Exception e){
            e.printStackTrace();
        }

    }


  /*  *//**
     * bc的Des加密方法
     *//*
    public static  void bcDes(){
       //todo...
    }*/
}
