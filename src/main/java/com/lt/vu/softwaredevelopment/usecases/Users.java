package com.lt.vu.softwaredevelopment.usecases;

import com.lt.vu.softwaredevelopment.entities.User;
import com.lt.vu.softwaredevelopment.persistence.UserDao;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Users {

    @Inject
    private UserDao userDao;

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
        userDao.persist(userToCreate);
    }

    private void loadAllUsers() {
        allUsers = userDao.loadAll();
    }
}
