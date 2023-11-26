package com.restapi.response.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.websocket.server.ServerEndpoint;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAttendanceResponse {
    private LocalDate joinedDate;
    private List<LocalDate> date;
}
