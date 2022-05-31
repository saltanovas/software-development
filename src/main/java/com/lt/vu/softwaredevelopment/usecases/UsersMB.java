package com.lt.vu.softwaredevelopment.usecases;

import com.lt.vu.softwaredevelopment.mybatis.User;
import com.lt.vu.softwaredevelopment.mybatis.dao.UserMapper;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class UsersMB {

    @Inject
    private UserMapper userMapper;

    @Getter
    private List<User> allUsers;

    @Getter
    @Setter
    private User userToCreate = new User();

    @PostConstruct
    public void init() {
        loadAllUsers();
    }

    @Transactional
    public void createUser() {
        userMapper.insert(userToCreate);
    }

    private void loadAllUsers() {
        allUsers = userMapper.selectAll();
    }
}
