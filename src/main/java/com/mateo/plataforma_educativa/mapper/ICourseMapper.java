package com.mateo.plataforma_educativa.mapper;

import com.mateo.plataforma_educativa.dto.CourseResponseDTO;
import com.mateo.plataforma_educativa.dto.CourseRequestDTO;
import com.mateo.plataforma_educativa.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICourseMapper {

    ICourseMapper mapper = Mappers.getMapper(ICourseMapper.class);

    CourseResponseDTO courseToCourseGetDTO(Course course);
    Course courseGetDTOToCourse(CourseResponseDTO courseGetDTO);

    CourseRequestDTO courseToCourseSaveDTO(Course course);
    Course courseSaveDTOToCourse(CourseRequestDTO courseSaveDTO);
}
