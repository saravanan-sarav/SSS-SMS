package com.restapi.response.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.websocket.SendResult;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAssignmentResponse {
    private Long assignmentId;
    private String assignmentName;
    private String standard;
    private String Subject;
    private LocalDate createdDate;
    private LocalDate dueDate;
    private Integer totalMark;
    private Integer minMark;
}
