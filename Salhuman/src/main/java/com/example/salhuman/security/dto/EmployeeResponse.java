package com.example.salhuman.security.dto;

import lombok.Data;
import java.util.Date;

@Data
public class EmployeeResponse {
    private Long employeId;
    private String nom;
    private String prenom;
    private String departement;
    private String poste;
    private float salaire;
    private Date date_embauche;
}