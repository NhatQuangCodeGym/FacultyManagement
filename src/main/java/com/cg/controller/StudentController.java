package com.cg.controller;

import com.cg.model.Student;
import com.cg.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

//    public StudentController(StudentService studentService) {
//        super();
//        this.studentService = studentService;
//    }

    // handler method to handle list students and return mode and view
    @GetMapping()
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("students/students");

        List<Student> students = studentService.getAllStudents();

        modelAndView.addObject("students", students);

        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView showCreatePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("students/ModalCreate");
        modelAndView.addObject("student", new Student());
        modelAndView.addObject("script",null);
        modelAndView.addObject("success",null);
        modelAndView.addObject("error",null);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showeditStudentForm(@PathVariable Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("students/ModalUpdate");
        model.addAttribute("student", studentService.getStudentById(id));
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView deleteStudent(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView();
        studentService.deleteStudentById(id);
        return new ModelAndView("redirect:/students");
    }

    @PostMapping("/create")
    public ModelAndView saveStudent(@Validated @ModelAttribute("student") Student student,BindingResult bindingResult) {
       ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasFieldErrors()) {
            List<String> scripts = new ArrayList<>();
            for (ObjectError s: bindingResult.getAllErrors()
            ) {
                scripts.add(s.getDefaultMessage());
            }

            modelAndView.addObject("script",scripts);
            modelAndView.setViewName("students/ModalCreate");
            return modelAndView;
        }
        studentService.saveStudent(student);
        return new ModelAndView("redirect:/students");
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateStudent(@PathVariable Long id,
                                      @Validated @ModelAttribute("student") Student student,
                                      BindingResult bindingResult
                                      ) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasFieldErrors()){
//            return new ModelAndView("error")
            List<String> scripts = new ArrayList<>();
            for (ObjectError s: bindingResult.getAllErrors()
                 ) {
                scripts.add(s.getDefaultMessage());
            }

            modelAndView.addObject("script",scripts);
            modelAndView.setViewName("students/ModalUpdate");
            return modelAndView;
        }
        else {
        // get student from database by id

        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        // save updated student object
        studentService.updateStudent(existingStudent);
        return new ModelAndView("redirect:/students");
        }
//        return new ModelAndView("redirect:/students");
    }

    // handler method to handle delete student request



}
