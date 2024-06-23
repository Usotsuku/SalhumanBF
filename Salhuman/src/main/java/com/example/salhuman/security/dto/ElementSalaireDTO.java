package com.example.salhuman.security.dto;

import lombok.Data;

@Data
public class ElementSalaireDTO {
    private Long elementId;
    private String type;
    private float montant;
    private Long fichePaieId;
}
