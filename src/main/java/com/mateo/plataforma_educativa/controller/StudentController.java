package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.*;
import com.mateo.plataforma_educativa.service.IStudentService;
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
@RequestMapping("/students")
@PreAuthorize("denyAll()")
public class StudentController {

    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Get students",
            description = "Return the list of students. Only users with ADMIN, TEACHER or STUDENT roles.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Students returned succesfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)"),
    })
    @GetMapping("/")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<?> getStudents(){
        List<StudentResponseDTO> studentsList = studentService.getStudents();

        ResponseDTO<List<StudentResponseDTO>> getResponseStudents = new ResponseDTO<>(studentsList, 200, "Students returned succesfully");

        return ResponseEntity.ok(getResponseStudents);
    }

    @Operation(summary = "Get student",
            description = "Return one student. Only users with ADMIN, TEACHER or STUDENT roles.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student returned succesfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        StudentResponseDTO student = studentService.getStudentById(id);

        ResponseDTO<StudentResponseDTO> getResponseStudent = new ResponseDTO<>(student, 200, "Student returned succesfully");

        return ResponseEntity.ok(getResponseStudent);
    }

    @Operation(summary = "Create a student",
            description = "Return the created student. Only the users with ADMIN role can create new students.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created succesfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, fields validation error"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentRequestDTO studentDTO){
        studentService.saveStudent(studentDTO);

        ResponseDTO<StudentRequestDTO> saveStudentResponse = new ResponseDTO<>(studentDTO, 201, "Student created succesfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(saveStudentResponse);
    }

    @Operation(summary = "Edit a student",
            description = "Return the edited student. Only the users with ADMIN role can edit students.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student edited succesfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, fields validation error"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody StudentRequestDTO studentDTO, @PathVariable Long id){
        studentService.updateStudent(studentDTO, id);

        ResponseDTO<StudentRequestDTO> updateStudentResponse = new ResponseDTO<>(studentDTO, 200, "Student updated succesfully");

        return ResponseEntity.status(HttpStatus.OK).body(updateStudentResponse);
    }

    @Operation(summary = "Delete a student",
            description = "Return a confirmation message. Only the users with ADMIN role can delete students.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student deleted succesfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);

        return ResponseEntity.status(HttpStatus.OK).body("Student deleted");
    }
}
