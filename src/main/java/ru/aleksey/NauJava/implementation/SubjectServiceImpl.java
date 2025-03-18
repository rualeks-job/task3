package ru.aleksey.NauJava.implementation;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.aleksey.NauJava.objects.Subject;
import ru.aleksey.NauJava.repositories.SubjectRepository;
import ru.aleksey.NauJava.services.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    public String appVersion;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void createSubject(Long id, String title, String teacher, Double duration) {
        Subject newSubject = new Subject();
        newSubject.setId(id);
        newSubject.setTeacher(teacher);
        newSubject.setTitle(title);
        newSubject.setDuration(duration);
        subjectRepository.create(newSubject);
    }

    @Override
    public Subject findSubjectById(Long id) {
        return subjectRepository.read(id);
    }

    @Override
    public void deleteSubjectById(Long id) {
        subjectRepository.delete(id);
    }


    @Override
    public void updateTeacherById(Long id, String teacher) {
        Subject newSubject = new Subject();
        Subject oldSubject = subjectRepository.read(id);
        newSubject.setId(id);
        newSubject.setTeacher(teacher);
        newSubject.setDuration(oldSubject.getDuration());
        newSubject.setTitle(oldSubject.getTitle());
        subjectRepository.update(newSubject);
    }

    @PostConstruct
    public void init() {
        System.out.println("Приложение: " + appName);
        System.out.println("Верси приложения: " + appVersion);
    }
}
