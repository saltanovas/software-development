package com.lt.vu.softwaredevelopment.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@NamedQueries({
        @NamedQuery(name = "Project.findAll", query = "select a from Project as a")
})
@Table
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "project")
    @EqualsAndHashCode.Exclude
    private Set<Group> groups;

}
