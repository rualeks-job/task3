package ru.aleksey.NauJava.objects;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @OneToOne
    private Teacher teacher;
    @Column
    private Double duration;
    @OneToMany
    private List<Grade> gradeList;

    public Subject() {
    }

    public Subject(String title, Teacher teacher, Double duration, List<Grade> gradeList) {
        this.title = title;
        this.teacher = teacher;
        this.duration = duration;
        this.gradeList = gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", teacher='" + teacher + '\'' +
                ", duration=" + duration +
                '}';
    }
}
