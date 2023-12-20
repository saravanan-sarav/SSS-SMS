package com.restapi.dto;

import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.ParentRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.request.TeacherRequest;
import com.restapi.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthDto {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;

    public AppUser mapToAppUser(RegisterRequest user) {
        AppUser appUser = new AppUser();
        appUser.setUsername(user.getUsername());
        appUser.setPassword(user.getPassword());
        appUser.setName(user.getName());
        return appUser;
    }


    public AuthResponse mapToAuthResponse(AppUser appUser) {
        System.out.println(appUser.getRoles().getName());
        AuthResponse authResponse = new AuthResponse();
        if(appUser.getRoles().getName().equals(Role.ADMIN)){
            authResponse.setId(appUser.getId());
            authResponse.setUsername(appUser.getUsername());
            authResponse.setName(appUser.getName());
            authResponse.setRole(appUser.getRoles().getName());
        }else if (appUser.getRoles().getName().equals(Role.TEACHER)){
            Optional<ClassRoom> classRoom = classRoomRepository.findByTeacherUserClassRoom(appUser.getId());
            Optional<Teacher> teacher = teacherRepository.findByUserId(appUser.getId());
            authResponse.setId(appUser.getId());
            authResponse.setUsername(appUser.getUsername());
            authResponse.setName(appUser.getName());
            authResponse.setRole(appUser.getRoles().getName());
            if(classRoom.isPresent()){
                authResponse.setTeacherClassId(classRoom.get().getId());
            }
            authResponse.setSubjectId(teacher.get().getSubject().getId());
        }else if (appUser.getRoles().getName().equals(Role.STUDENT)){
            Parent parent = parentRepository.findByStudentUserId(appUser.getId());
            System.out.println(parent.getStudentUserForParent().getId());
            Optional<Student> student = studentRepository.findByUserIdForApprove(parent.getStudentUserForParent().getId());
            if(student.isPresent()){
                authResponse.setId(appUser.getId());
                authResponse.setUsername(appUser.getUsername());
                authResponse.setName(appUser.getName());
                authResponse.setRole(appUser.getRoles().getName());
                authResponse.setParentId(parent.getParentUser().getId());
                authResponse.setStudentActiveStatus(student.get().getStudentStatus().getId());
            }
        }else if (appUser.getRoles().getName().equals(Role.PARENT)){
            Optional<Parent> parent = parentRepository.findByUserId(appUser.getId());
            if(parent.isPresent()){
                Optional<Student> student = studentRepository.findByUserIdForApprove(parent.get().getStudentUserForParent().getId());
                authResponse.setStudentId(student.get().getStudentUser().getId());
                if(student.isPresent()) {
                    authResponse.setId(appUser.getId());
                    authResponse.setUsername(appUser.getUsername());
                    authResponse.setName(appUser.getName());
                    authResponse.setRole(appUser.getRoles().getName());
                    authResponse.setStudentActiveStatus(student.get().getStudentStatus().getId());
                    authResponse.setStudentActiveStatus(student.get().getStudentStatus().getId());
                }
            }

        }
        System.out.println(authResponse.getStudentActiveStatus());
        return authResponse;
    }

    public AppUser setParentAuth(Role parentRole, ParentRequest parentRequest) {
        AppUser user = new AppUser();
        if(parentRequest.getParentUserId()!=0){
            Optional<AppUser> userFetch = userRepository.findById(parentRequest.getParentUserId());
            return userFetch.get();
        }else {
            user.setUsername(parentRequest.getParentUsername());
            user.setPassword(bCryptPasswordEncoder.encode(parentRequest.getParentPassword()));
            user.setName(parentRequest.getFatherName());
            user.setRoles(parentRole);
            return user;
        }
    }

    public AppUser setStudentAuth(Role studentRole, ParentRequest parentRequest) {
        AppUser user = new AppUser();
        if(parentRequest.getStudentUserId()!=0){
            Optional<AppUser> userFetch = userRepository.findById(parentRequest.getStudentUserId());
            return userFetch.get();
        }
        else {
            user.setUsername(parentRequest.getStudentUsername());
            user.setPassword(bCryptPasswordEncoder.encode(parentRequest.getStudentPassword()));
            user.setName(parentRequest.getFirstName());
            user.setRoles(studentRole);
            return user;
        }
    }

    public AppUser setTeacherAuth(Role teacherRole, TeacherRequest teacherRequest) {
        if(teacherRequest.getTeacherUserId()!=0){
            Optional<AppUser> teacherFetch = userRepository.findById(teacherRequest.getTeacherUserId());
            return teacherFetch.get();
        }
        else {
            AppUser user = new AppUser();
            user.setUsername(teacherRequest.getTeacherUsername());
            user.setPassword(bCryptPasswordEncoder.encode(teacherRequest.getTeacherPassword()));
            user.setName(teacherRequest.getFirstName());
            user.setRoles(teacherRole);
            return user;
        }
    }
}
