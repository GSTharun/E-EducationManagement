package com.E.Education.Management.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.E.Education.Management.dto.Faculty;

public interface FacultyRepo extends JpaRepository<Faculty, Long> {

	Optional<Faculty> findByEmail(String email);
}
