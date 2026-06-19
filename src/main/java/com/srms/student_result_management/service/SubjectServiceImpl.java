package com.srms.student_result_management.service;


import com.srms.student_result_management.entity.Subject;
import com.srms.student_result_management.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class SubjectServiceImpl implements SubjectService{
    private final SubjectRepository subjectRepository;

    SubjectServiceImpl(SubjectRepository subjectRepository){this.subjectRepository=subjectRepository;}

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElseThrow(()->new RuntimeException("Subject not found"));

    }

    @Override
    public Subject updateSubject(Long id, Subject subject) {
        Subject existingSubject=subjectRepository.findById(id).orElseThrow(()-> new RuntimeException("Subject not found! please enter the correct subject to update"));

        existingSubject.setSubjectName(subject.getSubjectName());
        existingSubject.setSubjectCode(subject.getSubjectCode());
        existingSubject.setDepartment(subject.getDepartment());
        existingSubject.setCredits(subject.getCredits());

        return subjectRepository.save(existingSubject);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}