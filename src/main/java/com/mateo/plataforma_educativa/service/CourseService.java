package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.CourseGetDTO;
import com.mateo.plataforma_educativa.dto.CourseSaveDTO;
import com.mateo.plataforma_educativa.dto.CourseUpdateDTO;
import com.mateo.plataforma_educativa.exception.NotFoundException;
import com.mateo.plataforma_educativa.mapper.ICourseMapper;
import com.mateo.plataforma_educativa.model.Course;
import com.mateo.plataforma_educativa.repository.ICourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {

    private final ICourseRepository courseRepository;

    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseGetDTO> getCourses() {
        List<Course> courses = courseRepository.findAll();

        return courses.stream().map(course -> ICourseMapper.mapper.courseToCourseGetDTO(course)).collect(Collectors.toList());
    }

    @Override
    public CourseGetDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found with ID: " + id));

        return ICourseMapper.mapper.courseToCourseGetDTO(course);
    }

    @Override
    public CourseSaveDTO saveCourse(CourseSaveDTO courseDTO) {
        Course courseToSave = ICourseMapper.mapper.courseSaveDTOToCourse(courseDTO);

        courseToSave.setName(courseDTO.getName());
        courseToSave.setTeacher(courseDTO.getTeacher());
        courseToSave.setStudents(courseDTO.getStudents());

        courseRepository.save(courseToSave);

        return courseDTO;
    }

    @Override
    public CourseUpdateDTO updateCourse(CourseUpdateDTO courseDTO, Long id) {
        Course findedCourse = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));

        findedCourse.setName(courseDTO.getName());
        findedCourse.setTeacher(courseDTO.getTeacher());
        findedCourse.setStudents(courseDTO.getStudents());

        courseRepository.save(findedCourse);

        return courseDTO;
    }

    @Override
    public void deleteCourse(Long id) {
        Course courseToDelete = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));

        courseRepository.delete(courseToDelete);


    }
}
