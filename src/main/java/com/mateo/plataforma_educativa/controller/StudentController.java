package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.*;
import com.mateo.plataforma_educativa.model.Student;
import com.mateo.plataforma_educativa.service.IStudentService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getStudents(){
        List<StudentGetDTO> studentsList = studentService.getStudents();

        ResponseDTO<List<StudentGetDTO>> getResponseStudents = new ResponseDTO<>(studentsList, 200, "Students returned succesfully");

        return ResponseEntity.ok(getResponseStudents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        StudentGetDTO student = studentService.getStudentById(id);

        ResponseDTO<StudentGetDTO> getResponseStudent = new ResponseDTO<>(student, 200, "Student returned succesfully");

        return ResponseEntity.ok(getResponseStudent);
    }

    @PostMapping("/")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentSaveDTO studentDTO){
        studentService.saveStudent(studentDTO);

        ResponseDTO<StudentSaveDTO> saveStudentResponse = new ResponseDTO<>(studentDTO, 201, "Student created succesfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(saveStudentResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody StudentUpdateDTO studentDTO, @PathVariable Long id){
        studentService.updateStudent(studentDTO, id);

        ResponseDTO<StudentUpdateDTO> updateStudentResponse = new ResponseDTO<>(studentDTO, 200, "Student updated succesfully");

        return ResponseEntity.status(HttpStatus.OK).body(updateStudentResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);

        return ResponseEntity.status(HttpStatus.OK).body("Student deleted");
    }
}
