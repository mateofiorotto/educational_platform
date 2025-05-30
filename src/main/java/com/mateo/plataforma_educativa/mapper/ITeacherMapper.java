package com.mateo.plataforma_educativa.mapper;

import com.mateo.plataforma_educativa.dto.TeacherGetDTO;
import com.mateo.plataforma_educativa.dto.TeacherSaveDTO;
import com.mateo.plataforma_educativa.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ITeacherMapper {

    ITeacherMapper mapper = Mappers.getMapper(ITeacherMapper.class);

    //For get
    TeacherGetDTO teacherToTeacherGetDTO(Teacher teacher);
    Teacher teacherGetDTOToTeacher(TeacherGetDTO teacherGetDTO);

    //For post
    TeacherSaveDTO teacherToTeacherSaveDTO(Teacher teacher);
    Teacher teacherSaveDTOToTeacher(TeacherSaveDTO teacherSaveDTO);
}
