package com.example.crud_spring.service;

import com.example.crud_spring.models.Student;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    void addStudent(@Valid Student student);
    void updateStudent(@Valid Student student);
    void deleteStudent(Long id);
}
