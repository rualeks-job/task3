package ru.aleksey.NauJava.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.aleksey.NauJava.objects.Student;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("SELECT s.name from Student s join s.classroom c where c.title = :title")
    List<String> findAllStudentNamesByClassroomTitle(@Param("title") String title);
    Student findByNameAndSurname(String name, String surname);
}
