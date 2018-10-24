package com.dotawang.mvvm.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.dotawang.mvvm.app.AppApplication;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类（字节转数字、数字转字节、字节转字符串）
 * Created by Dota.Wang on 2018/8/11.
 */

public class Tool {
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

    /**
     * 格式化日期
     *
     * @param cal
     * @return
     */
    public static String getDateByCalendar(Calendar cal) {
        int year = cal.get(Calendar.YEAR);
        int monthOfYear = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DATE);
        String text = String.valueOf(year);
        monthOfYear += 1;
        if (monthOfYear < 10) {
            text += "0";
        }
        text += monthOfYear;
        if (dayOfMonth < 10) {
            text += "0";
        }
        text += dayOfMonth;
        return text;
    }

    /**
     * 获取明天
     *
     * @return
     */
    public static String getTomoDate() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        long millis = gregorianCalendar.getTimeInMillis() + 86400000;
        gregorianCalendar.setTimeInMillis(millis);
        int year = gregorianCalendar.get(Calendar.YEAR);
        int monthOfYear = gregorianCalendar.get(Calendar.MONTH);
        int dayOfMonth = gregorianCalendar.get(Calendar.DATE);
        String text = String.valueOf(year);
        monthOfYear += 1;
        if (monthOfYear < 10) {
            text += "0";
        }
        text += monthOfYear;
        if (dayOfMonth < 10) {
            text += "0";
        }
        text += dayOfMonth;
        return text;
    }

    /**
     * 格式化下一年日期
     *
     * @param cal
     * @return
     */
    public static String getDateAfterYearByCalendar(Calendar cal) {
        cal.add(Calendar.YEAR, 1);
        int year = cal.get(Calendar.YEAR);
        int monthOfYear = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DATE);
        if (monthOfYear == 2 && dayOfMonth == 29) {
            return (year + 1) + "0301";
        }
        String text = String.valueOf(year);
        monthOfYear += 1;
        if (monthOfYear < 10) {
            text += "0";
        }
        text += monthOfYear;
        if (dayOfMonth < 10) {
            text += "0";
        }
        text += dayOfMonth;
        return text;
    }

    /**
     * 格式化时间点
     *
     * @param cal
     * @return
     */
    public static String getTimeByCalendar(Calendar cal) {
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        String ret = null;
        if (hour < 10) {
            ret = "0" + hour;
        } else {
            ret = String.valueOf(hour);
        }
        if (minute < 10) {
            ret += "0" + minute;
        } else {
            ret += minute;
        }

        if (second < 10) {
            ret += "0" + second;
        } else {
            ret += second;
        }
        return ret;
    }

    /**
     * 获取int数组中的最大值
     *
     * @param list
     * @return
     */
    public static int getMaxOfArray_i(int[] list) {
        if (list == null || list.length <= 0) {
            return 0;
        }

        int max = list[0];
        for (int element : list) {
            if (element > max) {
                max = element;
            }
        }
        return max;
    }

    /**
     * 获取float数组中的最大值
     *
     * @param list
     * @return
     */
    public static float getMaxOfArray_f(float[] list) {
        if (list == null || list.length <= 0) {
            return 0;
        }

        float max = list[0];
        for (float element : list) {
            if (element > max) {
                max = element;
            }
        }
        return max;
    }

    /**
     * 获取数组中的最小值
     *
     * @param list
     * @return
     */
    public static float getMinOfArray_f(float[] list) {
        if (list == null || list.length <= 0) {
            return 0;
        }

        float min = list[0];
        for (float element : list) {
            if (element < min) {
                min = element;
            }
        }
        return min;
    }

    /**
     * 格式化时间(103000转为10:30:00)
     *
     * @param value
     * @return
     */
    public static String formatTime(String value) {
        String ret = null;
        if (value.length() == 6) {
            ret = value.substring(0, 2) + ":" + value.substring(2, 4) + ":" + value.substring(4);
        } else if (value.length() == 5) {
            ret = value.substring(0, 1) + ":" + value.substring(1, 3) + ":" + value.substring(3);
        }
        return ret;
    }

    /**
     * 格式化时分秒.
     *
     * @param formatData 日期HHmmss
     * @return 格式化后的时分秒
     */
    public static String formatDateHHMMSS(String formatData) {
        String returnData = formatData;
        if (returnData == null || "".equals(returnData)) {
            return "";
        }
        String dealFormatdate = formatData;
        if (formatData.length() > 6) {
            dealFormatdate = formatData.substring(formatData.length() - 6);
        } else if (formatData.length() < 6) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < 6 - formatData.length(); i++) {
                buffer.append("0");
            }
            buffer.append(formatData);
            dealFormatdate = buffer.toString();

        }
        SimpleDateFormat sf1 = new SimpleDateFormat("HHmmss");
        SimpleDateFormat sf2 = new SimpleDateFormat("HH:mm:ss");
        Date sDt = null;
        try {
            sDt = sf1.parse(dealFormatdate);
            if (null != sDt) {
                returnData = sf2.format(sDt);
            }


        } catch (Exception e) {
        }

        return returnData;
    }

    /**
     * 格式化浮点数
     *
     * @param count
     * @param value
     * @return
     */
    public static String formatDouble(int count, String value) {
        if (Tool.isTrimEmpty(value)) {
            return value;
        }
        String retVal = null;
        int dx = value.indexOf('.');
        if (dx != -1) {
            String[] splitStr = value.split("\\.");
            retVal = splitStr[0];
            if (count <= 0) {
                return retVal;
            }
            String decimalStr = "";
            if (splitStr.length > 1) {
                decimalStr = splitStr[1];
            }

            if (Tool.isTrimEmpty(decimalStr)) {
                String s = "";
                while (count != 0) {
                    s += "0";
                    count--;
                }
                retVal = s.length() > 0 ? retVal + "." + s : retVal;
            } else if (decimalStr.length() == count) {
                retVal = value;
            } else if (decimalStr.length() > count) {
                retVal = retVal + "." + decimalStr.substring(0, count);
            } else if (decimalStr.length() < count) {
                while (decimalStr.length() < count) {
                    decimalStr += "0";
                }
                retVal = retVal + "." + decimalStr;
            }
        } else if (count > 0) {
            retVal = value + '.';
            for (int b = 0; b < count; b++) {
                retVal += '0';
            }
        } else {
            retVal = value;
        }
        return retVal;
    }

    /**
     * 判断字符串numStr是否是浮点数
     *
     * @param numStr
     * @return
     */
    public static boolean isFloat(String numStr) {
        if (!isTrimEmpty(numStr)) {
            try {
                Float.parseFloat(numStr);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    /**
     * 判断字符串numStr是否是Double
     *
     * @param numStr
     * @return
     */
    public static boolean isDouble(String numStr) {
        if (!isTrimEmpty(numStr)) {
            try {
                Double.parseDouble(numStr);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    /**
     * 判断字符串numStr是否是int
     *
     * @param numStr
     * @return
     */
    public static boolean isInteger(String numStr) {
        if (!isTrimEmpty(numStr)) {
            try {
                Integer.parseInt(numStr);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    /**
     * 判断是否是纯数字
     *
     * @param numStr
     * @return
     */
    public static boolean isNumeric(String numStr) {
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher matcher = p.matcher(numStr);
        return matcher.matches();
    }

    /***************************************************************************
     * 判断字符串是否是10进制数字
     **************************************************************************/
    public static boolean isNum(String num) {
        if (num == null || num.length() <= 0) {
            return false;
        }
        byte[] numArray = num.getBytes();
        for (int i = 0; i < numArray.length; i++) {
            if (i == 0 && numArray[i] == '-') {
                continue;
            } else if (numArray[i] >= 48 && numArray[i] <= 57) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为纯英文字符
     *
     * @param text
     * @return
     */
    public static boolean isCharacters(String text) {
        Pattern p = Pattern.compile("^[A-Za-z]+$");
        Matcher matcher = p.matcher(text);
        return matcher.matches();
    }

    /**
     * 数字字符串中是否包含字母
     *
     * @param s
     * @return
     */
    public static boolean isContainLetter(String s) {
        boolean isContainLetter = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                isContainLetter = true;
                break;
            }
        }
        return isContainLetter;
    }

    // 将系统时间的秒数转为日期
    public static String getDate(long time) {
        String str = "";
        Date date = new Date(time);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC+8"));
        calendar.setTime(date);
        int mm = (calendar.get(Calendar.MONTH) + 1);
        int dd = calendar.get(Calendar.DATE);
        str = calendar.get(Calendar.YEAR) + (mm > 9 ? Integer.toString(mm) : "0" + mm) + (dd > 9 ? Integer.toString(dd) : "0" + dd);
        return str;
    }


    public static String getDateAfter(int after) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC+8"));
        calendar.add(Calendar.DATE, after);
        int mm = (calendar.get(Calendar.MONTH) + 1);
        int dd = calendar.get(Calendar.DATE);
        String str = calendar.get(Calendar.YEAR) + (mm > 9 ? Integer.toString(mm) : "0" + mm) + (dd > 9 ? Integer.toString(dd) : "0" + dd);
        return str;
    }

    /**
     * 获取两个日期之间的间隔天数
     *
     * @return
     */
    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * 判断字符串src是不是空的，空返回true否则返回false。 字符串会经过trim操作，就是如果字符串里都是空格也当成是空串
     *
     * @param str
     * @return
     */
    public static boolean isTrimEmpty(CharSequence str) {
        if (str == null || Tool.isEmpty(str.toString().trim())) {
            return true;
        }
        return false;
    }

    /**
     * 判断errorNo是否属于正确返回
     *
     * @param errorNo
     * @return
     */
    public static boolean isSuccessErrorNo(String errorNo) {
        if (isTrimEmpty(errorNo) || "0".equals(errorNo)) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为空
     *
     * @param trim
     * @return
     */
    public static boolean isEmpty(String trim) {
        if (trim == null || trim.trim().equals("")) {
            return true;
        }
        return false;
    }

    //-------------------------------------Toast吐丝------------------------------------

    private static Handler handler = new Handler(Looper.getMainLooper());
    public static void showToast(final String message) {

        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast toast = Toast.makeText(AppApplication.getInstance(), message, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    public static void showToast(final int messageRes) {

        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast toast = Toast.makeText(AppApplication.getInstance(), messageRes, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    //-------------------------------------EditText输入内容限制------------------------------------

    /**
     * 禁止EditText输入空格和换行符
     *
     * @param editText EditText输入框
     */
    public static void setEditTextInputSpaceAndEnter(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" ") || source.toString().contentEquals("\n")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 禁止EditText输入特殊字符
     *
     * @param editText EditText输入框
     */
    public static void setEditTextInputSpeChat(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 禁止EditText输入空格
     *
     * @param s CharSequence字符
     * @param editText EditText输入框
     */
    public static void setEditTextInputSpace(CharSequence s,EditText editText){
        if (s.toString().contains(" ")) {
            String[] str = s.toString().split(" ");
            String str1 = "";
            for (int i = 0; i < str.length; i++) {
                str1 += str[i];
            }
            editText.setText(str1);
            editText.setSelection(str1.length());
        }
    }

    //-------------------------------------版本号比较------------------------------------

    /**
     * 比较app的版本号
     * @param oldVersion
     * @param newVersion
     * @return
     */
    public static int compareVersion(String oldVersion, String newVersion) {
        if (null == newVersion || "".equals(newVersion) || null == oldVersion || "".equals(oldVersion)) {
            return 0;
        }
        String[] v1 = oldVersion.split("\\.");
        String[] v2 = newVersion.split("\\.");
        if (v1.length < v2.length) { // 如果版本.长度不一样，后面的缺省为0，如果保证长度一样，这里可以不需要
            String[] t = new String[v2.length];
            System.arraycopy(v1, 0, t, 0, v1.length);
            for (int i = v1.length; i < t.length; i++) {
                t[i] = "0";
            }
            v1 = t;
        } else if (v1.length > v2.length) {
            String[] t = new String[v1.length];
            System.arraycopy(v2, 0, t, 0, v2.length);
            for (int i = v2.length; i < t.length; i++) {
                t[i] = "0";
            }
            v2 = t;
        }

        for (int i = 0; i < v1.length; i++) {
            int n1 = Integer.valueOf(v1[i]).intValue();
            int n2 = Integer.valueOf(v2[i]).intValue();
            if (n1 != n2) {
                return (n1 - n2); // 大于0则s1版本大，小于0则s2版本大
            }
        }
        return 0; // 相等
    }

    public static boolean getUninatllApkInfo(Context context, String filePath) {
        boolean result = false;
        try {
            PackageManager pm = context.getPackageManager();
            Log.e("archiveFilePath", filePath);
            PackageInfo info = pm.getPackageArchiveInfo(filePath,
                    PackageManager.GET_ACTIVITIES);
//			    String packageName = null;
            if (info != null) {
                result = true;
            }
        } catch (Exception e) {
            result = false;
            Log.e("install error", "*****  解析未安装的 apk 出现异常 *****" + e.getMessage());
        }
        return result;
    }

    public static void openFile(File file, Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        //获取文件file的MIME类型
        String type = Tool.getMIMEType(file);
        //设置intent的data和Type属性。
        intent.setDataAndType(/*uri*/Uri.fromFile(file), type);
        //跳转
        context.startActivity(intent);
    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param file
     */
    public static String getMIMEType(File file) {
        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名 */
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "") {
            return type;
        }
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) {
            if (end.equals(MIME_MapTable[i][0])) {
                type = MIME_MapTable[i][1];
            }
        }
        return type;
    }

    //建立一个MIME类型与文件后缀名的匹配表
    private static final String[][] MIME_MapTable = {
            //{后缀名，    MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "*/*"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".prop", "text/plain"},
            {".rar", "*/*"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/zip"},
            {"", "*/*"}
    };

    //是否包含中文
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    //-------------------------------------手机屏幕宽高计算------------------------------------

    private static int sScreenWidth = -1;
    private static int sScreenHeight = -1;

    /**
     * 获取屏幕宽度
     *
     * @return 屏幕宽度
     */
    public static int getScreenWidth() {
        if (sScreenWidth == -1) {
            sScreenWidth = AppApplication.getInstance().getResources().getDisplayMetrics().widthPixels;
        }
        return sScreenWidth;
    }

    /**
     * 获取屏幕高度
     *
     * @return 屏幕高度
     */
    public static int getScreenHeight() {
        if (sScreenHeight == -1) {
            sScreenHeight = AppApplication.getInstance().getResources().getDisplayMetrics().heightPixels;
        }
        return sScreenHeight;
    }

    //-------------------------------------字符串转换------------------------------------
    /**
     * 字符串转float
     *
     * @param floatValue   float型字符串
     * @param defaultValue 默认值
     * @return float数值
     */
    public static float parseFloat(String floatValue, float defaultValue) {
        float value;
        try {
            value = Float.parseFloat(floatValue);
        } catch (Exception e) {
            value = defaultValue;
        }
        return value;
    }

    /**
     * 字符串转long
     *
     * @param longValue    字符型long
     * @param defaultValue 默认值
     * @return long型数值
     */
    public static long parseLong(String longValue, long defaultValue) {
        long value;
        try {
            value = Long.parseLong(longValue);
        } catch (Exception e) {
            value = defaultValue;
        }
        return value;
    }

    /**
     * 字符串转int
     *
     * @param intValue     字符型int
     * @param defaultValue 默认值
     * @return long型数值
     */
    public static int parseInt(String intValue, int defaultValue) {
        int value;
        try {
            value = Integer.parseInt(intValue);
        } catch (Exception e) {
            value = defaultValue;
        }
        return value;
    }

    /**
     * @param doubleValue  字符串double
     * @param defaultValue 默认值
     * @return double数值
     */
    public static double parseDouble(String doubleValue, double defaultValue) {
        double value;
        try {
            value = Double.parseDouble(doubleValue);
        } catch (Exception e) {
            value = defaultValue;
        }
        return value;
    }


    //-------------------------------------虚拟按键------------------------------------
    /**
     * 获取是否存在NavigationBar，是否有虚拟按钮
     */
    public static boolean isNavigationBarShow(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = activity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y != size.y;
        } else {
            boolean menu = ViewConfiguration.get(activity).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if (menu || back) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * 获取虚拟按钮ActionBar的高度
     *
     * @param activity activity
     * @return ActionBar高度
     */
    public static int getNavigationBarHeight(Activity activity) {
        if (!isNavigationBarShow(activity)) {
            return 0;
        }
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    public static int getSceenHeight(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getHeight() + getNavigationBarHeight(activity);
    }

    /**
     * 判断手机是否有虚拟按键功能
     *
     * @param activity
     * @return
     */
    public static boolean hasNavigationBar(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        Display display = activity.getWindowManager().getDefaultDisplay();
        display.getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        float density = dm.density;

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealMetrics(realDisplayMetrics);
        } else {
            Class c;
            try {
                c = Class.forName("android.view.Display");
                Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
                method.invoke(display, realDisplayMetrics);
            } catch (Exception e) {
                realDisplayMetrics.setToDefaults();
                e.printStackTrace();
            }
        }

        int screenRealHeight = realDisplayMetrics.heightPixels;
        int screenRealWidth = realDisplayMetrics.widthPixels;

        float diagonalPixels = (float) Math.sqrt(Math.pow(screenWidth, 2) + Math.pow(screenHeight, 2));
        float screenSize = (diagonalPixels / (160f * density)) * 1f;

        Resources rs = activity.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        boolean hasNavBarFun = false;
        if (id > 0) {
            hasNavBarFun = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavBarFun = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavBarFun = true;
            }
        } catch (Exception e) {
            hasNavBarFun = false;
        }
        return hasNavBarFun;
    }

    /**
     * 检查在有虚拟按键的设备上面，虚拟按键是否展示出来（收起或展示）
     *
     * @param windowManager
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(WindowManager windowManager) {
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealMetrics(realDisplayMetrics);
        } else {
            Class c;
            try {
                c = Class.forName("android.view.Display");
                Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
                method.invoke(display, realDisplayMetrics);
            } catch (Exception e) {
                realDisplayMetrics.setToDefaults();
                e.printStackTrace();
            }
        }
        int screenRealHeight = realDisplayMetrics.heightPixels;
        int screenRealWidth = realDisplayMetrics.widthPixels;

        return (screenRealHeight - screenWidth) > 0;//screenRealHeight上面方法中有计算
    }

    /**
     * 给界面全屏并且透明
     * @param activity
     */
    public static void initStatus(Activity activity){
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decoderView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decoderView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            //or ?
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
