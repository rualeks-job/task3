package ru.aleksey.NauJava.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class Config {

    private final CommandProcessor commandProcessor;
    private final ApplicationContext applicationContext;

    @Autowired
    public Config(CommandProcessor commandProcessor, ApplicationContext applicationContext) {
        this.commandProcessor = commandProcessor;
        this.applicationContext = applicationContext;
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
            SpringApplication.exit(applicationContext);
        };
    }


}
