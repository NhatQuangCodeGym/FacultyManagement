package com.cg.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="course_name")
    private String courseName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_header", referencedColumnName = "id")
    private Instructor courseHeader;


    @OneToMany(targetEntity = Student.class, mappedBy = "course")
    private Set<Student> students;

    public Course() {
    }

    public Course(Long id, String courseName, Instructor courseHeader, Set<Student> students) {
        this.id = id;
        this.courseName = courseName;
        this.courseHeader = courseHeader;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Instructor getCourseHeader() {
        return courseHeader;
    }

    public void setCourseHeader(Instructor courseHeader) {
        this.courseHeader = courseHeader;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
