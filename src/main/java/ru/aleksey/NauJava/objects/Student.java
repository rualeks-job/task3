package ru.aleksey.NauJava.objects;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Classroom classroom;
    @Column
    private String name;
    @Column
    private String surname;
    @OneToMany
    private List<Grade> gradeList;

    public Student() {
    }

    public Student(Classroom classroom, String name, String surname, List<Grade> gradeList) {
        this.classroom = classroom;
        this.name = name;
        this.surname = surname;
        this.gradeList = gradeList;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Classroom getaClass() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
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
