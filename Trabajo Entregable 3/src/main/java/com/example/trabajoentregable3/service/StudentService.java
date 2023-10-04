package com.example.trabajoentregable3.service;

import com.example.trabajoentregable3.dto.DTOStudent;
import com.example.trabajoentregable3.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
