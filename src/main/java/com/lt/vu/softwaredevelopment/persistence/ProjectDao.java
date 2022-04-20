package com.lt.vu.softwaredevelopment.persistence;

import com.lt.vu.softwaredevelopment.entities.Project;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ProjectDao {

    @Inject
    private EntityManager em;

    public List<Project> loadAll() {
        return em.createNamedQuery("Project.findAll", Project.class).getResultList();
    }

    public void persist(Project project) {
        this.em.persist(project);
    }

    public Project findOne(Long id) {
        return em.find(Project.class, id);
    }
}
