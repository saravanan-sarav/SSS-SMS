package com.restapi.dto;

import com.restapi.model.Address;
import com.restapi.request.ParentRequest;
import com.restapi.request.TeacherRequest;
import org.springframework.stereotype.Component;

@Component
public class AddressDto {

    public Address setParentAddress(ParentRequest parentRequest) {
        Address address = new Address();
        if(parentRequest.getAddressId()!= 0){
            address.setId(parentRequest.getAddressId());
        }
        address.setDoorNum(parentRequest.getDoorNum());
        address.setStreet(parentRequest.getStreet());
        address.setAddrLine(parentRequest.getAddrLine());
        address.setCity(parentRequest.getCity());
        address.setState(parentRequest.getState());
        address.setPincode(parentRequest.getPincode());
        return address;
    }

    public Address setTeacherAddress(TeacherRequest teacherRequest) {
        Address address = new Address();
        if(teacherRequest.getAddressId()!= 0){
            address.setId(teacherRequest.getAddressId());
        }
        address.setDoorNum(teacherRequest.getDoorNum());
        address.setStreet(teacherRequest.getStreet());
        address.setAddrLine(teacherRequest.getAddrLine());
        address.setCity(teacherRequest.getCity());
        address.setState(teacherRequest.getState());
        address.setPincode(teacherRequest.getPincode());
        return address;
    }
}
