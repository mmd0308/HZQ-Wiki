package com.hzqing.admin.common.utils;

public class ReplaceStrUtil {
    private ReplaceStrUtil() {
        throw new RuntimeException("工具类不能实例话");
    }

    public static String delHtmlTag(String str){
        String newstr = "";
        newstr = str.replaceAll("<[.[^>]]*>","");
        newstr = newstr.replaceAll(" ", "");
        return newstr;
    }
}
