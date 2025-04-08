package ru.aleksey.NauJava.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.aleksey.NauJava.objects.Classroom;

@RepositoryRestResource
public interface ClassroomRepository extends CrudRepository<Classroom, Long> {

    Classroom findClassroomByTitle(String title);
}
