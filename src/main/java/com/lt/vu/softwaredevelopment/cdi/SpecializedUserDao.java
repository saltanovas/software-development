package com.lt.vu.softwaredevelopment.cdi;

import com.lt.vu.softwaredevelopment.entities.User;
import com.lt.vu.softwaredevelopment.persistence.UserDao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class SpecializedUserDao extends UserDao {

    @Inject
    private EntityManager em;

    public List<User> loadAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }

}
