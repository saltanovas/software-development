package com.lt.vu.softwaredevelopment.cdi;

import com.lt.vu.softwaredevelopment.entities.User;

import java.util.List;

public interface IUserDao {
    List<User> loadAll();
    void persist(User user);
    User findOne(int id);
}
