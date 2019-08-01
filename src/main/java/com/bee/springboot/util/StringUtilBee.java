package com.bee.springboot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自己对字符串的操作
 */
public class StringUtilBee {

    public static void main(String[] args){

    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
