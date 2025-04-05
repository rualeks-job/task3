package ru.aleksey.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksey.NauJava.objects.Classroom;
import ru.aleksey.NauJava.objects.Teacher;
import ru.aleksey.NauJava.repositories.*;

import java.util.UUID;

@SpringBootTest
@Transactional
public class TeacherRepositoryTest {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    ClassroomRepository classroomRepository;

    void createTeacher(String name, String surname) {
        Classroom classroom = new Classroom("9A", null, null);
        Teacher teacher = new Teacher(null, name, surname, classroom);
        classroom.setTeacher(teacher);
        classroomRepository.save(classroom);
        teacherRepository.save(teacher);
    }

    @Test
    void testFindTeacherNameByClassroomTitle() {
        String teacherName = UUID.randomUUID().toString();
        String teacherSurname = UUID.randomUUID().toString();
        createTeacher(teacherName, teacherSurname);

        String findTeacherName = teacherRepository.findNameByClassroomTitle("9A");

        Assertions.assertNotNull(findTeacherName, "Учитель не найден");
        Assertions.assertEquals(findTeacherName, teacherName);
    }
}
