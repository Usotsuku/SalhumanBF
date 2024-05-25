package com.example.salhuman.security.dto;

import com.example.salhuman.models.Employe;
import com.example.salhuman.security.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class employeReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String nom;
    private String prenom;
    private String departement;
    private String poste;
    private float salaire;
    private Date date_embauche;
    private Employe employe;
    private List<Employe> employeList;
    private List<EmployeeResponse> employeDataList;
}
