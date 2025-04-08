package com.example.crud_spring.controllers;

import com.example.crud_spring.models.Student;
import com.example.crud_spring.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private static final Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    private final StudentService studentService;

    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        logger.info("GET /api/students - Fetching all students");
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        logger.info("GET /api/students/{} - Fetching student by ID", id);
        return studentService.getStudentById(id)
                .map(student -> {
                    logger.info("Student found: {}", student.getName());
                    return ResponseEntity.ok(student);
                })
                .orElseGet(() -> {
                    logger.warn("Student with id {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
public ResponseEntity<String> addStudent(@RequestBody Student student) {
    logger.info("POST /api/students - Adding student: {}", student.getName());
    try {
        studentService.addStudent(student);
        return ResponseEntity.ok("Student added successfully");
    } catch (Exception e) {
        logger.error("Error while adding student: {}", e.getMessage(), e);
        return ResponseEntity.status(500).body("Failed to add student");
    }
}


@PutMapping("/{id}")
public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Student student) {
    logger.info("PUT /api/students/{} - Updating student: {}", id, student.getName());
    try {
        student.setId(id);
        studentService.updateStudent(student);
        return ResponseEntity.ok("Student updated successfully");
    } catch (IllegalArgumentException e) {
        logger.warn("Update failed: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
        logger.error("Unexpected error during update: {}", e.getMessage(), e);
        return ResponseEntity.status(500).body("Failed to update student");
    }
}


@DeleteMapping("/{id}")
public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
    logger.info("DELETE /api/students/{} - Deleting student", id);
    try {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    } catch (Exception e) {
        logger.error("Error while deleting student with id {}: {}", id, e.getMessage(), e);
        return ResponseEntity.status(500).body("Failed to delete student");
    }
}


}
