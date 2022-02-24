package com.cg.service;

import com.cg.model.Student;

import java.util.List;


public interface StudentService {
    List<Student> getAllStudents();

    Student save(Student student);

    Student saveStudent(Student student);

    Student getStudentById(Long id);

    void updateStudent(Student student);

    void deleteStudentById(Long id);
}
