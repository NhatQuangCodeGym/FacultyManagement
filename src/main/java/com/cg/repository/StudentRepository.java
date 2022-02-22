package com.cg.repository;

import com.cg.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
//    List<Student> findAllByDeletedIsFalse();
//
//    List<Student> findAllByIdNot(Long id);

//    List<Student> findAllByIdNotAndDeletedIsFalse(Long id);

//    @Modifying
//    @Query(value="SELECT s.id, s.stname, c.coursename from student s Inner JOIN course c ON s.course=c.id", nativeQuery=true)
//    List<Object[]> findStudent();

    void deleteStudentById(Long id);
}
