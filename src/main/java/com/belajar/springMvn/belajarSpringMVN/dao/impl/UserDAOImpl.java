package com.belajar.springMvn.belajarSpringMVN.dao.impl;

import com.belajar.springMvn.belajarSpringMVN.dao.UserDAO;
import com.belajar.springMvn.belajarSpringMVN.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User save(User param) {
            String sql = "insert into user (username, password) value (?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, param.getUsername());
            preparedStatement.setString(2, param.getPassword());
            return preparedStatement;
        }, keyHolder);

        param.setId(keyHolder.getKey().intValue());
        return param;
    }

    @Override
    public User update(User param) {
        String sql = "update user set username = ?, password = ? where id = ? ";
        int del = jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, param.getUsername());
            preparedStatement.setString(2, param.getPassword());
            preparedStatement.setInt(3, param.getId());
            return preparedStatement;
        });
        param.setId(del);
        return param;
    }

    @Override
    public int delete(User param) {
        String sql ="delete from user where id = ?";

        int del = jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, param.getId());
            return preparedStatement;
        });

        return del;
    }

    @Override
    public User findById(User param) {
        String sql =" SELECT * FROM user where id = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{param.getId()},new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
