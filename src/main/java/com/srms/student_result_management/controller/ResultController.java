package com.srms.student_result_management.controller;

import com.srms.student_result_management.entity.Result;
import com.srms.student_result_management.service.ResultService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@CrossOrigin(origins =  "http://localhost:3000")
public class ResultController {
private final ResultService resultService;
    ResultController(ResultService resultService){
        this.resultService=resultService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_TEACHER')") // <--- THE VIP ROPE
    public Result createResult(@RequestBody Result result){
        return resultService.createResult(result);
    }

    @GetMapping

    public List<Result> getAllResults(){
        return resultService.getAllResults();
    }

    @GetMapping("/{id}")

    public Result getResultById(@PathVariable Long id){
        return resultService.getResultById(id);
    }

    @GetMapping("/student/{studentId}")

    public List<Result> getResultByStudentId(@PathVariable Long  studentId){
        return resultService.getResultsByStudentId(studentId);
    }

    @GetMapping("/top/{department}")

    public Result getTopStudentByDepartment(@PathVariable String department){
        return  resultService.getTopStudentByDepartment(department);
    }





}
