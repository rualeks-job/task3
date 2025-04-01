package ru.aleksey.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.aleksey.NauJava.objects.Classroom;
import ru.aleksey.NauJava.objects.Teacher;
import ru.aleksey.NauJava.repositories.ClassroomRepository;
import ru.aleksey.NauJava.repositories.TeacherRepository;

import java.util.UUID;

@SpringBootTest
public class TeacherRepositoryTest {
    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;

    @Autowired
    public TeacherRepositoryTest(TeacherRepository teacherRepository, ClassroomRepository classroomRepository) {
        this.teacherRepository = teacherRepository;
        this.classroomRepository = classroomRepository;
    }

    @Test
    void testFindTeacherNameByClassroomTitle() {
        String teacherName = UUID.randomUUID().toString();
        String teacherSurname = UUID.randomUUID().toString();

        Classroom classroom = classroomRepository.findClassroomByTitle("9A");
        Teacher teacher;
        if (classroom == null) {
            classroom = new Classroom("9A", null, null);
            classroomRepository.save(classroom);
            teacher = new Teacher(null, teacherName, teacherSurname, classroom);
            classroomRepository.save(classroom);
            classroom.setTeacher(teacher);
            teacher.setClassroom(classroom);
            teacherRepository.save(teacher);
            classroomRepository.save(classroom);
        } else {
            teacher = classroom.getTeacher();
        }

        String findTeacherName = teacherRepository.findTeacherNameByClassroomTitle(classroom.getTitle());
        Assertions.assertNotNull(findTeacherName, "Учитель не найден");
        Assertions.assertEquals(findTeacherName, teacher.getName());
    }

    @Test
    void testFindTeacherNameByClass123roomTitle() {
        teacherRepository.deleteById(3L);
        teacherRepository.deleteById(4L);
        teacherRepository.deleteById(5L);
    }
}
