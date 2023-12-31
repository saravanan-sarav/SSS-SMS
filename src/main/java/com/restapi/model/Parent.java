package com.restapi.model;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",unique = true)
    public AppUser parentUser;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "student_user_id", referencedColumnName = "id",unique = true)
    private AppUser studentUserForParent;

    @OneToMany(mappedBy = "leaveApplicationParent")
    private List<LeaveApplication> leaveApplication = new ArrayList<>();
}
