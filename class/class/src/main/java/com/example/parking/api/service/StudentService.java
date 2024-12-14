package com.example.parking.api.service;

import com.example.parking.api.model.Student;

public interface StudentService {
    Student saveStudent(Student student);

    StudentService getAllStudents(Long id);

    Student getUserById(Long id);

    void deleteById(long id);
}
