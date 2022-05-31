package com.lt.vu.softwaredevelopment.services;

import com.lt.vu.softwaredevelopment.entities.Group;
import com.lt.vu.softwaredevelopment.persistence.GroupDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Random;

@ApplicationScoped
public class RandomGroupGenerator {

    @Inject
    private GroupDao groupDao;

    public Group getRandomGroup() {
        try {
            var groups = groupDao.loadAll();
            Thread.sleep(9000);

            return groups.get(
                    new Random().nextInt(groups.size())
            );
        } catch (InterruptedException | IllegalArgumentException ex) {
            return null;
        }
    }
}
