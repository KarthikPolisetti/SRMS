package com.srms.student_result_management.repository;


import com.srms.student_result_management.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
public Optional<AppUser> findByUsername(String username);
}
