package ru.aleksey.NauJava.objects;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @OneToMany
    private List<Student> studentList;
    @OneToOne
    private Teacher teacher;

    public Classroom() {
    }

    public Classroom(String title, List<Student> studentList, Teacher teacher) {
        this.title = title;
        this.studentList = studentList;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
