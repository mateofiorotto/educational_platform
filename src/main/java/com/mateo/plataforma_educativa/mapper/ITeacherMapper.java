package com.mateo.plataforma_educativa.mapper;

import com.mateo.plataforma_educativa.dto.TeacherRequestDTO;
import com.mateo.plataforma_educativa.dto.TeacherResponseDTO;
import com.mateo.plataforma_educativa.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ITeacherMapper {

    ITeacherMapper mapper = Mappers.getMapper(ITeacherMapper.class);

    //For get
    TeacherResponseDTO teacherToTeacherGetDTO(Teacher teacher);
    Teacher teacherGetDTOToTeacher(TeacherResponseDTO teacherGetDTO);

    //For post
    TeacherRequestDTO teacherToTeacherSaveDTO(Teacher teacher);
    Teacher teacherSaveDTOToTeacher(TeacherRequestDTO teacherSaveDTO);
}
