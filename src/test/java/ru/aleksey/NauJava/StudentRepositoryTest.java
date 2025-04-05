package ru.aleksey.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksey.NauJava.objects.*;
import ru.aleksey.NauJava.repositories.*;
import ru.aleksey.NauJava.services.StudentService;

import java.util.*;

@SpringBootTest
@Transactional
public class StudentRepositoryTest {
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ClassroomRepository classroomRepository;
    @Autowired
    TeacherRepository teacherRepository;

    Student createStudent(String name, String surname) {
        Student student = new Student(null, name, surname, null);
        Grade grade1 = new Grade(5, null, student);
        Grade grade2 = new Grade(4, null, student);
        Grade grade3 = new Grade(3, null, student);
        student.setGradeList(List.of(grade1, grade2, grade3));
        gradeRepository.saveAll(List.of(grade1, grade2, grade3));
        studentRepository.save(student);

        return student;
    }

    Student createNegativeStudent(String name, String surname) {
        Student student = new Student(null, name, surname, null);
        Classroom classroom = new Classroom("9A", null, null);
        Teacher teacher = new Teacher(null, "Петр", "Ильдарович", classroom);
        Grade grade1 = new Grade(5, null, student);
        Grade grade2 = new Grade(4, null, student);
        Grade grade3 = new Grade(3, null, student);
        classroom.setStudentList(List.of(student));
        classroom.setTeacher(teacher);
        student.setGradeList(List.of(grade1, grade2, grade3));
        student.setClassroom(classroom);
        classroomRepository.save(classroom);
        gradeRepository.saveAll(List.of(grade1, grade2, grade3));
        studentRepository.save(student);

        return student;
    }


    @Test
    void findByNameAndSurnameTest() {
        String studentName = UUID.randomUUID().toString();
        String studentSurname = UUID.randomUUID().toString();
        Student student = createStudent(studentName, studentSurname);

        Student findStudent = studentRepository.findByNameAndSurname(studentName, studentSurname);

        Assertions.assertNotNull(findStudent, "Студент не найден");
        Assertions.assertEquals(student.getId(), findStudent.getId());
    }

    @Test
    void testDeleteStudentByNameAndSurname() {
        String studentName = UUID.randomUUID().toString();
        String studentSurname = UUID.randomUUID().toString();
        Student student = createStudent(studentName, studentSurname);
        List<Grade> gradeList = student.getGradeList();

        Assertions.assertTrue(gradeRepository.findById(gradeList.getFirst().getId()).isPresent());
        studentService.deleteByNameAndSurname(studentName, studentSurname);

        Optional<Student> foundStudent = studentRepository.findById(student.getId());
        Assertions.assertTrue(foundStudent.isEmpty());
        Optional<Grade> foundGrade = gradeRepository.findById(gradeList.getFirst().getId());
        Assertions.assertTrue(foundGrade.isEmpty());
    }

    @Test
    void negativeTestDeleteSubjectByTitle() {
        String studentName = UUID.randomUUID().toString();
        String studentSurname = UUID.randomUUID().toString();
        Student student = createNegativeStudent(studentName, studentSurname);
        List<Grade> gradeList = student.getGradeList();

        Assertions.assertTrue(gradeRepository.findById(gradeList.getFirst().getId()).isPresent());
        try {
            studentService.deleteByNameAndSurname(studentName, studentSurname);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional<Student> foundStudent = studentRepository.findById(student.getId());
        Assertions.assertTrue(foundStudent.isPresent());
        Optional<Grade> foundGrade = gradeRepository.findById(gradeList.getFirst().getId());
        Assertions.assertTrue(foundGrade.isPresent());
    }
}
