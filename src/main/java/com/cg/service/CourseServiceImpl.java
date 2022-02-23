package com.cg.service;

import com.cg.model.Course;
import com.cg.model.Student;
import com.cg.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Iterable<Course> findAll(){
        return courseRepository.findAll();
    };

    @Override
    public List<Student> getAllStudentNotInCourse() {
        return courseRepository.getAllStudentNotInCourse();
    }


}
