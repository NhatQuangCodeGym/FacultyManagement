package com.cg.repository;

import com.cg.model.Course;
import com.cg.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

        @Query("SELECT c from Student c WHERE (c.course is null)")
        List<Student> getAllStudentNotInCourse();

}
