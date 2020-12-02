package cn.whitetown.web.redis.manager.impl;

import cn.whitetown.web.base.util.WhiteToolUtil;
import cn.whitetown.web.config.RedisTemplateConfig;
import cn.whitetown.web.redis.manager.RedisBaseManager;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * redis基本操作实现
 * @author taixian
 * @date 2020/08/29
 **/
@Component
@ConditionalOnBean(RedisTemplateConfig.class)
public class RedisBaseManagerImpl implements RedisBaseManager {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String get(String key) {
        return redisTemplate.boundGeoOps(key).getKey();
    }

    @Override
    public <T> T get(String key, Class<T> valueClass) {
        String result = redisTemplate.opsForValue()
                .get(key);
        return WhiteToolUtil.text2Obj(result,valueClass);
    }

    @Override
    public <T> List<T> getAsList(String key, Class<T> claz) {
        String result = redisTemplate.opsForValue().get(key);
        if(result == null) {
            return new ArrayList<>();
        }
        return JSON.parseArray(result,claz).stream()
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    }

    @Override
    public <T> List<T> multiGet(Collection<String> keys, Class<T> claz) {
        List<String> list = redisTemplate.opsForValue().multiGet(keys);
        if(list == null) { return new ArrayList<>(); }
        return WhiteToolUtil.textList2ObjList(list,claz);
    }

    @Override
    public <T> void save(String key, T value, Long expireSecond) {
        this.save(key, value, expireSecond, TimeUnit.SECONDS);
    }

    @Override
    public <T> void save(String key, T value, Long expire, TimeUnit timeUnit) {
        String data = JSON.toJSONString(value);
        assert key != null && expire != null && timeUnit != null;
        redisTemplate.opsForValue().set(key,data,expire, timeUnit);
    }

    @Override
    public void multiSaveWithExpireTime(Map<String, String> map, long timeout, TimeUnit timeUnit) {
        long seconds = timeUnit.toSeconds(timeout);
        redisTemplate.executePipelined((RedisCallback<Object>) redisConnection -> {
            map.forEach((key, value) -> redisConnection.setEx(key.getBytes(), seconds, value.getBytes()));
            return null;
        });
    }

    @Override
    public <T> boolean putIfNotExists(String key, T value, Long timeout, TimeUnit timeUnit) {
        if(key == null || value == null || timeout == null) {
            throw new NullPointerException();
        }
        if(timeUnit == null) {
            timeUnit = TimeUnit.SECONDS;
        }
        String data = JSON.toJSONString(value);
        Boolean success = redisTemplate.opsForValue().setIfAbsent(key, data, timeout, timeUnit);
        return success == null ? false : success;
    }

    @Override
    public <T> void expire(String key, Long expireSeconds) {
        assert key != null && expireSeconds != null;
        redisTemplate.expire(key,expireSeconds,TimeUnit.SECONDS);
    }

    @Override
    public <T> void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public <T> boolean deleteIfEquals(String key, T value) {
        boolean exists = redisTemplate.opsForValue().get(key) != null;
        if(!exists) {
            return true;
        }
        if(value == null) {
            Boolean delete = redisTemplate.delete(key);
            return delete == null ? false : delete;
        }
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) " +
                "else return 0 end";
        redisScript.setScriptText(script);
        List<String> keys = new ArrayList<>();

        keys.add(key);
        String data = JSON.toJSONString(value);
        Long result = redisTemplate.execute(redisScript, keys, data);
        assert result != null;
        return result > 0L;
    }
}
