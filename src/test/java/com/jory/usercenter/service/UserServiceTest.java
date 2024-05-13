package com.jory.usercenter.service;

import com.jory.usercenter.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Jory Zhang
 * @Date: 2024/4/27 10 38
 * @Description:
 */

/**
 * 用户服务测试
 */

@SpringBootTest
class UserServiceTest {

    @Resource
    UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("jory");
        user.setAccount("123");
        user.setAvatar("https://www.chamberofcommerce.org/wp-content/themes/chamberofcommerce/img/logo.png");
        user.setGender(0);
        user.setPassword("12345678");
        user.setPhone("123");
        user.setEmail("345");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        assertTrue(result);//断言
    }

    @Test
    void userRegister() {
        //1.校验非空
        String userAccount = "jory";
        String userPassword = "";
        String checkPassword = "123456";

        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        assertEquals(-1, result);

        //2.校验账户长度不小于4位
        userAccount = "jy";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        assertEquals(-1, result);
        //3. 密码不小于8
        userAccount = "jory";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        assertEquals(-1, result);
        //4. 账户不能包含特殊字符
        userAccount = "jy @ ";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        assertEquals(-1, result);
        //6. 密码重复密码是否相同
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        assertEquals(-1, result);
        //5. 账户不能重复
        userAccount = "jory1";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        assertEquals(-1, result);
        //成功
        userAccount = "jory";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        assertTrue(result > 0);
    }
}