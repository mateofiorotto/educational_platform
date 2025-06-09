package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.CourseIdDTO;
import com.mateo.plataforma_educativa.dto.StudentRequestDTO;
import com.mateo.plataforma_educativa.dto.StudentResponseDTO;
import com.mateo.plataforma_educativa.exception.BadRequestException;
import com.mateo.plataforma_educativa.exception.NotFoundException;
import com.mateo.plataforma_educativa.mapper.IStudentMapper;
import com.mateo.plataforma_educativa.model.Course;
import com.mateo.plataforma_educativa.model.Student;
import com.mateo.plataforma_educativa.repository.ICourseRepository;
import com.mateo.plataforma_educativa.repository.IStudentRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService implements IStudentService {

    private final ICourseRepository courseRepository;
    private final IStudentRepository studentRepository;

    public StudentService(ICourseRepository courseRepository, IStudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentResponseDTO> getStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream().map(student -> IStudentMapper.mapper.studentToStudentGetDTO(student)).collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found with ID: " + id));

        return IStudentMapper.mapper.studentToStudentGetDTO(student);
    }

    @Override
    public StudentRequestDTO saveStudent(StudentRequestDTO studentDTO) {
        Student studentToSave = IStudentMapper.mapper.studentSaveDTOToStudent(studentDTO);

        //Create a new SET (list)
        Set<Course> courses = new HashSet<>();

        for (CourseIdDTO c : studentDTO.getCourses()) {
            //search and add if exists the student
            Course existing = courseRepository.findById(c.getId()).orElseThrow(() -> new BadRequestException("Course not found"));

            // bidirectional -> add courses to setCourse of student, the student table manage the N:N table
            existing.getStudents().add(studentToSave);
            courses.add(existing);
        }
        studentToSave.setCourses(courses);

        studentRepository.save(studentToSave);

        return studentDTO;
    }

    @Override
    public StudentRequestDTO updateStudent(StudentRequestDTO studentDTO, Long id) {
        Student findedStudent = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found"));

        findedStudent.setName(studentDTO.getName());

        //Delete the current "links" with courses
        for (Course course : new HashSet<>(findedStudent.getCourses())) {
            course.getStudents().remove(findedStudent);
        }
        findedStudent.getCourses().clear();

        //add the new links
        Set<Course> updatedCourses = new HashSet<>();
        for (CourseIdDTO c : studentDTO.getCourses()) {
            Course existing = courseRepository.findById(c.getId())
                    .orElseThrow(() -> new BadRequestException("Course not found"));

            existing.getStudents().add(findedStudent);
            updatedCourses.add(existing);
        }

        findedStudent.setCourses(updatedCourses);

        studentRepository.save(findedStudent);

        return studentDTO;
    }

    @Override
    public void deleteStudent(Long id) {
        Student studentToDelete = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        for (Course c : studentToDelete.getCourses()) {
            c.getStudents().remove(studentToDelete);
        }

        studentToDelete.getCourses().clear();

        studentRepository.delete(studentToDelete);

    }
}
