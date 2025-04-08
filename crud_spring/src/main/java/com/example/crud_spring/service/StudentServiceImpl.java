package com.example.crud_spring.service;

import com.example.crud_spring.models.Role;
import com.example.crud_spring.models.Student;
import com.example.crud_spring.models.User;
import com.example.crud_spring.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        log.info("Admission of all students");
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        log.info("Getting a student from ID: {}", id);
        return studentRepository.findById(id);
    }

    @Override
    public void addStudent(@Valid Student student) {
        log.info("Adding a new student: {}", student);
        studentRepository.add(student);
    }

    @Override
    public void updateStudent(@Valid Student student) {
        log.info("Student update: {}", student);
        studentRepository.update(student);
    }

    @Override
    public void deleteStudent(Long id) {
        log.warn("Removing a student from ID: {}", id);
        studentRepository.deleteById(id);
    }
    public User getCurrentUser() {
        // Імітуємо авторизованого користувача
        return new User(1L, "Admin1", Set.of(new Role(1L, "Admin")));
    }
}