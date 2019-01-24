package com.bee.springboot.util.encrypt.des;


import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * base64
 */
public class BeeBase64 {

    public static final String src = "D撒旦法sddes";

    public static void main(String[] args) {
        //jdkBase64();
        commonsCodesBase64();
    }


    public static void jdkBase64() {

        try {
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(src.getBytes());
            System.out.println("encode: " + encode);

            BASE64Decoder decoder = new BASE64Decoder();
            System.out.println("decode: " + new String(decoder.decodeBuffer(encode)));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void commonsCodesBase64() {
        byte[] encodeBytes = Base64.encodeBase64(src.getBytes());
        System.out.println("encode: " + new String(encodeBytes));

        byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
        System.out.println("decode: " + new String(decodeBytes));
    }

    public static void boucyCastleBase64() {



    }
}