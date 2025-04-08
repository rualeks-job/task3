package ru.aleksey.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.aleksey.NauJava.custom.TeacherRepositoryCustom;
import ru.aleksey.NauJava.objects.Classroom;
import ru.aleksey.NauJava.objects.Teacher;
import ru.aleksey.NauJava.repositories.ClassroomRepository;
import ru.aleksey.NauJava.repositories.TeacherRepository;

import java.util.UUID;

@SpringBootTest
@Transactional
public class TeacherRepositoryCustomTest {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    ClassroomRepository classroomRepository;

    Teacher createTeacher(String name, String surname) {
        Classroom classroom = new Classroom("9A", null, null);
        Teacher teacher = new Teacher(null, name, surname, classroom);
        classroom.setTeacher(teacher);
        classroomRepository.save(classroom);
        teacherRepository.save(teacher);
        return teacher;
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

    @Test
    void testFindByNameAndSurname() {
        String teacherName = UUID.randomUUID().toString();
        String teacherSurname = UUID.randomUUID().toString();
        Teacher teacher = createTeacher(teacherName,teacherSurname);

        Teacher findTeacher = teacherRepository.findByNameAndSurname(teacherName, teacherSurname);

        Teacher findTeacher = teacherRepositoryCustom.findByNameAndSurname(teacherName, teacherSurname);
        Assertions.assertNotNull(findTeacher, "Учитель не найден");
        Assertions.assertEquals(findTeacher.getId(), teacher.getId());
    }
}
