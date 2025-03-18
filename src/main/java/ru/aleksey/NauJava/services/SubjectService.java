package ru.aleksey.NauJava.services;

import ru.aleksey.NauJava.objects.Subject;

public interface SubjectService {
    void createSubject(Long id, String title, String teacher, Double duration);

    Subject findSubjectById(Long id);

    void deleteSubjectById(Long id);

    void updateTeacherById(Long id, String teacher);
}
