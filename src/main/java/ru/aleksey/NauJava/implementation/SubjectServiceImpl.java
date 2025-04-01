package ru.aleksey.NauJava.implementation;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksey.NauJava.objects.Grade;
import ru.aleksey.NauJava.objects.Subject;
import ru.aleksey.NauJava.repositories.GradeRepository;
import ru.aleksey.NauJava.repositories.SubjectRepository;
import ru.aleksey.NauJava.services.SubjectService;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final GradeRepository gradeRepository;
    private final String appName;
    public final String appVersion;

    @Autowired
    public SubjectServiceImpl(
            SubjectRepository subjectRepository, GradeRepository gradeRepository,
            @Value("${app.name}") String appName,
            @Value("${app.version}") String appVersion) {
        this.subjectRepository = subjectRepository;
        this.gradeRepository = gradeRepository;
        this.appVersion = appVersion;
        this.appName = appName;
    }

    @Override
    @Transactional
    public void deleteSubjectByTitle(String title) {
        Subject subject = subjectRepository.findByTitle(title);
        List<Grade> gradeList = gradeRepository.findAllGradeBySubjectTitle(subject.getTitle());
        gradeRepository.deleteAll(gradeList);
        subjectRepository.delete(subject);
    }

    @PostConstruct
    public void init() {
        System.out.println("Приложение: " + appName);
        System.out.println("Версия приложения: " + appVersion);
    }
}
