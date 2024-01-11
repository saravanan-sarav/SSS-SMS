package com.restapi.model;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private LocalDate toDate;

    @Column(nullable = false)
    private String Comments;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime applyDate;

    @ManyToOne
    @JoinColumn(name = "parent_user_id", referencedColumnName = "id", nullable = false)
    private AppUser leaveApplicationParent;

    @ManyToOne
    @JoinColumn(name = "student_user_id", referencedColumnName = "id", nullable = false)
    private AppUser leaveApplicationStudent;

    @ManyToOne
    @JoinColumn(name = "leave_status_id", referencedColumnName = "id", nullable = false)
    private LeaveStatus leaveStatus;

    @ManyToOne
    @JoinColumn(name = "leave_type_id", referencedColumnName = "id", nullable = false)
    private LeaveType leaveType;

    @ManyToOne
    @JoinColumn(name = "leave_reason_id", referencedColumnName = "id", nullable = false)
    private LeaveReason leaveReason;
}
