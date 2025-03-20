package ru.aleksey.NauJava.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.aleksey.NauJava.objects.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aleksey.NauJava.objects.Subject;

import java.util.List;
import java.util.Objects;

@Component
public class SubjectRepository implements CrudRepository<Subject, Long> {
    private final List<Subject> subjectContainer;

    @Autowired
    public SubjectRepository(List<Subject> subjectContainer) {
        this.subjectContainer = subjectContainer;
    }

    @Override
    public void create(Subject entry) {
        subjectContainer.add(entry);

    }

    @Override
    public Subject read(Long aLong) {
        return subjectContainer
                .stream()
                .filter(subject -> Objects.equals(subject.getId(), aLong))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void update(Subject entry) {
        Subject subject = read(entry.getId());
        subjectContainer.set(subjectContainer.indexOf(subject), entry);
    }

    @Override
    public void delete(Long aLong) {
        Subject subject = read(aLong);
        subjectContainer.remove(subject);
    }
}
