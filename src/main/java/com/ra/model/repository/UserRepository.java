package com.ra.model.repository;

import com.ra.model.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(Integer id) ;
    User saveOrUpdate(User user) ;
    Boolean delete(Integer id);

}
