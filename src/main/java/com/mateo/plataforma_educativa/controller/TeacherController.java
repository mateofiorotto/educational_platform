package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.*;
import com.mateo.plataforma_educativa.service.ITeacherService;
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
@RequestMapping("/teachers")
@PreAuthorize("denyAll()")
public class TeacherController {

    private final ITeacherService teacherService;

    public TeacherController(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Operation(summary = "Get teachers",
            description = "Return the list of teachers. Only users with ADMIN or STUDENT roles.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teachers returned succesfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)"),
    })
    @GetMapping("/")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<?> getTeachers(){
        List<TeacherResponseDTO> teachersList = teacherService.getTeachers();

        ResponseDTO<List<TeacherResponseDTO>> getResponseTeachers = new ResponseDTO<>(teachersList, 200, "Teachers returned succesfully");

        return ResponseEntity.ok(getResponseTeachers);
    }

    @Operation(summary = "Get teacher",
            description = "Return one teacher. Only users with ADMIN or STUDENT roles.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher returned succesfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "404", description = "Teacher not found"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id){

        TeacherResponseDTO teacher = teacherService.getTeacherById(id);

        ResponseDTO<TeacherResponseDTO> getResponseTeacher = new ResponseDTO<>(teacher, 200, "Teacher returned succesfully");

        return ResponseEntity.ok(getResponseTeacher);
    }

    @Operation(summary = "Create a teacher",
            description = "Return the created teacher. Only the users with ADMIN role can create new teachers.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teacher created succesfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, fields validation error"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createTeacher(@Valid @RequestBody TeacherRequestDTO teacherDTO){
        teacherService.saveTeacher(teacherDTO);

        ResponseDTO<TeacherRequestDTO> saveStudentResponse = new ResponseDTO<>(teacherDTO, 201, "Teacher created succesfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(saveStudentResponse);
    }

    @Operation(summary = "Edit a teacher",
            description = "Return the edited teacher. Only the users with ADMIN role can edit teachers.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher edited succesfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, fields validation error"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "404", description = "Teacher not found"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTeacher(@Valid @RequestBody TeacherRequestDTO teacherDTO, @PathVariable Long id){
       teacherService.updateTeacher(teacherDTO, id);

        ResponseDTO<TeacherRequestDTO> updateStudentResponse = new ResponseDTO<>(teacherDTO, 200, "Teacher updated succesfully");

        return ResponseEntity.status(HttpStatus.OK).body(updateStudentResponse);
    }

    @Operation(summary = "Delete a teacher",
            description = "Return a confirmation message. Only the users with ADMIN role can delete teachers.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teacher deleted succesfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "404", description = "Teacher not found"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id){
        teacherService.deleteTeacher(id);

        return ResponseEntity.status(HttpStatus.OK).body("Teacher deleted");
    }
}
