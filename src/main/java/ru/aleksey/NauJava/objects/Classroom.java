package ru.aleksey.NauJava.objects;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, name = "title")
    private String title;
    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    private List<Student> studentList;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Classroom() {
    }

    public Classroom(String title, List<Student> studentList, Teacher teacher) {
        this.title = title;
        this.studentList = studentList;
        this.teacher = teacher;
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
