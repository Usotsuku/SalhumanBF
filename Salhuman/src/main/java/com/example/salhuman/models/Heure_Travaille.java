package com.example.salhuman.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Setter
@Getter
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Heure_Travaille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long HeureId;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Format de date attendu
    private Date date;
    private String type;
    private int nb_heures;
    @ManyToOne
    private Employe employe;
    private String Statut;


}

