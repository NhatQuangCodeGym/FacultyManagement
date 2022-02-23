package com.cg.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

    @Entity
    @Table(name = "instructors")
    public class Instructor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message= "Vui lòng nhập tên đầy đủ")
        @Size(min = 5, max =35, message = "Độ dài tên từ 5-35 kí tự")
        @Column(name = "full_name")
        private String fullName;

        @NotBlank(message = "Vui lòng nhập email")
        @Column(unique = true, nullable = false)
        private String email;

        @Column(name ="phone")
        private String phone;

        @Column(name="address")
        private String address;

        @OneToOne(mappedBy = "courseHeader")
        private Course course;

        public Instructor() {
        }

//        public Instructor(Long id, String fullName, String email, String phone, String address, Course course) {
//            this.id = id;
//            this.fullName = fullName;
//            this.email = email;
//            this.phone = phone;
//            this.address = address;
//            this.course = course;
//        }

        public Instructor(Long id, String fullName, String email, String phone, String address) {
            this.id = id;
            this.fullName = fullName;
            this.email = email;
            this.phone = phone;
            this.address = address;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

//        public Course getCourse() {
//            return course;
//        }
//
//        public void setCourse(Course course) {
//            this.course = course;
//        }
    }
