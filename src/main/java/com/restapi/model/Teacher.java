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
@ToString
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length =100)
    @Size(min = 3, message = " First name should have at least 2 characters")
    private String firstName;

    @Column(nullable = false, length =100)
    @Size(min = 3, message = " Last name should have at least 2 characters")
    private String lastname;

    @Column(nullable = false, length =100)
    @Size(min = 10, message = " Phone number should contain 10 digits")
    private String phoneNumber;

    @Column(nullable = false, length =100)
    @Size(min = 3, message = " Email should be in correct format")
    private String email;

    @Column(nullable = false, length =100)
    @Size(min = 3, message = " Enter Valid Date of Birth")
    private LocalDate dateOfBirth;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate dateOfJoin;


    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    public AppUser appUser;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @OneToOne(mappedBy = "teacherUser")
    private ClassRoom classRoom;

    @OneToMany(mappedBy = "teacherUser")
    private List<Assignment> assignments;

}
