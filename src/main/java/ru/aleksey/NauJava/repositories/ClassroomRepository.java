package ru.aleksey.NauJava.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.aleksey.NauJava.objects.Classroom;

public interface ClassroomRepository extends CrudRepository<Classroom, Long> {

//    Classroom findClassroomById(Long id);
    Classroom findByTitle(String title);
}
