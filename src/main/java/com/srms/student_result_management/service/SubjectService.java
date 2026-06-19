package com.srms.student_result_management.service;

import com.srms.student_result_management.entity.Subject;

import java.util.List;

public interface SubjectService {
    public Subject createSubject(Subject subject);

    public List<Subject> getAllSubjects();

    public Subject getSubjectById(Long id);

    public Subject updateSubject(Long id,Subject subject);

    public void deleteSubject(Long id);

}
