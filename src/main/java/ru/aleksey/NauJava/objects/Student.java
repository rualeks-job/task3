package ru.aleksey.NauJava.objects;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Grade> gradeList;

    public Student() {
    }

    public Student(Classroom classroom, String name, String surname, List<Grade> gradeList) {
        this.classroom = classroom;
        this.name = name;
        this.surname = surname;
        this.gradeList = gradeList;
    }

    public Long getId() {
        return id;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
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
