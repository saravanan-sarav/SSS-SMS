package com.restapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassStandard {

    public static final String SIXTH = "SIXTH";
    public static final String SEVENTH = "SEVENTH";
    public static final String EIGHTH = "EIGHTH";
    public static final String NINTH = "NINTH";
    public static final String TENTH = "TENTH";
    public static final String PLUS_ONE = "PLUS_ONE";
    public static final String PLUS_TWO = "PLUS_TWO";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String standard;

    @OneToOne(mappedBy = "classStandard")
    private ClassRoom classRoom;

}
