package com.lt.vu.softwaredevelopment.usecases;

import com.lt.vu.softwaredevelopment.entities.Project;
import com.lt.vu.softwaredevelopment.persistence.ProjectDao;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.cdi.Param;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class ProjectGroups {

    @Inject
    private ProjectDao projectDao;

    @Getter
    @Param
    private Long projectId;

    @Getter
    @Setter
    private Project project;

    @PostConstruct
    public void init() {
        if (projectId != null) {
            project = projectDao.findOne(projectId);
        }
    }
}
