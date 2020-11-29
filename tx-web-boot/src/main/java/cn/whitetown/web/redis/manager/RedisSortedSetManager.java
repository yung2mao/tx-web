package cn.whitetown.web.redis.manager;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis -zset操作
 * @Author: taixian
 * @Date: created in 2020/11/29
 */
public interface RedisSortedSetManager {

    /**
     * 存储数据 - 指定评分
     * @param key key
     * @param ele zset中的一个元素
     * @param score 当前元素的评分
     * @param timeout 超时时间
     * @param timeUnit 超时时间单位
     * @param <E> 泛型
     */
    <E> void save(String key, E ele, double score, long timeout, TimeUnit timeUnit);

    /**
     * 获取评分范围内的数据
     * @param key key
     * @param minScore 最低评分
     * @param maxScore 最高评分
     * @param claz 返回类型
     * @param <E> 泛型
     * @return
     */
    <E> List<E> get(String key, double minScore, double maxScore, Class<E> claz);
}
