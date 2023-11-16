package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.repository.UserRepository;
import com.restapi.request.ParentRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.request.TeacherRequest;
import com.restapi.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthDto {

    public AppUser mapToAppUser(RegisterRequest user) {
        AppUser appUser = new AppUser();
        appUser.setUsername(user.getUsername());
        appUser.setPassword(user.getPassword());
        return appUser;
    }


    public AuthResponse mapToAuthResponse(AppUser appUser) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setId(appUser.getId());
        authResponse.setUsername(appUser.getUsername());
        return authResponse;
    }

    public AppUser setParentAuth(Role parentRole, ParentRequest parentRequest) {
        AppUser user = new AppUser();
        if(parentRequest.getParentUserId()!=null){
            user.setId(parentRequest.getParentUserId());
        }
        user.setUsername(parentRequest.getParentUsername());
        user.setPassword(parentRequest.getParentPassword());
        user.setRoles(parentRole);
        return user;
    }

    public AppUser setStudentAuth(Role studentRole, ParentRequest parentRequest) {
        AppUser user = new AppUser();
        if(parentRequest.getStudentUserId()!=null){
            user.setId(parentRequest.getStudentUserId());
        }
        user.setUsername(parentRequest.getStudentUsername());
        user.setPassword(parentRequest.getStudentPassword());
        user.setRoles(studentRole);
        return user;

    }

    public AppUser setTeacherAuth(Role teacherRole, TeacherRequest teacherRequest) {
        AppUser user = new AppUser();
        if(teacherRequest.getTeacherUserId()!=null){
            user.setId(teacherRequest.getTeacherUserId());
        }
        user.setUsername(teacherRequest.getTeacherUsername());
        user.setPassword(teacherRequest.getTeacherPassword());
        user.setRoles(teacherRole);
        return user;
    }
}
