package com.bb.hbx.utils;

import android.text.TextUtils;

import com.bb.hbx.bean.Entry;
import com.bb.hbx.bean.RelationShipBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断字符串是否为null或长度为0
     *
     * @param s 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * 判断两字符串是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        return (a == b) || (b != null) && (a.length() == b.length()) && a.regionMatches(true, 0, b, 0, b.length());
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = length(s);
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    //检测目标字符串的长度
    public static boolean checkStringLength(String string, int length) {
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        if (string.trim().length() != length) {
            return false;
        }
        return true;
    }

    public static String idTostring(int id) {
        for (int i = 0; i < Constants.idType_keys.length; i++) {
            if (Constants.idType_keys[i] == id) {

                return Constants.idTypes[i];
            }
        }

        return Constants.idTypes[0];
    }

    public static String reationTostring(int id) {
        for (int i = 0; i < Constants.beinsurer1_listkey.length; i++) {
            if (Constants.beinsurer1_listkey[i] == id) {

                return Constants.beinsurer1_listvalue[i];
            }
        }

        return Constants.beinsurer1_listvalue[0];
    }

    public static List<Entry> getJsonOpt(String priceElements) {
        List<Entry> entries = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(priceElements);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (jsonObject != null && jsonObject.has("price_element")) {
            JSONArray jaList = jsonObject.optJSONArray("price_element");
            for (int i = 0; i < jaList.length(); i++) {
                JSONObject obj = jaList.optJSONObject(i);
                final Entry entry = new Entry();
                if (obj.has("name"))
                    entry.setName(obj.optString("name"));
                if (obj.has("code"))
                    entry.setCode(obj.optString("code"));
                if (obj.has("option")) {
                    String opt = obj.optString("option");
                    if (opt.indexOf(",") > -1) {
                        String[] opts = opt.split(",");
                        entry.setOption(Arrays.asList(opts));
                    } else {
                        String[] opts = {opt};
                        entry.setOption(Arrays.asList(opts));
                    }
                }
                entries.add(entry);
            }
        }

        return entries;

    }

    public static List<RelationShipBean> getJsonRelationList(String relationship)
    {
        List<RelationShipBean> list=new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(relationship);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String code = object.getString("code");
                String name = object.getString("name");
                RelationShipBean bean = new RelationShipBean(code, name);
                list.add(bean);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}