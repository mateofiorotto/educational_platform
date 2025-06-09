package com.mateo.plataforma_educativa.mapper;

import com.mateo.plataforma_educativa.dto.StudentRequestDTO;
import com.mateo.plataforma_educativa.dto.StudentResponseDTO;
import com.mateo.plataforma_educativa.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IStudentMapper {

    IStudentMapper mapper = Mappers.getMapper(IStudentMapper.class);

    //For get
    StudentResponseDTO studentToStudentGetDTO(Student student);
    Student studentGetDTOToStudent(StudentResponseDTO studentGetDTO);

    //For post
    StudentRequestDTO studentToStudentSaveDTO(Student student);
    Student studentSaveDTOToStudent(StudentRequestDTO studentSaveDTO);
}
