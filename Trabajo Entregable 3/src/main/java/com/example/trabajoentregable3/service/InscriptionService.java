package com.example.trabajoentregable3.service;


import com.example.trabajoentregable3.dto.DTOCareer;
import com.example.trabajoentregable3.dto.DTOInscription;
import com.example.trabajoentregable3.dto.DTORequestCareer;
import com.example.trabajoentregable3.dto.DTORequestInscription;
import com.example.trabajoentregable3.entity.Career;
import com.example.trabajoentregable3.entity.Inscription;
import com.example.trabajoentregable3.repository.CareerRepository;
import com.example.trabajoentregable3.repository.InscriptionRepository;
import com.example.trabajoentregable3.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
@RequiredArgsConstructor
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;
    private final StudentRepository studentRepository;
    private final CareerRepository careerRepository;
    public List<DTOInscription> findAll() {

        return this.inscriptionRepository
                .findAll()
                .stream()
                .map(i -> new DTOInscription(i.getFecha_ingreso(),
                        i.getFecha_egreso(),
                        (int) i.getStudent().getUniversityNotebook(),
                        (int) i.getCareer().getId()
                ))
                .toList();
    }

    @Transactional
    public ResponseEntity save(DTORequestInscription instription ) {
        if (studentRepository.existsById(instription.getStudent_notebook_number())) {
            if (careerRepository.existsById(instription.getCareer_id())) {
                if (!inscriptionRepository.existsByStudentIdAndCareerId(instription.getStudent_notebook_number(), instription.getCareer_id())) {
                   System.out.println("ALUMNO NO ESTA INSCRIPTO, SE AGREGA");
                    Inscription i = this.inscriptionRepository.save(new Inscription(careerRepository.getReferenceById(instription.getCareer_id()), studentRepository.getReferenceById(instription.getStudent_notebook_number()), instription.getFecha_ingreso(), instription.getFecha_egreso()));
                    DTOInscription DTOi = new DTOInscription(i.getFecha_ingreso(), i.getFecha_egreso(), (int) i.getStudent().getUniversityNotebook(), (int) i.getCareer().getId());
                    return new ResponseEntity(DTOi, HttpStatus.CREATED);
                }else{
                    System.out.println("ALUMNO YA ESTA INSCRIPTO, NO SE AGREGA");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            //throw new ConflictExistException("Student","ID", (long) instription.getStudent_notebook_number());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
