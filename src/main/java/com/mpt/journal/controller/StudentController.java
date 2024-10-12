package com.mpt.journal.controller;

import com.mpt.journal.entity.StudentEntity;
import com.mpt.journal.model.StudentModel;
import com.mpt.journal.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String getAllStudents(@RequestParam(defaultValue = "") String name,
                                 @RequestParam(defaultValue = "") String lastName,
                                 @RequestParam(defaultValue = "") String firstName,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentEntity> studentsPage = studentService.findStudentsByFilter(name, lastName, firstName, pageable);
        model.addAttribute("students", studentsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentsPage.getTotalPages());
        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        model.addAttribute("firstName", firstName);
        return "studentList";
    }

    // Показать форму для добавления студента
    @GetMapping("/students/add")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new StudentEntity());
        return "studentForm";
    }

    // Добавление нового студента
    @PostMapping("/students/add")
    public String addStudent(@Valid @ModelAttribute("student") StudentEntity student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "studentForm";
        }
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditStudentForm(@PathVariable("id") int id, Model model) {
        StudentEntity student = studentService.getStudentById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "studentForm";
    }

    // Обновление существующего студента
    @PostMapping("/students/update")
    public String updateStudent(@Valid @ModelAttribute("student") StudentEntity student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "studentForm";
        }
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    // Физическое удаление студента
    @PostMapping("/students/delete")
    public String deleteStudent(@RequestParam int id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    // Множественное удаление студентов
    @PostMapping("/students/delete-multiple")
    public String deleteMultipleStudents(@RequestParam(value = "ids", required = false) List<Integer> ids) {
        if (ids != null && !ids.isEmpty()) {
            studentService.deleteMultipleStudents(ids); // Удаление по списку идентификаторов
        }
        return "redirect:/students";
    }

    // Логическое удаление студента
    @PostMapping("/students/soft-delete")
    public String softDeleteStudent(@RequestParam int id) {
        studentService.softDeleteStudent(id);
        return "redirect:/students";
    }
}
