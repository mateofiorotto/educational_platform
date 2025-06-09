package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.TeacherResponseDTO;
import com.mateo.plataforma_educativa.dto.TeacherRequestDTO;

import java.util.List;

public interface ITeacherService {
    /**
     * Returns a list of all teachers
     *
     * @return list of teachers
     * */
    public List<TeacherResponseDTO> getTeachers();

    /**
     * Returns a specific teacher
     *
     * @param id
     * @return a teacher
     * */
    public TeacherResponseDTO getTeacherById(Long id);

    /**
     * Create a teacher
     *
     * @param teacherDTO
     * @return the created teacher info
     * */
    public TeacherRequestDTO saveTeacher(TeacherRequestDTO teacherDTO);

    /**
     * Update a teacher by id
     *
     * @param teacherDTO
     * @param id
     * @return the updated teacher
     * */
    public TeacherRequestDTO updateTeacher(TeacherRequestDTO teacherDTO, Long id);

    /**
     * Delete a teacher
     * @param id
     * */
    public void deleteTeacher(Long id);
}
