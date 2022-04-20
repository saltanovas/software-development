package com.lt.vu.softwaredevelopment.persistence;


import com.lt.vu.softwaredevelopment.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class UserDao {
    @Inject
    private EntityManager em;

    public List<User> loadAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }

    public void persist(User user) {
        this.em.persist(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

}
