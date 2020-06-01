package com.rb.fmea.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @version v1.0
 * @ClassName: StringProcess
 * @Description: TODO 字符串处理拼接与拆分等
 * @Author: yyk
 * @Date: 2020/3/2 14:01
 */
public class StringProcess {

    /**
     * @Author yyk
     * @Description //TODO 判断一个字符串是否以逗号结尾，如果是去掉逗号返回，此方法主要用于splite拆分之前
     * @Date 2020/3/14 10:18
     * @Param []
     * @return java.lang.String
     **/
    public static String determinesWhetherStringEndsInComma(String str){
        //if(StringUtils.isBlank(str))  throw new RuntimeException("字符串内容为空,不能拆分成数组");
        return str.endsWith(",")?str.substring(0,str.length()-1):str;
    }


    /**
     * @Author yyk
     * @Description //TODO 拆分字符串为数组,传入字符串target,以symbol标识符拆分
     * @Date 2020/5/20 14:57
     * @Param [target, symbol]
     * @return java.lang.String[]
     **/
    public static String[] spliteString(String target,String symbol){
        target = determinesWhetherStringEndsInComma(target);
        String[] targetArray = target.split(symbol);
        return targetArray;
    }


    /**
     * @Author yyk
     * @Description //TODO 字符串前后加符号
     * @Date 2020/5/20 16:24
     * @Param [target, symbol]
     * @return java.lang.String
     **/
    public static String stringConcate(String target,String symbol){
        if(StringUtils.isBlank(target)){
            return null;
        }
        StringBuilder stringBuilder=new StringBuilder("%");
        stringBuilder.append(target);
        stringBuilder.append("%");
        return stringBuilder.toString();
    }


}
