package com.example.parking.api.controller;

import com.example.parking.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @DeleteMapping("{id}")
    public Long deleteById(@PathVariable Long id) {
        return studentService.deleteById(id);
    }


    @GetMapping("{id}")
    public StudentService getAllStudents(@PathVariable Long id) {
        return studentService.getAllStudents(id);
    }
}
