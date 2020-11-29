package cn.whitetown.web.web.base.util;

import cn.whitetown.web.web.base.model.PageQuery;
import cn.whitetown.web.web.base.model.ResponsePage;
import com.alibaba.fastjson.JSON;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 通用的处理各类一般事件的工具类
 * @author GrainRain
 * @date 2020/06/13 11:36
 **/
public class WhiteToolUtil {
    public WhiteToolUtil(){}

    /**
     * 随机生成一组字符串
     * @param len
     * @return
     */
    public static String createRandomString(int len){
        if(len<1){
            throw new IndexOutOfBoundsException("need >1, real "+len);
        }

        int start = '0';
        int end ='z';
        Random random = new Random();
        char[] textChar = new char[len];
        for (int i = 0; i < len; i++) {
            textChar[i] = (char)(random.nextInt(end-start)+start);
        }
        return String.valueOf(textChar);
    }

    /**
     * 两个对象的数据处理合并为一个对象的数据
     * 新对象中空值数据不保留
     * @param oldObj
     * @param newObj
     * @return
     */
    public static Object mergeObject(Object oldObj,Object newObj){
        if(oldObj.getClass() != newObj.getClass()){
            return newObj;
        }
        Field[] declaredFields = oldObj.getClass().getDeclaredFields();
        List<Field> fields = new ArrayList<>();
        Arrays.stream(declaredFields).forEach(field -> fields.add(field));
        Class<?> superClass = oldObj.getClass().getSuperclass();
        while (superClass != null){
            Field[] superFields = superClass.getDeclaredFields();
            Arrays.stream(superFields).forEach(field -> fields.add(field));
            superClass = superClass.getSuperclass();
        }
        fields.stream().forEach(field -> {
            field.setAccessible(true);
            try {
                if(field.get(newObj) == null){
                    field.set(newObj,field.get(oldObj));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return newObj;
    }

    /**
     * 获取md5密码
     * @param password
     * @param salt
     * @return
     */
    public static String md5Hex(String password, String salt) {
        return md5Hex(password + salt);
    }

    /**
     * 获取md5加密字符串
     * @param str
     * @return
     */
    public static String md5Hex(String str) {
        DataCheckUtil.checkTextNull(str);
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(str.getBytes());
            StringBuffer md5StrBuff = new StringBuffer();

            for(int i = 0; i < bs.length; ++i) {
                if (Integer.toHexString(255 & bs[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(255 & bs[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(255 & bs[i]));
                }
            }

            return md5StrBuff.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * 处理pageQuery，如果没有数据，赋默认值
     * @param pageQuery
     * @return
     */
    public static PageQuery defaultPage(PageQuery pageQuery){
        int defaultPage = 1;
        int defaultSize = 10;
        if(pageQuery==null){
            pageQuery = new PageQuery();
        }
        if(pageQuery.getPage()==null || pageQuery.getPage()<1){
            pageQuery.setPage(defaultPage);
        }
        if(pageQuery.getSize()==null || pageQuery.getSize()<1){
            pageQuery.setSize(defaultSize);
        }
        return pageQuery;
    }

    /**
     * 结果集分页处理
     * @param resultList 所有结果
     * @param page 页码
     * @param size 条数
     * @param <T>
     * @return
     */
    public static <T> ResponsePage<T> result2Page(List<T> resultList, Integer page, Integer size) {
        if(page == null || size == null || resultList == null){
            throw new NullPointerException("page , size or result is null");
        }
        ResponsePage<T> responsePage = new ResponsePage<>();
        Integer startRow = (page-1) * size;
        Integer endRow = page * size;
        if(startRow < 0 || startRow > resultList.size()){
            return responsePage;
        }
        for (int i = startRow; i < resultList.size(); i++) {
            responsePage.getResultList().add(resultList.get(i));
            if(i >= endRow){
                break;
            }
        }
        responsePage.setPage(page);
        responsePage.setSize(size);
        responsePage.setTotal(resultList.size());
        return responsePage;
    }

    /**
     * 从entity获取指定属性值
     * @param fieldName
     * @param entity
     * @return
     */
    public static Object getFieldValue(String fieldName,Object entity) {
        if(fieldName == null || entity == null) {
            return null;
        }
        String text = JSON.toJSONString(entity);
        Map map = JSON.parseObject(text, Map.class);
        return map.get(fieldName);
    }

    /**
     * 根据传入函数获取属性名称 - 函数方法名必须get开头
     * @param column
     * @param <R>
     * @return
     */
    public static <R> String getFieldName(WhiteFunc<R,Object> column) {
        String methodName = getMethodByLambda(column);
        String methodPrefix = "get";
        int minLen = 4;
        if(methodName == null || !methodName.startsWith(methodPrefix) || methodName.length() < minLen) {
            return null;
        }
        methodName = methodName.replace(methodPrefix, "");
        char first = methodName.charAt(0);
        return methodName.replace(first,Character.toLowerCase(first));
    }

    /**
     * 获取传入函数的方法名
     * @param column
     * @param <T>
     * @return
     */
    public static <T> String getMethodByLambda(WhiteFunc<T,Object> column) {
        try {
            String  reflectName = "writeReplace";
            Method writeReplace = column.getClass().getDeclaredMethod(reflectName);
            writeReplace.setAccessible(true);
            Object invoke = writeReplace.invoke(column);
            SerializedLambda serializedLambda = (SerializedLambda) invoke;
            return serializedLambda.getImplMethodName();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> textList2ObjList(List<String> values, Class<T> claz) {
        return values.stream()
                .map(str->text2Obj(str, claz))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 将string类型数据转换成所需的class类型
     * @param text text
     * @param valueClass 转换类型
     * @param <T> 泛型
     * @return
     */
    public static <T> T text2Obj(String text, Class<T> valueClass) {
        if(text == null) {
            return null;
        }
        if(valueClass == String.class) {
            return valueClass.cast(text);
        }
        if(valueClass == Integer.class || valueClass == int.class) {
            return valueClass.cast(Integer.parseInt(text));
        }
        if(valueClass == Long.class || valueClass == long.class) {
            return valueClass.cast(Long.parseLong(text));
        }
        return JSON.parseObject(text,valueClass);
    }
}
