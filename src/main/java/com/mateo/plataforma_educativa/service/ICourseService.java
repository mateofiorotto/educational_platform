package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.CourseResponseDTO;
import com.mateo.plataforma_educativa.dto.CourseRequestDTO;

import java.util.List;

public interface ICourseService {


    /**
     * Returns a list of all courses
     *
     * @return list of courses
     * */
    public List<CourseResponseDTO> getCourses();

    /**
     * Returns a specific course
     *
     * @param id
     * @return a course
     * */
    public CourseResponseDTO getCourseById(Long id);

    /**
     * Create a course
     *
     * @param courseDTO
     * @return the created course info
     * */
    public CourseRequestDTO saveCourse(CourseRequestDTO courseDTO);

    /**
     * Update a course by id
     *
     * @param courseDTO
     * @param id
     * @return the updated course
     * */
    public CourseRequestDTO updateCourse(CourseRequestDTO courseDTO, Long id);

    /**
     * Delete a course
     * @param id
     * */
    public void deleteCourse(Long id);
}
