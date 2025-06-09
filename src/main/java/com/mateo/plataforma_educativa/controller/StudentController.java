package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.*;
import com.mateo.plataforma_educativa.service.IStudentService;
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

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<?> getStudents(){
        List<StudentResponseDTO> studentsList = studentService.getStudents();

        ResponseDTO<List<StudentResponseDTO>> getResponseStudents = new ResponseDTO<>(studentsList, 200, "Students returned succesfully");

        return ResponseEntity.ok(getResponseStudents);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'ADMIN')")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        StudentResponseDTO student = studentService.getStudentById(id);

        ResponseDTO<StudentResponseDTO> getResponseStudent = new ResponseDTO<>(student, 200, "Student returned succesfully");

        return ResponseEntity.ok(getResponseStudent);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentRequestDTO studentDTO){
        studentService.saveStudent(studentDTO);

        ResponseDTO<StudentRequestDTO> saveStudentResponse = new ResponseDTO<>(studentDTO, 201, "Student created succesfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(saveStudentResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody StudentRequestDTO studentDTO, @PathVariable Long id){
        studentService.updateStudent(studentDTO, id);

        ResponseDTO<StudentRequestDTO> updateStudentResponse = new ResponseDTO<>(studentDTO, 200, "Student updated succesfully");

        return ResponseEntity.status(HttpStatus.OK).body(updateStudentResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);

        return ResponseEntity.status(HttpStatus.OK).body("Student deleted");
    }
}
