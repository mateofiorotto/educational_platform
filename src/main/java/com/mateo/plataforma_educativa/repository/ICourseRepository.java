package com.mateo.plataforma_educativa.repository;

import com.mateo.plataforma_educativa.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {
}
