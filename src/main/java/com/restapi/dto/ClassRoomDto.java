package com.restapi.dto;

import com.restapi.model.ClassRoom;
import com.restapi.response.ClassRoomResponse;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class ClassRoomDto {
    public ClassRoomResponse mapToClassRoomResponse(ClassRoom classRoom) {
        ClassRoomResponse classRoomResponse = new ClassRoomResponse();
        classRoomResponse.setClassRoomId(classRoom.getId());
        classRoomResponse.setClassStandard(classRoom.getClassStandard().getStandard());
        classRoomResponse.setTeacherUserId(classRoom.getTeacherUserClassRoom().getId());
        classRoomResponse.setTamilTeacherId(classRoom.getTamilTeacherUser().getId());
        classRoomResponse.setEnglishTeacherId(classRoom.getEnglishTeacherUser().getId());
        classRoomResponse.setMathsTeacherId(classRoom.getMathsTeacherUser().getId());
        classRoomResponse.setScienceTeacherId(classRoom.getScienceTeacherUser().getId());
        classRoomResponse.setSocialTeacherId(classRoom.getSocialTeacherUser().getId());
        return classRoomResponse;
    }
}
