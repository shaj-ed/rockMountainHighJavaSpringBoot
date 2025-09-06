package com.example.rockyMountainHigh.controller;

import com.example.rockyMountainHigh.entity.StudentEntity;
import com.example.rockyMountainHigh.exception.ResourceNotFoundException;
import com.example.rockyMountainHigh.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentEntity> createStudent(@Valid @RequestBody StudentEntity student) {
        StudentEntity savedStudent = studentService.createStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<StudentEntity>> getAllStudents(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentEntity> students = studentService.getAllStudents(pageable);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        List<StudentEntity> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Long id) {
        StudentEntity student = studentService.getStudentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable  Long id, @Valid @RequestBody StudentEntity updatedStudent) {
        StudentEntity student = studentService.updateStudent(id, updatedStudent);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>(Map.of("message", "Deleted Successfully"), HttpStatus.OK);
    }

}
