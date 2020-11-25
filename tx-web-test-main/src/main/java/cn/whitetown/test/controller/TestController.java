package cn.whitetown.test.controller;

import cn.whitetown.base.model.ResponseData;
import cn.whitetown.test.model.UserInfo;
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

    @GetMapping
    public ResponseData tes(UserInfo userInfo) {
        System.out.println(userInfo);
        return ResponseData.ok();
    }
}
