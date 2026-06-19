package com.srms.student_result_management.entity;

import jakarta.persistence.*;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id")
    private  Student student;

    @ManyToOne
    @JoinColumn(name="subject_id")
    private Subject subject;

    private Integer marks;
    private  String grade;

    public Result(){};

    //setters

    public void setId(Long id){
        this.id=id;
    }

    public void setStudent(Student student){
        this.student=student;
    }

    public void setSubject(Subject subject){
        this.subject=subject;
    }

    public void setMarks(Integer marks){
        this.marks=marks;
    }

    public void setGrade(String grade){
        this.grade=grade;
    }

    //getters

    public Long getId(){
        return id;
    }

    public Student getStudent() {
        return student;
    }


    public Subject getSubject() {
        return subject;
    }

    public Integer getMarks() {
        return marks;
    }


    public String getGrade() {
        return grade;
    }


}
