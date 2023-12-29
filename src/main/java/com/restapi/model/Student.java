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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =100)
    private String firstName;

    @Column(nullable = false, length =100)
    private String lastname;

    @Column(nullable = false, length =100)
    private LocalDate dateOfBirth;

    @Column(nullable = false, length =100)
    private String gender;

    private String photo;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate dateOfJoin;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",unique = true)
    public AppUser studentUser;

    @ManyToOne
    @JoinColumn( name = "status_id", referencedColumnName = "id")
    private StudentStatus studentStatus;

    @OneToOne
    @JoinColumn(name = "class_Id", referencedColumnName = "id")
    private ClassRoom classRoom;

    @OneToMany(mappedBy = "LeaveApplicationStudent")
    private List<LeaveApplication> leaveApplicationList= new ArrayList<>();

}
