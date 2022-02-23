package com.cg.controller;


import com.cg.model.Course;
import com.cg.service.CourseService;
import com.cg.service.InstructorService;
import com.cg.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ModelAndView showCourseList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("courses/addStudentIntoCourse");

        List<Course> courses = courseService.getAllCourse();
        modelAndView.addObject("courses",courses);
        return modelAndView;
    }

}
