package com.example.controller;

import com.example.annotation.TkParam;
import com.example.model.UserModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author WeiLi
 **/
@RestController
@RequestMapping
public class TestController {

    @RequestMapping("test")
    public String testSession(@TkParam String username) throws Exception {
        return username;
    }

    @RequestMapping("model")
    public String testModel(@TkParam UserModel model) throws Exception {
        return String.format("用户名：%s，密码：%s", model.getUsername(), model.getPassword());
    }

}
