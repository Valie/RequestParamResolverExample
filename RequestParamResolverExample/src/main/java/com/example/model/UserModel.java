package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description 用户登录信息
 * @Author WeiLi
 **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码(MD5)
     */
    private String password;
}
