package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.TeacherGetDTO;
import com.mateo.plataforma_educativa.dto.TeacherSaveDTO;
import com.mateo.plataforma_educativa.dto.TeacherUpdateDTO;
import com.mateo.plataforma_educativa.exception.NotFoundException;
import com.mateo.plataforma_educativa.mapper.ITeacherMapper;
import com.mateo.plataforma_educativa.model.Teacher;
import com.mateo.plataforma_educativa.repository.ITeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService implements ITeacherService {

    private final ITeacherRepository teacherRepository;


    public TeacherService(ITeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<TeacherGetDTO> getTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();

        return teachers.stream().map(teacher -> ITeacherMapper.mapper.teacherToTeacherGetDTO(teacher)).collect(Collectors.toList());
    }

    @Override
    public TeacherGetDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new NotFoundException("Teacher not found with ID: " + id));

        return ITeacherMapper.mapper.teacherToTeacherGetDTO(teacher);
    }

    @Override
    public TeacherSaveDTO saveTeacher(TeacherSaveDTO teacherDTO) {
        Teacher teacherToSave = ITeacherMapper.mapper.teacherSaveDTOToTeacher(teacherDTO);

        teacherToSave.setName(teacherDTO.getName());

        teacherRepository.save(teacherToSave);

        return teacherDTO;
    }

    @Override
    public TeacherUpdateDTO updateTeacher(TeacherUpdateDTO teacherDTO, Long id) {
        Teacher findedTeacher = teacherRepository.findById(id).orElseThrow(() -> new NotFoundException("Teacher not found"));

        findedTeacher.setName(teacherDTO.getName());

        teacherRepository.save(findedTeacher);

        return teacherDTO;
    }

    @Override
    public void deleteTeacher(Long id) {
        Teacher teacherToDelete = teacherRepository.findById(id).orElseThrow(() -> new NotFoundException("Teacher not found"));

        teacherRepository.delete(teacherToDelete);


    }
}
