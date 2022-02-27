package com.cg.controller;


import com.cg.dto.CourseDTO;
import com.cg.model.Course;
import com.cg.model.Instructor;
import com.cg.model.Student;
import com.cg.service.CourseService;
import com.cg.service.InstructorService;
import com.cg.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
@Autowired
    EntityManager entityManager;
    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public ModelAndView showCourseList() {
        ModelAndView modelAndView = new ModelAndView("/courses/list");
        List<Course> courses = courseService.getAllCourse();
        modelAndView.addObject("courses", courses);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createCourse(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("courses/createCourse");
        List<Instructor> instructors = instructorService.getAllInstructors();
        modelAndView.addObject("courseHeader", instructors);
        modelAndView.addObject("course", new Course());
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showEditCourseForm(@PathVariable Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("courses/updateCourse");
        modelAndView.addObject("courseHeader", instructorService.getAllInstructors());
        model.addAttribute("course", courseService.findById(id).get());

        return modelAndView;
    }

    @GetMapping("/addStudentToCourse/{id}")
    public ModelAndView getAllStudentNotInCourse(@PathVariable Long id, Model model){
        ModelAndView modelAndView = new ModelAndView();
        List<Student> students = courseService.getAllStudentNotInCourse();
        modelAndView.setViewName("courses/addStudentIntoCourse");
        modelAndView.addObject("students",students);
//        model.addAttribute("course", courseService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView handlerAddNewCourse(@Valid @ModelAttribute CourseDTO courseDTO, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(courseDTO);
        if (bindingResult.hasFieldErrors()) {
            List<String> scripts = new ArrayList<>();
            for (ObjectError s: bindingResult.getAllErrors()
            ) {
                scripts.add(s.getDefaultMessage());
            }

            modelAndView.addObject("script",scripts);
            modelAndView.setViewName("courses/createCourse");
            return modelAndView;
        }


        Course course=new Course();
        course.setCourseName(courseDTO.getCourseName());
        Instructor instructor=new Instructor();
        instructor.setId(courseDTO.getInstructorId());
        course.setCourseHeader(instructor);
        courseService.save(course);
        return new ModelAndView("redirect:/courses");
        }

    @PostMapping("/update/{id}")
    public ModelAndView updateCourse(@PathVariable Long id,
                                         @Validated @ModelAttribute("course") Course course, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasFieldErrors()) {
//            return new ModelAndView("error")
            List<String> scripts = new ArrayList<>();
            for (ObjectError s : bindingResult.getAllErrors()
            ) {
                scripts.add(s.getDefaultMessage());
            }

            modelAndView.addObject("script", scripts);
            modelAndView.setViewName("courses/updateCourse");
            return modelAndView;
        } else {
            // get student from database by id
            Course existingCourse = courseService.findById(id).get();
            existingCourse.setId(id);
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setCourseHeader(course.getCourseHeader());

            // save updated student object
            courseService.updateCourse(existingCourse);
            return new ModelAndView("redirect:/courses");
        }
    }




}
