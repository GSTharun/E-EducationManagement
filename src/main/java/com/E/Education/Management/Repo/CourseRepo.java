package com.E.Education.Management.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.E.Education.Management.dto.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {
}

