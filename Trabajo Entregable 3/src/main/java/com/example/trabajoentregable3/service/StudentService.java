package com.example.trabajoentregable3.service;

import com.example.trabajoentregable3.dto.DTORequestStudent;
import com.example.trabajoentregable3.dto.DTOResponseStudent;
import com.example.trabajoentregable3.entity.Student;
import com.example.trabajoentregable3.repository.CareerRepository;
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
    private final CareerRepository careerRepository;

    public List<DTOResponseStudent> findAll() {
        return this.studentRepository
                .findAll()
                .stream()
                .map(this::buildDTOStudent)
                .toList();
    }

    public List<DTOResponseStudent> findAllOrderBySurname() {
        return this.studentRepository
                .findAllByOrderBySurnameAsc()
                .stream()
                .map(this::buildDTOStudent)
                .toList();
    }

    public DTOResponseStudent findById(int id) {
        return this.studentRepository.findById((long) id)
                .map(this::buildDTOStudent)
                .orElseThrow(() -> new NotFoundException("Student", (long) id));
    }

    public List<DTOResponseStudent> findByGender(String gender) {
        return this.studentRepository
                .getStudentsByGender(gender)
                .stream()
                .map(this::buildDTOStudent)
                .toList();

    }

    public ResponseEntity findByCareerAndCity(long careerId, String city) {
        if (careerRepository.existsById(careerId)) {
            List<DTOResponseStudent> s = this.studentRepository.getStudentByCityAndCareer(careerId, city).stream().map(this::buildDTOStudent).toList();
            return new ResponseEntity<>(s, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("No existe carrera que quiere inscribir",HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<DTOResponseStudent> save(DTORequestStudent request) {
        Student s = this.studentRepository.save(new Student(request.getDocumentNumber(), request.getName(), request.getSurname(), request.getBirthdate(), request.getGender(), request.getCity()));
        DTOResponseStudent DTOs = new DTOResponseStudent((int)s.getUniversityNotebook(), s.getDocumentNumber(), s.getName(), s.getSurname(), s.getGender(), s.getCity(),s.getBirthdate());
        return new ResponseEntity<>(DTOs, HttpStatus.CREATED);
    }


    private DTOResponseStudent buildDTOStudent(Student s) {
        return new DTOResponseStudent(
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