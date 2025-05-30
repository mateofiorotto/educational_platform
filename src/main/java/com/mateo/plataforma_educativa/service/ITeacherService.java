package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.TeacherGetDTO;
import com.mateo.plataforma_educativa.dto.TeacherSaveDTO;
import com.mateo.plataforma_educativa.dto.TeacherUpdateDTO;

import java.util.List;

public interface ITeacherService {
    /**
     * Returns a list of all teachers
     *
     * @return list of teachers
     * */
    public List<TeacherGetDTO> getTeachers();

    /**
     * Returns a specific teacher
     *
     * @param id
     * @return a teacher
     * */
    public TeacherGetDTO getTeacherById(Long id);

    /**
     * Create a teacher
     *
     * @param teacherDTO
     * @return the created teacher info
     * */
    public TeacherSaveDTO saveTeacher(TeacherSaveDTO teacherDTO);

    /**
     * Update a teacher by id
     *
     * @param teacherDTO
     * @param id
     * @return the updated teacher
     * */
    public TeacherUpdateDTO updateTeacher(TeacherUpdateDTO teacherDTO, Long id);

    /**
     * Delete a teacher
     * @param id
     * */
    public void deleteTeacher(Long id);
}
