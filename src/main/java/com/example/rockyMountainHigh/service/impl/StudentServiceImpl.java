package com.example.rockyMountainHigh.service.impl;

import com.example.rockyMountainHigh.entity.StudentEntity;
import com.example.rockyMountainHigh.exception.DuplicateResourceException;
import com.example.rockyMountainHigh.exception.ResourceNotFoundException;
import com.example.rockyMountainHigh.repository.StudentRepository;
import com.example.rockyMountainHigh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentEntity createStudent(StudentEntity student) {
        if(studentRepository.existsByEmail((student.getEmail()))) {
            throw new DuplicateResourceException("Email already exist: " + student.getEmail());
        }
        return studentRepository.save(student);
    }

    @Override
    public Page<StudentEntity> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<StudentEntity> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public StudentEntity updateStudent(Long id, StudentEntity updatedStudent) {
        StudentEntity existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        if(!existingStudent.getEmail().equals(updatedStudent.getEmail()) && studentRepository.existsByEmail((existingStudent.getEmail()))) {
            throw new DuplicateResourceException("Email already exist: " + existingStudent.getEmail());
        }

        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setAge(updatedStudent.getAge());
        existingStudent.setCourse(updatedStudent.getCourse());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudentById(Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.delete(student);
    }
}
