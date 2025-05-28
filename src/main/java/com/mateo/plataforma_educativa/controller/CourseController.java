package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.CourseGetDTO;
import com.mateo.plataforma_educativa.dto.CourseSaveDTO;
import com.mateo.plataforma_educativa.dto.CourseUpdateDTO;
import com.mateo.plataforma_educativa.model.Course;
import com.mateo.plataforma_educativa.service.CourseService;
import com.mateo.plataforma_educativa.service.ICourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getCourses(){
        List<CourseGetDTO> coursesList = courseService.getCourses();

        return ResponseEntity.ok(coursesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id){

        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping("/")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseSaveDTO courseDTO){
        CourseSaveDTO created = courseService.saveCourse(courseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@Valid @RequestBody CourseUpdateDTO courseDTO, @PathVariable Long id){
        CourseUpdateDTO updated = courseService.updateCourse(courseDTO, id);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);

        return ResponseEntity.status(HttpStatus.OK).body("Course deleted");
    }
}
