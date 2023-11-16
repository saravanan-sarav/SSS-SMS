package com.restapi.dataloader;

import com.restapi.model.*;
import com.restapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private StudentStatusRepository studentStatusRepository;

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private AssignmentTypeRepository assignmentTypeRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
//        Create user roles
        Role studentRole = createRoleIfNotFound(Role.STUDENT);
        Role ParentRole = createRoleIfNotFound(Role.PARENT);
        Role staffRole = createRoleIfNotFound(Role.STAFF);
        Role adminRole = createRoleIfNotFound(Role.ADMIN);

//        Create Student Status
        StudentStatus studentStatusPending = createStudentStatusIfNotFound(StudentStatus.PENDING);
        StudentStatus studentStatusActive = createStudentStatusIfNotFound(StudentStatus.ACTIVE);
        StudentStatus studentStatusGraduate = createStudentStatusIfNotFound(StudentStatus.GRADUATED);
        StudentStatus studentStatusDropout = createStudentStatusIfNotFound(StudentStatus.DROPOUT);

//        Create Subjects
        Subject subjectTamil = createSubjectIfNotFound(Subject.TAMIL);
        Subject subjectEnglish = createSubjectIfNotFound(Subject.TAMIL);
        Subject subjectMaths = createSubjectIfNotFound(Subject.TAMIL);
        Subject subjectScience = createSubjectIfNotFound(Subject.TAMIL);
        Subject subjectSocial = createSubjectIfNotFound(Subject.TAMIL);

//        Create Assignment Type;
        AssignmentType assignmentTypeUnitTest = createAssignmentTypeIfNotFound(AssignmentType.UNIT_TEST);
        AssignmentType assignmentTypeMonthlyTest = createAssignmentTypeIfNotFound(AssignmentType.MONTHLY_TEST);
        AssignmentType assignmentTypeQuaterlyExam = createAssignmentTypeIfNotFound(AssignmentType.QUATERLY_EXAM);
        AssignmentType assignmentTypeHalfyearlyExam = createAssignmentTypeIfNotFound(AssignmentType.HALFYEARLY_EXAM);
        AssignmentType assignmentTypeAnnualExam = createAssignmentTypeIfNotFound(AssignmentType.ANNUAL_EXAM);
        AssignmentType assignmentTypeAssignment = createAssignmentTypeIfNotFound(AssignmentType.ASSIGNMENT);
        AssignmentType assignmentTypeClassTest = createAssignmentTypeIfNotFound(AssignmentType.CLASS_TEST);

        alreadySetup = true;
    }

    private AssignmentType createAssignmentTypeIfNotFound(String type) {
        AssignmentType assignmentType = assignmentTypeRepository.findByName(type);
        if(assignmentType ==null){
            assignmentType = new AssignmentType();
            assignmentType.setType(type);
            assignmentType = assignmentTypeRepository.save(assignmentType);

        }
        return assignmentType;
    }

    private Subject createSubjectIfNotFound(String subjectName) {
        Subject subject = subjectRepository.findByName(subjectName);
        if(subject == null){
            subject = new Subject();
            subject.setSubject(subjectName);
            subject = subjectRepository.save(subject);
        }
        return subject;
    }

    private StudentStatus createStudentStatusIfNotFound(final String status) {
        StudentStatus studentStatus = studentStatusRepository.findByName(status);
        if (studentStatus == null) {
            studentStatus = new StudentStatus();
            studentStatus.setStatus(status);
            studentStatus = studentStatusRepository.save(studentStatus);
        }
        return studentStatus;
    }

    @Transactional
    private Role createRoleIfNotFound(final String username) {
        Role role = roleRepository.findByName(username);
        if (role == null) {
            role = new Role();
            role.setName(username);
            role = roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    private AppUser createUserIfNotFound(final String username, final String password,
                                         final Role role) {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = null;
        if (optionalUser.isEmpty()) {
            user = new AppUser();
            user.setUsername(username);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setRoles(role);
            user = userRepository.save(user);
        }
        return user;
    }
}
