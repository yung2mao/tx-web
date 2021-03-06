package cn.whitetown.web.base.util;

import com.alibaba.fastjson.JSON;
import sun.net.util.IPAddressUtil;

/**
 * 数据校验工具类
 * @author GrainRain
 * @date 2020/06/13 14:21
 **/
public class DataCheckUtil {

    private DataCheckUtil(){}
    /**
     * 非空校验 - false抛出异常
     * @param str
     */
    public static void checkTextNull(String str){
        if(str==null || "".equals(str)){
            throw new NullPointerException("null text");
        }
    }

    /**
     * 非空校验
     * 如果为空返回true，否则返回false
     * @param str
     * @return
     */
    public static boolean checkTextNullBool(String str){
        if(str==null || "".equals(str)){
            return true;
        }
        return false;
    }

    /**
     * 校验字符串是否为日期
     * yyyy-MM-dd HH:mm:ss / yyyy-MM-dd HH:mm:ss.SSS /yyyy-MM-dd
     * @param str
     * @return
     */
    public static boolean isDate(String str) {
        if(checkTextNullBool(str)) {
            return false;
        }
        String regex = "^(\\d{4}-\\d{2}-\\d{2})|" +
                "(\\d{4}-\\d{2}-\\d{2}[ ]\\d{2}:\\d{2}:\\d{2}\\.\\d{3})" +
                "|(\\d{4}-\\d{2}-\\d{2}[ ]\\d{2}:\\d{2}:\\d{2})$";
        return str.matches(regex);
    }

    /**
     * 是否为手机号
     * @param str
     * @return
     */
    public static boolean isTelephone(String str) {
        if(checkTextNullBool(str)) {
            return false;
        }
        String regex = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
        return str.matches(regex);
    }

    /**
     * IP校验
     * @param str
     * @return
     */
    public static boolean isIp(String str) {
        if(checkTextNullBool(str)) {
            return false;
        }
        String baseRegex = "\\d+\\.\\d+\\.\\d+\\.\\d+";
        if(!str.matches(baseRegex)) {
            return false;
        }
        return IPAddressUtil.isIPv4LiteralAddress(str);
    }

    /**
     * 校验是否为JSON
     * @param str
     * @return
     */
    public static boolean isJson(String str) {
        try {
            if(!str.startsWith("{") && !str.startsWith("[")) {
                return false;
            }
            JSON.parse(str);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * 是否包含中文
     * @param str
     * @return
     */
    public static boolean containChinese(String str) {
        if(checkTextNullBool(str)) {
            return false;
        }
        String regex = "^.*[\\u4e00-\\u9fa5].*$";
        return regex.matches(str);
    }
}
