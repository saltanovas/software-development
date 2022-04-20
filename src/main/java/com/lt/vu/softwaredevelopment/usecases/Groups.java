package com.lt.vu.softwaredevelopment.usecases;

import com.lt.vu.softwaredevelopment.entities.Group;
import com.lt.vu.softwaredevelopment.persistence.GroupDao;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Groups {

    @Inject
    private GroupDao groupDao;

    @Getter
    private List<Group> allGroups;

    @Getter
    @Setter
    private Group groupToCreate = new Group();

    @PostConstruct
    public void init() {
        loadAllGroups();
    }

    @Transactional
    public void createGroup() {
        groupDao.persist(groupToCreate);
    }

    private void loadAllGroups() {
        allGroups = groupDao.loadAll();
    }
}
