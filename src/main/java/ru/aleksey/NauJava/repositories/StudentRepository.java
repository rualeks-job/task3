package ru.aleksey.NauJava.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.aleksey.NauJava.objects.Student;

import java.util.List;

@RepositoryRestResource(path = "students")
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query("SELECT s.name from Student s join s.classroom c where c.title = :title")
    List<String> findAllStudentNamesByClassroomTitle(@Param("title") String title);
    Student findByNameAndSurname(String name, String surname);
}
