package com.restapi.model;

import io.swagger.models.refs.GenericRef;
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
public class LeaveReason {
    public static String SICK_LEAVE = "SicK Leave";
    public static String FAMILY_VACATION = "Family Vacation";
    public static String RELIGIOUS_FUNCTION = "Religious Function";
    public static String ON_DUTY = "On Duty";
    public static String MEDICAL_LEAVE = "Medical Leave";
    public static String CASUAL_LEAVE = "Casual Leave";
    public static String OTHERS = "Others";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reason;

    @OneToMany(mappedBy = "leaveReason")
    private List<LeaveApplication> leaveApplicationList = new ArrayList<>();
}
