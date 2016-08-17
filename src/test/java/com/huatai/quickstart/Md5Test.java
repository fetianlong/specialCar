package com.huatai.quickstart;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;


public class Md5Test {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		String password = "123456";
		String salt = "4c0b9aa5b06abb4a";
//		System.out.println(encrypt(password));
		getPassWordBySalt(salt, password);
//		getSaltAndPassWord(password);
	}
	
	/**
	 * 生成随机盐值并根据密码加密
	 * @param password  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 下午3:51:37
	 */
	public static void getSaltAndPassWord(String password){
		/*
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
		
		*/
		byte[] salt = Digests.generateSalt(8);
		String s = Encodes.encodeHex(salt);
		System.out.println("新盐值：" + s);
		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, 1024);
		String p = Encodes.encodeHex(hashPassword);
		System.out.println("密码：" + p);
	}
	
	/**
	 * 根据盐值和密码进行加密
	 * @param salt
	 * @param password  
	 * @description   
	 * @version currentVersion  
	 * @author pujh  
	 * @createtime 2015年7月9日 下午3:52:15
	 */
	public static void getPassWordBySalt(String s, String password){
		System.out.println("老盐值：" + s);
		byte[] salt = Encodes.decodeHex(s);
		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, 1024);
		String p = Encodes.encodeHex(hashPassword);
		System.out.println("密码：" + p);
	}

	 //将传进来密码加密方法  
    private static String encrypt(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		//加密后的字符串
//        String newstr = Md5.getInstance().encode(str);
//		return newstr;
    	return null;
//        Md5Hash sha384Hex = new Md5Hash(data);//这里可以选择自己的密码验证方式 比如 md5或者sha256等  
//        return sha384Hex;  
    }
}
