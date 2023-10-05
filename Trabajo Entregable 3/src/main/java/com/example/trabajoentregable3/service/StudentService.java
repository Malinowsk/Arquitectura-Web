package com.example.trabajoentregable3.service;

import com.example.trabajoentregable3.dto.DTOStudent;
import com.example.trabajoentregable3.entity.Student;
import com.example.trabajoentregable3.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<DTOStudent> findAll() {
        return this.studentRepository
                .findAll()
                .stream()
                .map(s -> new DTOStudent(
                        (int) s.getUniversityNotebook(),
                        s.getDocumentNumber(),
                        s.getName(),
                        s.getSurname(),
                        s.getGender(),
                        s.getCity(),
                        s.getBirthdate()))
                .toList();
    }

    @Transactional
    public Student save(Student request) {
        return studentRepository.save(new Student(request.getDocumentNumber(), request.getName(), request.getSurname(), request.getBirthdate(), request.getGender(), request.getCity()));
    }

}