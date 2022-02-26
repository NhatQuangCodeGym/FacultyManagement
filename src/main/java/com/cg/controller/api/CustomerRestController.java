package com.cg.controller.api;

import com.cg.exception.DataInputException;
import com.cg.model.Course;

import com.cg.model.Student;
import com.cg.service.CourseService;
import com.cg.service.InstructorService;
import com.cg.service.StudentService;
import com.cg.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CustomerRestController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private AppUtil appUtil;

    @GetMapping()
    public ResponseEntity<Iterable<?>> getById() {
        Iterable<Course> listCoures= courseService.findAll();


//        if (listCoures.isPresent()) {
//            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//        }
        return new ResponseEntity<>(listCoures, HttpStatus.OK);
    }

    @GetMapping("/addStudentToCourse")
    public ResponseEntity<Iterable<?>> getAllStudentNotInCourse(){
        Iterable<Student> students = courseService.getAllStudentNotInCourse();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

//    @GetMapping("/courseList")
//    public

    @PostMapping("/handlerAddStudent/{courseID}/{studentId}")
    public ResponseEntity<Student> handlerAddStudent(@PathVariable("courseID") Long courseID, @PathVariable("studentId") Long studentId){
       Student student = studentService.getStudentById(studentId);
       Course course = courseService.findById(courseID).get();

        student.setCourse(course);
        studentService.save(student);
       return new ResponseEntity<>( student, HttpStatus.OK);
    }

//    @PostMapping("/create")
//    public ResponseEntity<Customer> doCreate(@RequestBody Customer customer) {
//        customer.setId(0L);
//        Customer createdCustomer = customerService.save(customer);
//
//        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<?> doUpdate(@Validated @RequestBody Customer customer,BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return appUtil.mapErrorToResponse(bindingResult);
//        }
//
//        Long id = customer.getId();
//        customerService.save(customer);
//        Customer updatedCustomer = customerService.findById(id).get();
//        return new ResponseEntity<>(updatedCustomer, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/deposit/{id}")
//    public ResponseEntity<?> doDeposit(@PathVariable Long id, @Validated @RequestBody Deposit deposit, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return appUtil.mapErrorToResponse(bindingResult);
//        }
//        Optional<Customer> optionalCustomer = customerService.findById(id);
//        if(optionalCustomer.isPresent()) {
//            BigDecimal currentBalance = optionalCustomer.get().getBalance();
//            BigDecimal transactionAmount = deposit.getTransactionAmount();
//            customerService.incrementBalance(id, deposit);
//            Customer updatedCustomer = customerService.findById(id).get();
//            return new ResponseEntity<>(updatedCustomer, HttpStatus.CREATED);
//        } else{
//            throw new DataInputException("Customer's information not valid");
//
//        }
////        customerService.incrementBalance(id, deposit);
////        Customer updatedCustomer = customerService.findById(id).get();
////        return new ResponseEntity<>(updatedCustomer, HttpStatus.CREATED);
//
//    }
//
//    @PutMapping("/withdraw/{id}")
//    public ResponseEntity<?> doWithdraw(@PathVariable Long id, @Validated @RequestBody Withdraw withdraw, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return appUtil.mapErrorToResponse(bindingResult);
//        }
//
//        Optional<Customer> optionalCustomer = customerService.findById(id);
//
//        if (optionalCustomer.isPresent()) {
//            BigDecimal currentBalance = optionalCustomer.get().getBalance();
//            BigDecimal transactionAmount = withdraw.getTransactionAmount();
//            if (currentBalance.compareTo(transactionAmount) >= 0) {
//                customerService.reduceBalance(id, withdraw);
//                Customer updatedCustomer = customerService.findById(id).get();
//                return new ResponseEntity<>(updatedCustomer, HttpStatus.CREATED);
//
//            } else {
//                throw new DataInputException("Not enough to withdraw");
//            }
//        } else {
//            throw new DataInputException("Customer's information not valid");
//        }
    }

