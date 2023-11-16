package com.restapi.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Parent {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String MotherName;

    @Column(nullable = false, length = 100)
    private String motherPhoneNumber;

    private String motherOccupation;

    @Column(nullable = false, length = 100)
    private String FatherName;

    @Column(nullable = false, length = 100)
    private String FatherPhoneNumber;

    private String fatherOccupation;

    @Column(nullable = false, length = 100)
    private String email;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate dateOfJoin;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    public AppUser appUser;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private AppUser studentUser;
}
