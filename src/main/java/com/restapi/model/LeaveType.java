package com.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveType {

    public static String LEAVE = "Leave";
    public static String PERMISSION = "Permission";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String leaveType;

    @OneToMany(mappedBy = "leaveType")
    private List<LeaveType> leaveTypeList = new ArrayList<>();
}
