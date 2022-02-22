package com.cg.repository;

import com.cg.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

//        @Query(value="SELECT c.id, s.course_name from courses c , nativeQuery=true)
//        List<Course>

}
