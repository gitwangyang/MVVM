package com.dotawang.mvvm.utils;

import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.dotawang.mvvm.app.AppApplication;

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
}
