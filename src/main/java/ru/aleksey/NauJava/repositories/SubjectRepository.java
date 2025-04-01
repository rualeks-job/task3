package ru.aleksey.NauJava.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.aleksey.NauJava.objects.Subject;

@RepositoryRestResource
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Subject findByTitle(String title);
}
