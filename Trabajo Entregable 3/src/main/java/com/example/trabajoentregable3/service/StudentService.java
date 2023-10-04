package com.example.trabajoentregable3.service;

import com.example.trabajoentregable3.entity.Student;
import com.example.trabajoentregable3.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

}
