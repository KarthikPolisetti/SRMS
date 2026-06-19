package com.srms.student_result_management.service;


import com.srms.student_result_management.entity.Student;
import com.srms.student_result_management.entity.Subject;
import com.srms.student_result_management.exception.ResourceNotFoundException;
import com.srms.student_result_management.repository.StudentRepository;
import com.srms.student_result_management.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private  final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository,SubjectRepository subjectRepository){
        this.subjectRepository=subjectRepository;
            this.studentRepository=studentRepository;
}

    @Override
    public Student createStudent(Student student) {

    return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();

    }

    @Override
    public Student getStudentById(Long id) {
         return studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found with the id"+id));

    }

    @Override
    public Student updateStudent(Long id, Student student) {
    Student existingStudent=studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found"));
    existingStudent.setName(student.getName());
    existingStudent.setEmail(student.getEmail());
    existingStudent.setDepartment(student.getDepartment());
    existingStudent.setPhone(student.getPhone());
    existingStudent.setEnrollmentYear(student.getEnrollmentYear());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
         studentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void enrollStudent(Long studentId, Long subjectId) {
Student student=studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("student not found"));
Subject subject=subjectRepository.findById(subjectId).orElseThrow(()->new RuntimeException("Subject not found"));
student.getSubjects().add(subject);
studentRepository.save(student);
    }



}
