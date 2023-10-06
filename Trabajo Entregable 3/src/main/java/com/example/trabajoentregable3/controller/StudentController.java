package com.example.trabajoentregable3.controller;

import com.example.trabajoentregable3.dto.DTOStudent;
import com.example.trabajoentregable3.entity.Student;
import com.example.trabajoentregable3.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    //TODO: Usar DTOStudent en vez de Entity Student
    @GetMapping("")
    public List<DTOStudent> findAll() {
        return this.studentService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Student> save(@RequestBody Student request ){
        return ResponseEntity.accepted().body( this.studentService.save( request ) );
    }

}
