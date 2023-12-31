package com.restapi.model;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fromDate;


    private LocalTime fromTime;
    private LocalTime toTime;

    @Column(nullable = false)
    private LocalDate toDate;

    @Column(nullable = false)
    private String Comments;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDate applyDate;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = false)
    private AppUser leaveApplicationParent;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private AppUser LeaveApplicationStudent;

    @ManyToOne
    @JoinColumn(name = "leave_status_id", referencedColumnName = "id", nullable = false)
    private LeaveStatus leaveStatus;
}
