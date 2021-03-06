package com.bb.hbx.utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.bb.hbx.MyApplication;

/**
 *
 * Created by fancl
 * 设备相关工具类.
 */

public class DeviceUtils {

    private DeviceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取设备系统版本号
     *
     * @return 设备系统版本号
     */
    public static int getSDKVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取设备厂商
     * <p>如Xiaomi</p>
     *
     * @return 设备厂商
     */

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }


    /**
     * 获取设备型号
     * <p>如MI2SC</p>
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }


    /**
     * 获得设备相关信息
     */
    public static void getDeviceIdentification(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        // the IMEI for GSM and the MEID or ESN for CDMA phones
        String imei = tm.getDeviceId();
        // the IMSI for a GSM phone.
        String imsi = tm.getSubscriberId();
        // 手机系统的唯一号码
        String android_id = android.provider.Settings.System.getString(context.getContentResolver(), "android_id");
        if (null != imei && !"".equals(imei.trim())) MyApplication.DUID = imei;
        else if (null != imsi && !"".equals(imsi.trim())) MyApplication.DUID = imsi;
        else if (null != android_id && !"".equals(android_id.trim()))
            MyApplication.DUID = android_id;

    }

}
