package com.mateo.plataforma_educativa.repository;

import com.mateo.plataforma_educativa.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
}
