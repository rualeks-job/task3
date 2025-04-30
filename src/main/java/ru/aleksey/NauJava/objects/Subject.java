package ru.aleksey.NauJava.objects;

import jakarta.persistence.*;

@Entity
@Table
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Teacher teacher;
    @Column(name = "duration")
    private Double duration;

    public Subject() {
    }

    public Subject(String title, Teacher teacher, Double duration) {
        this.title = title;
        this.teacher = teacher;
        this.duration = duration;
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
