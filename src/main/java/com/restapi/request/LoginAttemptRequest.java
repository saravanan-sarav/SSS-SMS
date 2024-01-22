package com.restapi.request;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  LoginAttemptRequest {
    private String username;
    private Double latitude;
    private Double longitude;
    private String ipAddress;
}
