package com.srms.student_result_management.controller;


import com.srms.student_result_management.entity.Subject;
import com.srms.student_result_management.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin(origins = "http://localhost:3000")
public class SubjectController {

    private final SubjectService subjectService;

    SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.createSubject(subject);
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")

    public Subject getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    @PutMapping("/{id}")

    public Subject updateSubject(@PathVariable Long id,@RequestBody Subject subject) {
        return subjectService.updateSubject(id, subject);
    }


    @DeleteMapping("/{id}")

    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }

}
