package ru.aleksey.NauJava.custom;

import ru.aleksey.NauJava.objects.Teacher;

public interface TeacherRepositoryCustom {
    Teacher findByNameAndSurname(String name, String surname);

    String findTeacherNameByClassroomTitle(String title);
}
