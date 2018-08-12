package com.linxingjie.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//MD5 摘要算法  单向  一般用于小小系统的密文
public class MD5Utils {
	public static String md5(String password){
		//获取Md5摘要
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			//设置密码
			md.update(password.getBytes());
			//生成MD5的结果
			return new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
