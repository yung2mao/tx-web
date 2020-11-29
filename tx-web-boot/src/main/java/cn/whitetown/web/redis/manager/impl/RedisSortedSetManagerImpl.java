package cn.whitetown.web.redis.manager.impl;

import cn.whitetown.web.config.RedisTemplateConfig;
import cn.whitetown.web.redis.manager.RedisSortedSetManager;
import cn.whitetown.web.web.base.util.WhiteToolUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: taixian
 * @Date: created in 2020/11/29
 */
@Component
@ConditionalOnBean(RedisTemplateConfig.class)
public class RedisSortedSetManagerImpl implements RedisSortedSetManager {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public <E> void save(String key, E ele, double score, long timeout, TimeUnit timeUnit) {
        assert key != null && ele != null;
        redisTemplate.boundZSetOps(key).add(JSON.toJSONString(ele), score);
        if(timeout > 0) {
            redisTemplate.expire(key, timeout, timeUnit);
        }
    }

    @Override
    public <E> List<E> get(String key, double minScore, double maxScore, Class<E> claz) {
        assert  key != null && claz != null;
        Set<String> results = redisTemplate.boundZSetOps(key).rangeByScore(minScore, maxScore);
        if(results == null) { return new ArrayList<>(); }
        return WhiteToolUtil.textList2ObjList(new ArrayList<>(results), claz);
    }
}