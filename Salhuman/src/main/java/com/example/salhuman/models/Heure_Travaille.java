package com.example.salhuman.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "heures_travaillees")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Heure_Travaille {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heureId; // Nom de champ ajusté pour correspondre aux conventions Java

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String type; // Peut être "travail", "absence", etc.

    private int nb_heures;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employe_id", nullable = false)
    private Employe employe;

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.EN_ATTENTE; // Définir le statut par défaut

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public enum Statut {
        EN_ATTENTE,
        APPROUVE,
        REJETE
    }
}
