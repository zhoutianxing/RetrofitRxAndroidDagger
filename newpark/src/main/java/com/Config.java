package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import android.os.Environment;

import com.common.utils.Log;


public class Config {

	public static final String MOBILE_TYPE = "android";

	public static final String CLIENT_TYPE = "user";

	public static final String WEIXIN_APP_ID = "wxecbd601b80d7e1d5";

	public static final int PHOTO_COMPRESS_WIDTH = 1280;

	public static final int PHOTO_COMPRESS_QUALITY = 50;

	public static final String APP_DOWNLOAD_URL = "http://a.app.qq.com/o/simple.jsp?pkgname=com.niupark.ttkuaiche&g_f=991653";

	public static final String ACTION_USER_LOGIN = "com.niupark.ttkuaiche.android.action.USER_LOGIN";
	public static final String ACTION_USER_LOGOUT = "com.niupark.ttkuaiche.android.action.USER_LOGOUT";
	public static final String ACTION_DEFAULT_CAR_CHANGED = "com.niupark.ttkuaiche.android.action.DEFAULT_CAR_CHANGED";
	public static final String ACTION_LOCAL_DEFAULT_CAR_CHANGED =  "com.niupark.ttkuaiche.android.action.LOCAL_DEFAULT_CAR_CHANGED";
	public static final String ACTION_WASH_CARD_BIND_SUCCESS = "com.niupark.ttkuaiche.android.action.WASH_CARD_BIND_SUCCESS";
	public static final String ACTION_WASH_CARD_SWIPE_SUCCESS = "com.niupark.ttkuaiche.android.action.WASH_CARD_SWIPE_SUCCESS";
	public static final String ACTION_WASH_CARD_BUY_SUCCESS = "com.niupark.ttkuaiche.android.action.WASH_CARD_BUY_SUCCESS";
	public static final String ACTION_WASH_ITEM_BUY_SUCCESS = "com.niupark.ttkuaiche.android.action.WASH_ITEM_BUY_SUCCESS";
	public static final String ACTION_MAINTAIN_BUY_SUCCESS = "com.niupark.ttkuaiche.android.action.MAINTAIN_BUY_SUCCESS";
	public static final String ACTION_WPAY_SUCCESS = "com.niupark.ttkuaiche.android.action.WPAY_SUCCESS";
	public static final String ACTION_IDENTIFY_SUCCESS = "com.niupark.ttkuaiche.android.action.IDENTIFY_SUCCESS";
	public static final String ACTION_NEW_STATE_CHANGED = "com.niupark.ttkuaiche.android.action.NEW_STATE_CHANGED";
	public static final String ACTION_JUDGE_SUCCESS = "com.niupark.ttkuaiche.android.action.JUDGE_SUCCESS";
	public static String QINIU_LOCATION = "http://appniupark.qiniudn.com/";
//	public static final String QINIU_LOCATION = "http://7fvgm4.com1.z0.glb.clouddn.com/";

	public static String SERVER_ADDRESS = "http://tts.ttkuaiche.com/carwash/";
//	public static String SERVER_ADDRESS ="http://120.26.65.218:8080/carwash/";
	
	public static boolean getConfig() {
		final File configFile = new File(Environment.getExternalStorageDirectory() + "/ttkuaiche.prop");
		if (configFile.exists()) {
			Log.setEnabled(true);
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(configFile);
				final Properties props = new Properties();
				props.load(fis);
				if (props.containsKey("domain")) {
					SERVER_ADDRESS = "http://" + props.getProperty("domain") + "/carwash/";
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			return true;
		} else {
			Log.setEnabled(false);
			return false;
		}
	}

	public static void saveConfig() {
		final File configFile = new File(Environment.getExternalStorageDirectory() + "/ttkuaiche.prop");
		final Properties props = new Properties();
		props.put("domain", SERVER_ADDRESS.split("/")[2]);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(configFile);
			props.store(fos, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

   
    public static final String SINA_APP_KEY      = "3069600668";
    
   
    public static final String SINA_REDIRECT_URL = "http://www.ttkuaiche.com";

   
    public static final String SINA_SCOPE = 
            "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";

	

}