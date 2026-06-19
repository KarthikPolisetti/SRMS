package com.srms.student_result_management.controller;


import com.srms.student_result_management.entity.Student;
import com.srms.student_result_management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
            this.studentService=studentService;
    }

    @PostMapping
    public Student createStudent(@Valid  @RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return  studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }


    @PutMapping("/{id}")

    public Student updateStudent(@PathVariable Long id,@Valid @RequestBody Student student){
        return studentService.updateStudent(id,student);
    }

    @DeleteMapping("/{id}")

    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }


    @PostMapping("/{studentId}/subjects/{subjectId}")
    public ResponseEntity<String> enrollStudent(
            @PathVariable Long studentId,
            @PathVariable Long subjectId) {

        studentService.enrollStudent(studentId, subjectId);

        return ResponseEntity.ok("Student enrolled successfully!");
    }

}
