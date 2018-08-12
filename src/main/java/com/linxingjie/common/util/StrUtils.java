package com.linxingjie.common.util;
//字符串工具类
public class StrUtils {
	public static boolean empty(String msg){
		return msg!=null && msg.length()>0;
	}
	public static boolean empty(String...msg){
		boolean res = true;
		for(String s:msg){
			res = (s!=null && s.length()>0);
			if(!res){
				break;
			}
		}
		return res;
	}
}
