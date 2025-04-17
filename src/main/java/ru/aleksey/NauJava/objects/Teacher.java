package ru.aleksey.NauJava.objects;

import jakarta.persistence.*;

@Entity
@Table
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Subject subject;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @OneToOne(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Classroom classroom;

    public Teacher(Subject subject, String name, String surname, Classroom classroom) {
        this.subject = subject;
        this.name = name;
        this.surname = surname;
        this.classroom = classroom;
    }

    public Teacher() {
    }

    public Long getId() {
        return id;
    }

    public Classroom getaClass() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
