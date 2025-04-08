package ru.aleksey.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aleksey.NauJava.custom.TeacherRepositoryCustom;
import ru.aleksey.NauJava.objects.Teacher;

@RestController
@RequestMapping("/rest/teachers")
public class TeacherCustomController {

    private final TeacherRepositoryCustom teacherRepositoryCustom;

    @Autowired
    public TeacherCustomController(TeacherRepositoryCustom teacherRepositoryCustom) {
        this.teacherRepositoryCustom = teacherRepositoryCustom;
    }

    @GetMapping("/findbynameandsurname")
    public Teacher findByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return teacherRepositoryCustom.findByNameAndSurname(name, surname);
    }

    @GetMapping("/findteachernamebyclassroomtitle")
    public String findTeacherNameByClassroomTitle(@RequestParam String title) {
        return teacherRepositoryCustom.findNameByClassroomTitle(title);
    }

}
