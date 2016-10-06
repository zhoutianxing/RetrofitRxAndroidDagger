package com.common.utils;

public class Log {

	private static boolean enabled;

	public static void setEnabled(boolean enabled) {
		Log.enabled = enabled;
	}

	public static void i(String tag, String info) {
		if (enabled) {
			android.util.Log.i(tag, info);
		}
	}

	public static void i(String tag, int info) {
		i(tag, String.valueOf(info));
	}

	public static void e(String tag, String error) {
		if (enabled) {
			android.util.Log.e(tag, error);
		}
	}

	public static void e(String tag, int error) {
		e(tag, String.valueOf(error));
	}

}