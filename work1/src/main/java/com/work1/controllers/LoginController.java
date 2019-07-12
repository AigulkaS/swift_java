package com.work1.controllers;

import com.work1.dao.HiberDAO;
import com.work1.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    HiberDAO dao;

    @PostConstruct
    public void init1() {
        dao.init();
    }

    @RequestMapping("login.do")
    public String login(String name, String password) {
        if (loginService.login(name,password)) {
            if (name.equals("admin")) {
                return "redirect:showForAdmin.do";
            } else return "redirect:showForUser.do";
        } else return "redirect:index.jsp";
    }
}
