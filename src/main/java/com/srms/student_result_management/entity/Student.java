package com.srms.student_result_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Invalid email format")
    private String email;
    private String phone;
    private String department;
    private Integer enrollmentYear;
private  String dob;


    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;



    @Column(unique = true)
    private String rollNumber;
    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects = new HashSet<>();

    //constructor
    public Student() {
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEnrollmentYear(Integer enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    //getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }


    public String getEmail() {
        return this.email;
    }


    public String getPhone() {
        return this.phone;
    }


    public String getDepartment() {
        return this.department;
    }


    public Integer getEnrollmentYear() {
        return this.enrollmentYear;
    }


    public Set<Subject> getSubjects() {
        return this.subjects;
    }


    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }


    public void setDob(String dob){this.dob=dob;}

    public String getDob(){return dob;}

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    // 3. THE MISSING GETTER (Good practice to have!)
    public AppUser getAppUser() {
        return appUser;
    }

}

