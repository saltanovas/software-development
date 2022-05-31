package com.lt.vu.softwaredevelopment.rest;

import com.lt.vu.softwaredevelopment.entities.User;
import com.lt.vu.softwaredevelopment.persistence.GroupDao;
import com.lt.vu.softwaredevelopment.persistence.UserDao;
import com.lt.vu.softwaredevelopment.rest.modals.CreateUserPayload;
import com.lt.vu.softwaredevelopment.rest.modals.GetUserResponse;
import com.lt.vu.softwaredevelopment.rest.modals.UpdateUserPayload;
import com.lt.vu.softwaredevelopment.services.RandomGroupGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@ApplicationScoped
@Path("/user")
public class UserController {

    @Inject
    private UserDao userDao;

    @Inject
    private GroupDao groupDao;

    @Inject
    private RandomGroupGenerator randomGroupGenerator;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response show(@PathParam("id") final int id) {
        User user = userDao.findOne(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(new GetUserResponse(user.getName()))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response store(CreateUserPayload createUserPayload) {
        User user = new User();

        user.setName(createUserPayload.getName());
        user.setGroups(Set.of(randomGroupGenerator.getRandomGroup()));

        userDao.persist(user);

        return Response.status(Response.Status.CREATED).build();
    }

    @Path("/update1/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update1(@PathParam("id") final int id, UpdateUserPayload updateUserPayload) {
        try {
            userDao.updateOne(updateUserPayload, id);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (InterruptedException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/update2/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update2(@PathParam("id") final int id, UpdateUserPayload updateUserPayload) {
        userDao.updateTwo(updateUserPayload, id);
        return Response.ok().build();
    }
}
