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

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] photo;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate dateOfJoin;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    public AppUser appUser;

    @OneToOne(mappedBy = "studentUser")
    private Parent parent;

    @OneToOne
    @JoinColumn( name = "status_id", referencedColumnName = "id")
    private StudentStatus studentStatus;

    @OneToMany(mappedBy = "studentUser")
    private List<AttendanceRegister> attendanceRegister = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private ClassRoom classRoom;

    @OneToOne(mappedBy = "studentUser")
    private AssignmentGrade assignmentGrade;
}
