package com.example.crud_spring;

import com.example.crud_spring.models.Student;
import com.example.crud_spring.repository.StudentRepository;
import com.example.crud_spring.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class StudentServiceIT {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setName("Alina");
        student.setAge(19);
    }

    @Test
    public void testAddAndGetStudent() {
        studentService.addStudent(student);

        Student foundStudent = studentRepository.findById(student.getId()).orElse(null);
        assertNotNull(foundStudent, "Student should be found in the database");
        assertEquals("Alina", foundStudent.getName(), "Student's name should match");
        assertEquals(19, foundStudent.getAge(), "Student's age should match");
    }
}
