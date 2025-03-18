package ru.aleksey.NauJava.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aleksey.NauJava.objects.Subject;

import java.util.List;

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
        return subjectContainer.stream().filter(subject -> subject.getId() == aLong).findFirst().get();
    }

    @Override
    public void update(Subject entry) {
        Subject subject = subjectContainer.stream().filter(it -> it.getId() == entry.getId()).findFirst().get();
        subjectContainer.set(subjectContainer.indexOf(subject), entry);
    }

    @Override
    public void delete(Long aLong) {
        Subject subject = subjectContainer.stream().filter(it -> it.getId() == aLong).findFirst().get();
        subjectContainer.remove(subject);
    }
}
