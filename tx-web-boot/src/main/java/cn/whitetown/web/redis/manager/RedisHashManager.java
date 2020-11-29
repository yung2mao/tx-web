package cn.whitetown.web.redis.manager;

import java.util.List;
import java.util.Map;

/**
 * hash结构操作
 * @author taixian
 * @date 2020/08/29
 **/
public interface RedisHashManager {

    /**
     * 获取hash结构中field对应value
     * @param key
     * @param field
     * @param valueClass
     * @param <T>
     * @return
     */
    <T> T get(String key, String field, Class<T> valueClass);

    /**
     * 获取hash中所有数据
     * @param key
     * @return
     */
    Map<String,String> getAll(String key);

    /**
     * 获取hash中指定的field
     * @param key
     * @param fields
     * @return
     */
    List<String> multiGet(String key, String... fields);

    /**
     * 存储为hash结构
     * @param key key
     * @param field hash结构field
     * @param value hash结构value
     * @param expireSeconds 超时时间
     * @param <T> 泛型
     */
    <T> void save(String key, String field, T value, Long expireSeconds);

    /**
     * 将map保存为hash结构
     * @param key key
     * @param map 需要保存到hash key的数据map
     * @param expireSeconds 超时时间
     * @param <T> 泛型
     */
    <T> void saveEntries(String key, Map<String, T> map, Long expireSeconds);

    /**
     * 删除hash结构中某个field
     * @param key key
     * @param fields 需要删除的fields
     * @param <T> 泛型
     */
    <T> void deleteCusFields(String key, String... fields);
}
