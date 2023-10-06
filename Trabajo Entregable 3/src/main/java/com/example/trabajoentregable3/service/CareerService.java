package com.example.trabajoentregable3.service;

import com.example.trabajoentregable3.dto.DTOCareer;
import com.example.trabajoentregable3.dto.DTORequestCareer;
import com.example.trabajoentregable3.entity.Career;
import com.example.trabajoentregable3.repository.CareerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CareerService {

    private final CareerRepository careerRepository;

    public List<DTORequestCareer> findAll() {
        return this.careerRepository
                .findAll()
                .stream()
                .map(career -> new DTORequestCareer((int) career.getId(), career.getName()))
                .toList();
    }


    @Transactional
    public ResponseEntity<DTOCareer> save(DTORequestCareer career) {
        /*if the career does not exist then I persist*/
            Career c = this.careerRepository.save(new Career(career.getId(), career.getName()));
            DTOCareer DTOc = new DTOCareer((int) c.getId(), c.getName());
            return new ResponseEntity<>(DTOc, HttpStatus.CREATED);
    }

/*    @Transactional
    public Career save(Career entity) throws Exception {
        try {
            return careerRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }*/

}
