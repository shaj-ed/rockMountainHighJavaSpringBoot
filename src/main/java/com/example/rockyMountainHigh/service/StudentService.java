package com.example.rockyMountainHigh.service;

import com.example.rockyMountainHigh.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    StudentEntity createStudent(StudentEntity student, MultipartFile avatar) throws IOException;

    Page<StudentEntity> getAllStudents(Pageable pageable);

    List<StudentEntity> getAllStudents();

    Optional<StudentEntity> getStudentById(Long id);

    StudentEntity updateStudent(Long id, StudentEntity student);

    void deleteStudentById(Long id);
}
