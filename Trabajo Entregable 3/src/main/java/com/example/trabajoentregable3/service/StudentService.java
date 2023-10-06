package com.example.trabajoentregable3.service;

import com.example.trabajoentregable3.dto.DTOStudent;
import com.example.trabajoentregable3.entity.Student;
import com.example.trabajoentregable3.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<DTOStudent> findAll() {
        return this.studentRepository
                .findAll()
                .stream()
                .map(this::buildDTOStudent)
                .toList();
    }

    public List<DTOStudent> findAllOrderBySurname() {
        return this.studentRepository
                .findAllByOrderBySurnameAsc()
                .stream()
                .map(this::buildDTOStudent)
                .toList();
    }

    public List<DTOStudent> findByGender(String gender) {
        return this.studentRepository
                .getStudentsByGender(gender)
                .stream()
                .map(this::buildDTOStudent)
                .toList();

    }

    @Transactional
    public Student save(Student request) {
        return studentRepository.save(new Student(request.getDocumentNumber(), request.getName(), request.getSurname(), request.getBirthdate(), request.getGender(), request.getCity()));
    }

    private DTOStudent buildDTOStudent(Student s) {
        return new DTOStudent(
                (int) s.getUniversityNotebook(),
                s.getDocumentNumber(),
                s.getName(),
                s.getSurname(),
                s.getGender(),
                s.getCity(),
                s.getBirthdate()
        );
    }

}