package com.cg.controller;


import com.cg.model.Instructor;
import com.cg.model.Student;
import com.cg.service.InstructorService;
import com.cg.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/instructors")
@Controller
public class InstructorController {
    private InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        super();
        this.instructorService = instructorService;
    }

    @GetMapping()
    public ModelAndView showListPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("instructors/list");

        List<Instructor> instructors = instructorService.getAllInstructors();

        modelAndView.addObject("instructors", instructors);

        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView showCreatePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("instructors/addInstructor");
        modelAndView.addObject("instructor", new Instructor());
        modelAndView.addObject("script",null);
        modelAndView.addObject("success",null);
        modelAndView.addObject("error",null);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditInstructorForm(@PathVariable Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("instructors/updateInstructor");
        model.addAttribute("instructor", instructorService.getInstructorById(id));
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView deleteInstructor(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView();
        instructorService.deleteInstructorById(id);
        return new ModelAndView("redirect:/instructors");
    }


    @PostMapping("/create")
    public ModelAndView saveInstructor( @Validated @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasFieldErrors()) {
            List<String> scripts = new ArrayList<>();
            for (ObjectError s: bindingResult.getAllErrors()
            ) {
                scripts.add(s.getDefaultMessage());
            }

            modelAndView.addObject("script",scripts);
            modelAndView.setViewName("instructors/addInstructor");
            return modelAndView;
        }
        instructorService.saveInstructor(instructor);
        return new ModelAndView("redirect:/instructors");
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateInstructor(@PathVariable Long id,
                                      @Validated @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasFieldErrors()) {
//            return new ModelAndView("error")
            List<String> scripts = new ArrayList<>();
            for (ObjectError s : bindingResult.getAllErrors()
            ) {
                scripts.add(s.getDefaultMessage());
            }

            modelAndView.addObject("script", scripts);
            modelAndView.setViewName("instructors/updateInstructor");
            return modelAndView;
        } else {
            // get student from database by id
            Instructor existingInstructor = instructorService.getInstructorById(id);
            existingInstructor.setId(id);
            existingInstructor.setFullName(instructor.getFullName());
            existingInstructor.setEmail(instructor.getEmail());
            existingInstructor.setPhone(instructor.getPhone());
            existingInstructor.setAddress(instructor.getAddress());

            // save updated student object
            instructorService.updateInstructor(existingInstructor);
            return new ModelAndView("redirect:/instructors");
        }
    }
}
