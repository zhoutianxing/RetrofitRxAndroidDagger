package com.crypto;

import android.annotation.SuppressLint;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3DES加密工具类
 */

public class Des3 {

	// 密钥
	@SuppressWarnings("unused") private final static String secretKey = "liuyunqiang@lx100$#365#$";

	// 向量
	private final static String iv = "01234567";

	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";

	/**
	 * 3DES加密
	 */
	@SuppressLint("TrulyRandom")
	public static String encode(String plainText, String key) throws Exception {

		Key deskey = null;

		DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());

		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");

		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");

		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());

		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);

		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));

		return Base64.encode(encryptData);

	}

	/**
	 * 3DES解密
	 */
	public static String decode(String encryptText, String key) throws Exception {

		Key deskey = null;

		DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());

		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");

		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");

		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());

		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));

		return new String(decryptData, encoding);

	}

//	public static void main(String[] args) {
//		try {
//			String date = WDateTime.getTDate();
//			date = date.replace("-", "");
//			date = date + date + date;
//			String key = Des3.encode("活活", "11");
//			System.out.println(key);
//			System.out.println(Des3.decode("AysCqGuu4XE=", "11"));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
