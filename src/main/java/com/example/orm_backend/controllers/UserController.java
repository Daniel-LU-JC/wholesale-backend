package com.example.orm_backend.controllers;

import com.example.orm_backend.entity.User;
import com.example.orm_backend.entity.UserWithMoney;
import com.example.orm_backend.service.TimeService;
import com.example.orm_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    WebApplicationContext webApplicationContext;

    @RequestMapping("/register")
    public void register(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("address") String address
    ) {
        userService.register(name, password, email, address);
    }

    @RequestMapping("/checkRepeat")
    public String checkRepeat(@RequestParam("name") String name) {
        return userService.checkRepeat(name);
    }

    @RequestMapping("/checkAuth")
    public List<User> checkAuth(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("identity") String identity
    ) {
        TimeService timeService =
                webApplicationContext.getBean(TimeService.class);
        System.out.println(timeService);
        System.out.println(System.currentTimeMillis());
        timeService.loginRecord();

        return userService.checkAuth(name, password, identity);
    }

    @RequestMapping("/logout")
    public Long logout() {
        TimeService timeService =
                webApplicationContext.getBean(TimeService.class);
        System.out.println(timeService);
        System.out.println(System.currentTimeMillis());
        System.out.println(timeService.logoutRecord());
        return timeService.logoutRecord() / 1000;
    }

    @RequestMapping("/getUsersAll")
    public List<User> getUsersAll() {
        return userService.getUsersAll();
    }

    @RequestMapping("/changeState")
    public void changeState(@RequestParam("user_id") String user_id) {
        userService.changeState(Integer.parseInt(user_id));
    }

    @RequestMapping("/getUsersRanking")
    public List<UserWithMoney> getUsersRanking(
            @RequestParam("start") String start,
            @RequestParam("end") String end
    ) throws ParseException {
        return userService.getUsersRanking(start, end);
    }
}
