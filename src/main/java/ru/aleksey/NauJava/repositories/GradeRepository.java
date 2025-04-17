package ru.aleksey.NauJava.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.aleksey.NauJava.objects.Grade;

import java.util.List;

public interface GradeRepository extends CrudRepository<Grade, Long> {
    List<Grade> findAllByStudentNameAndStudentSurname(String name,String surname);
}
