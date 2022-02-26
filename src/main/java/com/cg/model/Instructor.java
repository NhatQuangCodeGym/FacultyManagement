package com.cg.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

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

        @NotBlank(message = " Vui lòng nhập sđt")
        @Column(name ="phone")
        private String phone;

        @NotBlank(message = " Vui lòng nhập địa chỉ")
        @Column(name="address")
        private String address;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseHeader")
        private Set<Course> courses;

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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
        public String toString() {
            return "Instructor{" +
                    "id=" + id +
                    ", fullName='" + fullName + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", address='" + address + '\'' +

                    '}';
        }
    }
