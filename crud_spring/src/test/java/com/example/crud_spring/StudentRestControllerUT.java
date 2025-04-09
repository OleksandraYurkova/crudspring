package com.example.crud_spring;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.crud_spring.models.Student;
import com.example.crud_spring.service.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class StudentRestControllerUT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student(1L, "Alina", 19);
    }

    @Test
    void testGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(List.of(student));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Alina")))
                .andExpect(jsonPath("$[0].age", is(19)));

        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void testGetStudentById() throws Exception {
        when(studentService.getStudentById(1L)).thenReturn(Optional.of(student));

        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Alina")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", Matchers.is(19)));

        verify(studentService, times(1)).getStudentById(1L);
    }

    @Test
    void testAddStudent() throws Exception {
        doNothing().when(studentService).addStudent(Mockito.any(Student.class));

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk()); // змінено з isCreated() на isOk()

        verify(studentService, times(1)).addStudent(Mockito.any(Student.class));
    }


    @Test
    void testUpdateStudent() throws Exception {
        doNothing().when(studentService).updateStudent(Mockito.any(Student.class));
        mockMvc.perform(put("/api/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk());

        verify(studentService, times(1)).updateStudent(Mockito.any(Student.class));
    }

    @Test
    void testDeleteStudent() throws Exception {
        doNothing().when(studentService).deleteStudent(1L);

        mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isNoContent());

        verify(studentService, times(1)).deleteStudent(1L);
    }

    @Test
    void testGetStudentById_NotFound() throws Exception {
        when(studentService.getStudentById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/students/2"))
                .andExpect(status().isNotFound());
    }
}
