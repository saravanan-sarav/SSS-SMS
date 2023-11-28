package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @Column(nullable = false, length = 100)
    private String password;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roles;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToOne(mappedBy = "parentUser")
    private Parent parent;

    @JsonIgnore
    @OneToOne(mappedBy = "studentUser")
    private Student student;

    @JsonIgnore
    @OneToOne(mappedBy = "teacherUser")
    private Teacher teacher;

    @JsonIgnore
    @OneToOne(mappedBy = "studentUserForParent")
    private Parent parentStudentUserId;

    @JsonIgnore
    @OneToMany(mappedBy = "tamilTeacherUser")
    private List<ClassRoom> classRoomTamilTeacher;

    @JsonIgnore
    @OneToMany(mappedBy = "englishTeacherUser")
    private List<ClassRoom> classRoomEnglishTeacher;

    @JsonIgnore
    @OneToMany(mappedBy = "mathsTeacherUser")
    private List<ClassRoom> classRoomMathsTeacher;

    @JsonIgnore
    @OneToMany(mappedBy = "scienceTeacherUser")
    private List<ClassRoom> classRoomScienceTeacher;

    @JsonIgnore
    @OneToMany(mappedBy = "socialTeacherUser")
    private List<ClassRoom> classRoomSocialTeacher;

    @JsonIgnore
    @OneToMany(mappedBy = "studentUserAttendance")
    private List<AttendanceRegister> attendanceRegister = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "teacherUserClassRoom")
    private ClassRoom classRoom;

    @ManyToMany(mappedBy = "studentUserAssignmentGrade")
    private List<AssignmentGrade> assignmentGradeList = new ArrayList<>();
}
