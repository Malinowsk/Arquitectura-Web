package com.example.trabajoentregable3.service;


import com.example.trabajoentregable3.dto.DTOReport;
import com.example.trabajoentregable3.dto.DTOResponseInscription;
import com.example.trabajoentregable3.dto.DTORequestInscription;
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
    public List<DTOResponseInscription> findAll() {
        return this.inscriptionRepository
                .findAll()
                .stream()
                .map(i -> new DTOResponseInscription(i.getFecha_ingreso(),
                        i.getFecha_egreso(),
                        (int) i.getStudent().getUniversityNotebook(),
                        (int) i.getCareer().getId()
                ))
                .toList();
    }

    public List<DTOReport> generateReport() {
        return this.inscriptionRepository
                .getReport()
                .stream()
                .map(obj -> new DTOReport((String) obj[0], (Integer) obj[1], (Double) obj[2], (Double) obj[3]))
                .toList();
    }

    @Transactional
    public ResponseEntity save(DTORequestInscription instription ) {
        if (studentRepository.existsById(instription.getStudent_notebook_number())) {
            if (careerRepository.existsById(instription.getCareer_id())) {
                if (!inscriptionRepository.existsByStudentIdAndCareerId(instription.getStudent_notebook_number(), instription.getCareer_id())) {
                    if (instription.getFecha_egreso()!=null && instription.getFecha_ingreso().after(instription.getFecha_egreso())){
                        return new ResponseEntity<>("La fecha de egreso no puede ser anterior a la fecha de ingreso, hay una inconsistencia",HttpStatus.NOT_FOUND);
                    } else {
                        Inscription i = this.inscriptionRepository.save(new Inscription(careerRepository.getReferenceById(instription.getCareer_id()), studentRepository.getReferenceById(instription.getStudent_notebook_number()), instription.getFecha_ingreso(), instription.getFecha_egreso()));
                        DTOResponseInscription DTOi = new DTOResponseInscription(i.getFecha_ingreso(), i.getFecha_egreso(), (int) i.getStudent().getUniversityNotebook(), (int) i.getCareer().getId());
                        return new ResponseEntity(DTOi, HttpStatus.CREATED);
                    }
                }else{
                    return new ResponseEntity<>("El estudiante que quiere inscribir ya existe matriculado a la carrera",HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("No existe carrera que quiere inscribir",HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("No existe estudiante que quiere inscribir",HttpStatus.NOT_FOUND);
        }
    }

}
