package com.example.trabajoentregable3.controller;

import com.example.trabajoentregable3.dto.DTOStudent;
import com.example.trabajoentregable3.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    //TODO: Usar DTOStudent en vez de Entity Student
    @GetMapping("/")
    public List<DTOStudent> findAll() {
        return this.studentService.findAll();
    }

}
