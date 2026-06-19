package com.srms.student_result_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String username;
    private  String password;
    private  String role;


    public void setId(Long id){this.id=id;}
    public Long getId(){return  this.id;}

    public void setUsername(String username){this.username=username;}
    public String getUsername(){return this.username;}


    public void setPassword(String password){this.password=password;}
    public String getPassword(){return this.password;}

    public void setRole(String role){this.role=role;}
    public String getRole(){return this.role;}
}
