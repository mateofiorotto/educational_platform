package com.mateo.plataforma_educativa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mateo.plataforma_educativa.model.Student;
import com.mateo.plataforma_educativa.model.Teacher;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CourseGetDTO implements Serializable {
    private Long id;
    private String name;
    private Teacher teacher; //replace for DTO TeacherGetDTO
    private Set<Student> students = new HashSet<>(); //replace for DTO StudentGetDTO

    public CourseGetDTO(){}

    public CourseGetDTO(Long id, String name, Teacher teacher, Set<Student> students) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
