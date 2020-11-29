package cn.whitetown.web.redis.manager.impl;

import cn.whitetown.web.config.RedisTemplateConfig;
import cn.whitetown.web.redis.manager.RedisHashManager;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author taixian
 * @date 2020/08/29
 **/
@Component
@ConditionalOnBean(RedisTemplateConfig.class)
public class RedisHashManagerImpl implements RedisHashManager {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public <T> T get(String key, String field, Class<T> valueClass) {
        Object result = redisTemplate.opsForHash().get(key, field);
        if(result == null) { return null; }
        return JSON.parseObject(String.valueOf(result),valueClass);
    }

    @Override
    public Map<String,String> getAll(String key) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        Map<String,String> map = new HashMap<>(16);
        entries.forEach((k,v)->map.put(String.valueOf(k),String.valueOf(v)));
        map.remove(null);
        return map;
    }

    @Override
    public List<String> multiGet(String key, String ... fields) {
        if(fields == null) { return new ArrayList<>(); }
        List<Object> fieldList = new LinkedList<>(Arrays.asList(fields));
        List<Object> results = redisTemplate.opsForHash().multiGet(key, fieldList);
        return results.stream().
                filter(Objects::nonNull).
                map(String::valueOf).
                collect(Collectors.toList());
    }

    @Override
    public <T> void save(String key, String field, T value, Long expireSeconds) {
        redisTemplate.opsForHash().put(key,
                field, JSON.toJSONString(value));
        if(expireSeconds != null && expireSeconds > 0) {
            redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
        }
    }

    @Override
    public <T> void saveEntries(String key, Map<String, T> map, Long expireSeconds) {
        Map<String, String> saveMap = new HashMap<>();
        map.forEach((k,v) -> saveMap.put(k,JSON.toJSONString(v)));
        redisTemplate.opsForHash().putAll(key, saveMap);
        if(expireSeconds != null && expireSeconds > 0) {
            redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
        }
    }

    @Override
    public <T> void deleteCusFields(String key, String ... fields) {
        if (fields == null || fields.length < 1) {
            return;
        }
        redisTemplate.opsForHash().delete(key, (Object) fields);
    }
}
