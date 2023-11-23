package com.restapi.dataloader;

import com.restapi.model.*;
import com.restapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private StudentStatusRepository studentStatusRepository;

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private AssignmentTypeRepository assignmentTypeRepository;

    @Autowired
    private ClassStandardRepository classStandardRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
//        Create user roles
        Role studentRole = createRoleIfNotFound(Role.STUDENT);
        Role parentRole = createRoleIfNotFound(Role.PARENT);
        Role teacherRole = createRoleIfNotFound(Role.TEACHER);
        Role adminRole = createRoleIfNotFound(Role.ADMIN);

//        Create Admin

        AppUser adminUser = createUserIfNotFound("admin@123","admin","admin",adminRole);

//        Create Student Status
        StudentStatus studentStatusPending = createStudentStatusIfNotFound(StudentStatus.PENDING);
        StudentStatus studentStatusActive = createStudentStatusIfNotFound(StudentStatus.ACTIVE);
        StudentStatus studentStatusGraduate = createStudentStatusIfNotFound(StudentStatus.GRADUATED);
        StudentStatus studentStatusDropout = createStudentStatusIfNotFound(StudentStatus.DROPOUT);

//        Create Subjects
        Subject subjectTamil = createSubjectIfNotFound(Subject.TAMIL);
        Subject subjectEnglish = createSubjectIfNotFound(Subject.ENGLISH);
        Subject subjectMaths = createSubjectIfNotFound(Subject.MATHEMATICS);
        Subject subjectScience = createSubjectIfNotFound(Subject.SCIENCE);
        Subject subjectSocial = createSubjectIfNotFound(Subject.SOCIAL);

//        Create Assignment Type;
        AssignmentType assignmentTypeUnitTest = createAssignmentTypeIfNotFound(AssignmentType.UNIT_TEST);
        AssignmentType assignmentTypeMonthlyTest = createAssignmentTypeIfNotFound(AssignmentType.MONTHLY_TEST);
        AssignmentType assignmentTypeQuaterlyExam = createAssignmentTypeIfNotFound(AssignmentType.QUATERLY_EXAM);
        AssignmentType assignmentTypeHalfyearlyExam = createAssignmentTypeIfNotFound(AssignmentType.HALFYEARLY_EXAM);
        AssignmentType assignmentTypeAnnualExam = createAssignmentTypeIfNotFound(AssignmentType.ANNUAL_EXAM);
        AssignmentType assignmentTypeAssignment = createAssignmentTypeIfNotFound(AssignmentType.ASSIGNMENT);
        AssignmentType assignmentTypeClassTest = createAssignmentTypeIfNotFound(AssignmentType.CLASS_TEST);

//        Create ClassStandard

        ClassStandard sixthClass = createClassStandardIfNotFound(ClassStandard.SIXTH);
        ClassStandard seventhClass = createClassStandardIfNotFound(ClassStandard.SEVENTH);
        ClassStandard eighthClass = createClassStandardIfNotFound(ClassStandard.EIGHTH);
        ClassStandard ninthClass = createClassStandardIfNotFound(ClassStandard.NINTH);
        ClassStandard tenthClass = createClassStandardIfNotFound(ClassStandard.TENTH);
        ClassStandard plusOneClass = createClassStandardIfNotFound(ClassStandard.PLUS_ONE);
        ClassStandard plusTwoClass = createClassStandardIfNotFound(ClassStandard.PLUS_TWO);

//        Create Teacher with Subjects
        AppUser teacherAppUser = createUserIfNotFound("tamilteacher@123","tamil","Meena",teacherRole);
        Address addressTeacher = createAddressIfNotFound("18/1","MGR Street","taramani","chennai","tamilNadu",600113l);
        Teacher tamilTeacher = createTeacherIfNotFound("Meena","S","8807456056","meena@gmail.com","female","1977-11-10",teacherAppUser,addressTeacher,subjectTamil);


        AppUser teacherEnglishAppUser = createUserIfNotFound("englishteacher@123","english","Raj",teacherRole);
        Address addressEnglishTeacher = createAddressIfNotFound("20","Gohulam Street","AnnaNagar","Chennai","tamilNadu",600100l);
        Teacher englishTeacher = createTeacherIfNotFound("Raj","S","6379888041","raj@gmail.com","male","1977-01-10",teacherEnglishAppUser,addressEnglishTeacher,subjectEnglish);


        AppUser teacherMathsAppUser = createUserIfNotFound("mathsteacher@123","maths","John",teacherRole);
        Address addressMathsTeacher = createAddressIfNotFound("45","Mahatma Street","Anna nagar","chennai","tamilNadu",600045l);
        Teacher mathsTeacher = createTeacherIfNotFound("John","S","9042221661","john@gmail.com","male","1989-11-10",teacherMathsAppUser,addressMathsTeacher,subjectMaths);


        AppUser teacherScienceAppUser = createUserIfNotFound("scienceteacher@123","science","rajesh",teacherRole);
        Address addressScienceTeacher = createAddressIfNotFound("994/2","APJ Street","CMBT","chennai","tamilNadu",600034l);
        Teacher scienceTeacher = createTeacherIfNotFound("rajesh","S","9445542262","rajesh@gmail.com","male","1990-01-12",teacherScienceAppUser,addressScienceTeacher,subjectScience);


        AppUser teacherSocialAppUser = createUserIfNotFound("socialteacher@123","social","Guru",teacherRole);
        Address addressSocialTeacher = createAddressIfNotFound("65","stalin Street","gopal nagar","chennai","tamilNadu",600001l);
        Teacher socialTeacher = createTeacherIfNotFound("Guru","G","9554221743","guru@gmail.com","male","1977-11-10",teacherSocialAppUser,addressSocialTeacher,subjectSocial);


