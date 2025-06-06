package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.*;
import com.mateo.plataforma_educativa.service.ITeacherService;
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

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<?> getTeachers(){
        List<TeacherGetDTO> teachersList = teacherService.getTeachers();

        ResponseDTO<List<TeacherGetDTO>> getResponseTeachers = new ResponseDTO<>(teachersList, 200, "Teachers returned succesfully");

        return ResponseEntity.ok(getResponseTeachers);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id){

        TeacherGetDTO teacher = teacherService.getTeacherById(id);

        ResponseDTO<TeacherGetDTO> getResponseTeacher = new ResponseDTO<>(teacher, 200, "Teacher returned succesfully");

        return ResponseEntity.ok(getResponseTeacher);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createTeacher(@Valid @RequestBody TeacherSaveDTO teacherDTO){
        teacherService.saveTeacher(teacherDTO);

        ResponseDTO<TeacherSaveDTO> saveStudentResponse = new ResponseDTO<>(teacherDTO, 201, "Teacher created succesfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(saveStudentResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTeacher(@Valid @RequestBody TeacherUpdateDTO teacherDTO, @PathVariable Long id){
       teacherService.updateTeacher(teacherDTO, id);

        ResponseDTO<TeacherUpdateDTO> updateStudentResponse = new ResponseDTO<>(teacherDTO, 200, "Teacher updated succesfully");

        return ResponseEntity.status(HttpStatus.OK).body(updateStudentResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id){
        teacherService.deleteTeacher(id);

        return ResponseEntity.status(HttpStatus.OK).body("Teacher deleted");
    }
}
