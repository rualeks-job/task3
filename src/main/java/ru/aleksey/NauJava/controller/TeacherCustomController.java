package ru.aleksey.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.aleksey.NauJava.objects.Teacher;
import ru.aleksey.NauJava.repositories.TeacherRepository;

@RestController
@RequestMapping("/rest/teachers")
public class TeacherCustomController {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherCustomController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/teachers")
    public Teacher findByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        return teacherRepository.findByNameAndSurname(name, surname);
    }

    @GetMapping("//classrooms/{title}/teacher")
    public String findTeacherNameByClassroomTitle(@PathVariable String title) {
        return teacherRepository.findNameByClassroomTitle(title);
    }

}