//        Create classroom
        ClassRoom classRoom = createClassRoomIfNotFound(sixthClass,teacherAppUser,teacherAppUser,teacherEnglishAppUser,teacherMathsAppUser,teacherScienceAppUser,teacherSocialAppUser);

//        Create ParentAndTeacher
        AppUser studentAppUser = createUserIfNotFound("sarav@2001","2001","Saravanan",studentRole);
        AppUser parentAppUser = createUserIfNotFound("meena@2001","2001","Meena S",parentRole);
        Address parentAddress = createAddressIfNotFound("45","Mahatma Street","Anna nagar","chennai","tamilNadu",600045l);
        Student student = createStudentIfNotFound("Saravanan","S","2001-10-04","Male",studentAppUser,classRoom,sixthClass,studentStatusPending);
        Parent parent = createParentIfNotFound("Meena S","8807456056","houseWife","Subramani G","9042241331","Labour","meena@gmail.com",parentAppUser,parentAddress,studentAppUser);

        alreadySetup = true;
    }

    private Parent createParentIfNotFound(String motherName, String motherPhoneNumber, String motherOccupation, String fatherName, String fatherPhoneNumber, String fatherOccupation, String email, AppUser parentAppUser, Address parentAddress, AppUser studentAppUser) {
        Optional<Parent> OptionalParent = parentRepository.findByUserId(parentAppUser.getId());
        if(!OptionalParent.isPresent()){
            Parent parent = new Parent();
            parent.setMotherName(motherName);
            parent.setMotherPhoneNumber(motherPhoneNumber);
            parent.setMotherOccupation(motherOccupation);
            parent.setFatherName(fatherName);
            parent.setFatherPhoneNumber(fatherPhoneNumber);
            parent.setFatherOccupation(fatherOccupation);
            parent.setEmail(email);
            parent.setStudentUserForParent(studentAppUser);
            parent.setParentUser(parentAppUser);
            parent.setAddress(parentAddress);
            parent = parentRepository.save(parent);
            return parent;
        }
        return OptionalParent.get();
    }

    private Student createStudentIfNotFound(String firstName, String lastName, String dateOfBirth, String gender, AppUser studentAppUser, ClassRoom classRoom, ClassStandard sixthClass, StudentStatus studentStatusActive) {
        Optional<Student> optionalStudent = studentRepository.findByUserIdForApprove(studentAppUser.getId());
        if(!optionalStudent.isPresent()){
            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastname(lastName);
            student.setGender(gender);
            student.setDateOfBirth(LocalDate.parse(dateOfBirth));
            student.setStudentUser(studentAppUser);
            student.setStudentStatus(studentStatusActive);
            student.setClassRoom(classRoom);
            student = studentRepository.save(student);
            return student;
        }
        return optionalStudent.get();
    }

    private ClassRoom createClassRoomIfNotFound(ClassStandard standard, AppUser classTeacher, AppUser tamilTeacher, AppUser englishTeacher, AppUser mathsTeacher, AppUser scienceTeacher, AppUser socialTeacher) {
        Optional<ClassRoom> classRoomFetch = classRoomRepository.findByClassStandard(standard.getId());
        if(!classRoomFetch.isPresent()){
           ClassRoom classRoom = new ClassRoom();
            classRoom.setClassStandard(standard);
            classRoom.setTeacherUserClassRoom(classTeacher);
            classRoom.setTamilTeacherUser(tamilTeacher);
            classRoom.setEnglishTeacherUser(englishTeacher);
            classRoom.setMathsTeacherUser(mathsTeacher);
            classRoom.setScienceTeacherUser(scienceTeacher);
            classRoom.setSocialTeacherUser(socialTeacher);
            classRoom = classRoomRepository.save(classRoom);
            return classRoom;
        }
        return classRoomFetch.get();
    }

    private Teacher createTeacherIfNotFound(String firstName, String lastName, String phoneNumber, String email,String gender, String dateOfBirth, AppUser teacherAppUser, Address address, Subject subject) {
        if(teacherAppUser.getId()!=null){
            Optional<Teacher> optionalTeacher = teacherRepository.findByUserId(teacherAppUser.getId());
            Teacher teacher = null;
            if(optionalTeacher.isEmpty()){
                teacher = new Teacher();
                teacher.setFirstName(firstName);
                teacher.setLastname(lastName);
                teacher.setDateOfBirth(LocalDate.parse(dateOfBirth));
                teacher.setEmail(email);
                teacher.setGender(gender);
                teacher.setPhoneNumber(phoneNumber);
                teacher.setAddress(address);
                teacher.setSubject(subject);
                teacher.setTeacherUser(teacherAppUser);
                teacher = teacherRepository.save(teacher);
        }
            return teacher;

        }
        return null;
    }

    private Address createAddressIfNotFound(String doorNum, String street, String addrLine, String city, String state, Long pincode) {
        Address address = new Address();
        address.setDoorNum(doorNum);
        address.setStreet(street);
        address.setAddrLine(addrLine);
        address.setCity(city);
        address.setState(state);
        address.setPincode(pincode);
        address = addressRepository.save(address);
        return address;
    }

    private ClassStandard createClassStandardIfNotFound(String standard) {
        ClassStandard classStandard = classStandardRepository.findByStandard(standard);
        if(classStandard == null){
            classStandard = new ClassStandard();
            classStandard.setStandard(standard);
            classStandard = classStandardRepository.save(classStandard);
        }
        return classStandard;
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
    private AppUser createUserIfNotFound(final String username, final String password, String name,
                                         final Role role) {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            AppUser user = new AppUser();
            user.setUsername(username);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setName(name);
            user.setRoles(role);
            user = userRepository.save(user);
            return user;
        }
        AppUser user = optionalUser.get();
        return user;
    }
}
