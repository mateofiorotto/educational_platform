package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.CourseGetDTO;
import com.mateo.plataforma_educativa.dto.CourseSaveDTO;
import com.mateo.plataforma_educativa.dto.CourseUpdateDTO;
import com.mateo.plataforma_educativa.dto.ResponseDTO;
import com.mateo.plataforma_educativa.model.Course;
import com.mateo.plataforma_educativa.service.CourseService;
import com.mateo.plataforma_educativa.service.ICourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
@PreAuthorize("denyAll()")
public class CourseController {

    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<?> getCourses(){
        List<CourseGetDTO> coursesList = courseService.getCourses();

        ResponseDTO<List<CourseGetDTO>> getResponseCourses = new ResponseDTO<>(coursesList, 200, "Courses returned succesfully");

        return ResponseEntity.ok(getResponseCourses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<?> getCourseById(@PathVariable Long id){

        CourseGetDTO course = courseService.getCourseById(id);

        ResponseDTO<CourseGetDTO> getResponseCourse = new ResponseDTO<>(course, 200, "Course returned succesfully");

        return ResponseEntity.ok(getResponseCourse);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseSaveDTO courseDTO){
        courseService.saveCourse(courseDTO);

        ResponseDTO<CourseSaveDTO> saveCourseResponse = new ResponseDTO<>(courseDTO, 201, "Course created succesfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(saveCourseResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCourse(@Valid @RequestBody CourseUpdateDTO courseDTO, @PathVariable Long id){
        courseService.updateCourse(courseDTO, id);

        ResponseDTO<CourseUpdateDTO> updateCourseResponse = new ResponseDTO<>(courseDTO, 200, "Course updated succesfully");

        return ResponseEntity.status(HttpStatus.OK).body(updateCourseResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);

        return ResponseEntity.status(HttpStatus.OK).body("Course deleted");
    }
}
