package com.example.salhuman.models;

import com.example.salhuman.security.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeId;
    private String nom;
    private String prenom;
    private String departement;
    private String poste;
    private float salaire;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_embauche;
    @OneToMany(mappedBy = "employe",fetch = FetchType.EAGER)
    private List<Conge> conges = new ArrayList<>();
    @OneToMany(mappedBy = "employe",fetch = FetchType.EAGER)
    private List<Fiche_Paie> Fiches = new ArrayList<>();
    @OneToMany(mappedBy = "employe",fetch = FetchType.EAGER)
    private List<Heure_Travaille> Heures_Travailles = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
