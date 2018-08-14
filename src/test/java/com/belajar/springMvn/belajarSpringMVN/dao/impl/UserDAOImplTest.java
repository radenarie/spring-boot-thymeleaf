package com.belajar.springMvn.belajarSpringMVN.dao.impl;

import com.belajar.springMvn.belajarSpringMVN.dao.UserDAO;
import com.belajar.springMvn.belajarSpringMVN.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOImplTest {
    UserDAO userDAO = new UserDAOImpl();

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
        User user = new User();
        user.setId(8);
        int data = userDAO.delete(user);
        assertNotNull(data);
    }

    @Test
    public void findById() {
    }

    @Test
    public void findAll() {
    }
}