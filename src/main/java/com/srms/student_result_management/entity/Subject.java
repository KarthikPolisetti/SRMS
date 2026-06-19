package com.srms.student_result_management.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subjectName;
    private String subjectCode;
    private Integer credits;
    private String department;
    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private Set<Student> students;
    public Subject(){};

    //setters
    public void setId(Long id){
        this.id=id;
    }

    public void setSubjectName(String subName){
        this.subjectName=subName;
    }

    public void setSubjectCode(String subCode){
        this.subjectCode=subCode;
    }


    public void setCredits(Integer credits){
        this.credits=credits;
    }


    public  void setDepartment(String deptName){
        this.department=deptName;
    }


    //getters

    public Long getId(){
        return id;
    }


    public String getSubjectName(){
        return subjectName;
    }

    public String getSubjectCode(){
        return subjectCode;
    }

    public  Integer getCredits(){
        return credits;
    }

    public String getDepartment(){
        return department;
    }


}
