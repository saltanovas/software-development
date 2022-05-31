package com.lt.vu.softwaredevelopment.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "select a from User as a")
})
@Table
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "users")
    private Set<Group> groups;

    @Version
    @Column(name = "opt_lock_version")
    private int version;

    public User(int id, String name){
        this.id = id;
        this.name = name;
    }
}
