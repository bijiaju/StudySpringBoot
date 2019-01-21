package com.bee.springboot.util.encrypt.des;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * PBE特殊在口令 + 盐（就是加密时的随机数）
 */
public class BeePBE {

    public static final String src = "测试des";

    public static void main(String[] args){
        jdkPBE();
    }

    /**
     * jdk的Des加密方法
     */
    public static  void jdkPBE(){
        try{
            //初始化 盐
            SecureRandom random = new SecureRandom();
            byte[] salt = random.generateSeed(8);

            //口令和密钥
            String password = "imooc";
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            Key key = factory.generateSecret(pbeKeySpec);

            //加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");//加密方式， 工作模式  ，填充方式
            cipher.init(Cipher.ENCRYPT_MODE,key,pbeParameterSpec);
            byte[] result = cipher.doFinal(src.getBytes());//等待价密的文字
            System.out.println("jdk pbe加密： "+result);
            System.out.println("jdk pbe加密： "+ Hex.encodeHexString(result));//十六进制编码修改

            // 解密： 上面用到的是  一套东西，  只是在解密的模式上不同
            cipher.init(Cipher.DECRYPT_MODE,key,pbeParameterSpec);
            result = cipher.doFinal(result);//等待解密的文字
            System.out.println("jdk  pbe解密： "+new String(result));
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
