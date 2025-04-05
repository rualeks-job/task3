package ru.aleksey.NauJava.implementation;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksey.NauJava.objects.Grade;
import ru.aleksey.NauJava.objects.Student;
import ru.aleksey.NauJava.repositories.GradeRepository;
import ru.aleksey.NauJava.repositories.StudentRepository;
import ru.aleksey.NauJava.services.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
    private final String appName;
    public final String appVersion;

    @Autowired
    public StudentServiceImpl(
            StudentRepository studentRepository, GradeRepository gradeRepository,
            @Value("${app.name}") String appName,
            @Value("${app.version}") String appVersion) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.appVersion = appVersion;
        this.appName = appName;
    }

    @Override
    @Transactional
    public void deleteByNameAndSurname(String name, String surname) {
        Student student = studentRepository.findByNameAndSurname(name, surname);
        List<Grade> gradeList = gradeRepository.findAllByStudentNameAndStudentSurname(name, surname);
        gradeRepository.deleteAll(gradeList);
        studentRepository.delete(student);
    }

    @PostConstruct
    public void init() {
        System.out.println("Приложение: " + appName);
        System.out.println("Версия приложения: " + appVersion);
    }
}
