package com.restapi.dto;

import com.restapi.model.Address;
import com.restapi.model.AppUser;
import com.restapi.model.Parent;
import com.restapi.model.Student;
import com.restapi.repository.ParentRepository;
import com.restapi.request.ParentRequest;
import com.restapi.response.ParentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParentDto {

    @Autowired
    private AddressDto addressDto;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    public Parent setParent(Address parentAddress, AppUser parentAppUser, Student student, ParentRequest parentRequest) {
        Parent parent = new Parent();
        Parent parentFetch = parentRepository.findByUserId(parentAppUser.getId());
        if(parentFetch.getId()!=null){
            student.setId(parentFetch.getId());
        }
        parent.setMotherName(parentRequest.getMotherName());
        parent.setMotherPhoneNumber(parentRequest.getMotherPhoneNumber());
        parent.setMotherOccupation(parent.getMotherOccupation());
        parent.setFatherName(parentRequest.getFatherName());
        parent.setFatherPhoneNumber(parentRequest.getFatherPhoneNumber());
        parent.setFatherOccupation(parentRequest.getFatherOccupation());

        parent.setAddress(parentAddress);
        parent.setStudentUser(student.getAppUser());
        parent.setAppUser(parentAppUser);
        return parent;
    }

    public ParentResponse responseConversion(Parent parent) {
        ParentResponse parentResponse = new ParentResponse();
        parentResponse.setParentId(parent.getId());
        parentResponse.setStudentId(parent.getStudentUser().getId());
        parentResponse.setParentUserId(parent.getAppUser().getId());
        parentResponse.setStudentUserId(parent.getStudentUser().getId());
        parentResponse.setParentName(parent.getMotherName());
        parentResponse.setParentUsername(parent.getAppUser().getUsername());
        parentResponse.setStudentUsername(parent.getStudentUser().getUsername());
        parentResponse.setAddressId(parent.getAddress().getId());

        return parentResponse;
    }
}
