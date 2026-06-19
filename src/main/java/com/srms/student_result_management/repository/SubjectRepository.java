package com.srms.student_result_management.repository;

import com.srms.student_result_management.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
