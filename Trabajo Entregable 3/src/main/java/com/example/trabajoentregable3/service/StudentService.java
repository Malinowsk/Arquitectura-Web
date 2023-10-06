package com.example.trabajoentregable3.service;

import com.example.trabajoentregable3.dto.DTOCareer;
import com.example.trabajoentregable3.dto.DTONumberRegisteredPerCareer;
import com.example.trabajoentregable3.dto.DTORequestCareer;
import com.example.trabajoentregable3.dto.DTOStudent;
import com.example.trabajoentregable3.entity.Career;
import com.example.trabajoentregable3.entity.Student;
import com.example.trabajoentregable3.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.trabajoentregable3.service.exception.NotFoundException;

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

    public DTOStudent findById(int id) {
        return this.studentRepository.findById((long) id)
                .map(this::buildDTOStudent)
                .orElseThrow(() -> new NotFoundException("Student", (long) id));
    }

    public List<DTOStudent> findByGender(String gender) {
        return this.studentRepository
                .getStudentsByGender(gender)
                .stream()
                .map(this::buildDTOStudent)
                .toList();

    }

    @Transactional
    public ResponseEntity<DTOStudent> save(Student request) {
        Student s = this.studentRepository.save(new Student(request.getDocumentNumber(), request.getName(), request.getSurname(), request.getBirthdate(), request.getGender(), request.getCity()));
        DTOStudent DTOs = new DTOStudent((int)s.getUniversityNotebook(), s.getDocumentNumber(), s.getName(), s.getSurname(), s.getGender(), s.getCity(),s.getBirthdate());
        return new ResponseEntity<>(DTOs, HttpStatus.CREATED);
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