package com.lt.vu.softwaredevelopment.persistence;


import com.lt.vu.softwaredevelopment.cdi.IUserDao;
import com.lt.vu.softwaredevelopment.entities.User;
import com.lt.vu.softwaredevelopment.rest.modals.UpdateUserPayload;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class UserDao implements IUserDao {
    @Inject
    private EntityManager em;

    public List<User> loadAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }

    public void persist(User user) {
        this.em.persist(user);
    }

    public User findOne(int id) {
        return em.find(User.class, id);
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void updateOne(UpdateUserPayload updateUserPayload, int userId) throws InterruptedException {
        User user = findOne(userId);
        Thread.sleep(6000);
        user.setName(updateUserPayload.getName());
        em.flush();
    }

    @Transactional
    public void updateTwo(UpdateUserPayload updateUserPayload, int userId){
        User user = findOne(userId);
        user.setName(updateUserPayload.getName());
        em.flush();
    }
}
