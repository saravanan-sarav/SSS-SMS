package com.restapi.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =100)
    private String firstName;

    @Column(nullable = false, length =100)
    private String lastname;

    @Column(nullable = false, length =100)
    private String phoneNumber;

    @Column(nullable = false, length =100)
    private String email;

    @Column(nullable = false, length =100)
    private LocalDate dateOfBirth;


    @CreationTimestamp


    @Column(updatable = false)
    private LocalDate dateOfJoin;


    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    public AppUser teacherUser;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;


    @OneToMany(mappedBy = "teacherUserAssignment")
    private List<Assignment> assignments;

}
