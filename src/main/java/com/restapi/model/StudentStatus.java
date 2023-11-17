package com.restapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentStatus {

    public static final String ACTIVE = "ACTIVE";
    public static final String DROPOUT = "DROPOUT";
    public static final String GRADUATED = "GRADUATED";
    public static final String PENDING = "PENDING";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    @OneToOne(mappedBy = "studentStatus")
    private Student student;
}
