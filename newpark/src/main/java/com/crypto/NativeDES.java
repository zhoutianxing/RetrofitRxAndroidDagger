package com.crypto;

import android.os.Build;
import android.util.Log;

public class NativeDES {

	private NativeDES() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			System.loadLibrary("ttkey");
		}
	}

	private static NativeDES instance = null;

	public static NativeDES getInstance() {
		if (instance == null) {
			instance = new NativeDES();
		}
		return instance;
	}

	public static String encrypt(String data, String key) {
		String r = null;
		try {
			r = Des3.encode(data, key);
		} catch (Exception e) {
			Log.w("NativeDES", e);
		}
		return r;
	}

	public static String decrypt(String data, String key) {
		String r = null;
		try {
			r = Des3.decode(data, key);
		} catch (Exception e) {
			Log.w("NativeDES", e);
		}
		return r;
	}

	public String en(String str) {
		String ret = null;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			ret = encryptStr(str);
		}
		return ret;
	}

	public static native String encryptStr(String str);

	public static native String decryptStr(String str);

}
