package com.example.parking.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "CLASS")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CLASS_NAME")
    private String name;

    @Column(name = "TEACHER_NAME")
    private String teacherName;

    @ManyToMany(mappedBy = "classes")
    private Set<Student> Students = new HashSet<>();
}
