package com.lt.vu.softwaredevelopment.cdi;

import com.lt.vu.softwaredevelopment.entities.User;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import java.util.List;

@Alternative
@RequestScoped
public class AlternativeUserDao implements IUserDao {
    private List<User> users = List.of(
            new User(1, "user1"),
            new User(2, "user2"),
            new User(3, "user3"),
            new User(4, "user4"),
            new User(5, "user5")
    );

    public List<User> loadAll() {
        return users;
    }

    public void persist(User user) {
        user.setId(users.size() + 1);
        users.add(user);
    }

    public User findOne(int id) {
        return users.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
