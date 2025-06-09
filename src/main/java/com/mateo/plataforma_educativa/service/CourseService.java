package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.CourseResponseDTO;
import com.mateo.plataforma_educativa.dto.CourseRequestDTO;
import com.mateo.plataforma_educativa.exception.NotFoundException;
import com.mateo.plataforma_educativa.mapper.ICourseMapper;
import com.mateo.plataforma_educativa.model.Course;
import com.mateo.plataforma_educativa.model.Teacher;
import com.mateo.plataforma_educativa.repository.ICourseRepository;
import com.mateo.plataforma_educativa.repository.ITeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {

    private final ICourseRepository courseRepository;
    private final ITeacherRepository teacherRepository;


    public CourseService(ICourseRepository courseRepository, ITeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<CourseResponseDTO> getCourses() {
        List<Course> courses = courseRepository.findAll();

        return courses.stream().map(course -> ICourseMapper.mapper.courseToCourseGetDTO(course)).collect(Collectors.toList());
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found with ID: " + id));

        return ICourseMapper.mapper.courseToCourseGetDTO(course);
    }

    @Override
    public CourseRequestDTO saveCourse(CourseRequestDTO courseDTO) {
        Course courseToSave = ICourseMapper.mapper.courseSaveDTOToCourse(courseDTO);

        Teacher teacherToSave = teacherRepository.findById(courseDTO.getTeacher().getId()).orElseThrow(() -> new NotFoundException("Teacher not FOUND"));

        courseToSave.setTeacher(teacherToSave);

        courseRepository.save(courseToSave);

        return courseDTO;
    }

    @Override
    public CourseRequestDTO updateCourse(CourseRequestDTO courseDTO, Long id) {
        Course findedCourse = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));

        Teacher teacherFinded = teacherRepository.findById(courseDTO.getTeacher().getId()).orElseThrow(() -> new NotFoundException("Teacher not Found"));

        findedCourse.setName(courseDTO.getName());
        findedCourse.setTeacher(teacherFinded);

        courseRepository.save(findedCourse);

        return courseDTO;
    }

    @Override
    public void deleteCourse(Long id) {
        Course courseToDelete = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));

        courseRepository.delete(courseToDelete);


    }
}
