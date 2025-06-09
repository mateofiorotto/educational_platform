package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.CourseResponseDTO;
import com.mateo.plataforma_educativa.dto.CourseRequestDTO;
import com.mateo.plataforma_educativa.dto.ResponseDTO;
import com.mateo.plataforma_educativa.service.ICourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<CourseResponseDTO> coursesList = courseService.getCourses();

        ResponseDTO<List<CourseResponseDTO>> getResponseCourses = new ResponseDTO<>(coursesList, 200, "Courses returned succesfully");

        return ResponseEntity.ok(getResponseCourses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<?> getCourseById(@PathVariable Long id){

        CourseResponseDTO course = courseService.getCourseById(id);

        ResponseDTO<CourseResponseDTO> getResponseCourse = new ResponseDTO<>(course, 200, "Course returned succesfully");

        return ResponseEntity.ok(getResponseCourse);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseRequestDTO courseDTO){
        courseService.saveCourse(courseDTO);

        ResponseDTO<CourseRequestDTO> saveCourseResponse = new ResponseDTO<>(courseDTO, 201, "Course created succesfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(saveCourseResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCourse(@Valid @RequestBody CourseRequestDTO courseDTO, @PathVariable Long id){
        courseService.updateCourse(courseDTO, id);

        ResponseDTO<CourseRequestDTO> updateCourseResponse = new ResponseDTO<>(courseDTO, 200, "Course updated succesfully");

        return ResponseEntity.status(HttpStatus.OK).body(updateCourseResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);

        return ResponseEntity.status(HttpStatus.OK).body("Course deleted");
    }
}
