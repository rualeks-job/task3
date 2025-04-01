package ru.aleksey.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.aleksey.NauJava.objects.Grade;
import ru.aleksey.NauJava.objects.Subject;
import ru.aleksey.NauJava.objects.Teacher;
import ru.aleksey.NauJava.repositories.GradeRepository;
import ru.aleksey.NauJava.repositories.SubjectRepository;
import ru.aleksey.NauJava.repositories.TeacherRepository;
import ru.aleksey.NauJava.services.SubjectService;

import java.util.*;

@SpringBootTest
public class SubjectRepositoryTest {
    private final SubjectRepository subjectRepository;
    private final SubjectService subjectService;
    private final GradeRepository gradeRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    SubjectRepositoryTest(SubjectRepository subjectRepository, SubjectService subjectService, GradeRepository gradeRepository, TeacherRepository teacherRepository) {

        this.subjectRepository = subjectRepository;
        this.subjectService = subjectService;
        this.gradeRepository = gradeRepository;

        this.teacherRepository = teacherRepository;
    }

    @Test
    void testFindByTitle() {
        String subjectTitle = UUID.randomUUID().toString();
        double doubleDuration = new Random().nextDouble();
        Subject subject = new Subject(subjectTitle, null, doubleDuration, null);
        subjectRepository.save(subject);
        Subject findSubject = subjectRepository.findByTitle(subjectTitle);
        Assertions.assertNotNull(findSubject, "Предмет не найден");
        Assertions.assertEquals(subject.getId(), findSubject.getId());
        Assertions.assertEquals(subject.getDuration(), findSubject.getDuration());
    }

    @Test
    void testDeleteSubjectByTitle() {
        Integer intGrade = new Random().nextInt(2, 5);
        Integer intGrade2 = new Random().nextInt(2, 5);
        Grade grade = new Grade(intGrade, null, null);
        gradeRepository.save(grade);
        Grade grade2 = new Grade(intGrade2, null, null);
        gradeRepository.save(grade2);
        List<Grade> gradeList = List.of(grade, grade2);
        String subjectTitle = UUID.randomUUID().toString();
        double doubleDuration = new Random().nextDouble();
        Subject subject = new Subject(subjectTitle, null, doubleDuration, gradeList);
        grade.setSubject(subject);
        grade2.setSubject(subject);
        subjectRepository.save(subject);
        gradeRepository.save(grade);
        gradeRepository.save(grade2);

        subjectService.deleteSubjectByTitle(subjectTitle);

        Optional<Subject> foundSubject = subjectRepository.findById(subject.getId());
        Assertions.assertTrue(foundSubject.isEmpty());
        Optional<Grade> foundGrade = gradeRepository.findById(grade.getId());
        Assertions.assertTrue(foundGrade.isEmpty());
        Optional<Grade> foundGrade2 = gradeRepository.findById(grade2.getId());
        Assertions.assertTrue(foundGrade2.isEmpty());
    }

    @Test
    void negativeTestDeleteSubjectByTitle() {
        Integer intGrade = new Random().nextInt(2, 5);
        Integer intGrade2 = new Random().nextInt(2, 5);
        Grade grade = new Grade(intGrade, null, null);
        gradeRepository.save(grade);
        Grade grade2 = new Grade(intGrade2, null, null);
        gradeRepository.save(grade2);
        List<Grade> gradeList = List.of(grade, grade2);
        Teacher teacher = new Teacher(null, "asd", "asd", null);
        teacherRepository.save(teacher);
        String subjectTitle = UUID.randomUUID().toString();
        double doubleDuration = new Random().nextDouble();
        Subject subject = new Subject(subjectTitle, null, doubleDuration, gradeList);
        grade.setSubject(subject);
        grade2.setSubject(subject);
        subject.setTeacher(teacher);
        subjectRepository.save(subject);
        teacher.setSubject(subject);
        teacherRepository.save(teacher);
        gradeRepository.save(grade);
        gradeRepository.save(grade2);

        try {
            subjectService.deleteSubjectByTitle(subjectTitle);
        } catch (Exception e){
            e.printStackTrace();
        }

        Subject foundSubject = subjectRepository.findById(subject.getId()).orElseThrow();
        Assertions.assertEquals(foundSubject.getId(), subject.getId());
        Grade foundGrade = gradeRepository.findById(grade.getId()).orElseThrow();
        Assertions.assertEquals(foundGrade.getId(), grade.getId());
        Grade foundGrade2 = gradeRepository.findById(grade2.getId()).orElseThrow();
        Assertions.assertEquals(foundGrade2.getId(), grade2.getId());
    }
}
