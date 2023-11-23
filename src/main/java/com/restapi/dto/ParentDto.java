package com.restapi.dto;

import com.restapi.model.Address;
import com.restapi.model.AppUser;
import com.restapi.model.Parent;
import com.restapi.model.Student;
import com.restapi.repository.ParentRepository;
import com.restapi.request.ParentRequest;
import com.restapi.response.ParentResponse;
import com.restapi.response.admin.AdminParentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ParentDto {

    @Autowired
    private ParentRepository parentRepository;

    public Parent setParentDetails(AppUser parentAppUser, AppUser studentAppUser, Address parentAddress, ParentRequest parentRequest) {
        Parent parent = new Parent();
        if(parentRequest.getParentUserId()!=null){
            Optional<Parent> parentFetch = parentRepository.findByUserId(parentRequest.getParentUserId());
            if (parentFetch.get().getId() != null) {
                parent.setId(parentFetch.get().getId());
            }
        }
        parent.setMotherName(parentRequest.getMotherName());
        parent.setMotherPhoneNumber(parentRequest.getMotherPhoneNumber());
        parent.setMotherOccupation(parentRequest.getMotherOccupation());
        parent.setFatherName(parentRequest.getFatherName());
        parent.setFatherPhoneNumber(parentRequest.getFatherPhoneNumber());
        parent.setFatherOccupation(parentRequest.getFatherOccupation());
        parent.setEmail(parentRequest.getEmail());

        parent.setAddress(parentAddress);
        parent.setStudentUserForParent(studentAppUser);
        parent.setParentUser(parentAppUser);
        return parent;
    }

    public ParentResponse responseConversion(Parent parent) {
        ParentResponse parentResponse = new ParentResponse();
        parentResponse.setParentId(parent.getId());
        parentResponse.setStudentId(parent.getStudentUserForParent().getId());
        parentResponse.setParentUserId(parent.getParentUser().getId());
        parentResponse.setStudentUserId(parent.getStudentUserForParent().getId());
        parentResponse.setParentName(parent.getMotherName());
        parentResponse.setParentUsername(parent.getParentUser().getUsername());
        parentResponse.setStudentUsername(parent.getStudentUserForParent().getUsername());
        parentResponse.setAddressId(parent.getAddress().getId());

        return parentResponse;
    }

    public AdminParentResponse mapToAdminParentResponse(Parent parent, Student student) {
        AdminParentResponse adminParentResponse = new AdminParentResponse();
        adminParentResponse.setId(parent.getId());
        adminParentResponse.setStudentId(parent.getStudentUserForParent().getId());
        adminParentResponse.setStudentName(parent.getStudentUserForParent().getName());
        adminParentResponse.setClassName(student.getClassRoom().getClassStandard().getStandard());
        adminParentResponse.setParentId(parent.getParentUser().getId());
        adminParentResponse.setMotherName(parent.getMotherName());
        adminParentResponse.setFatherName(parent.getFatherName());
        adminParentResponse.setPhoneNumber(parent.getFatherPhoneNumber());
        adminParentResponse.setEmail(parent.getEmail());
        adminParentResponse.setDateOfJoin(student.getDateOfJoin());
        adminParentResponse.setStudentStatus(student.getStudentStatus().getStatus());
        return adminParentResponse;
    }
}
