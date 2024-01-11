package com.restapi.response.leave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveReasonResponse {
    private Long id;
    private String reason;
}
