package com.srms.student_result_management.repository;

import com.srms.student_result_management.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Long> {
    public List<Result> findByStudentId(Long studentId);
    public Result findTopByStudentDepartmentOrderByMarksDesc(String department);
}

