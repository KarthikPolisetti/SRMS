package com.srms.student_result_management.service;

import com.srms.student_result_management.entity.Result;
import com.srms.student_result_management.entity.Student;
import com.srms.student_result_management.entity.Subject;
import com.srms.student_result_management.repository.ResultRepository;
import com.srms.student_result_management.repository.StudentRepository;
import com.srms.student_result_management.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

@Service
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private static final NavigableMap<Integer,String> Grade_Scale=new TreeMap<>();

    static{
        Grade_Scale.put(90,"A+");
        Grade_Scale.put(80,"A");
        Grade_Scale.put(70,"B+");
        Grade_Scale.put(60,"B");
        Grade_Scale.put(50,"C+");
        Grade_Scale.put(40,"C");
        Grade_Scale.put(30,"D+");
        Grade_Scale.put(20,"D");
        Grade_Scale.put(10,"E+");
        Grade_Scale.put(0,"F");
    }



    public ResultServiceImpl(ResultRepository resRepo, StudentRepository stuRepo, SubjectRepository subRepo) {
        this.resultRepository = resRepo;
        this.studentRepository = stuRepo;
        this.subjectRepository = subRepo;
    }

    @Override
    public Result createResult(Result result) {

        Long studentId = result.getStudent().getId();
        Long subjectId = result.getSubject().getId();

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not Found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        result.setStudent(student);
        result.setSubject(subject);
        Integer marks=result.getMarks();
        String grade=calucluateGrade(marks);
        result.setGrade(grade);
        return resultRepository.save(result);
    }

    @Override
    public List<Result> getAllResults() {

        return resultRepository.findAll();


    }

    @Override
    public Result getResultById(Long id) {
        return resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result not found"));
    }

    @Override
    public List<Result> getResultsByStudentId(Long studentId) {
        return  resultRepository.findByStudentId(studentId);
    }

    @Override
    public Result getTopStudentByDepartment(String department) {
        return resultRepository.findTopByStudentDepartmentOrderByMarksDesc(department);
    }


    private String calucluateGrade(Integer marks){
        return  Grade_Scale.floorEntry(marks).getValue();
    }

}