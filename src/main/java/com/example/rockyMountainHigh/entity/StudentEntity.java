package com.example.rockyMountainHigh.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generated ID values
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 10, message = "First name must be between 2 and 10 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 10, message = "Last name must be between 2 and 10 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull(message = "Age is required")
    @Min(value = 16, message = "Student must be at least 16 years old")
    @Max(value = 90, message = "We don't accept old dudes!")
    private Integer age;

    @NotBlank(message = "Course is required")
    @Column(name = "course")
    private String course;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    // Default constructor required by JPA
    public StudentEntity() {
        this.enrollmentDate = LocalDate.now();
    }

    // Constructor with parameters for creating new student
    public StudentEntity(String firstName, String lastName, String email, Integer age, String course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.course = course;
        this.enrollmentDate = LocalDate.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
