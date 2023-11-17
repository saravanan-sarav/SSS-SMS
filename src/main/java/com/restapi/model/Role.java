package com.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    public static final String STUDENT = "STUDENT";
    public static final String ADMIN = "ADMIN";
    public static final String PARENT = "PARENT";
    public static final String TEACHER = "TEACHER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "roles")
    private List<AppUser> appUsers;
}