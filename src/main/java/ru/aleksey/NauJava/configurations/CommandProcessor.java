package ru.aleksey.NauJava.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aleksey.NauJava.services.SubjectService;

@Component
public class CommandProcessor {
    private final SubjectService subjectService;

    @Autowired
    public CommandProcessor(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public void processorCommand(String input) {
        String[] cmd = input.split(" ");
        switch (cmd[0]) {
            case "create" -> subjectService.createSubject(Long.valueOf(cmd[1]), cmd[2], cmd[3], Double.valueOf(cmd[4]));

            case "get" -> System.out.println(subjectService.findSubjectById(Long.valueOf(cmd[1])));

            case "update" -> subjectService.updateTeacherById(Long.valueOf(cmd[1]), cmd[2]);

            case "delete" -> subjectService.deleteSubjectById(Long.valueOf(cmd[1]));

            default -> {
                System.out.println("Введена неизвестная команда");
            }
        }
    }

    public void executeCurlCommand(String command) {
        try {
            Process process = new ProcessBuilder(command.split(" "))
                    .redirectErrorStream(true)
                    .start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
