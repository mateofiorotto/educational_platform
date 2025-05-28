package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.CourseGetDTO;
import com.mateo.plataforma_educativa.dto.CourseSaveDTO;
import com.mateo.plataforma_educativa.dto.CourseUpdateDTO;
import com.mateo.plataforma_educativa.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {


    /**
     * Returns a list of all courses
     *
     * @return list of courses
     * */
    public List<CourseGetDTO> getCourses();

    /**
     * Returns a specific course
     *
     * @param id
     * @return a course
     * */
    public CourseGetDTO getCourseById(Long id);

    /**
     * Create a course
     *
     * @param courseDTO
     * @return the created course info
     * */
    public CourseSaveDTO saveCourse(CourseSaveDTO courseDTO);

    /**
     * Update a course by id
     *
     * @param courseDTO
     * @param id
     * @return the updated course
     * */
    public CourseUpdateDTO updateCourse(CourseUpdateDTO courseDTO, Long id);

    /**
     * Delete a course
     * @param id
     * */
    public void deleteCourse(Long id);
}
