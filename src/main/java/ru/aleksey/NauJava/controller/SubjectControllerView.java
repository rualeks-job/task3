package ru.aleksey.NauJava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aleksey.NauJava.objects.Subject;
import ru.aleksey.NauJava.repositories.SubjectRepository;

@Controller
@RequestMapping("/custom/subject/view")
public class SubjectControllerView {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectControllerView(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/list")
    public String subjectListView(Model model) {
        Iterable<Subject> subject = subjectRepository.findAll();
        model.addAttribute("subjects", subject);
        return "subjectList";
    }
}
