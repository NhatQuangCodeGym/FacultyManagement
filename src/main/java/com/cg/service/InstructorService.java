package com.cg.service;

import com.cg.model.Instructor;
import com.cg.model.Student;

import java.util.List;

public interface InstructorService {

    List<Instructor> getAllInstructors();

    Instructor saveInstructor(Instructor instructor);

    Instructor getInstructorById(Long id);

    void updateInstructor(Instructor instructor);

    void deleteInstructorById(Long id);
}
