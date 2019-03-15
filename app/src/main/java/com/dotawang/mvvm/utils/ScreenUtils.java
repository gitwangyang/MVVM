package com.dotawang.mvvm.utils;

import android.content.res.Resources;
import android.text.TextUtils;


import com.dotawang.mvvm.app.AppApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * 屏幕宽高、像素转换工具类
 * Created by Dota.Wang on 2018/10/30.
 */

public class ScreenUtils {
    static Resources res = null;

    static {
        res = AppApplication.getInstance().getResources();
    }

    public static float getDimension(int demenId) {
        return res.getDimension(demenId);
    }

    public static float getDimensionPixelSize(int demenId) {
        return res.getDimensionPixelSize(demenId);
    }

    /**
     * 像素转换为密度值
     *
     * @param px
     * @return
     */
    public static int pxToDp(float px) {
        final float scale = res.getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 密度转换为像素值
     *
     * @param dp
     * @return
     */
    public static float dpToFloatPx(float dp) {
        final float scale = res.getDisplayMetrics().density;
        return dp * scale;
    }

    public static int getFormatEngTextSize(int deflateSize, int deflateLength, String str) {
        if (strIsAllEnglish(str)) {
            deflateLength *= 1.8;
        }
        deflateSize = getFormatTextSize(deflateSize, deflateLength, str);
        return deflateSize;
    }

    public static boolean strIsAllEnglish(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ' ') {
                continue;
            }
            if (!(word.charAt(i) >= 'A' && word.charAt(i) <= 'Z')
                    && !(word.charAt(i) >= 'a' && word.charAt(i) <= 'z')) {
                return false;
            }
        }
        return true;
    }

    public static int getFormatTextSize(int deflateSize, int deflateLength, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.replace(" ", "").length() > deflateLength) {
                deflateSize = deflateSize * deflateLength / str.length();
            }
        }
        return deflateSize;
    }

    /**
     * 密度转换为像素值
     *
     * @param dp
     * @return
     */
    public static int dpToPx(float dp) {
        final float scale = res.getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * sp 转换成 px
     *
     * @param spValue sp
     * @return px
     */
    public static int sp2px(float spValue) {
        float fontScale = res.getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 时间数值转化为字符串(单位：分钟)
     *
     * @param time
     * @return
     */
    public static String timeToStr(int time) {
        StringBuffer sb = new StringBuffer();
        sb.append(time / 60);
        sb.append(":");
        if (time % 60 < 10) {
            sb.append("0");
        }
        sb.append(time % 60);
        return sb.toString();
    }

    /**
     * 字符串换数字
     *
     * @param numberStr
     * @param nDefault
     * @return
     */
    public static int stringToInt(String numberStr, int nDefault) {
        if (numberStr == null || numberStr.trim().length() == 0) {
            return nDefault;
        }
        numberStr = numberStr.trim().toLowerCase();
        if (numberStr.length() == 0 || numberStr.equals("0x")) {
            return nDefault;
        }
        int ret = nDefault;
        try {
            if (numberStr.startsWith("0x")) {
                ret = Integer.parseInt(numberStr.substring(2), 10);
            } else {
                ret = Integer.parseInt(numberStr);
            }
        } catch (Exception ex) {
        }
        return ret;
    }

    /**
     * 字符串转换为字符串数组
     *
     * @param str
     * @param splite
     * @return
     */
    public static String[] str2Array(String str, String splite) {
        String[] retValue = null;
        if (null == str) {
            return retValue;
        }
        retValue = str.split(splite);
        return retValue;
    }

    /**
     * 字符串转换为Map
     *
     * @param str
     * @param split1
     * @param split2
     * @return
     */
    public static Map<String, String> str2Map(String str, String split1, String split2) {
        Map<String, String> retMap = new HashMap<String, String>();
        if (str == null || split1 == null || split2 == null) {
            return retMap;
        }
        String[] arr = str2Array(str, split1);
        for (int i = 0; i < arr.length; i++) {
            String[] temp = str2Array(arr[i], split2);
            if (temp != null) {
                if (temp.length == 2) {
                    retMap.put(temp[0], temp[1]);
                }
            }

        }
        return retMap;
    }
}
