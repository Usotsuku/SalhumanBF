package com.example.salhuman.security.dto;

import lombok.Data;

import java.util.Date;

@Data
public class heure_travailleDTO {
    private Long heureId;
    private Date date;
    private String type;
    private int nbHeures;
    private Long employeId;


}
