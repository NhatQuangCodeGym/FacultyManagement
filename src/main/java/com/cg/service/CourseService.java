package com.cg.service;

import com.cg.model.Course;
import com.cg.model.Instructor;
import com.cg.model.Student;

import java.util.List;
import java.util.Optional;


public interface CourseService {


    List<Course> getAllCourse();

    Iterable<Course> findAll();

    Optional<Course> findById(Long id);

    List<Student> getAllStudentNotInCourse();

    Course save(Course course);

    void updateCourse(Course course);



//    Student saveStudent(Student student);
//
//    Student getStudentById(Long id);
//
//    void updateStudent(Student student);
//
//    void deleteStudentById(Long id);

}
