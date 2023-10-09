package com.example.trabajoentregable3.controller;

import com.example.trabajoentregable3.dto.DTORequestStudent;
import com.example.trabajoentregable3.dto.DTOResponseStudent;
import com.example.trabajoentregable3.entity.Student;
import com.example.trabajoentregable3.service.StudentService;
import jakarta.validation.Valid;
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
    public List<DTOResponseStudent> findAll() {
        return this.studentService.findAll();
    }

    @GetMapping("/sortBySurname")
    public List<DTOResponseStudent> findAllOrderBySurname() {
        return this.studentService.findAllOrderBySurname();
    }

    @GetMapping("/{id}")
    public DTOResponseStudent findById(@PathVariable int id) {
        return this.studentService.findById(id);
    }

    @GetMapping("/gender/{g}")
    public List<DTOResponseStudent> findByGender(@PathVariable String g) {
        return this.studentService.findByGender(g);
    }

    @GetMapping("/findByCareerAndCity/{careerId}/{city}")
    public ResponseEntity findByCareerAndCity(@PathVariable long careerId, @PathVariable String city) {
        return this.studentService.findByCareerAndCity(careerId, city);
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody @Valid DTORequestStudent request){
        return studentService.save(request);
    }

}
