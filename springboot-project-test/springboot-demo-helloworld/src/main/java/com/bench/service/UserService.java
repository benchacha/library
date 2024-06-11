package com.bench.service;

import com.bench.dao.UserRepository;
import com.bench.entity.User;

import java.util.List;

public interface UserService {

//    private UserRepository userDao;

    public void addUser(User user);

    public List<User> list();
}
