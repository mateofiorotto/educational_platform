package com.mateo.plataforma_educativa.mapper;

import com.mateo.plataforma_educativa.dto.StudentGetDTO;
import com.mateo.plataforma_educativa.dto.StudentSaveDTO;
import com.mateo.plataforma_educativa.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IStudentMapper {

    IStudentMapper mapper = Mappers.getMapper(IStudentMapper.class);

    //For get
    StudentGetDTO studentToStudentGetDTO(Student student);
    Student studentGetDTOToStudent(StudentGetDTO studentGetDTO);

    //For post
    StudentSaveDTO studentToStudentSaveDTO(Student student);
    Student studentSaveDTOToStudent(StudentSaveDTO studentSaveDTO);
}
