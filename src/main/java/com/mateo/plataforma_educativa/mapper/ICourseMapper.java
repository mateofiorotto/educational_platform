package com.mateo.plataforma_educativa.mapper;

import com.mateo.plataforma_educativa.dto.CourseGetDTO;
import com.mateo.plataforma_educativa.dto.CourseSaveDTO;
import com.mateo.plataforma_educativa.dto.CourseUpdateDTO;
import com.mateo.plataforma_educativa.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICourseMapper {

    ICourseMapper mapper = Mappers.getMapper(ICourseMapper.class);

    //For get
    CourseGetDTO courseToCourseGetDTO(Course course);
    Course courseGetDTOToCourse(CourseGetDTO courseGetDTO);

    //For post
    CourseSaveDTO courseToCourseSaveDTO(Course course);
    Course courseSaveDTOToCourse(CourseSaveDTO courseSaveDTO);
}
