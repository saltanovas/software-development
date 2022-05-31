package com.lt.vu.softwaredevelopment.usecases;

import com.lt.vu.softwaredevelopment.persistence.ProjectDao;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class SuggestRandomProject implements Serializable {
    @Inject
    private ProjectDao projectDao;

    private CompletableFuture<String> asyncTask = null;

    public String generate() {
        var allProjects = projectDao.loadAll();

        asyncTask = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return allProjects.get(new Random().nextInt(allProjects.size())).getTitle();
        });

        return "/groups/groups.xhtml?faces-redirect=true";
    }

    public boolean isAsyncTaskRunning() {
        return asyncTask != null && !asyncTask.isDone();
    }

    public String getAsyncTaskStatus() throws ExecutionException, InterruptedException {
        if (asyncTask == null) {
            return null;
        }

        if (isAsyncTaskRunning()) {
            return "Calculating...";
        }

        return "Suggested project: " + asyncTask.get();
    }
}
