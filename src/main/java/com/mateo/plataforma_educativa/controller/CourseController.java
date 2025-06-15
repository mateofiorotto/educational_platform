package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.CourseResponseDTO;
import com.mateo.plataforma_educativa.dto.CourseRequestDTO;
import com.mateo.plataforma_educativa.dto.ResponseDTO;
import com.mateo.plataforma_educativa.service.ICourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Get courses",
            description = "Return the list of courses. Only users with ADMIN, TEACHER or STUDENT roles.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Courses returned succesfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)"),
    })
    @GetMapping("/")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<?> getCourses(){
        List<CourseResponseDTO> coursesList = courseService.getCourses();

        ResponseDTO<List<CourseResponseDTO>> getResponseCourses = new ResponseDTO<>(coursesList, 200, "Courses returned succesfully");

        return ResponseEntity.ok(getResponseCourses);
    }

    @Operation(summary = "Get course",
            description = "Return one course. Only users with ADMIN, TEACHER or STUDENT roles.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course returned succesfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "404", description = "Course not found"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<?> getCourseById(@PathVariable Long id){

        CourseResponseDTO course = courseService.getCourseById(id);

        ResponseDTO<CourseResponseDTO> getResponseCourse = new ResponseDTO<>(course, 200, "Course returned succesfully");

        return ResponseEntity.ok(getResponseCourse);
    }

    @Operation(summary = "Create a course",
            description = "Return the created course. Only the users with ADMIN role can create new courses.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created succesfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, fields validation error"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseRequestDTO courseDTO){
        courseService.saveCourse(courseDTO);

        ResponseDTO<CourseRequestDTO> saveCourseResponse = new ResponseDTO<>(courseDTO, 201, "Course created succesfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(saveCourseResponse);
    }

    @Operation(summary = "Edit a course",
            description = "Return the edited course. Only the users with ADMIN role can edit courses.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course edited succesfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, fields validation error"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "404", description = "Course not found"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCourse(@Valid @RequestBody CourseRequestDTO courseDTO, @PathVariable Long id){
        courseService.updateCourse(courseDTO, id);

        ResponseDTO<CourseRequestDTO> updateCourseResponse = new ResponseDTO<>(courseDTO, 200, "Course updated succesfully");

        return ResponseEntity.status(HttpStatus.OK).body(updateCourseResponse);
    }

    @Operation(summary = "Delete a course",
            description = "Return a confirmation message. Only the users with ADMIN role can delete courses.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course deleted succesfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "404", description = "Course not found"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);

        return ResponseEntity.status(HttpStatus.OK).body("Course deleted");
    }
}
