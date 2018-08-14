package com.belajar.springMvn.belajarSpringMVN.sercive.impl;

import com.belajar.springMvn.belajarSpringMVN.dao.UserDAO;
import com.belajar.springMvn.belajarSpringMVN.entity.User;
import com.belajar.springMvn.belajarSpringMVN.sercive.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public User save(User param) {
        return userDAO.save(param);
    }

    @Override
    public User update(User param) {
        return userDAO.update(param);
    }

    @Override
    public int delete(User param) {
        return userDAO.delete(param);
    }

    @Override
    public User findById(User param) {
        return userDAO.findById(param);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }
}
