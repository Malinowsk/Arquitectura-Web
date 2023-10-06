package com.example.trabajoentregable3.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data  // genera automáticamente los métodos getter, setter, toString(), equals(), y hashCode()
@NoArgsConstructor // genera automáticamente el contructor vacio
public class InscriptionId implements Serializable {

    @Column(name = "id_career")
    private Long idCareer;

    @Column(name = "university_notebook")
    private Long universityNotebook;

    public InscriptionId(Long idCareer,Long universityNotebook){
        this.idCareer=idCareer;
        this.universityNotebook=universityNotebook;
    }

}