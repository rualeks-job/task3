package ru.aleksey.NauJava.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class Config {

    private final CommandProcessor commandProcessor;

    @Autowired
    public Config(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Введите команду. exit для выхода.");
                while (true) {
                    System.out.println("> ");
                    String input = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(input.trim())) {
                        System.out.println("Выход из программы");
                        break;
                    }
                    commandProcessor.processorCommand(input);
                }
            }
            commandProcessor.executeCurlCommand("curl -X POST http://localhost:8082/actuator/shutdown");
        };
    }


}
