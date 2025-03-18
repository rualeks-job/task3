package ru.aleksey.NauJava.configurations;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.aleksey.NauJava.objects.Subject;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConfigurationSubject {
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Subject> subjectContainer() {
        return new ArrayList<>();
    }
}
