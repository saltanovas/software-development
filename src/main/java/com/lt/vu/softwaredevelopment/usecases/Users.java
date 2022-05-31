package com.lt.vu.softwaredevelopment.usecases;

import com.lt.vu.softwaredevelopment.cdi.IUserDao;
import com.lt.vu.softwaredevelopment.entities.User;
import com.lt.vu.softwaredevelopment.interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class Users {

    @Inject
    private IUserDao userDao;

    @Getter
    private List<User> allUsers;

    @Getter
    @Setter
    private User userToCreate = new User();

    @PostConstruct
    public void init() {
        loadAllUsers();
    }

    @LoggedInvocation
    public void createUser() {
        userDao.persist(userToCreate);
    }

    private void loadAllUsers() {
        allUsers = userDao.loadAll();
    }
}
