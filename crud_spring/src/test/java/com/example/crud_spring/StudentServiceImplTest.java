// package com.example.crud_spring;

// import com.example.crud_spring.models.Student;
// import com.example.crud_spring.repository.StudentRepository;
// import com.example.crud_spring.service.StudentServiceImpl;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.exceptions.base.MockitoException;
// import org.mockito.junit.jupiter.MockitoExtension;

// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// class StudentServiceImplTest {

//     @Mock
//     private StudentRepository studentRepository;

//     @InjectMocks
//     private StudentServiceImpl studentService;

//     private Student student;

//     @BeforeEach
//     void setUp() {
//         student = new Student(1L, "Alina", 19);
//     }

//     @Test
//     void testGetAllStudents() {
//         when(studentRepository.findAll()).thenReturn(List.of(student));
//         List<Student> students = studentService.getAllStudents();
//         assertFalse(students.isEmpty());
//         assertEquals(1, students.size());
//         verify(studentRepository, times(1)).findAll();
//     }

//     @Test
//     void testGetStudentById() {
//         when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
//         Optional<Student> foundStudent = studentService.getStudentById(1L);
//         assertTrue(foundStudent.isPresent());
//         assertEquals("Alina", foundStudent.get().getName());
//         verify(studentRepository, times(1)).findById(1L);
//     }

//     @Test
//     void testAddStudent() {
//         studentService.addStudent(student);
//         verify(studentRepository, times(1)).add(student);
//     }

//     @Test
//     void testUpdateStudent() {
//         studentService.updateStudent(student);
//         verify(studentRepository, times(1)).update(student);
//     }

//     @Test
//     void testDeleteStudent() {
//         studentService.deleteStudent(1L);
//         verify(studentRepository, times(1)).deleteById(1L);
//     }
// }
