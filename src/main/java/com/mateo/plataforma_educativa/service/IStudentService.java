package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.StudentGetDTO;
import com.mateo.plataforma_educativa.dto.StudentSaveDTO;
import com.mateo.plataforma_educativa.dto.StudentUpdateDTO;

import java.util.List;

public interface IStudentService {


    /**
     * Returns a list of all students
     *
     * @return list of students
     * */
    public List<StudentGetDTO> getStudents();

    /**
     * Returns a specific student
     *
     * @param id
     * @return a student
     * */
    public StudentGetDTO getStudentById(Long id);

    /**
     * Create a student
     *
     * @param studentDTO
     * @return the created student info
     * */
    public StudentSaveDTO saveStudent(StudentSaveDTO studentDTO);

    /**
     * Update a student by id
     *
     * @param studentDTO
     * @param id
     * @return the updated student
     * */
    public StudentUpdateDTO updateStudent(StudentUpdateDTO studentDTO, Long id);

    /**
     * Delete a student
     * @param id
     * */
    public void deleteStudent(Long id);
}
