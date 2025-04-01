package ru.aleksey.NauJava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.aleksey.NauJava.custom.TeacherRepositoryImpl;
import ru.aleksey.NauJava.implementation.SubjectServiceImpl;
import ru.aleksey.NauJava.objects.*;
import ru.aleksey.NauJava.repositories.*;

import java.util.List;

@SpringBootTest
class NauJavaApplicationTests {

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    ClassroomRepository classroomRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepositoryImpl teacherRepositoryImpl;
    @Autowired
    SubjectServiceImpl subjectService;
    @Autowired
    GradeRepository gradeRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void addTeacher() {
//        Teacher teacher = new Teacher(null, "Bdfy", "asd", null);
        Subject subject = subjectRepository.findByTitle("Русский");
//        Subject subject = new Subject("Русский", null, 123.1, null);
        Grade grade = new Grade(5, subject, null);

//        subjectRepository.save(subject);
        gradeRepository.save(grade);
//        teacherRepository.save(teacher);

    }

    @Test
    void addStudent() {
        Student student2 = new Student(null, "Иван", "Петров", null);
        List<Student> studentList = List.of(student2);

        Teacher teacher = teacherRepository.findByNameAndSurname("Bdfy", "asd");
        Classroom classroom = new Classroom("9A", studentList, teacher);
        Student student = new Student(classroom, "Петя", "Иванов", null);
        studentRepository.save(student2);
        classroomRepository.save(classroom);
        studentRepository.save(student);
    }

    @Test
    void getTeacher() {
        Teacher teacher = teacherRepositoryImpl.findByNameAndSurname("Bdfy", "asd");
        Classroom classroom = classroomRepository.findClassroomById(1L);
        teacher.setClassroom(classroom);
        teacherRepository.save(teacher);

        System.out.println(teacher.getName() + " " + teacher.getSurname());
    }

    @Test
    void getStudent() {
        List<String> students = studentRepository.findStudentNameByClassroomTitle("9A");

        students.forEach(System.out::println);
    }

    @Test
    void getTeacherName() {
        String students = teacherRepositoryImpl.findTeacherNameByClassroomTitle("9A");

//        students.forEach(System.out::println);
    }

    @Test
    void deleteSubject() {
        subjectService.deleteSubjectByTitle("Русский");
    }

}
