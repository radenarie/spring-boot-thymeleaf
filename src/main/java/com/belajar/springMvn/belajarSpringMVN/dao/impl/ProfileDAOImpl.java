package com.belajar.springMvn.belajarSpringMVN.dao.impl;

import com.belajar.springMvn.belajarSpringMVN.dao.ProfileDAO;
import com.belajar.springMvn.belajarSpringMVN.entity.Profile;
import com.belajar.springMvn.belajarSpringMVN.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@Repository
public class ProfileDAOImpl implements ProfileDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Profile save(Profile param) {

        String sql = "insert into tprofile (namaLengkap, alamat, umur, jenisKelamin) value (?, ?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, param.getNamaLengkap());
            preparedStatement.setString(2, param.getAlamat());
            preparedStatement.setInt(3, param.getUmur());
            preparedStatement.setString(4, param.getJenisKelamin());
            return preparedStatement;
        }, keyHolder);

        param.setId(keyHolder.getKey().intValue());
        return param;
    }

    @Override
    public Profile update(Profile param) {
        String sql = "update tprofile set namaLengkap = ?, alamat = ?, umur = ?, jenisKelamin = ? where id = ? ";
        int del = jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, param.getNamaLengkap());
            preparedStatement.setString(2, param.getAlamat());
            preparedStatement.setInt(3, param.getUmur());
            preparedStatement.setString(4, param.getJenisKelamin());
            preparedStatement.setInt(5, param.getId());
            return preparedStatement;
        });
        param.setId(del);
        return param;
    }

    @Override
    public int delete(Profile param) {
        String sql ="delete from tprofile where id = ?";

        int del = jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, param.getId());
            return preparedStatement;
        });

        return del;
    }

    @Override
    public Profile findById(Profile param) {
        String sql =" SELECT * FROM tprofile where id = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{param.getId()},new BeanPropertyRowMapper<>(Profile.class));
    }

    @Override
    public List<Profile> findAll() {
        String sql = "SELECT * FROM tprofile";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Profile.class));
    }
}
