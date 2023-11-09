package com.E.Education.Management.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.E.Education.Management.dto.Parent;

@Repository
public interface ParentRepo extends JpaRepository<Parent, Long> {

	Optional<Parent> findByEmail(String email);

}
