package com.restapi.dto;

import com.restapi.model.*;
import com.restapi.repository.TeacherRepository;
import com.restapi.request.TeacherRequest;
import com.restapi.response.TeacherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherDto {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher mapToTeacher(AppUser teacherAppUser, Address address, Subject subject, TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher();
        Teacher teacherFetch = teacherRepository.findByUserId(teacherAppUser.getId());
        if(teacherFetch.getId()!=null){
            teacher.setId(teacherFetch.getId());
        }
        teacher.setFirstName(teacherRequest.getFirstName());
        teacher.setLastname(teacherRequest.getLastname());
        teacher.setPhoneNumber(teacherRequest.getPhoneNumber());
        teacher.setEmail(teacherRequest.getEmail());
        teacher.setDateOfBirth(teacherRequest.getDateOfBirth());
        teacher.setAddress(address);
        teacher.setSubject(subject);
        teacher.setAppUser(teacherAppUser);
        return teacher;
    }

    public TeacherResponse mapToTeacherResponse(Teacher teacher) {
        TeacherResponse teacherResponse = new TeacherResponse();

        teacherResponse.setTeacherId(teacher.getAppUser().getId());
        teacherResponse.setTeacherName(teacher.getFirstName());
        teacherResponse.setTeacherUsername(teacher.getAppUser().getUsername());
        teacherResponse.setAddressId(teacher.getAddress().getId());
        return teacherResponse;
    }
}
