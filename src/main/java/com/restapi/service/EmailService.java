package com.restapi.service;

import com.restapi.dto.EmailFormatDto;
import com.restapi.model.AppUser;
import com.restapi.model.Parent;
import com.restapi.model.Student;
import com.restapi.repository.ParentRepository;
import com.restapi.repository.StudentRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.LoginAttemptRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private EmailFormatDto emailFormatDto;


    public String loginAttemptMail(LoginAttemptRequest loginAttemptRequest) {
        Optional<AppUser> optionalAppUser = userRepository.findByUsername(loginAttemptRequest.getUsername());
        if (optionalAppUser.isPresent()) {
            Optional<Student> optionalStudent = studentRepository.findByUserIdForApprove(optionalAppUser.get().getId());
            Parent optionalParent = parentRepository.findByStudentUserId(optionalAppUser.get().getId());
            try {
                loginAttemptMailSender(optionalParent.getEmail(), "New sign-in to your School Account", emailFormatDto.loginAttemptTemplateMaker(optionalStudent.get(), optionalParent, loginAttemptRequest));
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        return "Passed";
    }

    public void loginAttemptMailSender(String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        // Set the recipient, subject, and HTML content
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        // Set the sender address
        helper.setFrom("sssinternationalschool.noreply@gmail.com");
        // Send the email
        javaMailSender.send(message);
    }
}
