package com.work1.services;

import com.work1.dao.HiberDAO;
import com.work1.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class LoginService {

    @Autowired
    HiberDAO dao;

    String userName;
    Logger logger = Logger.getLogger(LoginService.class.getName());

    public boolean login(String name, String password) {
        logger.warning("Trying to login as "+name);
        List<Users> users = dao.getUserByLogin(name);
        if (users.size() != 0) {
            if (users.get(0).getPassword().equals(password)) {
                userName = name;
                logger.warning("Logged as "+userName);
                return true;
            } else return false;
        } else return false;
    }

    public String getUserName() {
        return userName;
    }
}
