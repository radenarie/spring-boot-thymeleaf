package com.belajar.springMvn.belajarSpringMVN.sercive.impl;

import com.belajar.springMvn.belajarSpringMVN.dao.ProfileDAO;
import com.belajar.springMvn.belajarSpringMVN.entity.Profile;
import com.belajar.springMvn.belajarSpringMVN.sercive.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileDAO profileDAO;
    @Override
    public Profile save(Profile param) {
        return profileDAO.save(param);
    }

    @Override
    public Profile update(Profile param) {
        return profileDAO.update(param);
    }

    @Override
    public int delete(Profile param) {
        return profileDAO.delete(param);
    }

    @Override
    public Profile findById(Profile param) {
        return profileDAO.findById(param);
    }

    @Override
    public List<Profile> findAll() {
        return profileDAO.findAll();
    }
}
