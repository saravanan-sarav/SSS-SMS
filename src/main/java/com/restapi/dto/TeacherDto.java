package com.restapi.dto;

import com.restapi.model.*;
import com.restapi.repository.TeacherRepository;
import com.restapi.request.TeacherRequest;
import com.restapi.response.TeacherResponse;
import com.restapi.response.admin.AdminTeacherResponse;
import com.restapi.response.teacher.TeacherAssignmentResponse;
import com.restapi.response.teacher.TeacherProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TeacherDto {

    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher mapToTeacher(AppUser teacherAppUser, Address address, Subject subject, TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher();
        if(teacherRequest.getTeacherUserId()!=null){
            Optional<Teacher> teacherFetch = teacherRepository.findByUserId(teacherAppUser.getId());
            if(teacherFetch.get().getId()!=null){
                teacher.setId(teacherFetch.get().getId());
            }
        }
        teacher.setFirstName(teacherRequest.getFirstName());
        teacher.setLastname(teacherRequest.getLastname());
        teacher.setPhoneNumber(teacherRequest.getPhoneNumber());
        teacher.setEmail(teacherRequest.getEmail());
        teacher.setDateOfBirth(teacherRequest.getDateOfBirth());
        teacher.setAddress(address);
        teacher.setSubject(subject);
        teacher.setTeacherUser(teacherAppUser);
        return teacher;
    }

    public TeacherResponse mapToTeacherResponse(Teacher teacher) {
        TeacherResponse teacherResponse = new TeacherResponse();

        teacherResponse.setTeacherId(teacher.getTeacherUser().getId());
        teacherResponse.setTeacherName(teacher.getFirstName());
        teacherResponse.setTeacherUsername(teacher.getTeacherUser().getUsername());
        teacherResponse.setAddressId(teacher.getAddress().getId());
        return teacherResponse;
    }

    public AdminTeacherResponse mapToAdminTeacherResponse(Teacher teacher, Subject subject, ClassRoom classRoom) {
        System.out.println("With classRoom");

        AdminTeacherResponse adminTeacherResponse = new AdminTeacherResponse();
        if(classRoom!=null){
            adminTeacherResponse.setClassInCharge(classRoom.getClassStandard().getStandard());
        }else {
            adminTeacherResponse.setClassInCharge(null);
        }
        adminTeacherResponse.setId(teacher.getId());
        adminTeacherResponse.setTeacherId(teacher.getTeacherUser().getId());
        adminTeacherResponse.setName(teacher.getFirstName());

        adminTeacherResponse.setSubject(subject.getSubject());
        adminTeacherResponse.setPhoneNumber(teacher.getPhoneNumber());
        adminTeacherResponse.setEmail(teacher.getEmail());
        adminTeacherResponse.setDateOfBirth(teacher.getDateOfBirth());
        adminTeacherResponse.setDateOfJoin(teacher.getDateOfJoin());
        return adminTeacherResponse;
    }

    public AdminTeacherResponse mapToAdminTeacherResponse(Teacher teacher, Subject subject) {
        System.out.println("With OUT classRoom");
        AdminTeacherResponse adminTeacherResponse = new AdminTeacherResponse();
        adminTeacherResponse.setId(teacher.getId());
        adminTeacherResponse.setTeacherId(teacher.getTeacherUser().getId());
        adminTeacherResponse.setName(teacher.getFirstName());
        adminTeacherResponse.setSubject(subject.getSubject());
        adminTeacherResponse.setPhoneNumber(teacher.getPhoneNumber());
        adminTeacherResponse.setEmail(teacher.getEmail());
        adminTeacherResponse.setDateOfBirth(teacher.getDateOfBirth());
        adminTeacherResponse.setDateOfJoin(teacher.getDateOfJoin());
        return adminTeacherResponse;
    }

    public TeacherAssignmentResponse MapToTeacherAssignmentResponse(Assignment assignment) {
        TeacherAssignmentResponse teacherAssignmentResponse = new TeacherAssignmentResponse();
        teacherAssignmentResponse.setAssignmentId(assignment.getId());
        teacherAssignmentResponse.setAssignmentName(assignment.getAssignmentType().getType());
        teacherAssignmentResponse.setStandard(assignment.getClassRoom().getClassStandard().getStandard());
        teacherAssignmentResponse.setSubject(assignment.getSubjectAssignment().getSubject());
        teacherAssignmentResponse.setCreatedDate(assignment.getCreatedDate());
        teacherAssignmentResponse.setDueDate(assignment.getDeadline());
        teacherAssignmentResponse.setTotalMark(assignment.getTotalGrade());
        teacherAssignmentResponse.setMinMark(assignment.getMinScore());
        return teacherAssignmentResponse;
    }

    public TeacherProfileResponse mapToTeacherProfileResponse(Teacher teacher) {
        TeacherProfileResponse teacherProfileResponse = new TeacherProfileResponse();
        teacherProfileResponse.setTeacherUserId(teacher.getTeacherUser().getId());
        teacherProfileResponse.setFirstName(teacher.getFirstName());
        teacherProfileResponse.setTeacherUsername(teacher.getTeacherUser().getUsername());
        teacherProfileResponse.setLastname(teacher.getLastname());
        teacherProfileResponse.setPhoneNumber(teacher.getPhoneNumber());
        teacherProfileResponse.setEmail(teacher.getEmail());
        teacherProfileResponse.setGender(teacher.getGender());
        teacherProfileResponse.setDateOfBirth(teacher.getDateOfBirth());
        teacherProfileResponse.setDateOfJoin(teacher.getDateOfJoin());
        teacherProfileResponse.setSubjectName(teacher.getSubject().getSubject());
        teacherProfileResponse.setAddressId(teacher.getAddress().getId());
        teacherProfileResponse.setDoorNum(teacher.getAddress().getDoorNum());
        teacherProfileResponse.setStreet(teacher.getAddress().getStreet());
        teacherProfileResponse.setAddrLine(teacher.getAddress().getAddrLine());
        teacherProfileResponse.setCity(teacher.getAddress().getCity());
        teacherProfileResponse.setState(teacher.getAddress().getState());
        teacherProfileResponse.setPincode(teacher.getAddress().getPincode());
        return teacherProfileResponse;
    }
}
