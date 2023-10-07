package com.E.Education.Management.Repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.E.Education.Management.dto.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {


	Optional<Student> findByEmail(String email);
}

