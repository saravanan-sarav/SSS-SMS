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

    @Column(nullable = false, length = 100)
    private String password;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roles;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "parentUser")
    private Parent parent;

    @OneToOne(mappedBy = "studentUser")
    private Student student;

    @OneToOne(mappedBy = "teacherUser")
    private Teacher teacher;

    @OneToOne(mappedBy = "studentUserForParent")
    private Parent parentStudentUserId;

    @OneToMany(mappedBy = "tamilTeacherUser")
    private List<ClassRoom> classRoomTamilTeacher;

    @OneToMany(mappedBy = "englishTeacherUser")
    private List<ClassRoom> classRoomEnglishTeacher;

    @OneToMany(mappedBy = "mathsTeacherUser")
    private List<ClassRoom> classRoomMathsTeacher;

    @OneToMany(mappedBy = "scienceTeacherUser")
    private List<ClassRoom> classRoomScienceTeacher;

    @OneToMany(mappedBy = "socialTeacherUser")
    private List<ClassRoom> classRoomSocialTeacher;

    @OneToMany(mappedBy = "studentUserAttendance")
    private List<AttendanceRegister> attendanceRegister = new ArrayList<>();

    @OneToOne(mappedBy = "teacherUserClassRoom")
    private ClassRoom classRoom;
}
