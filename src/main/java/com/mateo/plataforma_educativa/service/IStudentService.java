package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.StudentResponseDTO;
import com.mateo.plataforma_educativa.dto.StudentRequestDTO;

import java.util.List;

public interface IStudentService {


    /**
     * Returns a list of all students
     *
     * @return list of students
     * */
    public List<StudentResponseDTO> getStudents();

    /**
     * Returns a specific student
     *
     * @param id
     * @return a student
     * */
    public StudentResponseDTO getStudentById(Long id);

    /**
     * Create a student
     *
     * @param studentDTO
     * @return the created student info
     * */
    public StudentRequestDTO saveStudent(StudentRequestDTO studentDTO);

    /**
     * Update a student by id
     *
     * @param studentDTO
     * @param id
     * @return the updated student
     * */
    public StudentRequestDTO updateStudent(StudentRequestDTO studentDTO, Long id);

    /**
     * Delete a student
     * @param id
     * */
    public void deleteStudent(Long id);
}
