package com.example.crud_spring.controllers;

import com.example.crud_spring.StudentMapper;
import com.example.crud_spring.models.Student;
import com.example.crud_spring.RequestStudentDTO;
import com.example.crud_spring.ResponseStudentDTO;
import com.example.crud_spring.models.User;
import com.example.crud_spring.service.StudentServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final StudentServiceImpl studentService;


    public HomeController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/home")
    public String home(){
        return "welcome";
    }

    @GetMapping("/students")
    public String home(Model model) {
        List<ResponseStudentDTO> students = studentService.getAllStudents().stream()
                .map(student -> new ModelMapper().map(student, ResponseStudentDTO.class))
                .collect(Collectors.toList());
        model.addAttribute("students", students);
        model.addAttribute("newStudent", new RequestStudentDTO());
        User currentUser = studentService.getCurrentUser();
        model.addAttribute("user", currentUser);// Додаємо новий студент для форми
        return "home";
    }

    @PostMapping("/students")
    public String createStudent(@ModelAttribute("newStudent") @Valid RequestStudentDTO studentDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("students", studentService.getAllStudents());
            return "home";
        }
        Student student = new ModelMapper().map(studentDTO, Student.class);  // Перетворення DTO в сутність
        studentService.addStudent(student);
        return "redirect:/students";   // Перезавантажуємо сторінку
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isEmpty()) {
            return "redirect:/students";
        }
        model.addAttribute("student", student.get());
        return "editStudent";
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") @Valid RequestStudentDTO studentDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "editStudent";
        }
        Student student = new ModelMapper().map(studentDTO, Student.class);
        student.setId(id);
        studentService.updateStudent(student);
        redirectAttributes.addFlashAttribute("success", "Студента успішно оновлено!");
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("success", "Студента успішно видалено!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Студент не знайдений або не можна видалити.");
        }
        return "redirect:/students";
    }
}
