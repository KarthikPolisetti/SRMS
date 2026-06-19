package com.srms.student_result_management.service;

import com.srms.student_result_management.entity.Result;

import java.util.List;

public interface ResultService {

    public Result createResult(Result result);

    public List<Result> getAllResults();

    public Result getResultById(Long id);

    public List<Result> getResultsByStudentId(Long studentId);

    public Result getTopStudentByDepartment(String department);
}
