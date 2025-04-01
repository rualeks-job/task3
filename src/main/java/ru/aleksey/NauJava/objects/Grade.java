package ru.aleksey.NauJava.objects;

import jakarta.persistence.*;

@Entity
@Table
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer grade;
    @ManyToOne
    private Subject subject;
    @ManyToOne
    private Student student;

    public Grade() {
    }

    public Grade(Integer grade, Subject subject, Student student) {
        this.grade = grade;
        this.subject = subject;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
