package com.lt.vu.softwaredevelopment.usecases;

import com.lt.vu.softwaredevelopment.entities.Project;
import com.lt.vu.softwaredevelopment.persistence.ProjectDao;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Projects {

    @Inject
    private ProjectDao projectDao;

    @Getter
    private List<Project> allProjects;

    @Getter
    @Setter
    private Project projectToCreate = new Project();

    @PostConstruct
    public void init() {
        loadAllLibraries();
    }

    @Transactional
    public void createProject() {
        projectDao.persist(projectToCreate);
    }

    private void loadAllLibraries() {
        this.allProjects = projectDao.loadAll();
    }

}
