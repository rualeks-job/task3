package ru.aleksey.NauJava.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.aleksey.NauJava.objects.Grade;

import java.util.List;

@RepositoryRestResource(path = "grades")
public interface GradeRepository extends CrudRepository<Grade, Long> {
    List<Grade> findAllByStudentNameAndStudentSurname(String name,String surname);
}
