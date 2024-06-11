package com.bench.dao;

import com.bench.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository //这个注解是什么意思
public class UserRepository {
    private List<User> userDemoList = new ArrayList<User>();

    public void save(User user){
        userDemoList.add(user);
    }
    public List<User> findAll(){
        return userDemoList;
    }
}
