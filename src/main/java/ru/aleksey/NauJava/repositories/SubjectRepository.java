package ru.aleksey.NauJava.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.aleksey.NauJava.objects.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Subject findByTitle(String title);
}
