package com.example.parking.api.service;

import com.example.parking.api.model.Student;
import com.example.parking.api.repo.StudentRepository;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public StudentService getAllStudents(Long id) {
        return (StudentService) studentRepository.findAll();
    }

    @Override
    public Student getUserById(Long id) {
        return studentRepository.findById(id).orElseThrow(); //todo throw an exception if u want
    }



    @Override
    public void deleteById(long id) {
        Student student = getUserById(id);
        studentRepository.delete(student);
    }
}