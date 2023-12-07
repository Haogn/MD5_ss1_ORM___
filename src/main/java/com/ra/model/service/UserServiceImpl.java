package com.ra.model.service;

import com.ra.model.entity.User;
import com.ra.model.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository ;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveOrUpdate(User user) {
        String BCryptPass = user.getPassword();
        BCryptPass = BCrypt.hashpw(BCryptPass,BCrypt.gensalt(12));
        user.setPassword(BCryptPass);
        return userRepository.saveOrUpdate(user);
    }

    @Override
    public Boolean delete(Integer id) {
        return userRepository.delete(id);
    }
}
