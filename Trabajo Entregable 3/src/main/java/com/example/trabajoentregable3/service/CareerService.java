package com.example.trabajoentregable3.service;

import com.example.trabajoentregable3.dto.DTOCareer;
import com.example.trabajoentregable3.entity.Career;
import com.example.trabajoentregable3.entity.Student;
import com.example.trabajoentregable3.repository.CareerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CareerService {

    private final CareerRepository careerRepository;

    public List<DTOCareer> findAll() {
        return this.careerRepository
                .findAll()
                .stream()
                .map(career -> new DTOCareer(career.getName()))
                .toList();
    }

    @Transactional
    public Career save(Career request) {
        return careerRepository.save(new Career(request.getName()));
    }

}
