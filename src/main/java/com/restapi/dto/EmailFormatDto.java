package com.restapi.dto;

import com.restapi.model.Parent;
import com.restapi.model.Student;
import com.restapi.request.LoginAttemptRequest;
import com.restapi.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EmailFormatDto {
    @Autowired
    private LocationService locationService;

    public String loginAttemptTemplateMaker(Student student, Parent optionalParent, LoginAttemptRequest loginAttemptRequest) {

        String htmlTemplate = "<html>" +
                "<head>" +
                "<style>" +
                "table { width: 100%; border-collapse: collapse; }" +
                "th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }" +
                "tr { color: red; font-weight: bold;}" +
                "p {color: black;}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<h2>School Account Sign-In Alert</h2>" +
                "<p>Hi " + student.getFirstName() + " " + student.getLastname() + ",</p>" +
                "<p>There's been a new sign-in to your School account associated with the email address " + optionalParent.getEmail() + " on " + getFormattedDate() + ".</p>" +
                "<table>" +
                "<tr><th>Location</th>" + locationService.getLocationName(loginAttemptRequest.getLatitude(), loginAttemptRequest.getLongitude()) + "<td> (Location is approximated based on IP Address: " + loginAttemptRequest.getIpAddress() + ")</td></tr>" +
                "</table>" +
                "<p>Please contact <a href='mailto:sssinternationalschool.noreply@gmail.com'>sssinternationalschool.noreply@gmail.com</a> for any assistance.</p>" +
                "<p>Regards,<br/>SSS International School</p>" +
                "</body>" +
                "</html>";
        return htmlTemplate;
    }

    public String getFormattedDate() {
        LocalDateTime dateTime = LocalDateTime.now();

        // Define the desired time zone
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");

        // Convert LocalDateTime to ZonedDateTime with the specified time zone
        ZonedDateTime zonedDateTime = dateTime.atZone(zoneId);

        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy hh:mm:ss a z");

        // Format the ZonedDateTime
        String formattedDate = zonedDateTime.format(formatter);
        System.out.println(formattedDate);
        return formattedDate;
    }
}
