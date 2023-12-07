package com.ra.model.service;

import com.ra.model.entity.User;

import java.util.List;


public interface UserService {
    List<User> findAll();
    User findById(Integer id) ;
    User saveOrUpdate(User user) ;
    Boolean delete(Integer id);
}
