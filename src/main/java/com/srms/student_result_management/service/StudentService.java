package com.srms.student_result_management.service;

import com.srms.student_result_management.entity.Student;

import java.util.List;

public interface StudentService {
    public Student createStudent(Student student);

    public List<Student> getAllStudents();

    public Student getStudentById(Long id);

    public Student updateStudent(Long id,Student student);

    public void deleteStudent(Long id);

    public void enrollStudent(Long studentId,Long subjectId);
}
