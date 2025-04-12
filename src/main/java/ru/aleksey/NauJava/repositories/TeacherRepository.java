package ru.aleksey.NauJava.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.aleksey.NauJava.custom.TeacherRepositoryCustom;
import ru.aleksey.NauJava.objects.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long>, TeacherRepositoryCustom {
    Teacher findByNameAndSurname(String name, String surname);

    @Query("SELECT s.name from Teacher s join s.classroom c where c.title = :title")
    String findNameByClassroomTitle(@Param("title") String title);
}
