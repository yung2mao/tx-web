package cn.whitetown.web.redis.manager.impl;

import cn.whitetown.web.config.RedisTemplateConfig;
import cn.whitetown.web.redis.manager.LockManager;
import cn.whitetown.web.redis.manager.RedisBaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis分布式锁实现
 * @author taixian
 * @date 2020/08/29
 **/
@Component
@ConditionalOnBean(RedisTemplateConfig.class)
public class LockManagerImpl implements LockManager {

    @Autowired
    private RedisBaseManager baseManager;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean lock(String lockName, String lockId, Long expireMills) {
        return baseManager.putIfNotExists(lockName, lockId, expireMills, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean unLock(String lockName, String lockId) {
        return baseManager.deleteIfEquals(lockName,lockId);
    }

    @Override
    public void initInStock(String productName, Long totalInStock, Long expireSecond) {
        ValueOperations<String, String> stringOperation = redisTemplate.opsForValue();
        stringOperation.set(productName,String.valueOf(totalInStock),expireSecond,TimeUnit.SECONDS);
    }

    @Override
    public boolean decrementInStock(String productName) {
        Long total = redisTemplate.opsForValue().decrement(productName);
        assert total != null;
        return total >= 0;
    }

    @Override
    public Long returnInStock(String productName) {
        return redisTemplate.opsForValue().increment(productName);
    }
}
