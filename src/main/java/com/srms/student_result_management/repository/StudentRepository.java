package com.srms.student_result_management.repository;

import com.srms.student_result_management.entity.AppUser;
import com.srms.student_result_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {


    Optional<Student> findByRollNumberAndDob(String rollNumber, String dob);
    Optional<Student> findByAppUser(AppUser appUser);
}
