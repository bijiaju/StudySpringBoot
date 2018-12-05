package com.cwh.springbootMybatis.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用的工具类
 */
public class CommonUtil {

    /**
     * 设置返回JSON数据
     * @param returncode
     * @param returnmessage
     * @param retMap
     * @return Map<String, Object>
     */
    public static Map<String, Object> setReturnMap(String returncode, String returnmessage, Map<String, Object> retMap) {
        retMap.put("returnCode", returncode);
        retMap.put("returnMessage", returnmessage);
        return retMap;
    }

    /**
     * 将图片转换成Base64编码
     * @param imgFile 待处理图片
     * @return
     */
    public static String imgToBase64(String imgFile){
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理


        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }

    /**
     * 过滤中英文方括号的数字
     * @param src 过滤内容
     * @param reg 过滤规则
     * @return 过滤后的内容
     */
    public static String filterVerifyCode(String src,String reg[]){
        StringBuilder ret = new StringBuilder(src);
        //String [] reg = {"\\[\\d+\\]","\\【\\d+\\】"};//判断中英文方括号的正则
        for(int i=0;i<reg.length;i++){
            Pattern p = Pattern.compile(reg[i]);
            Matcher m = p.matcher(src);
            while(m.find())
            {
                ret.replace(m.start()+1, m.end()-1, addStar(m.start()+1, m.end()-1));
            }
        }
        return ret.toString();
    }

    /**
     * 根据起始位置添加 星号
     * @param start
     * @param end
     * @return
     */
    public static String addStar(int start,int end){
        String str ="";
        for(int i=start;i<end;i++){
            str+="*";
        }
        return str;
    }

    public static void main(String[] args){
        //测试生成base64的编码
       /* String imgFile = "C:\\Users\\bee\\Desktop\\test1\\2.png";
        String base64 = imgToBase64(imgFile);
        System.out.println(base64);*/
    }
}
