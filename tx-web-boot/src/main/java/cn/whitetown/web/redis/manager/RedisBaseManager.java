package cn.whitetown.web.redis.manager;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis基本操作
 * @author taixian
 * @date 2020/08/29
 **/
public interface RedisBaseManager {

    /**
     * 获取key的值 - String类型返回
     * @param key key
     * @return -
     */
    String get(String key);

    /**
     * 获取key对应的value，转换为指定对象
     * @param key key
     * @param valueClass 获取类型
     * @param <T> -
     * @return -
     */
    <T> T get(String key, Class<T> valueClass);

    /**
     * 获取list元素
     * @param key key
     * @param claz 返货list中元素类型
     * @param <T> 类型
     * @return -
     */
    <T> List<T> getAsList(String key, Class<T> claz);

    /**
     * 批量获取数据
     * @param keys 需要获取的keys
     * @param claz 数据类型
     * @param <T> 泛型
     * @return -
     */
    <T> List<T> multiGet(Collection<String> keys, Class<T> claz);

    /**
     * 保存元素
     * @param key
     * @param value
     * @param expireSeconds
     * @param <T>
     */
    <T> void save(String key, T value, Long expireSeconds);

    /**
     * 保存元素
     * @param key
     * @param value
     * @param expire
     * @param timeUnit
     * @param <T>
     */
    <T> void save(String key, T value, Long expire, TimeUnit timeUnit);

    /**
     * 批量缓存 - String结构
     * @param map 需要缓存的数据 key-value
     * @param timeout 超时时间
     * @param timeUnit 时间单位
     */
    void multiSaveWithExpireTime(Map<String, String> map, long timeout, TimeUnit timeUnit);

    /**
     * 添加元素 - 如果不存在
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     * @param <T>
     * @return 保存是否成功
     */
    <T> boolean putIfNotExists(String key, T value, Long timeout, TimeUnit timeUnit);

    /**
     * 为key设置过期时间
     * @param key
     * @param expireSeconds
     * @param <T>
     */
    <T> void expire(String key, Long expireSeconds);

    /**
     * 删除元素
     * @param key
     * @param <T>
     */
    <T> void delete(String key);

    /**
     * value相等时才会删除
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    <T> boolean deleteIfEquals(String key, T value);

}
