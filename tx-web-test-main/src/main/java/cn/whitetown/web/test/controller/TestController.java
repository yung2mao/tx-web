package cn.whitetown.web.test.controller;

import cn.whitetown.web.redis.manager.RedisBaseManager;
import cn.whitetown.web.redis.manager.RedisSortedSetManager;
import cn.whitetown.web.base.model.ResponseData;
import cn.whitetown.web.test.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: taixian
 * @Date: created in 2020/11/18
 */
@RequestMapping("/tes")
@RestController
public class TestController {

    @Autowired
    private RedisBaseManager redisBaseManager;

    @Autowired
    private RedisSortedSetManager sortedSetManager;

    @GetMapping
    public ResponseData tes(UserInfo userInfo) {
        System.out.println(userInfo);
        redisBaseManager.save("name","lucy", 100L);
        return ResponseData.ok();
    }
}
