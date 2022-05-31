package com.lt.vu.softwaredevelopment.persistence;

import com.lt.vu.softwaredevelopment.entities.Group;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GroupDao {

    @Inject
    private EntityManager em;

    public List<Group> loadAll() {
        return em.createNamedQuery("Group.findAll", Group.class).getResultList();
    }

    public void persist(Group group) {
        this.em.persist(group);
    }

    public Group findOne(int id) {
        return em.find(Group.class, id);
    }
}
