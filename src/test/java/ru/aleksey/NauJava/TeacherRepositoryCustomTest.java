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
public class TeacherRepositoryCustomTest {
    private final TeacherRepositoryCustom teacherRepositoryCustom;
    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;

    @Autowired
    public TeacherRepositoryCustomTest(TeacherRepositoryCustom teacherRepositoryCustom, TeacherRepository teacherRepository, ClassroomRepository classroomRepository) {
        this.teacherRepositoryCustom = teacherRepositoryCustom;
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

        String findTeacherName = teacherRepositoryCustom.findTeacherNameByClassroomTitle(classroom.getTitle());
        Assertions.assertNotNull(findTeacherName, "Учитель не найден");
        Assertions.assertEquals(findTeacherName, teacher.getName());
    }

    @Test
    void testFindByNameAndSurname() {
        String teacherName = UUID.randomUUID().toString();
        String teacherSurname = UUID.randomUUID().toString();

        Teacher teacher = new Teacher(null, teacherName, teacherSurname, null);
        teacherRepository.save(teacher);

        Teacher findTeacher = teacherRepositoryCustom.findByNameAndSurname(teacherName, teacherSurname);
        Assertions.assertNotNull(findTeacher, "Учитель не найден");
        Assertions.assertEquals(findTeacher.getId(), teacher.getId());
    }
}
