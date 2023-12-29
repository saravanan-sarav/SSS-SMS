package com.restapi.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveStatus {

    public static final String APPROVE = "APPROVED";
    public static final String REJECTED = "REJECTED";
    public static final String PENDING = "PENDING";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String leaveStatus;

    @OneToMany(mappedBy = "leaveStatus")
    private List<LeaveApplication> leaveApplicationList = new ArrayList<>();
}

